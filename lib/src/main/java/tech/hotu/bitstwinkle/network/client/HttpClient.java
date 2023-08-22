/*
 *
 *  *  * Copyright (C) 2023 The Developer bitstwinkle
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *
 */

package tech.hotu.bitstwinkle.network.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.hotu.bitstwinkle.network.IClient;
import tech.hotu.bitstwinkle.network.Network;
import tech.hotu.bitstwinkle.network.Response;
import tech.hotu.bitstwinkle.network.security.Token;
import tech.hotu.bitstwinkle.network.security.TokenData;
import tech.hotu.bitstwinkle.tools.crypto.CryptoHelper;
import tech.hotu.bitstwinkle.tools.unique.Unique;
import tech.hotu.bitstwinkle.types.errors.Err;

interface Http {
  String HEADER_PREFIX = "Twinkle-"; //Uniform prefix
  String HEADER_RUN_MODE = HEADER_PREFIX + "Run-Mode"; //Uniform prefix
  String HEADER_SECRET_PUB = HEADER_PREFIX + "Secret-Pub"; //Secret Pub
  String HEADER_TOKEN_PUB = HEADER_PREFIX + "Token-Pub";  //Token Public
  String HEADER_NONCE = HEADER_PREFIX + "Nonce";      //Nonce
  String HEADER_TIMESTAMP = HEADER_PREFIX + "Timestamp";  //Timestamp
  String HEADER_SIGNATURE = HEADER_PREFIX + "Signature";  //Signature
  String HEADER_TOKEN_EXPIRE = HEADER_PREFIX + "Expiration"; //Token Expiration

  String bodyInside = "__b_o_d_y__";
  String turnBySecretURL = "/security/access/secret";
  String turnByRefreshURL = "/security/access/refresh";
}
public class HttpClient implements IClient {
  private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);

  private OkHttpClient okHttpCli;
  public HttpClient() {
    this.okHttpCli = new Builder().
        connectTimeout(60, TimeUnit.SECONDS).
        addInterceptor(new HttpInterceptor()).
        build();
  }

  @Override
  public <D, R> Response<R> call(String api, D data, Class<R> targetType) {
    logger.info("[ api: {} ]", api);
    ObjectMapper objectMapper = new ObjectMapper();
    try{
      String jsonBody = objectMapper.writeValueAsString(data);

      Request.Builder reqBuilder = new Request.Builder()
          .url(Network.Options().getAccess() + api);
      if(data!=null){
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody reqBody = RequestBody.create(jsonBody, mediaType);
        reqBuilder.post(reqBody);
      }
      Request httpReq = reqBuilder.build();
      okhttp3.Response httpResp = this.okHttpCli.newCall(httpReq).execute();
      if(!httpResp.isSuccessful()){
        Err err;
        try {
          err = objectMapper.readValue(httpResp.body().string(), Err.class);
          logger.info("[ api.resp.err: {} ]", err.toString());
        }catch (Exception respJsonDeE){
          logger.error("objectMapper.readValue(httpResp.body().string()) err", respJsonDeE);
          return Response.Error(Err.System("analyze response data failed"));
        }
        return Response.Error(err);
      }
      byte[] respData = httpResp.body().bytes();
      R resultData = objectMapper.readValue(respData, targetType);
      return Response.Success(resultData);
    }catch (IOException e){
      logger.error("do http.call failed", e);
      return Response.Error(Err.System("analyze data failed"));
    }
  }
}

class HttpInterceptor implements Interceptor {
  private static final Logger logger = LoggerFactory.getLogger(HttpInterceptor.class);
  @NotNull
  @Override
  public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
    Request req = chain.request();
    String urlStr = req.url().url().toString();
    String signPub;
    String signKey;
    if (urlStr.endsWith(Http.turnBySecretURL)){
      signPub = Network.Options().getSecret().getPub();
      signKey = Network.Options().getSecret().getPri();
    }else if (urlStr.endsWith(Http.turnByRefreshURL)){
      signPub = Network.Token().getRefreshTokenPub();
      signKey = Network.Token().getRefreshTokenPri();
    }else {
      Token token = Network.Token();
      if(!token.isAvailable()){
        if(token.isRefreshAvailable()){
          doTurnByRefreshToken();
        }else{
          doTurnBySecret();
        }
      }
      signPub = token.getTokenPub();
      signKey = token.getTokenPri();
    }

    // handle Header
    String nonce = Unique.RandID();
    String timestamp = Long.toString(new Date().getTime());
    Request.Builder reqBuilder = chain.request().newBuilder()
        .addHeader(Http.HEADER_NONCE, nonce)
        .addHeader(Http.HEADER_TIMESTAMP, timestamp)
        .addHeader(Http.HEADER_TOKEN_PUB, signPub);

    LinkedHashMap<String, String> signHeader = new LinkedHashMap<>();
    signHeader.put(Http.HEADER_NONCE, nonce);
    signHeader.put(Http.HEADER_TIMESTAMP, timestamp);

    // handle params
    Map<String, String> params = new HashMap<>();
    Set<String> queryParameters = req.url().queryParameterNames();
    for (String parameter : queryParameters) {
      String value = req.url().queryParameter(parameter);
      params.put(parameter, value);
    }

    RequestBody requestBody = req.body();
    String bodyString = null;
    if (requestBody != null) {
      Buffer buffer = new Buffer();
      requestBody.writeTo(buffer);
      bodyString = buffer.readUtf8();
    }

    String signStr;
    try {
      signStr = this.doSign(signHeader, params, bodyString, signKey);
    } catch (Exception e) {
      throw new IOException(e);
    }

    reqBuilder.addHeader(Http.HEADER_SIGNATURE, signStr);

    return chain.proceed(reqBuilder.build());
  }

  //todo
  private void doTurnByRefreshToken() throws IOException{
    Response<TokenData> resp = Network.Client().call(Http.turnByRefreshURL, null, TokenData.class);
    if(resp.getErr()!=null){
      logger.error("turn by refresh failed: {}", resp.getErr().toString());
      throw new IOException("turn token by refresh failed");
    }
    Network.UpToken(resp.getData());
  }

  //todo
  private void doTurnBySecret() throws IOException {
    Response<TokenData> resp = Network.Client().call(Http.turnBySecretURL, null, TokenData.class);
    if(resp.getErr()!=null){
      logger.error("turn by secret failed: {}", resp.getErr());
      throw new IOException("turn token by secret failed");
    }
    Network.UpToken(resp.getData());
  }

  private String doSign(
      LinkedHashMap<String, String> signHeader
      , Map<String, String> params
      , String body
      , String priKey
      ) throws Exception {
    StringBuilder buf = new StringBuilder();
    if(signHeader!=null && !signHeader.isEmpty()){
      for(Map.Entry<String, String> entry : signHeader.entrySet()) {
        buf.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
      }
    }
    if(params!=null && !params.isEmpty()){
      List<Entry<String, String>> list = new ArrayList<>(params.entrySet());
      Collections.sort(list, Entry.comparingByKey());
      for(Map.Entry<String, String> entry: list){
        buf.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
      }
    }
    if(body!=null && !body.isBlank()){
      buf.append(Http.bodyInside).append("=").append(body);
    }

    logger.info("buf to sign: buf={}; priKey={}", buf, priKey);
    return CryptoHelper.SHA256(buf.toString(), priKey);
  }
}
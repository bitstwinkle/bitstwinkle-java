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

package tech.hotu.bitstwinkle.network.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenData {
    @JsonProperty("refresh_token_pub")
    private String refreshTokenPub;
    @JsonProperty("refresh_token_expire")
    private String refreshTokenExpire;

    @JsonProperty("refresh_token_pri")
    private String refreshTokenPri;

    @JsonProperty("token_pub")
    private String tokenPub;

    @JsonProperty("token_expire")
    private String tokenExpire;

    @JsonProperty("token_pri")
    private String tokenPri;

    public String getRefreshTokenPub() {
      return refreshTokenPub;
    }

    public void setRefreshTokenPub(String refreshTokenPub) {
      this.refreshTokenPub = refreshTokenPub;
    }

    public String getRefreshTokenExpire() {
      return refreshTokenExpire;
    }

    public void setRefreshTokenExpire(String refreshTokenExpire) {
      this.refreshTokenExpire = refreshTokenExpire;
    }

    public String getRefreshTokenPri() {
      return refreshTokenPri;
    }

    public void setRefreshTokenPri(String refreshTokenPri) {
      this.refreshTokenPri = refreshTokenPri;
    }

    public String getTokenPub() {
      return tokenPub;
    }

    public void setTokenPub(String tokenPub) {
      this.tokenPub = tokenPub;
    }

    public String getTokenExpire() {
      return tokenExpire;
    }

    public void setTokenExpire(String tokenExpire) {
      this.tokenExpire = tokenExpire;
    }

    public String getTokenPri() {
      return tokenPri;
    }

    public void setTokenPri(String tokenPri) {
      this.tokenPri = tokenPri;
    }

    public String toString() {
      ObjectMapper objectMapper = new ObjectMapper();
      try {
        return objectMapper.writeValueAsString(this);
      } catch (JsonProcessingException e) {
        return "invalid TokenData";
      }
    }
  }

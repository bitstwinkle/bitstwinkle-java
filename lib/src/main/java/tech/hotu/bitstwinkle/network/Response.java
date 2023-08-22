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

package tech.hotu.bitstwinkle.network;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tech.hotu.bitstwinkle.types.errors.Err;

public class Response<T> {
  private T data;
  private Err err;

  public Response(){}

  public Response(T data, Err err){
    this.data = data;
    this.err = err;
  }

  public static <T> Response<T> Success(T data) {
    return new Response<>(data, null);
  }

  public static <T> Response<T> Error(Err err) {
    return new Response<>(null, err);
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public Err getErr() {
    return err;
  }

  public void setErr(Err err) {
    this.err = err;
  }

  public String toString() {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      String jsonStr = objectMapper.writeValueAsString(this);
      return jsonStr;
    } catch (JsonProcessingException e) {
      return "invalid json";
    }
  }
}

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

package tech.hotu.bitstwinkle.types.errors;

public class Err {
  private int type;
  private String code;
  private String message;

  public static Err System(String message) {
    Err err = new Err();
    err.type = Type.SYSTEM;
    err.code = "system";
    err.message = message;
    return err;
  }

  public static Err Coding(String message) {
    Err err = new Err();
    err.type = Type.CODING;
    err.code = "coding";
    err.message = message;
    return err;
  }

  public static Err Verify(String message) {
    Err err = new Err();
    err.type = Type.APPLICATION;
    err.code = "verify";
    err.message = message;
    return err;
  }

  public String toString() {
    return "[" + this.type + "]"+this.code+":"+this.message;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

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

package tech.hotu.bitstwinkle.types.ref;

public class Lead {
  private Collar owner;
  private String code;

  public Lead putOwner(Collar owner){
    this.owner = owner;
    return this;
  }

  public Lead putCode(String code) {
    this.code = code;
    return this;
  }

  public Collar getOwner() {
    return owner;
  }

  public void setOwner(Collar owner) {
    this.owner = owner;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}

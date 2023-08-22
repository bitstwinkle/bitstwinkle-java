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

import tech.hotu.bitstwinkle.types.errors.Err;

public class Secret {
  private String pub;
  private String pri;

  public Err verify() {
    if(this.pub==null || this.pub.isBlank()){
      return Err.Coding("require pub");
    }
    if(this.pri==null || this.pri.isBlank()){
      return Err.Coding("require pri");
    }
    return null;
  }

  public String getPub() {
    return pub;
  }

  public Secret setPub(String pub) {
    this.pub = pub;
    return this;
  }

  public String getPri() {
    return pri;
  }

  public Secret setPri(String pri) {
    this.pri = pri;
    return this;
  }
}

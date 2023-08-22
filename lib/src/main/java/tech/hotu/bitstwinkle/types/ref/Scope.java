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

import tech.hotu.bitstwinkle.types.errors.Err;

public class Scope {
  private String vn;
  private String jd;
  private String code;

  public Err verify() {
    if(this.vn==null || this.vn.isBlank()){
      return Err.Coding("require vn");
    }
    if(this.jd==null || this.jd.isBlank()){
      return Err.Coding("require jd");
    }
    if(this.code==null || this.code.isBlank()){
      return Err.Coding("require code");
    }
    return null;
  }

  public String getVn() {
    return vn;
  }

  public Scope setVn(String vn) {
    this.vn = vn;
    return this;
  }

  public String getJd() {
    return jd;
  }

  public Scope setJd(String jd) {
    this.jd = jd;
    return this;
  }

  public String getCode() {
    return code;
  }

  public Scope setCode(String code) {
    this.code = code;
    return this;
  }
}

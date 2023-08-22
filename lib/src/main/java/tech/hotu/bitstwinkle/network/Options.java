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
import tech.hotu.bitstwinkle.types.ref.Scope;

public class Options {

  private String access;
  private Scope scope;
  private Secret secret;

  public Err verify() {
    if (this.access == null || this.access.isBlank()) {
      return Err.Coding("require access");
    }
    if (this.scope == null) {
      return Err.Coding("require scope");
    }
    Err err = this.scope.verify();
    if (err != null) {
      return err;
    }
    if(this.secret==null){
      return Err.Coding("require secret");
    }
    err = this.secret.verify();
    if (err != null) {
      return err;
    }
    return null;
  }

  public void clone(Options src) {
    this.access = src.access;
    this.scope = src.scope;
    this.secret = src.secret;
  }

  public String getAccess() {
    return access;
  }

  public Options setAccess(String access) {
    this.access = access;
    return this;
  }

  public Scope getScope() {
    return scope;
  }

  public Options setScope(Scope scope) {
    this.scope = scope;
    return this;
  }

  public Secret getSecret() {
    return secret;
  }

  public Options setSecret(Secret secret) {
    this.secret = secret;
    return this;
  }
}

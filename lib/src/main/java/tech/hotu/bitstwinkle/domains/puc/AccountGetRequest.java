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

package tech.hotu.bitstwinkle.domains.puc;

import com.fasterxml.jackson.annotation.JsonProperty;
import tech.hotu.bitstwinkle.types.ref.Scope;

public class AccountGetRequest {
  private Scope scope;
  @JsonProperty("account_unique")
  private Unique accountUnique;

  public class With {
    @JsonProperty("private_key")
    private boolean privateKey;
    @JsonProperty("public_key")
    private boolean publicKey;
    private boolean mnemonic;

    public boolean isPrivateKey() {
      return privateKey;
    }

    public void setPrivateKey(boolean privateKey) {
      this.privateKey = privateKey;
    }

    public boolean isPublicKey() {
      return publicKey;
    }

    public void setPublicKey(boolean publicKey) {
      this.publicKey = publicKey;
    }

    public boolean isMnemonic() {
      return mnemonic;
    }

    public void setMnemonic(boolean mnemonic) {
      this.mnemonic = mnemonic;
    }
  }
  private With with;

  public Scope getScope() {
    return scope;
  }

  public void setScope(Scope scope) {
    this.scope = scope;
  }

  public Unique getAccountUnique() {
    return accountUnique;
  }

  public void setAccountUnique(Unique accountUnique) {
    this.accountUnique = accountUnique;
  }

  public With getWith() {
    if(this.with == null) {
      this.with = new With();
    }
    return with;
  }

  public void setWith(With with) {
    this.with = with;
  }
}

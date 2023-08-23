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
import java.util.Map;
import tech.hotu.bitstwinkle.types.ref.Scope;

public class WalletGetRequest {
  private Scope scope;
  @JsonProperty("wallet_unique")
  private Unique walletUnique;
  private Map<String, Boolean> with;

  public Scope getScope() {
    return scope;
  }

  public void setScope(Scope scope) {
    this.scope = scope;
  }

  public Unique getWalletUnique() {
    return walletUnique;
  }

  public void setWalletUnique(Unique walletUnique) {
    this.walletUnique = walletUnique;
  }

  public Map<String, Boolean> getWith() {
    return with;
  }

  public void setWith(Map<String, Boolean> with) {
    this.with = with;
  }
}

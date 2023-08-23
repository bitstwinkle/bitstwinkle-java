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
import java.util.Date;
import tech.hotu.bitstwinkle.types.ref.Lead;

public class Wallet {
  private String addr;
  private Lead lead;

  @JsonProperty("birth_at")
  private Date birthAt;

  @JsonProperty("acc_array")
  private Account[] accArray;

  public String getAddr() {
    return addr;
  }

  public void setAddr(String addr) {
    this.addr = addr;
  }

  public Lead getLead() {
    return lead;
  }

  public void setLead(Lead lead) {
    this.lead = lead;
  }

  public Date getBirthAt() {
    return birthAt;
  }

  public void setBirthAt(Date birthAt) {
    this.birthAt = birthAt;
  }

  public Account[] getAccArray() {
    return accArray;
  }

  public void setAccArray(Account[] accArray) {
    this.accArray = accArray;
  }
}

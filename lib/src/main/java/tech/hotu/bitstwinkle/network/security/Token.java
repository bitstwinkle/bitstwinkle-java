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

import java.util.Date;
import tech.hotu.bitstwinkle.tools.times.TimeHelper;

public class Token {
  private String refreshTokenPub;
  private Date refreshTokenExpire;
  private String refreshTokenPri;
  private String tokenPub;
  private Date tokenExpire;
  private String tokenPri;

  public Token() {
    this.refreshTokenPub = "";
    this.refreshTokenExpire = new Date(0L);
    this.refreshTokenPri = "";
    this.tokenPub = "";
    this.tokenExpire = new Date(0L);
    this.tokenPri = "";
  }

  public void from(TokenData src) {
    this.refreshTokenPub = src.getRefreshTokenPub();
    this.refreshTokenExpire = TimeHelper.OfISO(src.getRefreshTokenExpire());
    this.refreshTokenPri = src.getRefreshTokenPri();
    this.tokenPub = src.getTokenPub();
    this.tokenPri = src.getTokenPri();
    this.tokenExpire = TimeHelper.OfISO(src.getTokenExpire());
  }

  public boolean isAvailable() {
    if(this.tokenPub==null || this.tokenPub.isBlank()) {
      return false;
    }
    if(this.tokenPri==null || this.tokenPri.isBlank()) {
      return false;
    }
    if(TimeHelper.IsBefore(this.tokenExpire, new Date())) {
      return false;
    }
    return true;
  }

  public boolean isRefreshAvailable() {
    if(this.refreshTokenPub==null || this.refreshTokenPub.isBlank()) {
      return false;
    }
    if(this.refreshTokenPri==null || this.refreshTokenPri.isBlank()) {
      return false;
    }
    if(TimeHelper.IsBefore(this.refreshTokenExpire, new Date())) {
      return false;
    }
    return true;
  }

  public String getRefreshTokenPub() {
    return refreshTokenPub;
  }

  public void setRefreshTokenPub(String refreshTokenPub) {
    this.refreshTokenPub = refreshTokenPub;
  }

  public Date getRefreshTokenExpire() {
    return refreshTokenExpire;
  }

  public void setRefreshTokenExpire(Date refreshTokenExpire) {
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

  public Date getTokenExpire() {
    return tokenExpire;
  }

  public void setTokenExpire(Date tokenExpire) {
    this.tokenExpire = tokenExpire;
  }

  public String getTokenPri() {
    return tokenPri;
  }

  public void setTokenPri(String tokenPri) {
    this.tokenPri = tokenPri;
  }
}

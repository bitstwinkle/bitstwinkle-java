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

package tech.hotu.bitstwinkle.tools.sys;

public enum Mode {
  LOCAL("local"),
  DEV("dev"),
  TEST("test"),
  PRE("pre"),
  PROD("prod");

  private String code;

  Mode(String code) {
    this.code = code;
  }

  public boolean isRd() {
    return this == Mode.LOCAL ||
        this == Mode.DEV ||
        this == Mode.TEST ||
        this == Mode.PRE;
  }

  public static Mode Of(String code) {
    for (Mode m : Mode.values()) {
      if (m.code == code) {
        return m;
      }
    }
    return LOCAL;
  }
}

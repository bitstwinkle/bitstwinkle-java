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

package tech.hotu.bitstwinkle.tools.times;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class TimeHelper {

  public static boolean IsBefore(Date src, Date dest) {
    int result = src.compareTo(dest);
    return result < 0;
  }

  public static boolean IsAfter(Date src, Date dest) {
    int result = src.compareTo(dest);
    return result > 0;
  }

  public static Date OfISO(String isoStr) {
    LocalDate localDate = LocalDate.parse(isoStr, DateTimeFormatter.ISO_DATE);
    return java.sql.Date.valueOf(localDate);
  }

  public static Date OfUnix(String unixStr) {
    if (unixStr == null || unixStr.isBlank()) {
      return new Date(0);
    }
    long unix = Long.parseLong(unixStr);
    return new Date(unix);
  }
}

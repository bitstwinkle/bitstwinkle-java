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

package tech.hotu.bitstwinke.demo;

import java.util.Date;
import tech.hotu.bitstwinkle.network.Network;
import tech.hotu.bitstwinkle.network.Options;
import tech.hotu.bitstwinkle.network.Response;
import tech.hotu.bitstwinkle.network.Secret;
import tech.hotu.bitstwinkle.network.client.HttpClient;
import tech.hotu.bitstwinkle.network.storage.KVStorage;
import tech.hotu.bitstwinkle.tools.crypto.CryptoHelper;
import tech.hotu.bitstwinkle.tools.times.TimeHelper;
import tech.hotu.bitstwinkle.types.ref.Scope;

public class Demo {
  public static void main(String []args) throws Exception {

    System.out.println(TimeHelper.IsBefore(new Date(0L), new Date()));

    String buf = "Twinkle-Nonce=33789767353e4c2e9c2dbbfb54fd2712;Twinkle-Timestamp=1692708815803;";
    String priKey = "0x2ae9d38c9adf4967488286b111ed3b511b57111b15e7eedca76e8caea228f99b";

    String signStr = CryptoHelper.SHA256(buf.toString(), priKey);
    System.out.println("signStr:" + signStr);

    Network.Init(
        new Options()
            .setAccess("http://localhost:80")
            .setScope(
                new Scope()
                    .setVn("steppe")
                    .setJd("steppe_jd")
                    .setCode("abc")
            )
            .setSecret(
                new Secret()
                    .setPub("0xd8460d6F1AA7f71458e939063119838dc2c70f99")
                    .setPri("0x2ae9d38c9adf4967488286b111ed3b511b57111b15e7eedca76e8caea228f99b")
            )
    );
    Network.Use(new KVStorage(), new HttpClient());
    Response<String> resp = Network.Client().call("/ping", null, String.class);
    System.out.println(resp);
  }
}
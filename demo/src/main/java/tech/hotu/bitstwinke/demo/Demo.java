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

import java.util.Map;
import tech.hotu.bitstwinkle.domains.puc.PucService;
import tech.hotu.bitstwinkle.domains.puc.WalletPreCreateRequest;
import tech.hotu.bitstwinkle.domains.puc.WalletPreCreateResponse;
import tech.hotu.bitstwinkle.domains.puc.impl.HttpPucService;
import tech.hotu.bitstwinkle.network.Network;
import tech.hotu.bitstwinkle.network.Options;
import tech.hotu.bitstwinkle.types.io.Pack;
import tech.hotu.bitstwinkle.types.io.Response;
import tech.hotu.bitstwinkle.network.Secret;
import tech.hotu.bitstwinkle.network.client.HttpClient;
import tech.hotu.bitstwinkle.network.storage.KVStorage;
import tech.hotu.bitstwinkle.tools.crypto.CryptoHelper;
import tech.hotu.bitstwinkle.types.ref.Collar;
import tech.hotu.bitstwinkle.types.ref.Lead;
import tech.hotu.bitstwinkle.types.ref.Scope;

public class Demo {
  public static void main(String []args) throws Exception {

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
    PucService puc = new HttpPucService();
    WalletPreCreateRequest req = new WalletPreCreateRequest();
    req.setLead(new Lead()
        .putOwner(new Collar()
            .putCode("user")
            .putId("user_id")
        )
        .putCode("lead_code"));
    Pack<WalletPreCreateResponse> pack = puc.walletPreCreate(req);
    if(pack.isSuccess()){
      System.out.println(pack.getData());
    }else{
      System.out.println(pack.getErr());
    }
  }
}
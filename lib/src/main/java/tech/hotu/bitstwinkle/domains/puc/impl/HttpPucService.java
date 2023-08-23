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

package tech.hotu.bitstwinkle.domains.puc.impl;

import tech.hotu.bitstwinkle.domains.puc.AccLoadTransferRequest;
import tech.hotu.bitstwinkle.domains.puc.Account;
import tech.hotu.bitstwinkle.domains.puc.AirdropRequest;
import tech.hotu.bitstwinkle.domains.puc.PucService;
import tech.hotu.bitstwinkle.domains.puc.Transfer;
import tech.hotu.bitstwinkle.domains.puc.Wallet;
import tech.hotu.bitstwinkle.domains.puc.WalletAddAccRequest;
import tech.hotu.bitstwinkle.domains.puc.WalletCreateConfirmRequest;
import tech.hotu.bitstwinkle.domains.puc.WalletGetRequest;
import tech.hotu.bitstwinkle.domains.puc.WalletLoadAccRequest;
import tech.hotu.bitstwinkle.domains.puc.WalletMnemonicCheckRequest;
import tech.hotu.bitstwinkle.domains.puc.WalletMnemonicCheckResponse;
import tech.hotu.bitstwinkle.domains.puc.WalletPreCreateRequest;
import tech.hotu.bitstwinkle.domains.puc.WalletPreCreateResponse;
import tech.hotu.bitstwinkle.network.Network;
import tech.hotu.bitstwinkle.types.io.Pack;
import tech.hotu.bitstwinkle.types.io.PagePack;
import tech.hotu.bitstwinkle.types.io.Response;
import tech.hotu.bitstwinkle.types.load.Pagination;

public class HttpPucService implements PucService {
  public static String WALLET_PRE_CREATE = "/puc/wallet/create/pre";
  public static String WALLET_MNEMONIC_CHECK = "/puc/wallet/mon/check";
  public static String WALLET_CREATE_CONFIRM = "/puc/wallet/create/confirm";
  public static String WALLET_ADD_ACCOUNT = "/puc/wallet/acc/add";
  public static String WALLET_GET = "/puc/wallet/g";
  public static String WALLET_LOAD_ACCOUNT = "/puc/acc/trans/q";
  public static String ACC_LOAD_TRANSFER = "/puc/acc/trans/q";
  public static String AIRDROP = "/puc/acc/airdrop";

  @Override
  public Pack<WalletPreCreateResponse> walletPreCreate(WalletPreCreateRequest req) {
    Response<WalletPreCreateResponse> resp = Network.Client().call(WALLET_PRE_CREATE, req,
        WalletPreCreateResponse.class);
    return new Pack<>(resp);
  }

  @Override
  public Pack<WalletMnemonicCheckResponse> walletMnemonicCheck(WalletMnemonicCheckRequest req) {
    Response<WalletMnemonicCheckResponse> resp = Network.Client().call(WALLET_MNEMONIC_CHECK, req,
        WalletMnemonicCheckResponse.class);
    return new Pack<>(resp);
  }

  @Override
  public Pack<Wallet> walletCreateConfirm(WalletCreateConfirmRequest req) {
    Response<Wallet> resp = Network.Client().call(WALLET_CREATE_CONFIRM, req,
        Wallet.class);
    return new Pack<>(resp);
  }

  @Override
  public Pack<Account> walletAddAccount(WalletAddAccRequest req) {
    Response<Account> resp = Network.Client().call(WALLET_ADD_ACCOUNT, req,
        Account.class);
    return new Pack<>(resp);
  }

  @Override
  public Pack<Wallet> walletGet(WalletGetRequest req) {
    Response<Wallet> resp = Network.Client().call(WALLET_GET, req,
        Wallet.class);
    return new Pack<>(resp);
  }

  @Override
  public Pack<Account[]> walletLoadAccount(WalletLoadAccRequest req) {
    Response<Account[]> resp = Network.Client().call(WALLET_LOAD_ACCOUNT, req,
        Account[].class);
    return new Pack<>(resp);
  }

  @Override
  public PagePack<Transfer[]> accLoadTransfer(AccLoadTransferRequest req) {
    Response<Pagination> resp = Network.Client().call(ACC_LOAD_TRANSFER, req, Pagination.class);
    if(resp.getErr()!=null){
      return new PagePack<>(null, null, resp.getErr());
    }
    return new PagePack<>((Transfer[])resp.getData().getItems(), resp.getData().getPaging(), null);
  }

  @Override
  public Pack<String> airdrop(AirdropRequest req) {
    Response<String> resp = Network.Client().call(AIRDROP, req, String.class);
    return new Pack<>(resp);
  }
}

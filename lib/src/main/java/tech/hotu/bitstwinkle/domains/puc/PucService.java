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

import tech.hotu.bitstwinkle.types.errors.Err;
import tech.hotu.bitstwinkle.types.io.Pack;
import tech.hotu.bitstwinkle.types.io.PagePack;
import tech.hotu.bitstwinkle.types.load.Pagination;

public interface PucService {

  Pack<WalletPreCreateResponse> walletPreCreate(WalletPreCreateRequest req);


  Pack<WalletMnemonicCheckResponse> walletMnemonicCheck(WalletMnemonicCheckRequest req);


  Pack<Wallet> walletCreateConfirm(WalletCreateConfirmRequest req);


  Pack<Account> walletAddAccount(WalletAddAccRequest req);


  Pack<Wallet> walletGet(WalletGetRequest req);

  Pack<Account[]> walletLoadAccount(WalletLoadAccRequest req);

  PagePack<Transfer[]> accLoadTransfer(AccLoadTransferRequest req);

}

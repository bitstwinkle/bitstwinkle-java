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

package tech.hotu.bitstwinkle.network;

import java.util.logging.Logger;
import tech.hotu.bitstwinkle.network.security.TokenData;
import tech.hotu.bitstwinkle.types.errors.Err;
import tech.hotu.bitstwinkle.network.security.Token;

public final class Network {
  private static final Logger logger = Logger.getLogger(Network.class.getName());
  private static final Options gOptions = new Options();
  private static IStorage gStorage;
  private static IClient gClient;

  private static final Token gToken = new Token();

  public static void Init(Options options) {
    if(options==null){
      logger.severe("Network.Init require options");
      System.exit(1);
    }
    Err err = options.verify();
    if(err!=null){
      logger.severe("Network.Init options invalid: " + err.getMessage());
      System.exit(1);
    }
    gOptions.clone(options);
  }

  public static void Use(IStorage storage, IClient client) {
    if(storage==null){
      logger.severe("Network.Init failed: require storage" );
      System.exit(1);
    }
    if(client==null){
      logger.severe("Network.Init failed: require client" );
      System.exit(1);
    }
    gStorage = storage;
    gClient = client;
  }

  public static Options Options(){
    return gOptions;
  }

  public static IStorage Storage() {
    return gStorage;
  }

  public static IClient Client() {
    return gClient;
  }

  public static Token Token() {
    return gToken;
  }

  public static void UpToken(Token src) {
    gToken.clone(src);
  }
}

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

package tech.hotu.bitstwinkle.network.storage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import tech.hotu.bitstwinkle.network.IStorage;

public class KVStorage implements IStorage {

  private Map<String, Object> db = new ConcurrentHashMap<>();

  @Override
  public <V> V get(String key, Class<V> targetType) {
    Object obj = this.db.get(key);
    if(obj==null){
      return null;
    }
    if(targetType.isInstance(obj)){
      return targetType.cast(obj);
    }
    return null;
  }

  @Override
  public <V> void set(String key, V data) {
    this.db.put(key, data);
  }

  @Override
  public void remove(String key) {
    this.db.remove(key);
  }

  @Override
  public void clean() {
    this.db.clear();
  }

  @Override
  public <V> void iter(IterCallback callback) {
    for(Map.Entry<String, Object> entry : this.db.entrySet()) {
      callback.callback(entry.getKey(), entry.getValue());
    }
  }
}

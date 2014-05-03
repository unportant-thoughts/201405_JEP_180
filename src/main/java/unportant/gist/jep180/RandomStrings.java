/*
 * Copyright 2014 Clément MATHIEU
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package unportant.gist.jep180;

import com.google.common.collect.ImmutableList;
import com.google.common.io.BaseEncoding;

import java.util.Random;

class RandomStrings implements Collider {

  Random rand = new Random();

  @Override
  public ImmutableList<String> generate(int count) {
    return generateRandomStrings(count, 15);
  }

  public ImmutableList<String> generate(int count, int size) {
    return generateRandomStrings(count, size);
  }

  private ImmutableList<String> generateRandomStrings(int desiredStrings, int stringSize) {
    String[] strings = new String[desiredStrings];

    for (int i = 0; i < desiredStrings; i++) {
      strings[i] = randomString(stringSize, rand);
    }

    return ImmutableList.copyOf(strings);
  }

  private String randomString(int size, Random rand) {
    final byte[] buffer = new byte[size];
    rand.nextBytes(buffer);
    return BaseEncoding.base64Url().omitPadding().encode(buffer);
 }
}

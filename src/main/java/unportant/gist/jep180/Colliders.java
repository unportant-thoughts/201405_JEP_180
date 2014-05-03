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

class Colliders {

  public static final String MURMUR3 = "Murmur3";
  public static final String DJBX31A = "DJBX31A";
  public static final String RANDOM = "Random";

  static Collider get(String name) {
    if (name.equals(MURMUR3)) {
      return new Murmur3Collider();
    } else if (name.equals(DJBX31A)) {
      return new DJBX31ACollider();
    } else if (name.equals(RANDOM)) {
      return new RandomStrings();
    } else {
      throw new IllegalStateException("Unknown collider: " + name);
    }
  }
}

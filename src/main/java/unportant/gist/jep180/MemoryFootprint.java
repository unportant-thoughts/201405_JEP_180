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
import org.openjdk.jol.info.GraphLayout;

import java.util.HashMap;

public class MemoryFootprint {

  public static void main(String[] args) {

    for (int count : ImmutableList.of(10, 100, 1000, 10000, 100000, 1000000)) {
      Collider djbCollider = Colliders.get(Colliders.DJBX31A);
      ImmutableList<String> strings = djbCollider.generate(count);

      {
        HashMap<String, Object> map = new HashMap<String, Object>();
        for (String s : strings) {
          map.put(s, s);
        }

        System.out.println(count + " djb " + GraphLayout.parseInstance(map).totalSize());
      }

      {
        RandomStrings randomCollider = new RandomStrings();
        strings = randomCollider.generate(count, strings.get(0).length());

        HashMap<String, Object> map = new HashMap<String, Object>();
        for (String s : strings) {
          map.put(s, s);
        }

        System.out.println(count + " rnd " + GraphLayout.parseInstance(map).totalSize());
      }
    }
  }
}

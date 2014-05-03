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

import java.util.ArrayList;
import java.util.List;

class DJBX31ACollider implements Collider {

  private static final ImmutableList<String> ROOTS = ImmutableList.of("Aa", "BB");

  @Override
  public final ImmutableList<String> generate(int desiredCollisions) {
    if (desiredCollisions == 0) {
      return ImmutableList.of();
    }

    return generate0(desiredCollisions, ROOTS);
  }

  private ImmutableList<String> generate0(int desiredCollisions, List<String> roots) {
    List<String> collisions = new ArrayList<String>(roots.size() << 1);
    for (String s : roots) {
      for (String t : roots) {
        collisions.add(s + t);

        if (collisions.size() == desiredCollisions) {
          return ImmutableList.copyOf(collisions);
        }
      }
    }

    return generate0(desiredCollisions, collisions);
  }
}
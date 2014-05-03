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

import org.fest.assertions.api.Assertions;
import org.fest.reflect.core.Reflection;
import org.fest.reflect.exception.ReflectionError;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

@RunWith(value = Parameterized.class)
public class Murmur3ColliderTest {

  private int count;

  public Murmur3ColliderTest(int count) {
    this.count = count;
  }

  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    Object[][] data = new Object[][] { { 10 }, { 100 }, { 1000 }, { 15000 }, {25000}, {30000} };
    return Arrays.asList(data);
  }

  @Before
  public void before() {
    ReflectionError reflectionError = null;
    try {
      Reflection.method("hash32").withReturnType(int.class).in(String.class);
    } catch (ReflectionError e) {
      reflectionError = e;
    }
    Assume.assumeNoException(reflectionError);
  }

  @Test
  public void test() {
    List<String> strings = new Murmur3Collider().generate(count);
    Assertions.assertThat(strings).hasSize(count);

    Set<Integer> hashes = new HashSet<Integer>(count);

    for (String str: strings) {
      hashes.add(
              Reflection.method("hash32")
                      .withReturnType(int.class)
                      .in(str)
                      .invoke()
      );
    }

    Assertions.assertThat(hashes).hasSize(1);
  }
}

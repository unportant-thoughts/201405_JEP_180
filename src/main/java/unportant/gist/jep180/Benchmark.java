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
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.parameters.TimeValue;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class Benchmark {

  @Param({"10", "100", "1000", "2500", "5000", "10000", "15000", "20000", "25000", "30000"})
  public int count;

  @Param({Colliders.DJBX31A, Colliders.MURMUR3, Colliders.RANDOM})
  public String colliderName;

  ImmutableList<String> strings;

  @Setup
  public void setup() {
    Collider collider = Colliders.get(colliderName);
    strings = collider.generate(count);
  }

  @GenerateMicroBenchmark
  public int put() {
    HashMap<String, Object> map = new HashMap<String, Object>();

    for (String s: strings) {
      map.put(s, s);
    }

    return map.size();
  }

  public static void main(String[] args) throws RunnerException {

    Options opt = new OptionsBuilder()
            .include(".*")
            .jvmArgs("-Djmh.stack.period=1", "-Djdk.map.althashing.threshold=-1", "-Xmx1G")
            .forks(2)
            .shouldDoGC(true)
            .warmupIterations(2)
            .warmupTime(TimeValue.seconds(1))
            .measurementIterations(3)
            .measurementTime(TimeValue.seconds(2))
            .timeUnit(TimeUnit.MILLISECONDS)
            .mode(Mode.SampleTime)
            .build();

    new Runner(opt).run();
  }
}

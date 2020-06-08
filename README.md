# tlight.spin

ASCII spinner library for long running terminal operations.

## Usage

```clj
(ns my-app
  (:require [tlight.spin :refer (spin done)]))
  
(defn work[] (Thread/sleep 3000))

(spin)
(work)
(done)
```

```clj
(spin :style :box1 )
(spin :style :box1 :ms 500)
```

[deps.edn](https://clojure.org/reference/deps_and_cli) dependency information:

```clj
  org.tlight/spin {:mvn/version "0.0.1"}
  ```

[Leiningen](https://github.com/technomancy/leiningen) dependency information:

```clj
 [org.tlight/spin "0.0.1"]
```

[Maven](http://maven.apache.org/) dependency information:

```xml
<dependency>
  <groupId>org.tlight</groupId>
  <artifactId>spin</artifactId>
  <version>0.0.1</version>
</dependency>
```

## License

Copyright © 2020 tlight

Distributed under the Eclipse Public License either version 1.0 or any later version.

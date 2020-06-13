# spin

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
(spin :type :box1)
(spin :type :box1 :ms 500)
```

[deps.edn](https://clojure.org/reference/deps_and_cli) dependency information:

```clj
  tlight/spin {:mvn/version "0.0.4"}
  ```

[Leiningen](https://github.com/technomancy/leiningen) dependency information:

```clj
 [tlight/spin "0.0.4"]
```

[Maven](http://maven.apache.org/) dependency information:

```xml
<dependency>
  <groupId>tlight</groupId>
  <artifactId>spin</artifactId>
  <version>0.0.4</version>
</dependency>
```

## License

Copyright © 2020 tlight

Distributed under the Eclipse Public License either version 1.0 or any later version.

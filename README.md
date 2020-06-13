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

; :type  style - defaults to :box1 | Options :box1 to :box7, :spin1 to :spin9
; :ms    delay - defaults to 100 ms
(spin :type :box1 :ms 100) 
```

## Example

![](https://raw.githubusercontent.com/tlight/spin/master/spin.gif)

## Installation

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

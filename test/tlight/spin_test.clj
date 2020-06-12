(ns tlight.spin-test
  (:require [clojure.test :refer :all]
            [tlight.spin :as spin]))

(defn spin-and-sleep [spinner]
  (spin/spin :type spinner :ms 100)
  (printf " %-6s " (str spinner))
  (Thread/sleep 1000)
  (spin/done)
  (println " OK"))

(deftest spin-types
  (testing "spin types"
    (println)
    (run! spin-and-sleep (-> spin/spinners keys sort))
    (is true)))

(deftest spin-fast
  (testing "spin fast"
    (println)
    (doseq [i (range 1 101)]
      (printf " %-3s " i)
      (spin/spin)
      (Thread/sleep 10)
      (spin/done)
      (println " OK"))
    (is true)))

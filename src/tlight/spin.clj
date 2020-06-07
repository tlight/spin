(ns tlight.spin
  (:require [clojure.core.async :as async :refer
             [<! >! >!! timeout chan alt! go go-loop]]))

(def strings {:box1  "⠋⠙⠹⠸⠼⠴⠦⠧⠇⠏"
              :box2  "⠋⠙⠚⠞⠖⠦⠴⠲⠳⠓"
              :box3  "⠄⠆⠇⠋⠙⠸⠰⠠⠰⠸⠙⠋⠇⠆"
              :box4  "⠋⠙⠚⠒⠂⠂⠒⠲⠴⠦⠖⠒⠐⠐⠒⠓⠋"
              :box5  "⠁⠉⠙⠚⠒⠂⠂⠒⠲⠴⠤⠄⠄⠤⠴⠲⠒⠂⠂⠒⠚⠙⠉⠁"
              :box6  "⠈⠉⠋⠓⠒⠐⠐⠒⠖⠦⠤⠠⠠⠤⠦⠖⠒⠐⠐⠒⠓⠋⠉⠈"
              :box7  "⠁⠁⠉⠙⠚⠒⠂⠂⠒⠲⠴⠤⠄⠄⠤⠠⠠⠤⠦⠖⠒⠐⠐⠒⠓⠋⠉⠈⠈"
              :spin1 "|/-\\"
              :spin2 "◴◷◶◵"
              :spin3 "◰◳◲◱"
              :spin4 "◐◓◑◒"
              :spin5 "▉▊▋▌▍▎▏▎▍▌▋▊▉"
              :spin6 "▌▄▐▀"
              :spin7 "╫╪"
              :spin8 "■□▪▫"
              :spin9 "←↑→↓"})

(def done? (atom (chan)))

(defn spin [& {:keys [type template ms]
               :or   {type :box1 template "\u001b[1000D %s" ms 200}}]
  "The spin function loops a spinner to STDOUT until a call to (done)"
  (let [s      (get strings type)
        length (count s)]
    (go-loop [i 0]
      (let [c   (get s i)
            i++ (mod (+ i 1) length)]
        (printf template c)
        (alt!
          @done? :done
          (timeout ms) (recur i++))))))

(defn done []
  (>!! @done? true)) ;; done function


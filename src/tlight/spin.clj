(ns tlight.spin
  (:require [clojure.core.async :as async :refer [<! >! >!! timeout chan alts! go go-loop]]))

(def spinners {:box1  "⠋⠙⠹⠸⠼⠴⠦⠧⠇⠏"
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
(defn done [] (>!! @done? true))
(defn ok []
  (locking *out*
    (print "✓")
    (flush)))

(defn blit [frame]
  (locking *out*
    (print frame)
    (flush)
    (print "\033[1D")))

(defn spin [& {:keys [type ms]
               :or   {type :box1, ms 100}}]
  "The spin function loops a spinner to STDOUT until a call to (done)"
  (let [frames (get spinners type)
        length (count frames) ]
    (go-loop [i 0]
      (let [frame   (get frames i)
            i++     (mod (+ i 1) length)
            [val _] (alts! [@done? (timeout ms)])]
        (if val
          (ok)
          (do
            (blit frame)
            (recur i++)))))))

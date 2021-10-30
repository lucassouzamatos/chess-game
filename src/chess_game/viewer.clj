(ns chess-game.viewer
  (:require [clojure.string :as str]))

(def piece
  {:p "♙"
   :r "♖"
   :n "♘"
   :b "♗"
   :q "♕"
   :k "♔"
   :P "♟"
   :R "♜"
   :N "♞"
   :B "♝"
   :Q "♛"
   :K "♚"
   :x " "})

(defn run
  [board]
  (doseq [row board]
    (print "\n")
    (doseq [col row]
      (print (str ((keyword col) piece) " "))))
  (print "\n"))
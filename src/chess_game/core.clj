(ns chess-game.core
  (:require [chess-game.board :as board])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [board (board/make-board)]
    (board/move-piece board {:x 1 :y 1} {:x 0 :y 2})))

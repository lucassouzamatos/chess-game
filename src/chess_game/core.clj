(ns chess-game.core
  (:require [chess-game.board :as board])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [board (board/make-board)]
    (-> board
     (board/move-piece ,,, {:x 1 :y 1} {:x 1 :y 2})
     (board/move-piece ,,, {:x 1 :y 2} {:x 1 :y 3}))))

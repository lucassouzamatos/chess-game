(ns chess-game.core
  (:require 
    [chess-game.board :as board]
    [chess-game.move :as move])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [board (board/make-board)]
    (-> board
      (move/move-piece {:x 1 :y 1} {:x 1 :y 2})
      (move/move-piece {:x 1 :y 2} {:x 1 :y 3}))))

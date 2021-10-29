(ns chess-game.core
  (:require [chess-game.board :as board])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (board/make-board)))

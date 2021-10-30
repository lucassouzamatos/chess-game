(ns chess-game.move
  (:require [chess-game.validate :as validate]))

(defn- get-piece
  [board current]
  (-> board
    (get ,,, (-> :x current))
    (get ,,, (-> :y current))))

(defn- realloc-piece
  [board current target piece]
  (->
    (update-in board [(:y current)]
      (fn [value]
        (update-in value [(:x current)]
          (fn [value] "x"))))
    (update-in ,,, [(:y target)]
      (fn [value]
        (update-in value [(:x target)]
          (fn [value] piece))))))

(defn move-piece
  [board current target]
  (let [piece (get-piece board current)]
    (if (validate/can-move piece current target)
      (do
        (println "is a valid movement")
        (realloc-piece board current target piece))
      (do
        (println "not is a valid movement") board))))
    
(ns chess-game.board
  (:require 
    [clojure.string :as str]
    [chess-game.validate :as validate]))

(def default-fen "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR")

(defn- parse-int [v]
  (try
    (Integer/parseInt (re-find #"^\d+" (.toString v)))
    (catch NumberFormatException e 0)))

(defn- reduce-empty-values
  [prev curr]
  (conj prev "x"))

(defn- update-in-array
  [curr value]
  (let [range-empty (parse-int curr)]
    (if (not= 0 range-empty)
      (reduce reduce-empty-values value (range range-empty))
      (conj value curr))))

(defn- make-array!
  [prev curr]
  (let [last (- (count prev) 1)]
    (if (= "/" curr)
      (conj prev [])
      (update-in prev [last] (fn [value] (update-in-array curr value))))))

(defn- board-by-fen
  []
  (let [fen (str/split default-fen #"")]
    (reduce make-array! [[]] fen)))

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
      (do (println (realloc-piece board current target piece)) true)
      (do (println "not is a valid movement") false))))

(defn make-board
  []
  (board-by-fen))
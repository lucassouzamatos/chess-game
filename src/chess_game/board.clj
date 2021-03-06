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

(defn make-board
  []
  (board-by-fen))
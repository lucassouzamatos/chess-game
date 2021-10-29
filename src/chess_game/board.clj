(ns chess-game.board
  (:require [clojure.string :as str]))

;; pieces are represented by fen
;; lower case are white pieces
;; upper case are black pieces
(def pieces
  {:wpawn "p"
   :wrook "r"
   :wknight "n"
   :wbishop "b"
   :wqueen "q"
   :wking "k"
   :bpawn "P"
   :brook "R"
   :bknight "N"
   :bbishop "B"
   :bqueen "Q"
   :bking "K"})

(def default-fen "rnbqkbnr/3ppppp/ppp5/8/8/8/PPPPPPPP/RNBQKBNR")

(defn- parse-int [v]
  (try
    (Integer/parseInt (re-find #"^\d+" (.toString v)))
    (catch NumberFormatException e 0)))

(defn- reduce-empty-values
  [prev curr]
  (conj prev "x"))

(defn- make-array!
  [prev curr]
  (let [last (- (count prev) 1)]
    (if (= "/" curr)
      (conj prev [])
      (update-in prev [last]
                 (fn [value]
                   (let [range-empty (parse-int curr)]
                     (if (not= 0 range-empty)
                       (reduce reduce-empty-values value (range range-empty))
                       (conj value curr))))))))

(defn board-by-fen
  []
  (let [fen (str/split default-fen #"")]
    (reduce make-array! [[]] fen)))

(defn make-board
  []
  (board-by-fen))
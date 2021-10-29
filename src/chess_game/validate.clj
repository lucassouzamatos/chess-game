(ns chess-game.validate
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

(defn- get-diff
  [current target]
    {:x (- (-> :x target) (-> :x current))
     :y (- (-> :y target) (-> :y current))})

(defn- can-move-pawn
  [current target]
    (println "validating pawn...")
    (let [diff (get-diff current target)]
      (and (= (:x diff) 0) (= (:y diff) 1))))

(defn- can-move-rook
  [current target]
    (println "validating rook...")
    true) 

(defn- can-move-knight
  [current target]
    (println "validating knight...")
    true) 

(defn- can-move-bishop
  [current target]
    (println "validating bishop...")
    true) 

(defn- can-move-queen
  [current target]
    (println "validating queen...")
    true) 

(defn- can-move-king
  [current target]
    (println "validating king...")
    true) 
            
(def can-move-piece
  {:p can-move-pawn
   :r can-move-rook
   :n can-move-knight
   :b can-move-bishop
   :q can-move-queen
   :k can-move-king})

(defn can-move
  [piece current target]
  ((get can-move-piece (keyword (str/lower-case piece))) current target))

(ns clojure-sicp.chap1-test
  (:require [clojure.test :refer :all]
            [clojure-sicp.core :refer :all]))

(deftest one-one-one-test
  (testing "Chap 1.1.1"
    (is (= 486 (+ 137 349)))
    (is (= 75 (+ 21 35 12 7)))
    (is (= 19 (+ (* 3 5) (- 10 6))))))


(def size 2)
(def pi 3.14159)
(def radius 10)
(def circumference (* 2 pi radius))


(deftest one-one-two-test
  (testing "Chap 1.1.2"
    (is (= 2 size))
    (is (= 314.159 (* pi (* radius radius))))
    (is (= 62.8318 circumference))))


; equivalent to (define (square x) (* x x)) but this aint scheme
; purposefully skipping the defn macro
(def square (fn [x] (* x x)))

(defn sum-of-squares [x y]
  (+ (square x) (square y)))

(deftest one-one-four-test
  (testing "Chap 1.1.4"
    (is (= 441 (square 21)))
    (is (= 13 (+ (square 2) (square 3))))
    (is (= 13 (sum-of-squares 2 3)))
    (let [ f (fn [a] (sum-of-squares (inc a) (* a 2)))]
        (is (= 136 (f 5))))))

(defn abs [x]
  (cond
    (> x 0) x
    (= x 0) 0
    (< x 0) (- x)))

; other impl of abs
; could use redef or something but thats lame
(defn abs2 [x]
  (cond (neg? x) (- x)
    :else x))

(defn abs3 [x]
  (if (neg? x)
    (- x)
    x))

(defn gte [x y]
  (or (> x y) (= x y)))

(deftest one-one-six-test
  (testing "Chap 1.1.6"
    (dorun (map ; dorun forces evaluation, do this in a map to ensure we get the same answers
      (fn [abs-func]
        (is (= 0 (abs-func 0)))
        (is (= 4 (abs-func 4)))
        (is (= 4 (abs-func -4))))
      [abs abs2 abs3] ))
    ; second part
    (is (true? (gte 9 5)))
    (is (true? (gte 5 5)))
    (is (false? (gte 3 5)))
    (with-redefs [gte (fn [x y] (not (< x y)))]
      (is (true? (gte 9 5)))
      (is (true? (gte 5 5)))
      (is (false? (gte 3 5))))))


(deftest exercise-one-one-test
  (testing "Exercise 1.1"
    (is (= 10 10))
    (is (= 12 (+ 5 3 4)))
    (is (= 8 (- 9 1)))
    (is (= 3 (/ 6 2)))
    (is (= 6 (+ (* 2 4) (- 4 6))))
    (let [a 3 b (+ a 1)]
      (is (= 3 a))
      (is (= 4 b))
      (is (= 19 (+ a b (* a b))))
      (is (false? (= a b)))
      (is (= b (if (and (> b a) (< b (* a b)))
                  b
                  a)))
      (is (= 16 (cond
                  (= a 4) 6
                  (= b 4) (+ 6 7 a)
                  :else 25)))
      (is (= 6 (+ 2 (if (> b a) b a))))
      (is (= 16 (* (cond
                  (> a b) a
                  (< a b) b
                  :else -1)
                (inc a)))))))








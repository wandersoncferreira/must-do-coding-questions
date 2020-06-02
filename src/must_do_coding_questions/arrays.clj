(ns must-do-coding-questions.arrays
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]))

;;; Constrains are implemented as specs.

(s/def ::size (s/and int? #(< % 10e7)))
(s/def ::expected-sum int?)
(s/def ::unsorted-array (s/and vector? #(< (count %) 10e10)))
(s/def ::tests (s/coll-of (s/keys :req-un [::size ::expected-sum ::unsorted-array])))
(s/def ::number-of-tests (s/and #(< % 100) int?))

(defn- consistency-check [{:keys [number-of-tests tests]}]
  (= (count tests) number-of-tests))

(s/def ::input (s/and (s/keys :req-un [::tests ::number-of-tests]) consistency-check))

(defn indices-subarray-given-sum [m]
  {:pre [(s/valid? ::input m)]}
  (for [test (:tests m)]
    (loop [uns (:unsorted-array test)
           rounds 0
           res {}]
      (if (some? (:end-index res))
        (-> res
            (update :beg-index #(+ rounds %))
            (update :end-index #(+ rounds %))
            (select-keys [:beg-index :end-index])
            vals)
        (recur (rest uns)
               (inc rounds)
               (merge res (reduce-kv
                           (fn [acc k v] (let [acc (update acc :total #(+ v %))]
                                           (if (= (:total acc) (:expected-sum test))
                                             (reduced (assoc acc :end-index k))
                                             acc)))
                           {:total 0 :beg-index 0 :end-index nil}
                           (vec uns))))))))

(defn compute-triplet [arr]
  "The performace may not be too good when dealing with T close to 100 elements."
  (let [ret (->> (for [a1 arr a2 arr a3 arr
                       :when (and (= a3 (+ a1 a2)) (not= a1 a2))]
                   (list a1 a2 a3))
                 (map sort)
                 distinct
                 count)]
    (if (= ret 0) -1 ret)))

(defn count-triplets [m]
  (map (comp compute-triplet :array) (:tests m)))



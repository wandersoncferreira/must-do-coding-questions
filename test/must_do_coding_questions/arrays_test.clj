(ns must-do-coding-questions.arrays-test
  (:require [must-do-coding-questions.arrays :as sut]
            [clojure.test :refer [deftest testing is]]))


(deftest subarray-with-given-sum
  (testing "Each test case has T (number of tests), N (size), S (expected sum) and A (unsorted array)"
    (let [ret (sut/indices-subarray-given-sum {:number-of-tests 2
                                               :tests [{:size 5
                                                        :expected-sum 12
                                                        :unsorted-array [1 2 3 7 5]}
                                                       {:size 10
                                                        :expected-sum 15
                                                        :unsorted-array [1 2 3 4 5 6 7 8 9 10]}]})]
      (is (= [[2 4] [1 5]] ret)))))


(deftest count-the-triplets
  (testing "Given an array of distinct integers. 
The task is to count all the triplets such that sum of two elements equals the third element."
    (let [ret (sut/count-triplets {:number-of-tests 2
                                   :tests [{:size 4 :array [1 5 3 2]}
                                           {:size 3 :array [3 2 7]}]})]
      (is (= [2 -1] ret)))))

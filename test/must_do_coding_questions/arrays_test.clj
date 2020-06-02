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

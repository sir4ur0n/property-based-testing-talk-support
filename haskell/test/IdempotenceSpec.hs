module IdempotenceSpec where

import           Idempotence
import           Test.QuickCheck
import           Test.QuickCheck.Arbitrary.ADT
import           Test.QuickCheck.Instances.Scientific
import           Test.QuickCheck.Instances.Time
import           Test.Tasty.HUnit
import           Test.Tasty.QuickCheck

test_empty = testCase "Empty is still empty" $ sortByEvenThenValue [] @?= []

test_one = testCase "One sorted is still one" $ sortByEvenThenValue [1] @?= [1]

test_many_ordered =
  testCase "Many ordered, sorted, are ordered with even first" $
  sortByEvenThenValue [1, 2, 3, 4, 5, 6] @?= [2, 4, 6, 1, 3, 5]

test_many_unordered =
  testCase "Many unordered, sorted, are ordered with even first" $
  sortByEvenThenValue [43, 16, 22, 99, 68, 9865, 4567] @?= [16, 22, 68, 43, 99, 4567, 9865]

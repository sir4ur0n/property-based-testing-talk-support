module IdempotenceSpec where

import qualified Idempotence           as SUT
import           Test.Tasty
import           Test.Tasty.HUnit
import           Test.Tasty.QuickCheck

test_idempotence =
  testProperty "Weird sorter is idempotent" $ \integers ->
    SUT.sortByEvenThenValue integers == (SUT.sortByEvenThenValue . SUT.sortByEvenThenValue) integers

test_empty = testCase "Empty is still empty" $ SUT.sortByEvenThenValue [] @?= []

test_one = testCase "One sorted is still one" $ SUT.sortByEvenThenValue [1] @?= [1]

test_many_ordered =
  testCase "Many ordered, sorted, are ordered with even first" $
  SUT.sortByEvenThenValue [1, 2, 3, 4, 5, 6] @?= [2, 4, 6, 1, 3, 5]

test_many_unordered =
  testCase "Many unordered, sorted, are ordered with even first" $
  SUT.sortByEvenThenValue [43, 16, 22, 99, 68, 9865, 4567] @?= [16, 22, 68, 43, 99, 4567, 9865]

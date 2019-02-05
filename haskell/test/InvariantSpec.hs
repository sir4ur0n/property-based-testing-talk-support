module InvariantSpec where

import           Invariant
import           Test.QuickCheck
import           Test.QuickCheck.Arbitrary.ADT
import           Test.QuickCheck.Instances.Scientific
import           Test.QuickCheck.Instances.Time
import           Test.Tasty.HUnit
import           Test.Tasty.QuickCheck

test_2018_12_31 = testCase "2018-12-31 is a new year's eve" $ isNewYearEve (2018, 12, 31) @?= True

test_1989_12_31 = testCase "1989-12-31 is a new year's eve" $ isNewYearEve (1989, 12, 31) @?= True

test_2018_12_25 = testCase "2018-12-25 is not a new year's eve" $ isNewYearEve (2018, 12, 25) @?= False

test_2018_08_25 = testCase "2018-08-31 is not a new year's eve" $ isNewYearEve (2018, 8, 31) @?= False
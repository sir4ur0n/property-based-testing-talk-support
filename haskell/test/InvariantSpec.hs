module InvariantSpec where

import           Data.Time
import qualified Invariant                      as SUT
import           Test.QuickCheck.Instances.Time
import           Test.Tasty
import           Test.Tasty.HUnit
import           Test.Tasty.QuickCheck

test_invariant_12_31 =
  testProperty "Every 12-31 is a new year's eve" $ \anyYear -> SUT.isNewYearEve $ fromGregorian anyYear 12 31

test_invariant_not_12_31 =
  localOption (QuickCheckTests 1000) $ -- The default is 100, which statistically means we only have ~1 out of 3 chances to come across the new year eve
  localOption (QuickCheckMaxSize 1000) $ -- The default Day arbitrary only moves +-100 days around the 1864-05-09, meaning it will NEVER come across a new year's eve. We
  testProperty "Every other day is not a new year's eve" $ \anyDate ->
    let (_, month, day) = toGregorian anyDate
     in month /= 12 || day /= 31 ==> not (SUT.isNewYearEve anyDate)

test_2018_12_31 = testCase "2018-12-31 is a new year's eve" $ SUT.isNewYearEve (fromGregorian 2018 12 31) @?= True

test_1989_12_31 = testCase "1989-12-31 is a new year's eve" $ SUT.isNewYearEve (fromGregorian 1989 12 31) @?= True

test_2018_12_25 = testCase "2018-12-25 is not a new year's eve" $ SUT.isNewYearEve (fromGregorian 2018 12 25) @?= False

test_2018_08_25 = testCase "2018-08-31 is not a new year's eve" $ SUT.isNewYearEve (fromGregorian 2018 8 31) @?= False

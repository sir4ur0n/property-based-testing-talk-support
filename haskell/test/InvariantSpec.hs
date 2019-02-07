module InvariantSpec where

import           Data.Time
import qualified Invariant                            as SUT
import           Test.QuickCheck
import           Test.QuickCheck.Arbitrary.ADT
import           Test.QuickCheck.Instances.Scientific
import           Test.QuickCheck.Instances.Time
import           Test.Tasty
import           Test.Tasty.HUnit
import           Test.Tasty.QuickCheck

test_invariant_12_31 =
  testProperty "Every 12-31 is a new year's eve" $ \anyYear -> SUT.isNewYearEve $ fromGregorian anyYear 12 31

test_invariant_not_12_31 =
  localOption (QuickCheckTests 1000) $
  testProperty "Every other day is not a new year's eve" $ \anyDate ->
    let anyDay = toDay anyDate
        (_, month, day) = toGregorian anyDay
     in month /= 12 || day /= 31 ==> not (SUT.isNewYearEve anyDay)

test_2018_12_31 = testCase "2018-12-31 is a new year's eve" $ SUT.isNewYearEve (fromGregorian 2018 12 31) @?= True

test_1989_12_31 = testCase "1989-12-31 is a new year's eve" $ SUT.isNewYearEve (fromGregorian 1989 12 31) @?= True

test_2018_12_25 = testCase "2018-12-25 is not a new year's eve" $ SUT.isNewYearEve (fromGregorian 2018 12 25) @?= False

test_2018_08_25 = testCase "2018-08-31 is not a new year's eve" $ SUT.isNewYearEve (fromGregorian 2018 8 31) @?= False

newtype Date = Date
  { toDay :: Day
  } deriving (Show)

instance Arbitrary Date where
  arbitrary = Date . ModifiedJulianDay . (2000 +) <$> choose (-678170, 416465) -- Aprox. year between 0 and 3000, the default is 100 days around year 1864...
  shrink = (Date <$>) . (ModifiedJulianDay <$>) . shrink . toModifiedJulianDay . toDay

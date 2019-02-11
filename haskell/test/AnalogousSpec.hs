module AnalogousSpec where

import           Analogous
import qualified Analogous                     as SUT
import           Test.QuickCheck
import           Test.QuickCheck.Arbitrary.ADT
import           Test.Tasty
import           Test.Tasty.HUnit
import           Test.Tasty.QuickCheck

test_analogous_lens =
  testProperty "Good ol' record updates and lenses are iso functional" $ \city ->
    SUT.upperCityPetsNameUsingRecordUpdate city == SUT.upperCityPetsNameUsingLenses city

instance Arbitrary City where
  arbitrary = genericArbitrary

instance Arbitrary Person where
  arbitrary = genericArbitrary

instance Arbitrary Pet where
  arbitrary = genericArbitrary

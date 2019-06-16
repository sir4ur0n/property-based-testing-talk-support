module AnalogousSpec where

import           Analogous
import qualified Analogous                     as SUT
import           Test.QuickCheck
import           Test.QuickCheck.Arbitrary.ADT
import           Test.Tasty
import           Test.Tasty.HUnit
import           Test.Tasty.QuickCheck

{-|
  This is an analogous property test. Analogous means there are at least 2 paths from an input to an output.
  Use cases for analogous properties:
  * Consistency between functions (e.g. for any number x, `add(x, x) == multiply(x, 2)`)
  * Refactoring: ensure for any input x that the old, legacy function and the new one return the same value: (`legacy(x) == refacto(x)`). Once the property passes, you can remove the legacy function, the property test, and rename the refactored function with the legacy name
-}
test_analogous_lens =
  testProperty "Good ol' record updates and lenses are iso functional" $ \city ->
    SUT.upperCityPetsNameUsingRecordUpdate city == SUT.upperCityPetsNameUsingLenses city

instance Arbitrary City where
  arbitrary = genericArbitrary

instance Arbitrary Person where
  arbitrary = genericArbitrary

instance Arbitrary Pet where
  arbitrary = genericArbitrary

module InverseSpec where

import           Data.Scientific                      (Scientific, scientific)
import           Inverse
import qualified Inverse                              as SUT
import           Test.QuickCheck
import           Test.QuickCheck.Arbitrary.ADT
import           Test.QuickCheck.Instances.Scientific ()
import           Test.Tasty
import           Test.Tasty.HUnit
import           Test.Tasty.QuickCheck

test_inverse_add_remove =
  testProperty "Adding and removing taxes are inverse" $ \price -> (removeTaxes . addTaxes) price == price

-- Add taxes
test_1_1dot20 = testCase "addTaxes: 1.00 -> 1.20" $ addTaxes (Price 1.00 Standard) @?= Price 1.20 Standard

test_23_27dot60 = testCase "addTaxes: 23.00 -> 27.60" $ addTaxes (Price 23.00 Standard) @?= Price 27.60 Standard

test_100_120 = testCase "addTaxes: 100 -> 120" $ addTaxes (Price 100.00 Standard) @?= Price 120.00 Standard

-- Remove taxes
test_1dot20_1 = testCase "removeTaxes: 1.00 -> 1.20" $ removeTaxes (Price 1.20 Standard) @?= Price 1.00 Standard

test_27dot60_23 = testCase "removeTaxes: 23.00 -> 27.60" $ removeTaxes (Price 27.60 Standard) @?= Price 23.00 Standard

test_120_100 = testCase "removeTaxes: 100 -> 120" $ removeTaxes (Price 120.00 Standard) @?= Price 100.00 Standard

instance Arbitrary Price where
  arbitrary = genericArbitrary

instance Arbitrary PriceType where
  arbitrary = genericArbitrary

instance Arbitrary PriceValue where
  arbitrary = PriceValue <$> (scientific <$> choose (1, 10000) <*> choose (-2, 0))

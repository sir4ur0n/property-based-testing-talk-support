module Inverse where

import           Data.Scientific (Scientific)
import           GHC.Generics    (Generic)

data Price = Price
  { _value :: PriceValue
  , _type  :: PriceType
  } deriving (Show, Eq, Generic)

data PriceType
  = Standard
  | Discount
  deriving (Show, Eq, Generic)

type PriceWithoutTaxes = Price

type PriceWithTaxes = Price

newtype PriceValue = PriceValue
  { _scientificValue :: Scientific
  } deriving (Show, Eq, Generic, Num, Fractional)

tva :: PriceValue
tva = PriceValue 1.2

addTaxes :: PriceWithoutTaxes -> PriceWithTaxes
addTaxes price = price {_value = _value price * tva}

removeTaxes :: PriceWithTaxes -> PriceWithoutTaxes
removeTaxes price = price {_value = _value price / tva}

module Inverse where

import           Data.Scientific (Scientific)
import           GHC.Generics    (Generic)

data Price = Price
  { _value :: Scientific
  , _type  :: PriceType
  } deriving (Show, Eq, Generic)

data PriceType
  = Standard
  | Discount
  deriving (Show, Eq, Generic)

type PriceWithoutTaxes = Price

type PriceWithTaxes = Price

tva :: Scientific
tva = 1.2

addTaxes :: PriceWithoutTaxes -> PriceWithTaxes
addTaxes price = price {_value = _value price * tva}

removeTaxes :: PriceWithTaxes -> PriceWithoutTaxes
removeTaxes price = price {_value = _value price / tva}

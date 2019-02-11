{-# LANGUAGE TemplateHaskell #-}

module Analogous
  ( City(City)
  , Person(Person)
  , Pet(Pet)
  , upperCityPetsNameUsingRecordUpdate
  , upperCityPetsNameUsingLenses
  ) where

import           Control.Lens
import           Data.Char    (toUpper)
import           GHC.Generics (Generic)

newtype City = City
  { _citizen :: [Person]
  } deriving (Show, Eq, Generic)

newtype Person = Person
  { _pets :: [Pet]
  } deriving (Show, Eq, Generic)

newtype Pet = Pet
  { _name :: String
  } deriving (Show, Eq, Generic)

makeLenses ''City

makeLenses ''Person

makeLenses ''Pet

upperCityPetsNameUsingRecordUpdate :: City -> City
upperCityPetsNameUsingRecordUpdate (City people) = City $ fmap upperPersonPetsName people
  where
    upperPersonPetsName (Person animals) = Person $ fmap upperPetName animals
    upperPetName (Pet cuteName) = Pet $ fmap toUpper cuteName

upperCityPetsNameUsingLenses :: City -> City
upperCityPetsNameUsingLenses = over (citizen . traverse . pets . traverse . name . traverse) toUpper

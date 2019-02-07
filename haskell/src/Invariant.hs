module Invariant
  ( Day
  , isNewYearEve
  ) where

import           Data.Time (Day, toGregorian)

isNewYearEve :: Day -> Bool
isNewYearEve date = month == 12 && day == 31
  where
    (_, month, day) = toGregorian date

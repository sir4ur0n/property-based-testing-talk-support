module Idempotence
  ( sortByEvenThenValue
  ) where

import           Data.List

sortByEvenThenValue :: [Int] -> [Int]
sortByEvenThenValue input = evenSorted ++ oddSorted
  where
    evenSorted = sortGroupBy (\i -> i `mod` 2 == 0)
    oddSorted = sortGroupBy (\i -> i `mod` 2 /= 0)
    sortGroupBy grouper = sort . filter grouper $ input

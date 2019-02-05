module Invariant (LocalDate, isNewYearEve) where

type LocalDate = (Integer, Int, Int)

isNewYearEve :: LocalDate -> Bool
isNewYearEve (_, month, day) = month == 12 && day == 31
namespace Samples
{
    using System;

    public static class Invariant
    {
        public static bool IsNewYear(this DateTime dateTime)
        {
            return dateTime.Day == 31 && dateTime.Month == 12;
        }
    }
}

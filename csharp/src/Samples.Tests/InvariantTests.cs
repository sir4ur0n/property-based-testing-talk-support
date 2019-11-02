namespace Samples.Tests
{
    using FsCheck;
    using FsCheck.Xunit;
    using System;
    using Xunit;

    public class InvariantTests
    {
        [Fact]
        public void _1989_12_31()
        {
            Assert.True(new DateTime(1989, 12, 31).IsNewYear());
        }

        [Fact]
        public void _2018_08_31()
        {
            Assert.False(new DateTime(2018, 08, 31).IsNewYear());
        }

        [Fact]
        public void _2018_12_25()
        {
            Assert.False(new DateTime(2018, 12, 25).IsNewYear());
        }

        [Fact]
        public void _2018_12_31()
        {
            Assert.True(new DateTime(2018, 12, 31).IsNewYear());
        }

        [Property(Verbose = true)]
        public Property Invariant_31_12(PositiveInt year)
        {
            return new DateTime(year.Get, 12, 31).IsNewYear().ToProperty();
        }

        [Property(Verbose = true, MaxTest = 10000)]
        public Property Invariant_Not_31_12(DateTime dateTime)
        {
            return (!dateTime.IsNewYear()).When(dateTime.Day != 31 || dateTime.Month != 12);
        }
    }
}

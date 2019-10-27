namespace Samples.Tests
{
    using System;

    using FsCheck;
    using FsCheck.Xunit;

    using Xunit;
    using Xunit.Abstractions;

    public class InverseTests
    {
        private readonly ITestOutputHelper output;

        public InverseTests(ITestOutputHelper output)
        {
            this.output = output;
        }

        [Fact]
        public void _1_1dot20()
        {
            var actual = new Price(1m).AddTaxes();

            Assert.Equal(1.2m, actual.Value);
        }

        [Fact]
        public void _100_120()
        {
            var actual = new Price(100.0m).AddTaxes();
            Assert.Equal(120m, actual.Value);
        }

        [Fact]
        public void _120_100()
        {
            var actual = new Price(120m).RemoveTaxes();
            Assert.Equal(100m, actual.Value);
        }

        [Fact]
        public void _1dot20_1()
        {
            var actual = new Price(1.2m).RemoveTaxes();
            Assert.Equal(1m, actual.Value);
        }

        [Fact]
        public void _23_27dot6()
        {
            var actual = new Price(23m).AddTaxes();
            Assert.Equal(27.6m, actual.Value);
        }

        [Fact]
        public void _27dot6_23()
        {
            var actual = new Price(27.6m).RemoveTaxes();
            Assert.Equal(23m, actual.Value);
        }

        [Property(Verbose = true, Arbitrary = new[] { typeof(PriceArbitrary) }, MaxTest = 10000)]
        public Property Inverse_add_remove(Price anyPrice)
        {
            this.output.WriteLine(anyPrice.ToString());

            var priceWithTaxes = anyPrice.AddTaxes();
            var priceWithoutTaxes = priceWithTaxes.RemoveTaxes();

            return (Math.Abs(decimal.Subtract(anyPrice.Value, priceWithoutTaxes.Value)) < 0.00001m).ToProperty();
        }
    }

    public static class PriceArbitrary
    {
        public static Arbitrary<Price> Generate =>
            Arb.From(
                from value in Arb.Default.Decimal().Generator
                from type in Gen.Elements(PriceTypes.Discount, PriceTypes.Standard)
                select new Price(value, type));
    }
}

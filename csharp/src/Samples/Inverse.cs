namespace Samples
{
    public static class Inverse
    {
        public static readonly decimal VTA = 1.2m;

        public static Price AddTaxes(this Price price)
        {
            return new Price(decimal.Multiply(price.Value, VTA), price.PriceType);
        }

        public static Price RemoveTaxes(this Price price)
        {
            return new Price(decimal.Divide(price.Value, VTA), price.PriceType);
        }
    }

    public class Price
    {
        public Price(decimal value, PriceTypes priceType = PriceTypes.Standard)
        {
            this.Value = value;
            this.PriceType = priceType;
        }

        public PriceTypes PriceType { get; }

        public decimal Value { get; }

        public override string ToString()
        {
            return $"Value: {this.Value}, PriceType: {this.PriceType}";
        }
    }

    public enum PriceTypes
    {
        Standard,
        Discount
    }
}

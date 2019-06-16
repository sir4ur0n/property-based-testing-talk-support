package inverse;

import com.google.auto.service.AutoService;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;

@AutoService(Generator.class)
public class PriceGen extends Generator<Price> {

  public PriceGen() {
    super(Price.class);
  }

  /**
   * This is an example of generator. We arbitrarily decide that no price will be lower than a tenth of cent, and no price will be greater than 100 000.
   */
  @Override
  public Price generate(SourceOfRandomness random, GenerationStatus status) {
    return new Price()
        .withValue(BigDecimal.valueOf(random.nextDouble(0.001, 100_000)))
        .withType(gen().type(PriceType.class).generate(random, status));
  }

  /**
   * Shrinking triggers when a property test fails, to help the developer finding the smallest value for which the property still doesn't pass. It's simpler to debug/think about a bug with a price value of 1, rather than 32 584.
   */
  @Override
  public List<Price> doShrink(SourceOfRandomness random, Price larger) {
    return asList(
        // Considered the smallest possible
        larger.withValue(BigDecimal.ONE).withType(PriceType.STANDARD),
        // Variants by modifying only 1 field
        larger.withValue(BigDecimal.ONE),
        larger.withType(PriceType.STANDARD),
        // Additional prices between the smallest and the larger, e.g. by dividing the value by 2
        larger.withMappedValue(previousValue -> previousValue.divide(BigDecimal.valueOf(2), BigDecimal.ROUND_CEILING)).withType(PriceType.STANDARD),
        larger.withMappedValue(previousValue -> previousValue.divide(BigDecimal.valueOf(2), BigDecimal.ROUND_CEILING))
    );
  }
}

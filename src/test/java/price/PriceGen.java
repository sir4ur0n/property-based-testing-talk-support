package price;

import com.google.auto.service.AutoService;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

@AutoService(Generator.class)
public class PriceGen extends Generator<Price> {

  public PriceGen() {
    super(Price.class);
  }

  @Override
  public Price generate(SourceOfRandomness random, GenerationStatus status) {
    return gen().fieldsOf(Price.class).generate(random, status);
  }
}

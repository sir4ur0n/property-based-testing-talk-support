package inverse;

import com.google.auto.service.AutoService;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import java.math.BigDecimal;
import java.util.List;

@AutoService(Generator.class)
public class PriceGen extends Generator<Price> {

  public PriceGen() {
    super(Price.class);
  }

  @Override
  public Price generate(SourceOfRandomness random, GenerationStatus status) {
    return new Price()
        .withValue(BigDecimal.valueOf(random.nextFloat(1, 10_000)))
        .withType(PriceType.STANDARD);
//    return gen().fieldsOf(Price.class).generate(random, status);
  }

  @Override
  public boolean canShrink(Object larger) {
    return super.canShrink(larger);
  }

  @Override
  public List<Price> doShrink(SourceOfRandomness random, Price larger) {
    return super.doShrink(random, larger);
  }
}

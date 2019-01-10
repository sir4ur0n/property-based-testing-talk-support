package analogous;

import com.google.auto.service.AutoService;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

@AutoService(Generator.class)
public class SportGen extends Generator<Sport> {

  public SportGen() {
    super(Sport.class);
  }

  @Override
  public Sport generate(SourceOfRandomness random, GenerationStatus status) {
    return gen().fieldsOf(Sport.class).generate(random, status);
  }
}

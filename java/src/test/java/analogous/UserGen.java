package analogous;

import com.github.sir4ur0n.generator.VavrListGenerator;
import com.google.auto.service.AutoService;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import io.vavr.collection.List;

@SuppressWarnings("unchecked")
@AutoService(Generator.class)
public class UserGen extends Generator<User> {

  public UserGen() {
    super(User.class);
  }

  @Override
  public User generate(SourceOfRandomness random, GenerationStatus status) {
    return new User()
        .withId(random.nextLong())
        .withLogin(gen().type(String.class).generate(random, status))
        .withSports(generateSports(random, status))
        .withFriends(buildFriends(random, status));
  }

  /**
   * Because of the recursive type of `User`, we customize the creation of its friends.
   */
  private List<Sport> generateSports(SourceOfRandomness random, GenerationStatus status) {
    return (List<Sport>) gen().make(VavrListGenerator.class, gen().type(Sport.class)).generate(random, status);
  }

  private List<User> buildFriends(SourceOfRandomness random, GenerationStatus status) {
    return List.fill(random.nextInt(0, 10), () -> gen().constructor(User.class).generate(random, status)
        .withSports(List.fill(random.nextInt(0, 10), () -> gen().type(Sport.class).generate(random, status))));
  }

}

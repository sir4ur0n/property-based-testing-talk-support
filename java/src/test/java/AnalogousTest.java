import analogous.Sport;
import analogous.User;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.control.Option;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitQuickcheck.class)
public class AnalogousTest {

  private Analogous cut;

  @Before
  public void setUp() {
    cut = new Analogous();
  }

  /**
   * This is an analogous property test. Analogous means there are at least 2 paths from an input to an output.
   * Use cases for analogous properties:
   * * Consistency between functions (e.g. for any number x, `add(x, x) == multiply(x, 2)`)
   * * Refactoring: ensure for any input x that the old, legacy function and the new one return the same value: (`legacy(x) == refacto(x)`). Once the property passes, you can remove the legacy function, the property test, and rename the refactored function with the legacy name
   */
  @Property
  public void analogous_legacy_vavr(Option<User> anyMaybeUser) {
    List<Sport> legacy = cut.friendsSportsOrderByName_legacy(anyMaybeUser.getOrNull());
    List<Sport> vavr = cut.friendsSportsOrderByName_vavr(anyMaybeUser);

    assertThat(vavr).isEqualTo(legacy);
  }
}

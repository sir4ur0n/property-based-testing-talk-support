import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class AnalogousProp {

  private Analogous cut;

  @Before
  public void setUp() {
    cut = new Analogous();
  }

  @Property
  public void fizz_period(int i) {
    assumeThat(cut.fizzBuzz(i)).contains("fizz");

    assertThat(cut.fizzBuzz(i + 3)).contains("fizz");
  }

  @Property
  public void buzz_period(int i) {
    assumeThat(cut.fizzBuzz(i)).contains("buzz");

    assertThat(cut.fizzBuzz(i + 5)).contains("buzz");
  }

  @Property
  public void not_fizz_buzz(int i) {
    String result = cut.fizzBuzz(i);
    assumeThat(result).doesNotContain("fizz", "buzz");

    assertThat(Integer.valueOf(result)).isEqualTo(i);
    assertThat(Integer.valueOf(cut.fizzBuzz(i + 15))).isEqualTo(i + 15);
  }
}

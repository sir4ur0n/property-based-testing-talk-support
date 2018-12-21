import static java.lang.Math.PI;
import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class SomeMathsTest {

  private SomeMaths cut;

  @Before
  public void setUp() {
    cut = new SomeMaths();
  }

  @Test
  public void linear_0() {
    assertThat(cut.linear(0)).isEqualTo(0);
  }

  @Test
  public void linear_1() {
    assertThat(cut.linear(PI / 2)).isEqualTo(1);
  }

  @Test
  public void exponentiation_0() {
    assertThat(cut.exponentiation(0)).isEqualTo(0);
  }

  @Test
  public void exponentiation_1() {
    assertThat(cut.exponentiation(PI / 2)).isEqualTo(1);
  }

  @Test
  public void sine_0() {
    assertThat(cut.sine(0)).isEqualTo(0);
  }

  @Test
  public void sine_1() {
    assertThat(cut.sine(PI / 2)).isEqualTo(1);
  }

}

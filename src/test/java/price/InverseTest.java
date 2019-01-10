package price;

import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class InverseTest {

  private Inverse cut;

  @Before
  public void setUp() {
    cut = new Inverse();
  }

  @Test
  public void _1_1dot20() {
    Price actual = cut.addTaxes(new Price().withValue(BigDecimal.valueOf(1.0)));

    assertThat(actual.getValue()).isEqualByComparingTo(BigDecimal.valueOf(1.2));
  }

  @Test
  public void _23_27dot6() {
    Price actual = cut.addTaxes(new Price().withValue(BigDecimal.valueOf(23.0)));

    assertThat(actual.getValue()).isEqualByComparingTo(BigDecimal.valueOf(27.6));
  }

  @Test
  public void _100_120() {
    Price actual = cut.addTaxes(new Price().withValue(BigDecimal.valueOf(100.0)));

    assertThat(actual.getValue()).isEqualByComparingTo(BigDecimal.valueOf(120.0));
  }
}

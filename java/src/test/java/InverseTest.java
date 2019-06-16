import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import inverse.Price;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitQuickcheck.class)
public class InverseTest {

  private Inverse cut;

  @Before
  public void setUp() {
    cut = new Inverse();
  }

  /**
   * This is an inverse property test: we check that from any input transformed by a function `f`, `f` is reversible if it's possible from its output to get back to the original input.
   * <p>
   * This is useful because a reversible function means it doesn't lose any information during the transformation. If it did, it would be impossible to get back to the original input. This is particularly useful for conversion functions (e.g. from your public REST models to your business models).
   * <p>
   * Here, this helps proving there is no loss of precision due to tax processing.
   */
  @Property
  public void inverse_add_remove(Price anyPrice) {
    Price priceWithTaxes = cut.addTaxes(anyPrice);
    Price priceWithoutTaxes = cut.removeTaxes(priceWithTaxes);

    assertThat(priceWithoutTaxes).isEqualTo(anyPrice);
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

  @Test
  public void _1dot20_1() {
    Price actual = cut.removeTaxes(new Price().withValue(BigDecimal.valueOf(1.2)));

    assertThat(actual.getValue()).isEqualByComparingTo(BigDecimal.valueOf(1.0));
  }

  @Test
  public void _27dot6_23() {
    Price actual = cut.removeTaxes(new Price().withValue(BigDecimal.valueOf(27.6)));

    assertThat(actual.getValue()).isEqualByComparingTo(BigDecimal.valueOf(23.0));
  }

  @Test
  public void _120_100() {
    Price actual = cut.removeTaxes(new Price().withValue(BigDecimal.valueOf(120.0)));

    assertThat(actual.getValue()).isEqualByComparingTo(BigDecimal.valueOf(100.0));
  }
}

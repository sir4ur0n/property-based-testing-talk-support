import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import inverse.Price;
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

  @Property
  public void inverse_add_remove(Price anyPrice) {
    System.out.println(anyPrice);
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

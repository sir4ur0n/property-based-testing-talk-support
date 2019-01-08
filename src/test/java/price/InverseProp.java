package price;

import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class InverseProp {

  private Inverse cut;

  @Before
  public void setUp() {
    cut = new Inverse();
  }

  @Property
  public void analogous_add_remove_taxes(Price anyPrice) {
    Price actual = cut.removeTaxes(cut.addTaxes(anyPrice));

    assertThat(actual).isEqualTo(anyPrice);
  }
}

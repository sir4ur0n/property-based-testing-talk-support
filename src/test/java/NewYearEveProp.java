import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class NewYearEveProp {

  private NewYearEve cut;

  @Before
  public void setUp() {
    cut = new NewYearEve();
  }

  @Property
  public void _XXXX_12_31(short anyYear) {
    assertThat(cut.isNewYearEve(LocalDate.of(anyYear, 12, 31))).isTrue();
  }

  @Property
  public void _not_12_31(LocalDate anyDate) {
    assumeThat(anyDate.getDayOfMonth() == 31 && anyDate.getMonthValue() == 12).isFalse();

    assertThat(cut.isNewYearEve(anyDate)).isFalse();
  }
}

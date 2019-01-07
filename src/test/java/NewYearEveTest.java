import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class NewYearEveTest {

  private NewYearEve cut;

  @Before
  public void setUp() {
    cut = new NewYearEve();
  }

  @Property
  public void invariant_31_12(short anyYear) {
    assertThat(cut.isNewYearEve(LocalDate.of(anyYear, 12, 31))).isTrue();
  }

  @Property
  public void otherwise_false(LocalDate anyDate) {
    assumeThat(anyDate.getDayOfMonth() == 31 && anyDate.getMonthValue() == 12).isFalse();

    assertThat(cut.isNewYearEve(anyDate)).isFalse();
  }

  @Test
  public void _2018_12_31() {
    assertThat(cut.isNewYearEve(LocalDate.of(2018, 12, 31))).isTrue();
  }

  @Test
  public void _1989_12_31() {
    assertThat(cut.isNewYearEve(LocalDate.of(1989, 12, 31))).isTrue();
  }

  @Test
  public void _2018_12_25() {
    assertThat(cut.isNewYearEve(LocalDate.of(2018, 12, 25))).isFalse();
  }

  @Test
  public void _2018_08_31() {
    assertThat(cut.isNewYearEve(LocalDate.of(2018, 8, 31))).isFalse();
  }
}

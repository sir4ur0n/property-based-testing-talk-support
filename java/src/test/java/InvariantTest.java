import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class InvariantTest {

  private Invariant cut;

  @Before
  public void setUp() {
    cut = new Invariant();
  }

  @Property
  public void invariant_31_12(short anyYear) {
    assertThat(cut.isNewYearEve(LocalDate.of(anyYear, 12, 31))).isTrue();
  }

  @Property(trials = 1000)
  public void invariant_not_31_12(LocalDate anyDate) {
    assumeThat(anyDate.getMonthValue() != 12 || anyDate.getDayOfMonth() != 31).isTrue();

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

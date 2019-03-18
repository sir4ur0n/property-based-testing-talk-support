import static org.assertj.core.api.Assertions.assertThat;

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

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

@RunWith(JUnitQuickcheck.class)
public class InvariantTest {

  private Invariant cut;

  @Before
  public void setUp() {
    cut = new Invariant();
  }

  /**
   * This is an invariance property: we check that something is always true, no matter the input.
   * <p>
   * Invariance properties usually are the first type of property test people write when coming from unit tests. Typically, any test with "random" values (e.g. with any date, or "John Doe", or "Lorem ipsum", or 42) can be rewritten as an invariance test, where you have all "random" values injected instead of choosing them. The benefit is twofold: you ensure those values truly don't matter, and you make it explicit to the reader.
   */
  @Property
  public void invariant_31_12(short anyYear) {
    assertThat(cut.isNewYearEve(LocalDate.of(anyYear, 12, 31))).isTrue();
  }

  /**
   * A second invariance property test, also showcasing assumptions (which are also often called "implications").
   */
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

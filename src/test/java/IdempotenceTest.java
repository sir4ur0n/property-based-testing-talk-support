import static io.vavr.API.List;
import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class IdempotenceTest {

  private Idempotence cut;

  @Before
  public void setUp() {
    cut = new Idempotence();
  }

  @Test
  public void empty_empty() {
    assertThat(cut.sortByEvenThenValue(List())).isEqualTo(List());
  }

  @Test
  public void one_one() {
    assertThat(cut.sortByEvenThenValue(List(1))).isEqualTo(List(1));
  }

  @Test
  public void many_ordered() {
    assertThat(cut.sortByEvenThenValue(List(1, 2, 3, 4, 5, 6))).isEqualTo(List(2, 4, 6, 1, 3, 5));
  }

  @Test
  public void many_random() {
    assertThat(cut.sortByEvenThenValue(List(43, 16, 22, 99, 68, 9865, 4567)))
        .isEqualTo(List(16, 22, 68, 43, 99, 4567, 9865));
  }
}

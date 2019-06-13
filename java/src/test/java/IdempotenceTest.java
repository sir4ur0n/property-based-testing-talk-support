import static io.vavr.API.List;
import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.collection.List;
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

  @Property
  public void idempotence(List<String> anyStrings) {
    List<String> result1 = cut.removeSmallWords(anyStrings);
    List<String> result2 = cut.removeSmallWords(result1);

    assertThat(result1).isEqualTo(result2);
  }

  @Test
  public void empty_empty() {
    assertThat(cut.removeSmallWords(List())).isEqualTo(List());
  }

  @Test
  public void keep_long_enough() {
    assertThat(cut.removeSmallWords(List("a", "bc", "def", "ghij"))).isEqualTo(List("def", "ghij"));
  }

  @Test
  public void remove_all_small() {
    assertThat(cut.removeSmallWords(List("    a", "b", "c            ", " de "))).isEqualTo(List());
  }

  @Test
  public void many_random() {
    assertThat(cut.removeSmallWords(List("hello", "", "wo  rld  ", "    !!"))).isEqualTo(List("hello", "wo  rld"));
  }
}

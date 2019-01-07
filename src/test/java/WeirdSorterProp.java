import static org.assertj.core.api.Assertions.assertThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.collection.List;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class WeirdSorterProp {

  private WeirdSorter cut;

  @Before
  public void setUp() {
    cut = new WeirdSorter();
  }

  @Property
  public void idempotent(List<Integer> anyIntegers) {
    System.out.println(anyIntegers);
    List<Integer> runOnce = cut.sortByEvenThenValue(anyIntegers);
    List<Integer> runTwice = cut.sortByEvenThenValue(runOnce);

    assertThat(runOnce).isEqualTo(runTwice);
  }
}

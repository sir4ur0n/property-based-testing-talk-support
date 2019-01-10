import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class AnalogousTest {

  private Analogous cut;

  @Before
  public void setUp() {
    cut = new Analogous();
  }

}

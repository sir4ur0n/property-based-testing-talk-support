import static org.assertj.core.api.Assertions.assertThat;

import analogous.Sport;
import analogous.User;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.vavr.control.Option;
import java.util.List;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class AnalogousTest {

  private Analogous cut;

  @Before
  public void setUp() {
    cut = new Analogous();
  }

  @Property
  public void analogous_legacy_vavr(Option<User> anyMaybeUser) {
    System.out.println(anyMaybeUser);
    List<Sport> legacy = cut.friendsSportsOrderByName_legacy(anyMaybeUser.getOrNull());
    List<Sport> vavr = cut.friendsSportsOrderByName_vavr(anyMaybeUser).toJavaList();

    assertThat(vavr).isEqualTo(legacy);
  }
}

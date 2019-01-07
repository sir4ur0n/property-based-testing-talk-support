import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class FizzBuzzTest {

  private FizzBuzz cut;

  @Before
  public void setUp() {
    cut = new FizzBuzz();
  }

  @Property
  public void fizz_plus3(int anyInt) {
    assumeThat(cut.fizzBuzz(anyInt)).contains("fizz");

    assertThat(cut.fizzBuzz(anyInt + 3)).contains("fizz");
  }

  @Property
  public void buzz_plus5(int anyInt) {
    assumeThat(cut.fizzBuzz(anyInt)).contains("buzz");

    assertThat(cut.fizzBuzz(anyInt + 5)).contains("buzz");
  }

  @Test
  public void _1() {
    assertThat(cut.fizzBuzz(1)).isEqualTo("1");
  }

  @Test
  public void _2() {
    assertThat(cut.fizzBuzz(2)).isEqualTo("2");
  }

  @Test
  public void _3_fizz() {
    assertThat(cut.fizzBuzz(3)).isEqualTo("fizz");
  }

  @Test
  public void _4() {
    assertThat(cut.fizzBuzz(4)).isEqualTo("4");
  }

  @Test
  public void _5_buzz() {
    assertThat(cut.fizzBuzz(5)).isEqualTo("buzz");
  }

  @Test
  public void _6_fizz() {
    assertThat(cut.fizzBuzz(6)).isEqualTo("fizz");
  }

  @Test
  public void _15_fizzbuzz() {
    assertThat(cut.fizzBuzz(15)).isEqualTo("fizzbuzz");
  }
}

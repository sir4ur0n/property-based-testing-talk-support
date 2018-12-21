import static java.lang.Math.PI;
import static java.lang.Math.pow;
import static java.lang.Math.sin;

public class SomeMaths {

  public double linear(double x) {
    return (2 / PI) * x;
  }

  public double exponentiation(double x) {
    return pow(linear(x), 2);
  }

  public double sine(double x) {
    return sin(x);
  }
}

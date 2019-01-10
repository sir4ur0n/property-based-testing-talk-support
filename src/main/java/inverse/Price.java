package inverse;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Wither
public class Price {

  BigDecimal value;
  PriceType type;

  public Price withMappedValue(Function<BigDecimal, BigDecimal> mapper) {
    return withValue(mapper.apply(value));
  }

  private boolean canEqual(final Object other) {
    return other instanceof Price;
  }

  /**
   * Enjoy BigDecimal shitty equality in Java: equals() compares scale but compareTo() doesn't
   */
  @Override
  public boolean equals(final Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Price)) {
      return false;
    }
    final Price other = (Price) o;
    if (!other.canEqual(this)) {
      return false;
    }
    final BigDecimal this$value = this.getValue();
    final BigDecimal other$value = other.getValue();
    if (this$value == null ? other$value != null : this$value.compareTo(other$value) != 0) {
      return false;
    }
    final Object this$type = this.getType();
    final Object other$type = other.getType();
    return Objects.equals(this$type, other$type);
  }

  @Override
  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $value = this.getValue();
    result = result * PRIME + ($value == null ? 43 : $value.hashCode());
    final Object $type = this.getType();
    result = result * PRIME + ($type == null ? 43 : $type.hashCode());
    return result;
  }
}

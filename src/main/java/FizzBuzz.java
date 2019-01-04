public class FizzBuzz {

  String fizzBuzz(int i) {
    String result = "";
    boolean isFizz = i % 3 == 0;
    boolean isBuzz = i % 5 == 0;
    if (isFizz || isBuzz) {
      if (isFizz) {
        result += "fizz";
      }
      if (isBuzz) {
        result += "buzz";
      }
      return result;
    }
    return String.valueOf(i);
  }

}

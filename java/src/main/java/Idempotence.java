import io.vavr.collection.List;

class Idempotence {

  List<String> removeSmallWords(List<String> input) {
    return input
        .map(String::trim)
        .filter(s -> s.length() >= 3);
  }

}

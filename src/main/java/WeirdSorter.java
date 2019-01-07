import static io.vavr.API.List;

import io.vavr.collection.List;
import io.vavr.collection.Map;

class WeirdSorter {

  List<Integer> sortByEvenThenValue(List<Integer> input) {
    Map<Boolean, List<Integer>> evenOrNot = input.groupBy(i -> i % 2 == 0)
        .mapValues(List::sorted);
    List<Integer> evenSorted = evenOrNot.get(true).getOrElse(List());
    List<Integer> oddSorted = evenOrNot.get(false).getOrElse(List());
    return evenSorted.appendAll(oddSorted);
  }

}

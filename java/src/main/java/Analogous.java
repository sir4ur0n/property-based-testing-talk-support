import static java.util.Collections.emptyList;

import analogous.Sport;
import analogous.User;
import io.vavr.collection.List;
import io.vavr.control.Option;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

@SuppressWarnings("ALL")
class Analogous {

  List<Sport> friendsSportsOrderByName_vavr(Option<User> user) {
    return user.toList()
        .flatMap(User::getFriends)
        .flatMap(User::getSports)
        .distinct()
        .sortBy(Sport::getName);
  }

  java.util.List<Sport> friendsSportsOrderByName_legacy(User user) {
    if (user == null) {
      return emptyList();
    }

    SortedSet<Sport> result = new TreeSet<>(new Comparator<Sport>() {
      @Override
      public int compare(Sport o1, Sport o2) {
        return o1.getName().compareTo(o2.getName());
      }
    });

    java.util.List<User> friends = user.getFriends().toJavaList();
    for (User friend : friends) {
      result.addAll(friend.getSports().toJavaList());
    }

    return new ArrayList<>(result);
  }

}

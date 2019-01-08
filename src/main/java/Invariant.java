import java.time.LocalDate;

class Invariant {

  boolean isNewYearEve(LocalDate date) {
    //
    return date.getDayOfMonth() == 31 && date.getMonthValue() == 12;
  }

}

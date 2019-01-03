import java.time.LocalDate;

class NewYearEve {

  boolean isNewYearEve(LocalDate date) {
    //
    return date.getDayOfMonth() == 31 && date.getMonthValue() == 12;
  }

}

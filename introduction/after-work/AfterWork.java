import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalAdjuster;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

public class AfterWork {

  public static void main(String[] args) {
    
    LocalDate today = LocalDate.now();
    String weekDayToday = today
      .getDayOfWeek()
      .getDisplayName(TextStyle.FULL, Locale.getDefault());

    System.out.println("Today is " + weekDayToday + " " + today);

    TemporalAdjuster lastMonday =
      TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY);
    LocalDate afterWork = 
      today.with(lastMonday);

    if (today.equals(afterWork)) {
      System.out.println("Today!");
    } else if (today.isAfter(afterWork)) {
      System.out.println("We missed it this month.");
      afterWork = today.plusMonths(1)
        .with(lastMonday);
      System.out.println("Next month, it is: " + afterWork);
    } else {
      System.out.println("Next AW at: " + afterWork);
    }
  }
}

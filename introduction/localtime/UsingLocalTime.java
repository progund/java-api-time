import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class UsingLocalTime {
  public static void main(String[] args) {
    LocalTime lectureStart = LocalTime.parse("10:15:00");
    LocalTime lectureStartNoAcademic = lectureStart.minusMinutes(15);
    System.out.println("lectureStart with academic 15 minutes: " +
                       lectureStart);
    System.out.println("lecture start without academic 15 minutes: " +
                       lectureStartNoAcademic);
    LocalTime late = LocalTime.parse("23:55:00");
    LocalTime later = late.plusMinutes(10);
    System.out.println("Late: " + late + " later: " + later);
    System.out.println("late is before later? " + late.isBefore(later));
    LocalTime lectureEnd = lectureStart.plusMinutes(45);
    long lectureMinutes = lectureStart.until(lectureEnd, ChronoUnit.MINUTES);
    System.out.println("The lecture is " + lectureMinutes + " minutes long");
    lectureMinutes = ChronoUnit.MINUTES.between(lectureStart, lectureEnd);
    System.out.println("The lecture is " + lectureMinutes + " minutes long");
    
  }
}

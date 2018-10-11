import java.time.LocalTime;
import java.time.Duration;

public class TestDuration {
  public static void main(String[] args) {
    LocalTime lectureStart = LocalTime.parse("10:15:00");
    LocalTime lectureEnd = lectureStart.plusMinutes(45);
    Duration lecture = Duration.between(lectureStart, lectureEnd);
    long minutes = lecture.toMinutes(); // 45
    System.out.println(minutes);
    Duration aDay = Duration.ofDays(1);
    minutes = aDay.toMinutes(); // 60x24 = 1440
    System.out.println(minutes);
  }
}

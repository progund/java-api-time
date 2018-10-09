import java.time.Duration;
import java.time.LocalTime;

public class VHSOO {

  private static int duration(LocalTime start, LocalTime end) {
    Duration day = Duration.ofHours(24);
    Duration lengthOfTheShow = null;
    if (end.isBefore(start)) { // after midnight
      lengthOfTheShow = day.minus(Duration.between(end, start));
    } else {
      lengthOfTheShow = Duration.between(start, end);
    }
    return (int)lengthOfTheShow.toMinutes();
  }

  public static void main(String[] args) {
    ArgumentParser parser = new ArgumentParser(args);
    if (parser.hasParseErrors()) {
      System.out.println(parser.parseErrorMessage().length());
      System.err.println(parser.parseErrorMessage());
      System.exit(parser.exitCode());
    }
    int duration = duration(parser.start(), parser.end());
    System.out.printf("You have %d minutes left on the tape.\n", (parser.tapeLength() - parser.used()) );
    System.out.printf("The show is %d minutes long.\n", duration);
    System.out.printf("It ");
    if (duration > (parser.tapeLength() - parser.used())) {
      System.out.println("won't fit.\n");
    } else {
      System.out.println("will fit.\n");
    }
  }

}

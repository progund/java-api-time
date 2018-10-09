import java.time.*;

public class VHS {

  /* We use int, since we are not aware of shows on TV whose
   * number of minute duration requires a long variable.
   */
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

    if (args.length != 4) {
      System.err.println("Usage: java VHS <length> <used> <start-time> <end-time>");
      System.err.println("Where:");
      System.err.println(" length is the length of the cassette in minutes");
      System.err.println(" used is the length of already recorded shows on the cassete");
      System.err.println("      in minutes");
      System.err.println(" start-time is a time on the format HH:MM");
      System.err.println(" end-time is a time on the format HH:MM");
      System.err.println("Example:");
      System.err.println("  java VHS 180 90 22:10 23:55");
      System.exit(1);
    }

    try {
      int tapeLength = Integer.parseInt(args[0]);
      int used = Integer.parseInt(args[1]);
      int minutesLeft = tapeLength - used;
      LocalTime start = LocalTime.parse(args[2]);
      LocalTime end = LocalTime.parse(args[3]);
      int showDuration = duration(start, end);
      System.out.printf("You have %d minutes left on the tape.", minutesLeft);
      System.out.printf(" The show is %d minutes long.\nIt %s fit on the tape.\n",
                        showDuration,
                        (showDuration > (minutesLeft) ? "won't" : "will"));
    } catch (Exception e) {
      System.err.println("Could not parse arguments: " + e.getMessage());
      System.exit(2);
    }
  }
}

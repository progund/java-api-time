import java.time.*;

/**
 * Represents a time interval, e.g. 20:00:00 - 04:00:00 (wraps midnight) or
 * 08:00:00 - 16:00:00
 */
public class Interval {
  
  private LocalTime from;
  private LocalTime to;

  /**
   * Constructs a new Interval from the given LocalTimes, inclusive.
   * @param from The start time of the Interval
   * @param to The end time of this Interval
   */
  public Interval(LocalTime from, LocalTime to) {
    this.from = from;
    this.to = to;
  }

  /**
   * A helper method to check if this Interval includes times
   * in the same day.
   */
  private boolean isSameDayInterval() {
    return from.compareTo(to) < 0;
  }

  /**
   * Checks if this Interval encompasses the given LocalTime.
   * @return <code>true</code> if this Interval encompasses the given LocalTime,
   * <code>false</code> otherwise
   */
  public Boolean encompasses(LocalTime time) {
    // If same day interval:
    //   from <= time <= to
    // Else
    //   from <= time || time <= to
    if(isSameDayInterval()) {
      return from.compareTo(time) <= 0 && time.compareTo(to) <= 0;
    } else {
      return from.compareTo(time) <= 0 || time.compareTo(to) <= 0;
    }
  }

  /**
   * Returns a String representation of this Interval in the format:
   * <pre>22:00:00 - 04:00:00</pre>
   * @return A String representation of this Interval
   */
  @Override
  public String toString() {
    return from + " - " + to;
  }

}

import java.time.LocalTime;

public class TestInterval {

  private static void assertEncompasses(Interval interval, LocalTime time) {
    assert interval.encompasses(time) : interval + " should encompass " + time;
  }
  
  private static void assertNotEncompasses(Interval interval, LocalTime time) {
    assert !interval.encompasses(time) : interval + " shouldn't encompass " + time;
  }
  
  public static void main(String[] args) {
    System.out.println("========Running tests=========");
    LocalTime time = LocalTime.parse("23:00:00");
    Interval interval = new Interval(LocalTime.parse("22:00:00"),
                                     LocalTime.parse("04:00:00"));
    System.out.println("Interval " + interval + " Tests for encompassing");
    assertEncompasses(interval, time);
    time = LocalTime.parse("03:00:00");
    assertEncompasses(interval, time);
    time = LocalTime.parse("22:00:01");
    assertEncompasses(interval, time);
    time = LocalTime.parse("22:00:00.000000001");
    assertEncompasses(interval, time);
    System.out.println("Tests passed OK");
    System.out.println("Interval " + interval + " Tests for non-encompassing");    
    time = LocalTime.parse("06:00:00");
    assertNotEncompasses(interval, time);
    time = LocalTime.parse("21:00:00");
    assertNotEncompasses(interval, time);
    time = LocalTime.parse("21:59:59");
    assertNotEncompasses(interval, time);
    time = LocalTime.parse("21:59:59.999999999");
    assertNotEncompasses(interval, time);
    time = LocalTime.parse("04:00:01");
    assertNotEncompasses(interval, time);
    System.out.println("Tests passed OK");
    System.out.println("===========New interval==============");
    interval = new Interval(LocalTime.parse("04:00:00"),
                            LocalTime.parse("22:00:00"));
    System.out.println("Interval " + interval + " Tests for encompassing");
    time = LocalTime.parse("06:00:00");
    assertEncompasses(interval, time);
    time = LocalTime.parse("21:00:00");
    assertEncompasses(interval, time);
    time = LocalTime.parse("04:00:01");
    assertEncompasses(interval, time);
    time = LocalTime.parse("21:59:59");
    assertEncompasses(interval, time);
    time = LocalTime.parse("04:00:00").plusNanos(1L);
    assertEncompasses(interval, time);
    System.out.println("Tests passed OK");
    System.out.println("Interval " + interval + " Tests for non-encompassing");    
    time = LocalTime.parse("22:00:01");
    assertNotEncompasses(interval, time);
    time = LocalTime.parse("03:00:00");
    assertNotEncompasses(interval, time);
    time = LocalTime.parse("03:59:59");
    assertNotEncompasses(interval, time);
    System.out.println("Tests passed OK");
    System.out.println("=========Using Interval as a TemporalQuery============");
    if(time.query(interval)) {
      System.out.println(interval + " encompasses " + time);
    } else {
      System.out.println(interval + " does not encompass " + time);
    }
    time = LocalTime.parse("12:00:00");
    if(time.query(interval)) {
      System.out.println(interval + " encompasses " + time);
    } else {
      System.out.println(interval + " does not encompass " + time);
    }
    /*
     * The below would work if we changed the parameter
     * of encompasses to accept a TemporalAccessor
    if(time.query(interval::encompasses)) {
      System.out.println(interval + " encompasses " + time);
    } else {
      System.out.println(interval + " does not encompass " + time);
    }
    */

    
    System.out.println("========New Interval - sample output===========");
    interval = new Interval(LocalTime.parse("04:00:00"),
                            LocalTime.parse("04:00:00"));
    time = LocalTime.parse("22:00:01");
    System.out.println(interval + " encompasses " + time + "? " + interval.encompasses(time));
    time = LocalTime.parse("04:00:00");
    System.out.println(interval + " encompasses " + time + "? " + interval.encompasses(time));
    System.out.println("========New Interval sample output===========");
    interval = new Interval(LocalTime.parse("04:00:00"),
                            LocalTime.parse("03:59:59"));
    time = LocalTime.parse("22:00:01");
    System.out.println(interval + " encompasses " + time + "? " + interval.encompasses(time));
    time = LocalTime.parse("04:00:00");
    System.out.println(interval + " encompasses " + time + "? " + interval.encompasses(time));

    System.out.println("=========All day interval - sample output==========");
    Interval allDayLong
      = new Interval(LocalTime.parse("12:00:00"),
                     LocalTime.parse("12:00:00").minusNanos(1L));
    System.out.println(allDayLong);
    
  }

}

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A small program displaying the time in the terminal
 */
public class Java8Clock {

  public static void main(String[] args) {

    Timer timer = new Timer("Ticker");
    timer.schedule(new TimerTask(){
        @Override
        public void run() {
          System.out.print("\r" +
                           LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
        }
      }, 0, 1000);
  }

}

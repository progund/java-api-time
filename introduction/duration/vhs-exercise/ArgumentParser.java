import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class ArgumentParser {

  private StringBuilder errorMessage;
  private int tapeLength;
  private int used;
  private LocalTime start;
  private LocalTime end;
  private int exitCode;
  
  public ArgumentParser(String[] args) {    
    errorMessage = new StringBuilder();
    checkArgsLength(args.length);
    if (!hasParseErrors()) {
      parseTapeLength(args[0]);
      parseUsed(args[1]);
      parseStart(args[2]);
      parseEnd(args[3]);
    }
  }

  private void checkArgsLength(int length) {
    if (length != 4) {
      errorMessage.append("Usage: java VHS <length> <used> <start-time> <end-time>\n");
      errorMessage.append("Where:\n");
      errorMessage.append(" length is the length of the cassette in minutes\n");
      errorMessage.append(" used is the length of already recorded shows on the cassete\n");
      errorMessage.append("      in minutes\n");
      errorMessage.append(" start-time is a time on the format HH:MM\n");
      errorMessage.append(" end-time is a time on the format HH:MM\n");
      errorMessage.append("Example:\n");
      errorMessage.append("  java VHS 180 90 22:10 23:55\n");
      exitCode = 1;
    }
  }
  
  public boolean hasParseErrors() {
    return errorMessage.length() != 0;
  }


  public int exitCode() {
    return exitCode;
  }

  public String parseErrorMessage() {
    return errorMessage.toString();
  }

  public int tapeLength() {
    return tapeLength;
  }
  
  private void parseTapeLength(String arg) {
    try {
      tapeLength = Integer.parseInt(arg);
    } catch (NumberFormatException nfe) {
      errorMessage.append("Could not parse tape length: " + arg + "\n");
      exitCode = 2;
    }
  }

  public int used() {
    return used;
  }
  
  private void parseUsed(String arg) {
    try {
      used = Integer.parseInt(arg);
    } catch (NumberFormatException nfe) {
      errorMessage.append("Could not parse minutes used: " + arg + "\n");
      exitCode = 2;
    }
  }

  public LocalTime start() {
    return start;
  }
  
  private void parseStart(String arg) {
    try {
      start = LocalTime.parse(arg);
    } catch (DateTimeParseException e) {
      errorMessage.append("Could not parse start time: " + arg + "\n"); 
     exitCode = 2;
    }
  }

  public LocalTime end() {
    return end;
  }
  
  private void parseEnd(String arg) {
    try {
      end = LocalTime.parse(arg);
    } catch (DateTimeParseException e) {
      errorMessage.append("Could not parse end time: " + arg + "\n"); 
     exitCode = 2;
    }
  }
  
}

/*
 * Copyright 2018 Rikard Fröberg and Henrik Sandklef, Juneday education
 * a part of Morus konsult AB.
 */

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Parses the arguments for the VHSOO application and provides
 * error message and exit code for any parse error (including
 * wrong number of arguments), and provides the arguments as the
 * correct parsed type.
 * @author Rikard Fröberg
 */
public class ArgumentParser {

  private StringBuilder errorMessage;
  private int tapeLength;
  private int used;
  private LocalTime start;
  private LocalTime end;
  private int exitCode;

  /**
   * Constructs a new ArgumentParser and parses the arguments.
   *
   * Parses the arguments if the length of the argument array is
   * correct.
   * @param args The arguments as an array of String
   */
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

  /**
   * Checks that the length of the argument is 4, creates a USAGE string
   * as the errorMessage and sets the exit code to 1 otherwise.
   * @param length The length of the arguments as an int
   */
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

  /**
   * Checks whether the parsing ran into any parse errors.
   * @return true if any errors were encountered, false otherwise
   */
  public boolean hasParseErrors() {
    return errorMessage.length() != 0;
  }


  /**
   * Returns the exit code based on how successful the parsing was.
   * @return The exit code as an int. 0 if the parsing was successful,
   *         1 if there was wrong number of arguments, 2 if any argument
   *         wouldn't parse.
   */
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

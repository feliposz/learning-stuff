/* Exercise 3 of Chapter 2 of Think Java */

public class Time {
  public static void main(String[] args) {
    int hours = 0;
    int minutes = 23;
    int seconds = 46;
    System.out.println("Base time: " + hours + ":" + minutes + ":" + seconds);

    int secondsSinceMidnight = hours * 60 * 60 + minutes * 60 + seconds;
    System.out.println("The number of seconds since midnight is " + secondsSinceMidnight + ".");

    int totalSecondsInDay = 24 * 60 * 60;
    int secondsUntilMidnight = totalSecondsInDay - secondsSinceMidnight;
    System.out.println("The number of seconds until the end of the day is " + secondsUntilMidnight + ".");

    int percentageOfDayPassed = secondsSinceMidnight * 100 / totalSecondsInDay;
    System.out.println("The passed percentage of the day is " + percentageOfDayPassed + "%.");
  }

}
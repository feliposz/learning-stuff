/* Exercise 4 for Chapter 3 in Think Java */

public class DateMethods {
  public static void main(String[] args) {
    String day = "Friday";
    int date = 2;
    String month = "March";
    int year = 2012;

    System.out.println("Print date in standard American form");
    printAmericanDate(day, date, month, year);
    System.out.println("Print date in European form");
    printEuropeanDate(day, date, month, year);
  }

  private static void printAmericanDate(String day, int date, String month, int year) {
    System.out.println(day + ", " + month + " " + date + ", " + year + ".");
  }

  private static void printEuropeanDate(String day, int date, String month, int year) {
    System.out.println(day + " " + date + " " + month + ", " + year + ".");
  }

}
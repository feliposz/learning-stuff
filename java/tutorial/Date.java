/* Exercise 2 for Chapter 2 in Think Java */

public class Date {
  public static void main(String[] args) {
    String day = "Friday";
    int date = 2;
    String month = "March";
    int year = 2012;

    System.out.println("Step 1: Print 1 value per line");
    System.out.println(day);
    System.out.println(date);
    System.out.println(month);
    System.out.println(year);

    System.out.println("Step 2: Print date in standard American form");
    System.out.println(day + ", " + month + " " + date + ", " + year + ".");

    System.out.println("Step 3: Print date in standard American and European form");
    System.out.println("American format: ");
    System.out.println(day + ", " + month + " " + date + ", " + year + ".");
    System.out.println("European format: ");
    System.out.println(day + " " + date + " " + month + ", " + year + ".");
  }

}
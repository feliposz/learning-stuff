/* Exercises for Chapter 3 in Think Java */

class NumTest {
  public static void main(String[] args) {
    System.out.println("Testing some double to int casting");
    double d1 = 0.9999999999999999; // = 0.999999999...
    double d2 = 0.99999999999999999; // = 1.0
    int i1 = (int) d1; // = 0
    int i2 = (int) d2; // = 1
    System.out.println(d1);
    System.out.println(i1);
    System.out.println(d2);
    System.out.println(i2);

    System.out.println("Testing operations on doubles");
    double d1_3 = 1.0 / 3.0;
    double d1_0 = d1_3 * 3.0;
    System.out.println(d1_3);
    System.out.println(d1_0);

    System.out.println("Testing rounding and other math functions");
    double x = 3.4;
    double y = 3.6;
    System.out.println((int) x);
    System.out.println((int) y);
    System.out.println(Math.round(x));
    System.out.println(Math.round(y));
    System.out.println(Math.floor(x));
    System.out.println(Math.ceil(x));
    
    System.out.println("Log/exp");
    double l = Math.exp(Math.log(10.0));
    System.out.println(l);

    printSinCosTable();
    printTwice("Test");
    zool(30, "Felipo", "Jd. Sandra");
  }

  private static void printSinCosTable() {
    System.out.printf("ang    sin    cos\n");
    for (double angle = 0.0; angle <= 360.0; angle += 10.0) {
      double sin = Math.sin(Math.toRadians(angle));
      double cos = Math.cos(Math.toRadians(angle));
      System.out.printf("%3.0f %+5.3f %+5.3f\n", angle, sin, cos);
    }
  }

  private static void printTwice(String s) {
    System.out.println(s);
    System.out.println(s);
  }

  private static void zool(int age, String name, String neighbourhood) {
    System.out.print(name);
    System.out.print(", you are ");
    System.out.print(age);
    System.out.print(" years old and you live in ");
    System.out.print(neighbourhood);
    System.out.println(".");
  }

}
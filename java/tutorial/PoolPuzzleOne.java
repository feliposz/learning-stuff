class PoolPuzzleOne {
  public static void main(String[] args) {
    int x = 0;
    while (x < 4) {
      x = x - 2;
      if (x < 1) {
        System.out.print("a ");
      }
      x = x + 1;
      if (x > 1) {
        System.out.print("an");
        System.out.print(" oyster");
      }
      if (x == 1) {
        System.out.print("annoys");
      }
      if (x < 1) {
        System.out.print("noise");
      }
      System.out.println("");
      x = x + 2;
    }
  }
}

/*
x = x + 1;
x = x + 2;
x = x - 2;
x = x - 1;

x > 0
x < 1
x > 3
x < 4

System.out.print("noys ");
System.out.print("oise");
System.out.print(" oyster");
System.out.print("annoys");
System.out.print("noise");

System.out.print(" ");
System.out.print("a ");
System.out.print("n ");
System.out.print("an");



*/
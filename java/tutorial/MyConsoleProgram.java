import java.io.*;

public class MyConsoleProgram {
  private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  
  public static int readInt(String s) throws IOException {
    System.out.print(s);
	return Integer.parseInt(in.readLine());
  }

  public static double readDouble(String s) throws IOException {
    System.out.print(s);
	return Double.parseDouble(in.readLine());
  }
  
  public void run() throws IOException {
    System.out.println("ok");
  }
  
  public static void main(String[] args) throws IOException {
    new MyConsoleProgram().run();
  }
  
}
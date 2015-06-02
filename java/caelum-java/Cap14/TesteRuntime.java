import java.io.InputStream;
import java.io.IOException;

class TesteRuntime {
    public static void main(String args[]) {    
        runCmd("ping www.google.com");
        runCmd("cmd /c dir");
    }
    
    static void runCmd(String cmd) {
        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec(cmd);
            InputStream is = p.getInputStream();
            int i;
            while ((i = is.read()) != -1) {
                System.out.write(i);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
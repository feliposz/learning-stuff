import java.util.Map;

class TesteSystem2 {
    public static void main(String args[]) {

        System.getProperties().list(System.out);

        Map<String, String> env = System.getenv();
        for (Object o : env.keySet().toArray()) {
            String k = (String) o;
            System.out.printf("env[%s] = '%s'\n", k, env.get(k));
        }

        System.out.println("currentTimeMillis = " + System.currentTimeMillis());
        
        System.out.println("nanoTime = " + System.nanoTime());
        
        char[] sep = System.lineSeparator().toCharArray();
        String sepStr = "";
        for (char c : sep) {
            if (c == '\n') {
                sepStr += "\\n";
            } else if (c == '\r') { 
                sepStr += "\\r";
            } else {
                sepStr += c;
            }
        }
        System.out.println("lineSeparator = " + sepStr);
        
    }
}

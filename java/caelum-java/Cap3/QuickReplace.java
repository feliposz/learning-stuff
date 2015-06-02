import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class QuickReplace {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
           System.out.println("Usage: QuickReplace <old> <new> <fileName>");
           return;
        }

        String oldStr = args[0];
        String newStr = args[1];
        String fileName = args[2];

        System.out.println("oldStr = " + oldStr);
        System.out.println("newStr = " + newStr);
        System.out.println("fileName = " + fileName);

        String tmpName = fileName + "_TMP1234";

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tmpName));

        String line;
        while ((line = br.readLine()) != null) {
            line = line.replaceAll(oldStr, newStr);
            bw.write(line + "\n");
        }
        br.close();
        bw.close();

        File oldFile = new File(fileName);
        oldFile.delete();

        File newFile = new File(tmpName);
        newFile.renameTo(oldFile);
    }
}
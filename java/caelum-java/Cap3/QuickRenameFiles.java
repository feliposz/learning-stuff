import java.io.File;
import java.io.IOException;

class QuickRenameFiles {
    public static void main(String[] args) {
        File folder = new File(".");
        File[] listOfFiles = folder.listFiles();
        
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
               String name = listOfFiles[i].getName();
               if (name.startsWith("Cap2") && name.endsWith(".java")) {
                   File f = new File(listOfFiles[i].getName());
                   String newName = name.replace("Cap2", "Cap3");
                   System.out.println("name = " + name + " newName = " + newName);
                   f.renameTo(new File(newName));
               }
            }
        }
    } 
}

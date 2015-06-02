import acm.program.ConsoleProgram;
import java.io.*;
import java.util.*;

public class WordCount extends ConsoleProgram {

	public void run() {		
		String fileName = readLine("File: ");
		ArrayList<String> content = getLinesFromFile(fileName);
		if (content != null) {
			printWordCount(content);
		}
	}

	private ArrayList<String> getLinesFromFile(String fileName) {
		ArrayList<String> content = null; 		
		try {
			BufferedReader rd = new BufferedReader(new FileReader(fileName));	
			content = new ArrayList<String>();
			while (true) {
				String s = null;
				s = rd.readLine();
				if (s == null)
					break;
				content.add(s.trim());
			}
			rd.close();
		} catch (FileNotFoundException ex) {
			println("Couldn't open file " + fileName + ". Error: " + ex.getMessage()); 
		} catch (IOException ex) {
			println("Error reading file " + fileName + ". Error: " + ex.getMessage());
		}		
		return content; 
	}
	
	private void printWordCount(ArrayList<String> content) {
		int chars = 0;
		int words = 0;
		int lines = content.size();
		for (int i = 0; i < lines; i++) {
			chars += content.get(i).length();
			words += countWords(content.get(i));
		}
		println("Lines = " + lines);
		println("Word = " + words);
		println("Characters = " + chars);			
	}
	
	private int countWords(String s) {
		int i = 0;
		int words = 0;
		while (i < s.length()) {
			// word is a sequence of letters or digits
			while (i < s.length() && Character.isLetterOrDigit(s.charAt(i))) {
				i++;
			}
			// non-word character found (separator)
			words++;
			// skip all non-word characters
			while (i < s.length() && !Character.isLetterOrDigit(s.charAt(i))) {
				i++;
			}
		}
		return words;
	}
	
}

import acm.program.ConsoleProgram;
import java.io.*;
import java.util.*;

public class Histogram extends ConsoleProgram {
	
	private ArrayList<Integer> scores; 
	int[] histogram = new int[11];
	
	public void run () {
		readScores();
		buildHistogram();			
		printHistogram();
	}
	
	private void readScores() {
		try {
			Scanner sc;
			sc = new Scanner(new File("MidtermScores.txt"));
			scores = new ArrayList<Integer>();
			while (sc.hasNextInt()) {
				scores.add(sc.nextInt());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void buildHistogram() {
		for (int i = 0; i < scores.size(); i++) {
			int score = scores.get(i);
			histogram[score/10]++;
		}
	}

	private void printHistogram() {
		for (int i = 0; i < histogram.length; i++) {
			int min = i*10;
			int max = (i+1)*10-1;
			String label; 
			if (i == 0) { 
				label = "0" + min + "-0" + max;
			} else if (i < 10) { 
				label = min + "-" + max;
			} else {
				label = "  100";
			}
			println(label + ": " + repeatStr("*", histogram[i]));
		}	
	}
	
	private String repeatStr(String s, int num) {
		String str = "";
		for (int j = 0; j < num; j++) {
			s += ("*");
		}
		return s;
	}

}

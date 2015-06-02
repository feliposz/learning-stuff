import acm.program.*;

/* The problem definition only asks for a simple local calculation.
 * But I decided to implement 3 function versions just to analyse the differences.
 * The local version is fast, has constant space and linear time but lacks reusability.
 * The recursive version is trivial to implement, but has O(fib(n)) space and time complexity.
 * That actually worsens to O(n*fib(n)) because of the main loop.
 * The iterative version has O(n) time and space complexity,
 * but because of the main loop, the program is O(n^2).
 * The table (dynamic programming???) version, despite being implemented in recursive fashion,
 * also has O(n) complexity with O(n^2) for the whole program. 
 */

public class FibonacciSequence extends ConsoleProgram {

	private static final int MAX_TERM_VALUE = 21;
	private int[] fibTable = new int[MAX_TERM_VALUE]; 
	
	int r_calls = 0, i_calls = 0, d_calls = 0;
	
	public void run() {
		initFibTable();
		println("This program lists the Fibonacci sequence.");
		int term = 0;
		int my_fib = 0, next_fib = 1; // local calculation
		
		while (term < MAX_TERM_VALUE) {
			println(my_fib + " " + fib(term) + " " + fib_i(term) + " " + fib_d(term));
			term++;
			
			int tmp = my_fib + next_fib;
			my_fib = next_fib;
			next_fib = tmp;
		}		
		println("R = " + r_calls + " I = " + i_calls + " D = " + d_calls); 
	}	

	// recursive version
	private int fib(int num) {
		r_calls++;
		if (num < 2) {
			return num;
		} else {
			return fib(num-1) + fib(num-2);
		}
	}

	// iterative version
	private int fib_i(int num) {
		i_calls++;
		int a = 0, b = 1;
		for (int i = 0; i < num; i++) {
			int c = a + b;
			a = b;
			b = c;
		}
		return a;
	}

	// recursion with table version	
	private void initFibTable() {
		for (int i = 0; i < fibTable.length; i++) {
			fibTable[i] = -1;
		}
	}
	
	private int fib_d(int num) {
		d_calls++;
		if (fibTable[num] != -1) {
			return fibTable[num];
		} else {			
			if (num < 2) {
				fibTable[num] = num;
				return fibTable[num];
			} else {
				fibTable[num] = fib_d(num-1) + fib_d(num-2);
				return  fibTable[num];
			}
		}
	}

}

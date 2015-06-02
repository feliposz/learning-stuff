/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

	public void init() {
		rgen = RandomGenerator.getInstance();
		lexi = new HangmanLexicon();
		canvas = new HangmanCanvas();
		add(canvas);
	}
	
    public void run() {
		println("Welcome to Hangman!");
		
		String response;
		do {
			playGame();
			response = readLine("Do you want to play again? ").toUpperCase();
		} while (response.equals("Y") || response.equals("YES"));
		println("Bye!");
	}    

    private void playGame() {
    	guessesLeft = INITIAL_GUESSES;
    	chooseRandomWord();
    	makeSecretWord();
    	canvas.reset();
    	canvas.displayWord(secret);
    	playing = true;
    	while (playing) {
    		println("The word now looks like: " + secret);
    		if (guessesLeft > 1) {
    			println("You have " + guessesLeft + " guesses left.");
    		} else {
    			println("You have only one guess left.");
    		}
    		String guess = readLine("Your guess: ").toUpperCase();
    		checkGuess(guess);
    		checkGameState();
    	}
    }
    
    private void chooseRandomWord() {
    	word = lexi.getWord(rgen.nextInt(lexi.getWordCount()));
    }
    
    private void makeSecretWord() {
    	secret = "";
    	for (int i = 0; i < word.length(); i++) {
    		secret += "-";
    	}
    }
    
    private void checkGuess(String guess) {
    	if (guess.equals(word)) { // special case where user tries to guess the whole word
    		secret = word;
    	} else if (guess.length() == 1) { // only single letters are valid guesses
	    	checkLetter(guess.charAt(0));
    	} else {
    		println("'" + guess + "' is not a valid guess. Try again.");
    	}
    }

	private void checkLetter(char guessChar) {
		if (word.indexOf(guessChar) >= 0) {
			String newSecret = "";
			for (int i = 0; i < word.length(); i++) {
				if (word.charAt(i) == guessChar) {
					newSecret += word.charAt(i);
				} else {
					newSecret += secret.charAt(i);
				}
			}
			secret = newSecret;
			println("That guess is correct.");
			canvas.displayWord(secret);
		} else {
			println("There are no " + guessChar + "'s in the word.");
			guessesLeft--;
			canvas.noteIncorrectGuess(guessChar);
		}
	}
    
    private void checkGameState() {
    	if (guessesLeft == 0) {
    		println("You're completely hung.");
    		println("The word was: " + word);
    		canvas.displayWord(word);
    		println("You lose.");
    		playing = false;
    	} else if (secret.equals(word)) {
    		println("You guessed the world: " + word);
    		println("You win.");
    		playing = false;
    	}
    }
    
	private static final int INITIAL_GUESSES = 8;
	
	private RandomGenerator rgen;
	private HangmanLexicon lexi;
	private HangmanCanvas canvas;

	private String word; 
	private String secret;
	private int guessesLeft;
	private boolean playing;
	
}

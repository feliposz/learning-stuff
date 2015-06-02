/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		selectedCategories = new boolean[nPlayers][N_CATEGORIES];
		playerScores = new int[nPlayers][N_SCORES];
		display = new YahtzeeDisplay(getGCanvas(), playerNames);		
		playGame();
	}

	private void playGame() {
		/* You fill this in */
		player = 1;
		while (!isGameOver()) {
			initialRoll();
			additionalRolls();
			selectCategory();
			nextPlayer();
		}
		congratulateWinner();
	}

	// Felipo
	
	private boolean isGameOver() {
		int count = 0;
		for (int i = 0; i < selectedCategories.length; i++) {
			for (int j = 0; j < selectedCategories[i].length; j++) {
				if (selectedCategories[i][j]) {
					count++;
				}
			}
		}
		return count == N_SCORING_CATEGORIES * nPlayers;
	}

	private void initialRoll() {
		display.printMessage(playerNames[player - 1] + "'s turn. Click 'Roll Dice' to roll the dice.");
		display.waitForPlayerToClickRoll(player);
		rollDice();
	}

	private void additionalRolls() {
		for (int rolls = 0; rolls < 2; rolls++) {
			display.printMessage("Select the dice you wish to re-roll and click 'Roll Dice' again.");
			display.waitForPlayerToSelectDice();
			rollSelectedDice();
		}
	}

	private void selectCategory() {
		int category;
		do {
			display.printMessage("Select a category for this roll.");
			category = display.waitForPlayerToSelectCategory();
			/* TEST
			do {
				category = rgen.nextInt(ONES, CHANCE);
			} while (category == UPPER_SCORE || category == UPPER_BONUS);
			*/  
		} while (categoryIsUsed(category));
		markCategoryAsUsed(category);
		//display.printMessage("Category selected = " + category);
		updateScore(category);
	}

	private void nextPlayer() {
		player++;
		if (player > nPlayers) {
			player = 1;
		}
	}

	private void congratulateWinner() {
		int maxScore = 0;
		int winner = 0;
		for (int i = 0; i < nPlayers; i++) {
			if (playerScores[i][TOTAL_INDEX] > maxScore) {
				maxScore = playerScores[i][TOTAL_INDEX];
				winner = i;
			}
		}
		display.printMessage("Contratulations, " + playerNames[winner] +
			" you are the winner with a total score of " + maxScore);
	}
	
	private boolean categoryIsUsed(int category) {
		return selectedCategories[player - 1][category - 1];
	}
	
	private void markCategoryAsUsed(int category) {
		selectedCategories[player - 1][category - 1] = true;
	}

	private void rollDice() {
		for (int i = 0; i < 5; i++) {
			rollSingleDie(i);
		}
		display.displayDice(dice);
	}
	
	private void rollSingleDie(int i) {
		dice[i] = rgen.nextInt(1, 6);	
	}
	
	private void rollSelectedDice() {
		for (int i = 0; i < 5; i++) {
			if (display.isDieSelected(i))
				rollSingleDie(i);
		}
		display.displayDice(dice);
	}
	
	private void updateScore(int category) {
		if (checkCategory(dice, category)) {
			int score = calculateCategoryScore(dice, category); 
			display.updateScorecard(category, player, score);
			
			if (category >= ONES && category <= SIXES) {
				playerScores[player-1][UPPER_INDEX] += score;
				display.updateScorecard(UPPER_SCORE, player, playerScores[player-1][UPPER_INDEX]);
				if (playerScores[player-1][UPPER_INDEX] >= BONUS_THRESHOLD) {
					playerScores[player-1][BONUS_INDEX] = BONUS_SCORE;
					display.updateScorecard(UPPER_BONUS, player, BONUS_SCORE);
				}
			} else if (category >= THREE_OF_A_KIND && category <= CHANCE) {
				playerScores[player-1][LOWER_INDEX] += score;
				display.updateScorecard(LOWER_SCORE, player, playerScores[player-1][LOWER_INDEX]);				
			}
			
			playerScores[player-1][TOTAL_INDEX] = playerScores[player-1][UPPER_INDEX] +
				playerScores[player-1][BONUS_INDEX] + playerScores[player-1][LOWER_INDEX];
			display.updateScorecard(TOTAL, player, playerScores[player-1][TOTAL_INDEX]);
		} else {
			display.updateScorecard(category, player, 0);
		}
	}

	private boolean checkCategory(int[] dice, int category) {
		int count[] = new int[7]; // 0 is unused 
		for (int i = 0; i < dice.length; i++) {
			count[dice[i]]++;
		}
		
		switch (category) {
			case ONES:
			case TWOS:
			case THREES:
			case FOURS:
			case FIVES:
			case SIXES:
			case CHANCE:
				return true;
				
			case THREE_OF_A_KIND:
			case FOUR_OF_A_KIND:
			case YAHTZEE:
				for (int i = 1; i <= 6; i++) {
					if (count[i] >= 5 && category == YAHTZEE) {
						return true;
					}
					if (count[i] >= 4 && category == FOUR_OF_A_KIND) {
						return true;
					}
					if (count[i] >= 3 && category == THREE_OF_A_KIND) {
						return true;
					}
				}
				return false;
			
			case FULL_HOUSE:
				boolean pair = false, trio = false;
				for (int i = 1; i <= 6; i++) {
					if (count[i] == 2) {
						pair = true;
					}
					if (count[i] == 3) {
						trio = true;
					}
				}
				return pair && trio;
				
			case SMALL_STRAIGHT:
				return (count[1] > 0 && count[2] > 0 && count[3] > 0 && count[4] > 0) ||
					   (count[2] > 0 && count[3] > 0 && count[4] > 0 && count[5] > 0) ||
					   (count[3] > 0 && count[4] > 0 && count[5] > 0 && count[6] > 0);
				
				
			case LARGE_STRAIGHT:
				return (count[1] == 1 && count[2] == 1 &&  count[3] == 1 &&	count[4] == 1 && count[5] == 1) ||
					   (count[2] == 1 && count[3] == 1 &&  count[4] == 1 &&	count[5] == 1 && count[6] == 1);
		}
				
		return false;
	}
	
	private int calculateCategoryScore(int[] dice, int category) {
		int score = 0, total = 0;
		int count[] = new int[7]; // 0 is unused 
		for (int i = 0; i < dice.length; i++) {
			count[dice[i]]++;
			total += dice[i];
		}
	
		switch (category) {
			case ONES:
			case TWOS:
			case THREES:
			case FOURS:
			case FIVES:
			case SIXES:
				return count[category] * category;

			case THREE_OF_A_KIND:
			case FOUR_OF_A_KIND:
			case YAHTZEE:
				for (int i = 1; i <= 6; i++) {
					if (count[i] >= 5 && category == YAHTZEE) {
						return 50;
					}
					if (count[i] >= 4 && category == FOUR_OF_A_KIND) {
						return count[i] * i;
					}
					if (count[i] >= 3 && category == THREE_OF_A_KIND) {
						return count[i] * i;
					}
				}
				return 0;
				
			case FULL_HOUSE:
				return 25;
				
			case SMALL_STRAIGHT:
				return 30;
				
			case LARGE_STRAIGHT:
				return 40;
				
			case CHANCE:
				return total;
				
		}
		return 0;
	}
	
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	
	//Felipo
	private int player;
	int[] dice = new int[5];
	boolean[][] selectedCategories;
	private int playerScores[][];
}

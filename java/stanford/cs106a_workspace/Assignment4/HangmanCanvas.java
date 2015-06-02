/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	
	private void setup() {
		if (initialized)
			return;
		
		labelWord = new GLabel("TEST1", getWidth() * 0.20, getHeight() * 0.85);
		labelWord.setFont("Consolas-bold-20");
		add(labelWord);

		labelIncorrect = new GLabel("TEST2", getWidth() * 0.20, getHeight() * 0.90);
		labelIncorrect.setFont("Consolas-bold-20");
		add(labelIncorrect);
		
		initDrawing();
		initialized = true;
	}
	
/** Resets the display so that only the scaffold appears */
	public void reset() {
		setup();
		labelWord.setLabel("");
		labelIncorrect.setLabel("");
		removeAllBodyParts();		
	}


/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		labelWord.setLabel(word);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		labelIncorrect.setLabel(labelIncorrect.getLabel() + letter);
		addBodyPart();
	}

	private void initDrawing() {
		int x = (getWidth() / 2);
		int y = (getHeight() / 2) - (SCAFFOLD_HEIGHT * 5 / 8);
		
		scaffold = new GLine(x - BEAM_LENGTH, y, x - BEAM_LENGTH, y + SCAFFOLD_HEIGHT);
		beam = new GLine(x, y, x - BEAM_LENGTH, y);
		rope = new GLine(x, y, x, y + ROPE_LENGTH);
		add(scaffold);
		add(beam);
		add(rope);
		
		int headY = y + ROPE_LENGTH;
		head = new GOval(x - HEAD_RADIUS, headY, 2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
		
		int bodyY = y + ROPE_LENGTH + HEAD_RADIUS * 2;
		body = new GLine(x, bodyY, x, bodyY + BODY_LENGTH);
		
		int armY = bodyY + ARM_OFFSET_FROM_HEAD;
		int leftArmX = x - UPPER_ARM_LENGTH;
		int rightArmX = x + UPPER_ARM_LENGTH;
		leftUpperArm = new GLine(x, armY, leftArmX, armY);
		rightUpperArm = new GLine(x, armY, rightArmX, armY);
		leftLowerArm = new GLine(leftArmX, armY, leftArmX, armY + LOWER_ARM_LENGTH);
		rightLowerArm = new GLine(rightArmX, armY, rightArmX, armY + LOWER_ARM_LENGTH);
		
		int hipY = bodyY + BODY_LENGTH;
		int footY = hipY + LEG_LENGTH;
		int leftLegX = x - HIP_WIDTH / 2;
		int rightLegX = x + HIP_WIDTH / 2;
		leftHip = new GLine(x, hipY, leftLegX, hipY);
		rightHip = new GLine(x, hipY, rightLegX, hipY);
		leftLeg = new GLine(leftLegX, hipY, leftLegX, footY);
		rightLeg = new GLine(rightLegX, hipY, rightLegX, footY);
		leftFoot = new GLine(leftLegX, footY, leftLegX - FOOT_LENGTH, footY);
		rightFoot = new GLine(rightLegX, footY, rightLegX + FOOT_LENGTH, footY);
	}
	
	private void addBodyPart() {
		bodyPart++;
		
		switch (bodyPart) {
		case 1:
			add(head);
			break;
		case 2:
			add(body);
			break;
		case 3:
			add(leftUpperArm);
			add(leftLowerArm);
			break;
		case 4:
			add(rightUpperArm);
			add(rightLowerArm);
			break;
		case 5:
			add(leftHip);
			add(leftLeg);
			break;
		case 6:
			add(rightHip);
			add(rightLeg);
			break;
		case 7:
			add(leftFoot);
			break;
		case 8:
			add(rightFoot);
			break;
		}
	}

	private void removeAllBodyParts() {
		bodyPart = 0;
		remove(head);
		remove(body);
		remove(leftUpperArm);
		remove(rightUpperArm);
		remove(leftLowerArm);
		remove(rightLowerArm);
		remove(leftHip);
		remove(rightHip);
		remove(leftLeg);
		remove(rightLeg);
		remove(leftFoot);
		remove(rightFoot);
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

	private GLabel labelWord, labelIncorrect;
	private boolean initialized = false;
	private int bodyPart;
	
	private GLine scaffold, beam, rope;
	private GOval head;
	private GLine body;
	private GLine leftUpperArm, leftLowerArm, rightUpperArm, rightLowerArm;
	private GLine leftHip, leftLeg, leftFoot, rightHip, rightLeg, rightFoot ;
}


/*
 * File: Breakout.java
 * -------------------
 * Name: Felipo
 * Section Leader: Myself
 * 
 * This file implements the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** Miliseconds to wait, so the game won't be too fast */
	private static final int DELAY = 10;
		
	private static final int NO_COLLISION = 1;
	private static final int TOP_COLLISION = 1;
	private static final int BOTTOM_COLLISION = 2;
	private static final int LEFT_COLLISION = 3;
	private static final int RIGHT_COLLISION = 4;
	
/** The color of the bricks - by Me*/
	private static Color[] brickColors = {
		Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN
	};

/* Game objects */
	private GLabel score;
	private GRect paddle;
	private GOval ball;
	RandomGenerator rgen = RandomGenerator.getInstance();
	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");

/* Keeps track of ball position and velocity */
	private double vx, vy;
	private double x, y;

/* Game state variables */
	private int bricksLeft;
	private boolean gamePlaying;

/* Check which direction the collision ocurred (check _COLLISION constants above) */	
	private int collisionType;
	
	
/** Runs the Breakout program. */
	public void run() {
		setupGame();
		playGame();
	}
	
	private void setupGame() {
		setupScore();
		setupBricks();
		setupPaddle();
		setupBall();
		resetGame();
		addMouseListeners();
	}
	
	private void playGame() {
		gamePlaying = true;
		while (true) {
			if (gamePlaying) {
				moveBall();
				checkBallCollision();
				pause(DELAY);
			}
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (!gamePlaying) {
			resetGame();
			gamePlaying = true;
		}
	}

	private void resetGame() {
		// reset bricks if last game was won
		if (bricksLeft == 0) {
			setupBricks();
		}
		
		// center ball on the screen
		x = getWidth()/2 - BALL_RADIUS;
		y = getHeight()/2 - BALL_RADIUS;
		ball.setLocation(x, y);

		// TODO: create constants for these values?
		vx = rgen.nextDouble(1.0, 3.0);
		vy = 3.0;
		if (rgen.nextBoolean(0.5)) vx = -vx;
		
		score.setColor(Color.BLACK);
		score.setLabel("Bricks left: " + bricksLeft);
	}

	private void setupScore() {
		score = new GLabel("");
		score.setLocation(0, getHeight() - score.getAscent());
		score.setFont("SansSerif-bold-16");
		add(score);
	}

	private void setupBricks() {
		for (int i = 0; i < NBRICK_ROWS; i++) {
			for (int j = 0; j < NBRICKS_PER_ROW; j++) {
				int x = j * (BRICK_WIDTH + BRICK_SEP);
				int y = i * (BRICK_HEIGHT + BRICK_SEP) + BRICK_Y_OFFSET; 
				GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				int colorIndex = (i/2) % brickColors.length;
				brick.setColor(brickColors[colorIndex]);
				add(brick);
			}
		}
		bricksLeft = NBRICK_ROWS * NBRICKS_PER_ROW;
	}
	
	private void setupPaddle() {
		paddle = new GRect(
				(getWidth() - PADDLE_WIDTH) / 2,
				getHeight() - PADDLE_Y_OFFSET,
				PADDLE_WIDTH,
				PADDLE_HEIGHT
			);
		paddle.setFilled(true);
		add(paddle);
	}
	
	public void mouseMoved(MouseEvent e) {
		// mouse locked to the center of the paddle
		int x = e.getX() - PADDLE_WIDTH/2;
		// limit the movement of the paddle to the screen width
		if (x < 0)
			x = 0;
		else if (x > getWidth() - PADDLE_WIDTH)
			x = getWidth() - PADDLE_WIDTH;
		paddle.setLocation(x, paddle.getY());
	}
	
	private void setupBall() {
		ball = new GOval(2*BALL_RADIUS, 2*BALL_RADIUS);
		ball.setFilled(true);
		add(ball);
	}	
	
	private void moveBall() {
		x += vx;
		y += vy;
		checkWallCollision();
		ball.setLocation(x, y);
	}
	
	private void checkWallCollision() {
		if (ball.getX() < 0) {
			x = 0;
			vx = -vx;
			bounceClip.play();
		}
		if (ball.getX() + 2*BALL_RADIUS > getWidth()) {
			x = getWidth() - 2*BALL_RADIUS;
			vx = -vx;
			bounceClip.play();
		}
		if (ball.getY() < 0) {
			y = 0;
			vy = -vy;		
			bounceClip.play();
		}
		if (ball.getY() + 2*BALL_RADIUS > getHeight()) {
			score.setColor(Color.RED);
			score.setLabel("You lose! Click to continue.");
			gamePlaying = false;
		}
	}

	private void checkBallCollision() {
		GObject collider = getCollidingObject();
		if (collider != null && collider != score) {
			// only considering vertical collisions for simplification
			y -= vy;
			vy = -vy;
			if (collider == paddle) {
				handlePaddleCollision();
				bounceClip.play();
			} else {
				removeBrick(collider);
				bounceClip.play();
			}
		}
	}
	
	private GObject getCollidingObject() {
		GObject obj;
		
		obj = getElementAt(x, y);
		if (obj != null) { 
			return obj;
		}

		obj = getElementAt(x, y + 2*BALL_RADIUS);
		if (obj != null) { 
			return obj;
		}
		
		obj = getElementAt(x + 2*BALL_RADIUS, y);
		if (obj != null) { 
			return obj;
		}

		obj = getElementAt(x + 2*BALL_RADIUS, y + 2*BALL_RADIUS);		
		if (obj != null) { 
			return obj;
		}

		return null;
	}

	//NOTE: not using this now...
	private GObject getCollidingObjectEdges() {
		GObject obj;
		
		obj = getElementAt(x + BALL_RADIUS, y - 1);
		if (obj != null) { 
			collisionType = TOP_COLLISION;
			return obj;
		}
		
		obj = getElementAt(x + BALL_RADIUS, y + 2*BALL_RADIUS + 1);
		if (obj != null) { 
			collisionType = BOTTOM_COLLISION;
			return obj;
		}
		
		obj = getElementAt(x - 1, y + BALL_RADIUS);
		if (obj != null) { 
			collisionType = LEFT_COLLISION;
			return obj;
		}
		
		obj = getElementAt(x + 2*BALL_RADIUS + 1, y + BALL_RADIUS);		
		if (obj != null) { 
			collisionType = RIGHT_COLLISION;
			return obj;
		}

		collisionType = NO_COLLISION;
		return null;
	}
	
	private void handlePaddleCollision() {
		vx = ((ball.getX() - paddle.getX() + BALL_RADIUS) - PADDLE_WIDTH/2) / PADDLE_WIDTH * 6.0;
		// to avoid the ball getting "sticked" to the paddle
		y = getHeight() - PADDLE_Y_OFFSET - 2*BALL_RADIUS;
		// increase vertical velocity a little bit every bounce
		vy -= 0.05;
	}

	private void removeBrick(GObject collider) {
		remove(collider);
		bricksLeft--;
		if (bricksLeft > 0) {
			score.setColor(Color.BLACK);
			score.setLabel("Bricks left: " + bricksLeft);
		} else {
			gamePlaying = false;
			score.setColor(Color.BLUE);
			score.setLabel("You win! Click to restart.");
		}
	}

}

/*
 * File: UfoGame.java
 * ------------------
 * This program plays a game where the user tries to
 * shoot a UFO before the UFO "lands".
 */
import acm.program.*;
import acm.graphics.*;
import java.awt.*;
import java.awt.event.*;

public class UfoGame extends GraphicsProgram {

	/** Size and speed of UFO */
	private static final int UFO_WIDTH = 40;
	private static final int UFO_HEIGHT = UFO_WIDTH / 2;
	private static final int UFO_SPEED = 5;
	
	/** Size and speed of bullets */
	private static final int BULLET_SPEED = 10;
	private static final int BULLET_SIZE = 5;

	/** Animation cycle delay */
	private static final int DELAY = 20;
	
	/* private instance variables */
	private GRect ufo;
	private GOval bullet;
	private boolean ufoToLeft;
	
	public void run() {
		setup();
		while (!gameOver()) {
			moveUfo();
			moveBullet();
			checkCollisions();
			pause(DELAY);
		}
	}
	
	/** setup UFO and add mouse listeners */
	private void setup() {
		ufo = new GRect(0, 0, UFO_WIDTH, UFO_HEIGHT);
		add(ufo);
		ufoToLeft = false;
		addMouseListeners();
	}
	
	/** determines if game is over -- true if either
     * the UFO is destroyed or if the UFO lands */
	private boolean gameOver() {
		return (ufo == null) || ((ufo.getY() + UFO_HEIGHT) >= getHeight());
	}
	
	/** when mouse is clicked create bullet, unless a bullet
 	 * already exists.
	 */
	public void mouseClicked(MouseEvent e) {
		if (bullet == null) {
			bullet = new GOval(BULLET_SIZE, BULLET_SIZE);
			bullet.setFilled(true);
			bullet.setColor(Color.RED);
			add(bullet, (getWidth() - BULLET_SIZE) / 2,
					getHeight() - BULLET_SIZE);
		}
	}
	
	/** moves UFO, if UFO has moved to edge of screen, moves
	 * UFO down and changes UFO direction.
	 */
	private void moveUfo() {
		if (ufoToLeft) {
			ufo.move(-UFO_SPEED, 0);
			if (ufo.getX() <= 0) {
				ufoToLeft = false;
				ufo.move(0, UFO_HEIGHT);
			}
		} else {
			ufo.move(UFO_SPEED, 0);			
			if (ufo.getX() + UFO_WIDTH >= getWidth()) {
				ufoToLeft = true;
				ufo.move(0, UFO_HEIGHT);
			}
		}
	}
	
	/** moves bullet */
	private void moveBullet() {
		if (bullet != null) {
			bullet.move(0, -BULLET_SPEED);
		}
	}
	
	/** checks for bullet interaction with the world
	 * (either colliding with the UFO or moving offscreen
	 */
	private void checkCollisions() {
			moveOffScreen();
			collideWithUfo();
	}
	
	/** checks to see if UFO and bullet collide, if so
 	 * bullet and UFO are removed and both variables are
	 * set to null.
	 */
	private void collideWithUfo() {
		if (bullet != null) {
			if (bullet.getBounds().intersects(ufo.getBounds())) {
				remove(bullet);
				bullet = null;
				remove(ufo);
				ufo = null;
			}
		}
	}
	
	/** determines if bullet has moved of screen,
	 * if it has, removes bullet, sets variable to null
	 */
	private void moveOffScreen() {
		if (bullet != null) {
			if (bullet.getY() <= 0) {
				remove(bullet);
				bullet = null;
			}
		}
	}
}

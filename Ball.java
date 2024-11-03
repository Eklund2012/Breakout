import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Ball extends Sprite {
	private int max = Const.BALL_ANGLEMAX, min = Const.BALL_ANGLEMIN;
	private int directiony = Const.BALL_SPEED, directionx = 0;
	private int life = Const.LIFES;
	private int score = 0;
	private boolean gamewon = false; // tillstånd för att hantera om man vunnit eller förlorat
	private boolean gamelost = false;
	private boolean gamestate = false;
	private boolean gamestate2 = false;

	public Ball() {
		super(Const.BALL_STARTX, Const.BALL_STARTY, Const.BALLSIZE, Const.BALLSIZE);
	}

	@Override
	public void update(Keyboard keyboard) {
		setY(getY() + directiony);
		setX(getX() - directionx);
		if (getY() < 0) {// kollision med övre kant
			directiony = -directiony;
		} else if (getX() < 0 || getX() > Const.GAMEBOARD_X - Const.BALLX) { // kollision med sidokanter
			directionx = -directionx;
		} else if (getY() > Const.GAMEBOARD_Y && life != 0) { // om bollen åker utfanför undre kant minskas life med ett
			life--;
			if (life > 0 && gamewon != true) { // efter man förlorat ett liv och man fortfarande har liv kvar sätts
												// bollen tillbaka till startposition
				setY(Const.MIDDLE_Y);
				setX(Const.MIDDLE_X);
				directionx = (int) Math.floor(Math.random() * (max - min + 1) + min);
			}
		}
		if ((gamelost == true || gamewon == true) && keyboard.isKeyDown(Key.Space)) {// om man förlorat och klickar på
																						// space startar spelet om
			setLife(Const.LIFES);
		}
	}

	@Override
	public void draw(Graphics2D graphics) {
		if (life == 0 && gamewon != true) {// om man förlorar alla liv
			graphics.setColor(Color.RED);
			graphics.setFont(new Font("TimesRoman", Font.PLAIN, Const.LOST_SIZE));
			graphics.drawString("Game over!", Const.MIDDLE_Y, Const.MIDDLE_X);
			graphics.setFont(new Font("TimesRoman", Font.PLAIN, Const.LOST_SIZE - Const.BRICK_WIDTH));
			graphics.drawString("Press Space to restart", Const.MIDDLE_Y, Const.MIDDLE_X + Const.TEXT_POS);
			gamelost = true;
		} else if (gamewon) {// om gameWon metoden har kallats från BrickCollection betyder det att alla
								// bricks är borta "score" och "you won" visas på skärmen
			setY(Const.GAMEBOARD_X + Const.BRICK_WIDTH);
			setX(Const.GAMEBOARD_Y + Const.BRICK_WIDTH);
			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font("TimesRoman", Font.PLAIN, Const.WON_SIZE));
			graphics.drawString("You won!", Const.MIDDLE_Y, Const.MIDDLE_X);
			graphics.drawString("Score: " + score, Const.MIDDLE_Y, Const.SCORE_Y);
			graphics.setFont(new Font("TimesRoman", Font.PLAIN, Const.LOST_SIZE - Const.BRICK_WIDTH));
			graphics.drawString("Press Space to restart", Const.MIDDLE_Y, Const.MIDDLE_X + Const.TEXT_POS);
		}
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("TimesRoman", Font.BOLD, Const.SCORE_SIZE));
		graphics.drawString("Life: " + life + " Score: " + score, Const.SCORE_SIZE, Const.SCORE_SIZE + 1);
		graphics.setColor(Color.WHITE);
		graphics.fillOval(getX(), getY(), getWidth(), getHeight());
	}

	public void collidePlayer(Keyboard keyboard) {// kollision med spelare kan styras åt vilket håll bollen studsar med
													// right och left
		if (keyboard.isKeyDown(Key.Right)) {
			directiony = -directiony;
			directionx = -Const.BALL_ANGLE;
		} else if (keyboard.isKeyDown(Key.Left)) {
			directiony = -directiony;
			directionx = +Const.BALL_ANGLE;
		} else {
			directiony = -directiony;
		}
	}

	public void collideBrick(int point) {// kollision med brick anropas från BrickCollection point som inparameter för
											// att öka score
		directiony = -directiony;
		score += point;
	}

	public void setLife(int y) { // startar om spelet genom att nollställa score och sätta life till y
		setY(Const.BALL_STARTY);
		setX(Const.BALL_STARTX);
		directiony = Const.BALL_SPEED;
		this.life = y;
		score = 0;
		gamelost = false;
		gamewon = false;
		gamestate = false;
		gamestate2 = false;
	}

	public void gameWon() {// om alla bricks är borta
		gamewon = true;
	}

	// setters och getters för tillstånd
	public boolean gameLost() {
		return gamelost;
	}

	public int getScore() {
		return score;
	}

	public boolean getGameWon() {
		return gamewon;
	}

	public void setGameWon(Boolean x) {
		gamewon = x;
	}

	public void setGameState(Boolean x) {
		gamestate = x;
	}

	public boolean getGameState() {
		return gamestate;
	}

	public void setGameState2(Boolean x) {
		gamestate2 = x;
	}

	public boolean getGameState2() {
		return gamestate2;
	}

}

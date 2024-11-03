import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends Sprite implements ICollidable {
	public Player() {
		super(Const.MIDDLE_X - Const.PLAYER_HEIGHT, Const.PLAYER_Y, Const.PLAYER_WIDTH, Const.PLAYER_HEIGHT);
	}

	@Override
	public void update(Keyboard keyboard) {
		if (keyboard.isKeyDown(Key.Right) && getX() < Const.GAMEBOARD_X - getWidth()) {// updatering av vänster höger
																						// med right och left
			setX(getX() + Const.PLAYER_ANGLE);
		} else if (keyboard.isKeyDown(Key.Left) && getX() > 0) {
			setX(getX() - Const.PLAYER_ANGLE);
		}
	}

	@Override
	public void draw(Graphics2D graphics) { // uppritandet av slagträt med fillRect
		graphics.setColor(Color.WHITE);
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	public boolean isColliding(Keyboard keyboard, Ball ball) { // från interface ICollidable uppdateras i Game.java
		Rectangle playerRect = new Rectangle(getX(), getY(), getWidth(), getHeight());
		Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
		if (playerRect.intersects(ballRect)) { // om bollen och slagträt kolliderar kallas metoden collidePlayer i
												// klassen ball
			ball.collidePlayer(keyboard);
		}
		return false;
	}
}

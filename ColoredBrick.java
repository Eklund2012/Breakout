import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ColoredBrick extends Sprite implements ICollidable {
	private Color color;

	public ColoredBrick(int x, int y, Color color) {
		super(x, y, Const.BRICK_WIDTH, Const.BRICK_HEIGHT);
		this.color = color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public void update(Keyboard keyboard) {

	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(color);
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	public boolean isColliding(Keyboard keyboard, Ball ball) { //Interface för kollidering från ICollidable
		Rectangle brickRect = new Rectangle(getX(), getY(), getWidth(), getHeight());
		Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
		return brickRect.intersects(ballRect); // returnerar sant om brick och bollen kolliderar
	}
}

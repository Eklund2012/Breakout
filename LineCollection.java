import java.awt.Color;
import java.awt.Graphics2D;

public class LineCollection extends Sprite{

	public LineCollection(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(Color.red);
		graphics.drawLine(getY(), getX(), getWidth(), getHeight());		
	}
	@Override
	public void update(Keyboard keyboard) {}
}

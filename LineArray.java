import java.awt.Graphics2D;
import java.util.ArrayList;

public class LineArray {
	private ArrayList<LineCollection> lines;
	public LineArray() {
		lines = new ArrayList<LineCollection>();	
		lines.add(new LineCollection(0, 0, Const.GAMEBOARD_X, 0));
		lines.add(new LineCollection(0, 0, 0, Const.GAMEBOARD_Y));
		lines.add(new LineCollection(0, Const.GAMEBOARD_X - 1, Const.GAMEBOARD_X - 1, Const.GAMEBOARD_Y));
	}
	public void draw(Graphics2D graphics) {
		for(LineCollection line: lines) {
			line.draw(graphics);
		}
	}
}


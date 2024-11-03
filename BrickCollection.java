import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class BrickCollection {
	private ArrayList<ColoredBrick> allbricks;
	private int count = 0;
	private int ycord = Const.BRICK_STARTY;

	public BrickCollection() {
		allbricks = new ArrayList<ColoredBrick>();
		while (count < Const.BRICK_AMOUNT) {// skickar x och y kordinater
			int choose = (int) Math.floor(Math.random() * (Const.RED_POINTS) + 1); // random tal mellan 1 - 3 bestämmer vilken färg som brickan ska ha
			if (choose == Const.GREEN_POINTS)
				allbricks.add(new ColoredBrick(Const.BRICK_XDIST * (count % Const.BRICK_PER_ROW) + Const.PLAYER_HEIGHT, //anropar ColoreBricks konstruktor skickar med x och y kordinat samt färg
						ycord, Color.GREEN));
			else if (choose == Const.ORANGE_POINTS) {
				allbricks.add(new ColoredBrick(Const.BRICK_XDIST * (count % Const.BRICK_PER_ROW) + Const.PLAYER_HEIGHT,
						ycord, Color.ORANGE));
			} else if (choose == Const.RED_POINTS) {
				allbricks.add(new ColoredBrick(Const.BRICK_XDIST * (count % Const.BRICK_PER_ROW) + Const.PLAYER_HEIGHT,
						ycord, Color.RED));
			}
			count++;
			if (count % Const.BRICK_PER_ROW == 0 && count != 0) { // efter 13 bricks minskar y kordinaten för nästa rad
																	// av bricks
				ycord -= Const.BRICK_YDIST;
			}
		}
	}

	public void draw(Graphics2D graphics) { //en forEach loop som ritar upp alla brickor metoden som anropas ligger i klasses ColoredBrick
		for (ColoredBrick brick : allbricks) {
			brick.draw(graphics);
		}
	}

	public void remove() { //tar bort alla brickor för att starta om spelet vid vinst eller förlust
		for (int i = 0; i < allbricks.size(); i++) {
			allbricks.remove(i);
		}
	}

	public void update(Keyboard keyboard, Ball ball) {
		if (allbricks.size() == 0) {// kollar om alla bricks är borta (Const.BRICK_AMOUNT för att kolla gamewon i
									// början)
			ball.gameWon();
		}
		for (ColoredBrick brick : allbricks) { // kollar vilken färg varje brick som kollideras har för att veta poäng
												// och om den ska tas bort.
			if (brick.isColliding(keyboard, ball) && brick.getColor() == Color.GREEN) {
				ball.collideBrick(Const.GREEN_POINTS);
				allbricks.remove(brick);
				break;
			} else if (brick.isColliding(keyboard, ball) && brick.getColor() == Color.ORANGE) {
				ball.collideBrick(Const.ORANGE_POINTS);
				brick.setColor(Color.GREEN);
				break;
			} else if (brick.isColliding(keyboard, ball) && brick.getColor() == Color.RED) {
				ball.collideBrick(Const.RED_POINTS);
				brick.setColor(Color.ORANGE);
				break;
			}
		}
	}
}
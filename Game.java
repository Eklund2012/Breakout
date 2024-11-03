import java.awt.Graphics2D;

public class Game {
	private Ball ball;
	private Player player;
	private LineArray linearray;
	private BrickCollection brickcollection;
	private Fonster fonster;

	public Game(GameBoard board) {
		linearray = new LineArray();// konturer av gameboard
		player = new Player();// slagträt
		brickcollection = new BrickCollection();// brickor
		ball = new Ball();// bollen
		fonster = new Fonster();// JFrame för highscore och latest runs
	}

	public void update(Keyboard keyboard) {
		player.update(keyboard);
		player.isColliding(keyboard, ball);
		ball.update(keyboard);
		brickcollection.update(keyboard, ball);
		if (ball.gameLost() == true && ball.getGameState() == false) {// om man förlorat eller vunnit kallas higscore
																		// och latest runs i fonster

			ball.setGameState(true);
			fonster.addInFonster(ball.getScore());
			fonster.addHighscore(ball.getScore());
		}
		if (ball.getGameWon() == true && ball.getGameState() == false) {
			ball.setGameState(true);
			fonster.addInFonster(ball.getScore());
			fonster.addHighscore(ball.getScore());
		}
	}

	public void draw(Graphics2D graphics) {
		ball.draw(graphics);
		linearray.draw(graphics);
		player.draw(graphics);
		brickcollection.draw(graphics);
		if (ball.gameLost() == true && ball.getGameState2() == false) {// om man förlorat eller vunnit tas brickorna
																		// bort och sedan skapas nya för att kunna spela
																		// igen
			ball.setGameState2(true);
			brickcollection.remove();
			brickcollection = new BrickCollection();
		}
		if (ball.getGameWon() == true && ball.getGameState2() == false) {
			ball.setGameState2(true);
			brickcollection.remove();
			brickcollection = new BrickCollection();
		}
	}
}

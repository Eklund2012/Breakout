import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

public class Fonster extends JFrame {
	private static final long serialVersionUID = 1L;
	private LatestRuns minListaLeft, indexList;
	private HighscoreList minListaRight;
	private JList<Object> guiListaLeft, guiListaRight, indexLeft;
	private JLabel text2Label;
	private int count = 0;

	public Fonster() {
		super("Latest runs & Highscores");
		minListaLeft = new LatestRuns();
		guiListaLeft = new JList<Object>(minListaLeft.getList());
		text2Label = new JLabel();
		minListaRight = new HighscoreList();
		guiListaRight = new JList<Object>(minListaRight.getList());
		indexList = new LatestRuns();
		indexLeft = new JList<Object>(indexList.getList());

		setLayout(new BorderLayout());
		setSize(Const.WINDOW_X, Const.WINDOW_Y);
		setLocation(Const.GAMEBOARD_X, 0);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		guiListaLeft.setFocusable(false);
		text2Label.setFocusable(false);
		guiListaRight.setFocusable(false);
		for (int i = Const.LIST_SIZE; i > 0; i--) {
			indexList.add(i + ". ");
		}

		add(text2Label, BorderLayout.NORTH);
		add(indexLeft, BorderLayout.WEST);
		add(guiListaLeft, BorderLayout.EAST);
		add(guiListaRight, BorderLayout.CENTER);

		indexLeft.setBackground(Color.WHITE);
		indexLeft.setForeground(Color.BLACK);
		indexLeft.setFont(new Font("Arial", Font.BOLD, Const.BALLSIZE));

		text2Label.setFont(new Font("Arial", Font.BOLD, Const.BRICK_HEIGHT));
		text2Label.setText("Highscores     Latest runs");

		guiListaLeft.setBackground(Color.black);
		guiListaLeft.setForeground(Color.YELLOW);
		guiListaLeft.setFont(new Font("Arial", Font.BOLD, Const.BALLSIZE));

		guiListaRight.setBackground(Color.black);
		guiListaRight.setForeground(Color.YELLOW);
		guiListaRight.setFont(new Font("Arial", Font.BOLD, Const.BALLSIZE));
	}

	public void addInFonster(Object anElement) {// lägger till recent runns tar bort när listan blir större än 3
		minListaLeft.add(anElement);
		if (count > Const.LATEST_MAX) {
			minListaLeft.remove(Const.LATEST_MAX + 1);
		}
		count++;
	}

	public void addHighscore(int anElement) {// lägger till highscore
		minListaRight.add(anElement);
	}
}

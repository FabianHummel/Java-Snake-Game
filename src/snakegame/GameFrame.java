package snakegame;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameFrame extends JFrame {
	
	public GameFrame() {
		this.add(new GamePanel());
		this.setTitle("Snake");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}

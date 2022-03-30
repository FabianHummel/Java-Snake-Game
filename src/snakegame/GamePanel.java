package snakegame;
import javax.swing.JPanel;
import javax.swing.Timer;

import snakegame.library.JoinedList;
import snakegame.features.apples.Apple;
import snakegame.features.apples.AppleManager;
import snakegame.snake.*;
import snakegame.util.MoveDirection;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class GamePanel extends JPanel implements ActionListener {

	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 400;
	public static final int UNIT_SIZE = 25;

	public static final int GAME_WIDTH = SCREEN_WIDTH / UNIT_SIZE;
	public static final int GAME_HEIGHT = SCREEN_HEIGHT / UNIT_SIZE;

	public static final int TILE_PADDING = 5;

	private static final int DELAY = 130;

	private static final int START_APPLES = 2;

	private static boolean running;

	private List<Snake> allSnakes;
	private Snake thisSnake;
	private AppleManager apples;
	private Timer timer;

	public static GamePanel gp;

	public GamePanel() {
		gp = this;
		this.setPreferredSize(
			new Dimension(
				SCREEN_WIDTH, SCREEN_HEIGHT
			)
		);
		this.setBackground(new Color(30, 31, 33));
		this.setFocusable(true);

		gameStart();
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				SnakeTile head = getSnake().getHead();
				switch (e.getKeyCode()) {
					case KeyEvent.VK_W -> {
						if (head.getDirection() != MoveDirection.UP.inverseDirection())
							head.setDirection(MoveDirection.UP);
					}
					case KeyEvent.VK_A -> {
						if (head.getDirection() != MoveDirection.LEFT.inverseDirection())
							head.setDirection(MoveDirection.LEFT);
					}
					case KeyEvent.VK_S -> {
						if (head.getDirection() != MoveDirection.DOWN.inverseDirection())
							head.setDirection(MoveDirection.DOWN);
					}
					case KeyEvent.VK_D -> {
						if (head.getDirection() != MoveDirection.RIGHT.inverseDirection())
							head.setDirection(MoveDirection.RIGHT);
					}
				}
			}
		});

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			gameLoop();
			repaint();
		}
	}

	public static void stopGame() {
		running = false;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawGame(g);
	}

	public void drawGame(Graphics g) {

//		HERE: Game Grid
		g.setColor(new Color(255, 255, 255, 16));
		for (int i = 0; i < GAME_WIDTH; i++)
			g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
		
		for (int i = 0; i < GAME_HEIGHT; i++)
			g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);

//		HERE: Snake
		allSnakes.forEach(
			snake -> {
				g.setColor(snake.getColor());
				snake.forEach(
					tile -> tile.drawRect(g)
				);
			}
		);

//		HERE: Apples
		g.setColor(new Color(255, 94, 94));
		apples.getApples().forEach(
			apple -> apple.drawOval(g)
		);
	}

	public void gameStart() {

		apples = new AppleManager();
		for (int i = 0; i < START_APPLES; i++) {
			apples.spawnRndApple();
		}


		// HERE: Initialize Snake
		thisSnake = new Snake(
			5,
			(SCREEN_WIDTH / UNIT_SIZE) / 2,
			(SCREEN_HEIGHT / UNIT_SIZE) / 2,
			new Color(200, 200, 200)
		);

		allSnakes = new ArrayList<>();
		allSnakes.add(
			thisSnake
		);

		// HERE: Initialize Game status and event updates
		running = true;

		if (timer != null) {
			timer.stop();
		}
		timer = new Timer(DELAY, this);
		timer.start();
	}

	public void gameLoop() {
		allSnakes.forEach(
			snake -> {

//				Movement and rotation
				snake.forContraryJoin(
					tile -> {
						tile.getElement().move();

						if (!tile.getElement().isHead()) {
							tile.getElement().setDirection(
								tile.getPrev().getElement().getDirection()
							);
						}
					}
				);

//				Collision
				SnakeTile head = snake.getHead();
				if (head.getX() < 0 || head.getX() >= SCREEN_WIDTH / UNIT_SIZE || head.getY() < 0 || head.getY() >= SCREEN_HEIGHT / UNIT_SIZE) {
					snake.collide();
				}

				snake.forEach(
					tile -> {
						if (!tile.isHead())
							if (tile.getX() == head.getX() && tile.getY() == head.getY()) {
								snake.collide();
							}
					}
				);

				AtomicReference<Apple> remove = new AtomicReference<>(null);
				apples.getApples().forEach(
					apple -> {
						if (apple.getX() == head.getX() && apple.getY() == head.getY()) {
							remove.set(apple);
							getSnake().appendTile();
						}
					}
				);

				if (remove.get() != null) { // Apple has been eaten
					apples.removeRefApple(remove.get());
					apples.spawnRndApple();
				}
			}
		);
	}

	public Snake getSnake() {
		return thisSnake;
	}
}

package snakegame.snake;

import java.awt.*;

import com.fabii.joinedlist.JoinedList;
import snakegame.GamePanel;
import snakegame.util.MoveDirection;

public class Snake extends JoinedList<SnakeTile> {
	private final int startX, startY;
	private final Color color;

	public Snake(int startLength, int startX, int startY, Color color) {
		this.startX = startX;
		this.startY = startY;
		this.color = color;

		for (int i = 0; i < startLength; i++)
			appendTile();
	}

	public SnakeTile getHead() {
		return filter(
			snakeTile -> snakeTile.isHead()
		).getFirst();
	}

	public Color getColor() {
		return color;
	}

	public void appendTile() {
		if(isEmpty())
			append(new SnakeHead(
				startX, startY,
				MoveDirection.RIGHT
			));

		else {
			SnakeTile last = getLastElement();
			SnakeTile tile = new SnakeBody(
				last.getX(),
				last.getY(),
				last.getDirection()
			);

			switch (last.getDirection().inverseDirection()) {
				case UP -> tile.setY(last.getY() - 1);
				case DOWN -> tile.setY(last.getY() + 1);
				case RIGHT -> tile.setX(last.getX() + 1);
				case LEFT -> tile.setX(last.getX() - 1);
			}

			append(
				tile
			);
		}
	}

	public SnakeTile getLastElement() {
		return get(size() - 1);
	}

	public void collide() {
		System.out.println("Collided");
		GamePanel.stopGame();
	}
}

package snakegame.snake;

import snakegame.core.Tile;
import snakegame.util.MoveDirection;

public abstract class SnakeTile extends Tile {
	private MoveDirection direction;

	protected SnakeTile() {
		super(0, 0);
		this.direction = MoveDirection.RIGHT;
	}

	protected SnakeTile(int x, int y, MoveDirection d) {
		super(x, y);
		this.direction = d;
	}

	public void move() {
		switch (direction) {
			case UP -> setY(getY() - 1);
			case DOWN -> setY(getY() + 1);
			case LEFT -> setX(getX() - 1);
			case RIGHT -> setX(getX() + 1);
		}
	}

	public void setDirection(MoveDirection dir) {
		this.direction = dir;
	}

	public MoveDirection getDirection() {
		return this.direction;
	}

	public boolean isHead() {
		return this instanceof SnakeHead;
	}

	@Override
	public String toString() {
		return "SnakeTile: " +
			"\n    direction= " + direction +
			"\n    posX= " + getX() +
			"\n    posY= " + getY() +
			"\n    head?= " + isHead();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof SnakeTile snakeTile))
			return false;

		return snakeTile.getX() == getX() && snakeTile.getY() == getY();
	}
}
package snakegame.snake;

import snakegame.util.MoveDirection;

public class SnakeBody extends SnakeTile {

	public SnakeBody() {
		super();
	}
	
	public SnakeBody(int x, int y, MoveDirection d) {
		super(x, y, d);
	}
}

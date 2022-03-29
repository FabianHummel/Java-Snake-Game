package snakegame.util;

public enum MoveDirection {
	UP,
	DOWN,
	LEFT,
	RIGHT;

	public MoveDirection inverseDirection() {
		return switch (this) {
			case UP -> DOWN;
			case DOWN -> UP;
			case RIGHT -> LEFT;
			default -> RIGHT;
		};
	}
}
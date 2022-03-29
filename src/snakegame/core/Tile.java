package snakegame.core;

import snakegame.GamePanel;

import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

public abstract class Tile implements Serializable {
	private int x, y;

	protected Tile() {
		// Nothing to see here
	}

	protected Tile(int x, int y) {
		setX(x);
		setY(y);
	}

	public void setX(int c) {
		this.x = c;
	}

	public int getX() {
		return this.x;
	}

	public void setY(int c) {
		this.y = c;
	}

	public int getY() {
		return this.y;
	}

	public void setPos(int x, int y) {
		setX(x);
		setY(y);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Tile tile = (Tile) o;
		return getX() == tile.getX() && getY() == tile.getY();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getX(), getY());
	}

	public void drawRect(Graphics g) {
		g.drawRect(
			getX() * GamePanel.UNIT_SIZE + GamePanel.TILE_PADDING,
			getY() * GamePanel.UNIT_SIZE + GamePanel.TILE_PADDING,
			GamePanel.UNIT_SIZE - GamePanel.TILE_PADDING * 2,
			GamePanel.UNIT_SIZE - GamePanel.TILE_PADDING * 2
		);
	}

	public void drawOval(Graphics g) {
		g.fillOval(
			getX() * GamePanel.UNIT_SIZE + GamePanel.TILE_PADDING,
			getY() * GamePanel.UNIT_SIZE + GamePanel.TILE_PADDING,
			GamePanel.UNIT_SIZE - GamePanel.TILE_PADDING * 2,
			GamePanel.UNIT_SIZE - GamePanel.TILE_PADDING * 2
		);
	}
}

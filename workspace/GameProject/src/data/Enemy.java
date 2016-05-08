package data;

import org.newdawn.slick.opengl.Texture;
import static helpers.Artist.*;
import static helpers.Clock.*;

import java.util.ArrayList;

public class Enemy implements Entity {
	private int width, height, currentCheckpoint;
	private float speed, x, y, health, startHealth;
	private Texture texture, healthBack, healthFore, healthBorder;
	private Tile startTile;
	private boolean first, alive;
	private TileGrid grid;
	
	private ArrayList<CheckPoint> checkPoints;
	private int[] direction;

	public Enemy(Texture texture, Tile startTile, TileGrid grid, int width, int height, float speed, float health) {
		this.texture = texture;
		this.healthBack = quickLoad("healthback");
		this.healthFore = quickLoad("healthfore");
		this.healthBorder = quickLoad("healthborder");
		this.startTile = startTile;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = width;
		this.height = height;
		this.health = health;
		this.startHealth = health;
		this.speed = speed;
		this.grid = grid;
		this.first = true;
		this.alive = true;

		this.checkPoints = new ArrayList<CheckPoint>();
		this.direction = new int[2];
		// X direction
		this.direction[0] = 0;
		// Y direction
		this.direction[1] = 0;
		direction = findNextD(startTile);
		this.currentCheckpoint = 0;
		populateCheckpointList();
	}

	public void update() {
		//check if it the first time this class is update
		if (first) {
			first = false;
		} else {
			// if(pathContinue())
			if (checkpointReached()) {
				//check if there are more checkpoint before moving
				if (currentCheckpoint + 1 == checkPoints.size())
					endOfMazeReach();
				else
					currentCheckpoint++;
			} else {
				//if not a checkpoint continue the direction
				x += delta() * checkPoints.get(currentCheckpoint).getxDriection() * speed;
				y += delta() * checkPoints.get(currentCheckpoint).getyDirection() * speed;

			}
			// x += delta() * direction[0];
			// y += delta() * direction[1];

		}
	}
	
	private void endOfMazeReach(){
		Player.modifyLives(-1);
		die();
	}

	private boolean checkpointReached() {
		boolean reached = false;
		Tile t = checkPoints.get(currentCheckpoint).getTile();
		// check if position reached tile within variance of 3
		if (x < t.getX() + 3 && x > t.getX() - 3 && y < t.getY() + 3 && y > t.getY() - 3) {
			reached = true;
			x = t.getX();
			y = t.getY();
		}
		return reached;
	}

	private void populateCheckpointList() {
		//Add first checkpoint manually based on startTIle
		checkPoints.add(findNextC(startTile, direction = findNextD(startTile)));

		int counter = 0;
		boolean cont = true;
		while (cont) {
			int[] currentD = findNextD(checkPoints.get(counter).getTile());
			// check if next direction/checkpoint exists, end after 20
			// checkpoints
			if (currentD[0] == 2 || counter == 20) {
				cont = false;
			} else {
				checkPoints.add(findNextC(checkPoints.get(counter).getTile(),
						direction = findNextD(checkPoints.get(counter).getTile())));
			}
			counter++;
		}
	}

	private CheckPoint findNextC(Tile s, int[] dir) {
		Tile next = null;
		CheckPoint c = null;

		// boolean to decide if next checkpoint is found
		boolean found = false;

		// increment each loop
		int counter = 1;

		while (!found) {

			if (s.getXPlace() + dir[0] * counter == grid.getTileWidth()
					|| s.getYPlace() + dir[1] * counter == grid.getTileHeight()) {
				found = true;
				// move counter back 1 to find tile before new tiletype
				counter -= 1;
				next = grid.getTile(s.getXPlace() + dir[0] * counter, s.getYPlace() + dir[1] * counter);
			} else if (s.getXPlace() + dir[0] * counter == grid.getTileWidth()
					|| s.getYPlace() + dir[1] * counter == grid.getTileHeight() || s.getType() != grid
							.getTile(s.getXPlace() + dir[0] * counter, s.getYPlace() + dir[1] * counter).getType()) {

				found = true;
				// move counter back 1 to find tile before new tiletype
				counter -= 1;
				next = grid.getTile(s.getXPlace() + dir[0] * counter, s.getYPlace() + dir[1] * counter);
			}

			c = new CheckPoint(next, dir[0], dir[1]);
			counter++;
		}

		return c;
	}

	private int[] findNextD(Tile s) {
		int[] dir = new int[2];

		Tile up = grid.getTile(s.getXPlace(), s.getYPlace() - 1);
		Tile right = grid.getTile(s.getXPlace() + 1, s.getYPlace());
		Tile down = grid.getTile(s.getXPlace(), s.getYPlace() + 1);
		Tile left = grid.getTile(s.getXPlace() - 1, s.getYPlace());

		//check if current inhabited tiletpe match tile above, down , right or left
		if (s.getType() == up.getType() && direction[1] != 1) {
			dir[0] = 0;
			dir[1] = -1;
		} else if (s.getType() == right.getType() && direction[0] != -1) {
			dir[0] = 1;
			dir[1] = 0;
		} else if (s.getType() == down.getType() && direction[1] != -1) {
			dir[0] = 0;
			dir[1] = 1;
		} else if (s.getType() == left.getType() && direction[0] != 1) {
			dir[0] = -1;
			dir[1] = 0;
		} else {
			dir[0] = 2;
			dir[1] = 2;
			// System.out.println("No direction found");
		}

		return dir;
	}

	//take damge from external source
	public void damage(int amount) {
		health -= amount;
		if (health < 0) {
			die();
			Player.modifyCash(5);
		}
	}

	private void die() {
		alive = false;
	}

	public void draw() {
		float healthPercentage = health / startHealth;
		//enemy texture
		drawQuadTex(texture, x, y, width, height);
		//health bar texture
		drawQuadTex(healthBack, x, y - 16, width, 8);
		drawQuadTex(healthFore, x, y - 16, TILE_SIZE * healthPercentage, 8);
		drawQuadTex(healthBorder, x, y - 16, width, 8);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Tile getStartTile() {
		return startTile;
	}

	public void setStartTile(Tile startTile) {
		this.startTile = startTile;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public TileGrid getTileGrid() {
		return this.grid;
	}

	public boolean isAlive() {
		return alive;
	}
}
package data;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import helpers.Clock;

import static helpers.Artist.*;

import java.util.ArrayList;

public class Player {
	private TileGrid grid;
	private WaveManager waveManager;
	private ArrayList<Tower> towerList;
	private boolean leftMouseButtonDown, rightMouseButtonDown, holdingTower;
	public static int cash, lives;
	private Tower tempTower;

	public Player(TileGrid grid, WaveManager waveManager) {
		this.grid = grid;
		this.waveManager = waveManager;
		this.towerList = new ArrayList<Tower>();
		this.leftMouseButtonDown = false;
		this.rightMouseButtonDown = false;
		this.holdingTower = false;
		this.tempTower = null;
		cash = 0;
		lives = 0;
	}

	public void setup() {
		cash = 550;
		lives = 10;
	}

	//check if player have enough money to build tower
	public static boolean modifyCash(int amount) {
		if (cash + amount >= 0) {
			cash += amount;
			System.out.println(cash);
			return true;
		}
		System.out.println(cash);
		return false;
	}

	public static void modifyLives(int amount) {
		lives += amount;
		System.out.println(lives);
	}

	public void update() {

		// update holding tower
		if (holdingTower) {
			tempTower.setX(getMouseTile().getX());
			tempTower.setY(getMouseTile().getY());
			tempTower.draw();
		}
		// update all tower in the game
		for (Tower t : towerList) {
			t.update();
			t.draw();
			t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
		}

		// Handle Mouse Input
		if (Mouse.isButtonDown(0) && !leftMouseButtonDown) {
			placeTower();
		}

		if (Mouse.isButtonDown(1) && !rightMouseButtonDown) {
			System.out.println("Right Mouse Clicked");
		}

		leftMouseButtonDown = Mouse.isButtonDown(0);
		rightMouseButtonDown = Mouse.isButtonDown(1);

		// Handle KeyBoard input
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {

				Clock.changeMultiplier(0.2f);
			}

			if (Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState()) {
				Clock.changeMultiplier(-0.2f);
			}

		}
	}

	private void placeTower() {
		Tile currentTile = getMouseTile();
		if (holdingTower) {
			if (  !currentTile.getOccupied() && modifyCash(-tempTower.getCost())) {
				towerList.add(tempTower);
				currentTile.setOccupied(true);
				holdingTower = false;
				tempTower = null;
			}
		}
		
	}

	public void pickTower(Tower t) {
		tempTower = t;
		holdingTower = true;
	}

	private Tile getMouseTile() {
		return grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE);

	}
}

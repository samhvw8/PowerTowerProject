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
	private boolean leftMouseButtonDown, rightMouseButtonDown;
	public static int cash, lives;

	public Player(TileGrid grid, WaveManager waveManager) {
		this.grid = grid;
		this.waveManager = waveManager;
		this.towerList = new ArrayList<Tower>();
		this.leftMouseButtonDown = false;
		this.rightMouseButtonDown = false;
		cash = 0;
		lives = 0;
	}

	public void setup() {
		cash = 550;
		lives = 10;
	}

	public static boolean modifyCash(int amount) {
		if (cash + amount >= 0) {
			cash += amount;
			return true;
		}
		return false;
	}

	public static void modifyLives(int amount) {
		lives += amount;
	}
	

	public void update() {
		//update all tower in the game
		for (Tower t : towerList) {
			t.update();
			t.draw();
			t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
		}

		// Handle Mouse Input
		if (Mouse.isButtonDown(0) && !leftMouseButtonDown) {
			if (modifyCash(-25)) {
				towerList.add(new TowerBlue(TowerType.BlueTower,
						grid.getTile(Mouse.getX() / 64, (HEIGHT - Mouse.getY() - 1) / 64),
						waveManager.getCurrentWave().getEnemyList()));

			}
		}

		if (Mouse.isButtonDown(1) && !rightMouseButtonDown) {
			if (modifyCash(-55)) {
				towerList.add(new TowerIce(TowerType.IceTower,
						grid.getTile(Mouse.getX() / 64, (HEIGHT - Mouse.getY() - 1) / 64),
						waveManager.getCurrentWave().getEnemyList()));

			}
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

}

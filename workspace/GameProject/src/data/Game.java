package data;

import static helpers.Artist.quickLoad;
import static helpers.Artist.drawQuadTex;
import org.lwjgl.input.Mouse;

import UI.Button;
import UI.UI;

import static helpers.Artist.*;

public class Game {

	// hold every component of the game
	private TileGrid grid;
	private Player player;
	private WaveManager waveManager;
	private UI towerPickerUI;
	// private TowerBlue blue;

	// private float test;
	// // temp variable
	// TowerCannon tower;

	public Game(int[][] map) {
		grid = new TileGrid(map);

		waveManager = new WaveManager(
				new Enemy(quickLoad("Ufo"), grid.getTile(2, 9), grid, TILE_SIZE, TILE_SIZE, 70, 25), 2, 10);
		player = new Player(grid, waveManager);
		player.setup();
		setupUI();

	}

	private void setupUI() {
		towerPickerUI = new UI();
//		towerPickerUI.addButton("TowerBlue", "bluetower", 0, 0);
//		towerPickerUI.addButton("TowerIce", "tower", TILE_SIZE, 0);
		towerPickerUI.createMenu("TowerPicker",TILE_SIZE*16,0,2,0);
		towerPickerUI.getMenu("TowerPicker").addButton(new Button("TowerBlue",quickLoad("bluetower"),0,0));
		towerPickerUI.getMenu("TowerPicker").addButton(new Button("TowerIce",quickLoad("tower"),0,0));
		towerPickerUI.getMenu("TowerPicker").addButton(new Button("TowerBlue",quickLoad("bluetower"),0,0));
		towerPickerUI.getMenu("TowerPicker").addButton(new Button("TowerIce",quickLoad("tower"),0,0));
		towerPickerUI.getMenu("TowerPicker").addButton(new Button("TowerBlue",quickLoad("bluetower"),0,0));
		towerPickerUI.getMenu("TowerPicker").addButton(new Button("TowerIce",quickLoad("tower"),0,0));

	}

	private void updateUI() {
		towerPickerUI.draw();
		if (Mouse.next()) {
			boolean mouseClicked = Mouse.isButtonDown(0);
			if (mouseClicked) {
				if (towerPickerUI.getMenu("TowerPicker").isButtonClick("TowerBlue")) {
					player.pickTower(new TowerBlue(TowerType.BlueTower, grid.getTile(0, 0),
							waveManager.getCurrentWave().getEnemyList()));
				}
				if (towerPickerUI.getMenu("TowerPicker").isButtonClick("TowerIce")) {
					player.pickTower(new TowerIce(TowerType.IceTower, grid.getTile(0, 0),
							waveManager.getCurrentWave().getEnemyList()));
				}
			}
		}
	}

	public void update() {
		drawQuadTex(quickLoad("menu_background"),TILE_SIZE*16,0,128,960);
		grid.draw();
		waveManager.update();
		player.update();
		updateUI();

	}

}

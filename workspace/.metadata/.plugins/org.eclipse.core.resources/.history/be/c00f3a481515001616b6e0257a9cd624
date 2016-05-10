package data;

import static helpers.Artist.quickLoad;
import static helpers.Artist.*;

public class Game {

	// hold every component of the game
	private TileGrid grid;
	private Player player;
	private WaveManager waveManager;
	// private TowerBlue blue;

	// private float test;
	// // temp variable
	// TowerCannon tower;

	public Game(int[][] map) {
		grid = new TileGrid(map);

		waveManager = new WaveManager(
				new Enemy(quickLoad("Ufo"), grid.getTile(2, 9), grid, TILE_SIZE, TILE_SIZE, 70, 25), 2, 2);
		player = new Player(grid, waveManager);
		player.setup();

	}

	public void update() {
		grid.draw();

		waveManager.update();
		player.update();

	}

}

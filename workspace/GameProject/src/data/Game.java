package data;

import UI.UI;
import UI.UI.Menu;
import static helpers.Artist.*;
import helpers.StateManager;
import helpers.StateManager.GameState;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

public class Game {

    // hold every component of the game
    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;
    private UI gameUI;
    private Menu towerPickerMenu, controllerMenu;
    // private TowerBlue blue;

    // private float test;
    // // temp variable
    // TowerCannon tower;
    public Game(TileGrid grid) {
        this.grid = grid;

        waveManager = new WaveManager(
                new Enemy(quickLoad("Ufo"), grid.getTile(2, 9), grid, TILE_SIZE, TILE_SIZE, 70, 25), 2, 10);
        player = new Player(grid, waveManager);
        player.setup();
        setupUI();

    }

    private void setupUI() {
        gameUI = new UI();
        gameUI.createMenu("TowerPicker", TILE_SIZE * 16, 128, 128, 128, 2, 0);
        gameUI.createMenu("Controller", TILE_SIZE * 16, 500, 128, 128, 2, 0);
        towerPickerMenu = gameUI.getMenu("TowerPicker");
        controllerMenu = gameUI.getMenu("Controller");
        towerPickerMenu.quickAdd("TowerBlue", "bluetower");
        towerPickerMenu.quickAdd("TowerIce", "tower");
        controllerMenu.quickAdd("Pause", "pause");
        controllerMenu.quickAdd("Stop", "stop");
        coin = quickLoad("coin");
        heart = quickLoad("heart");
    }

    private void updateUI() {
        // Show cash
        // Number
        drawYellowText(TILE_SIZE * 17 - TILE_SIZE / 2, 350, Integer.toString(player.cash));
        // Image
        drawQuadTex(coin, TILE_SIZE * 17 - TILE_SIZE / 2 + 43, 350, 35, 35);

        int heartOnLine = 4;
        for (int i = 0, printedHeart = 0; i < player.lives / heartOnLine + 1; i++) {
            for (int j = 0; j < heartOnLine && printedHeart < player.lives; j++) {
                drawQuadTex(heart, TILE_SIZE * 16 + 30 * j + 6, 250 + i * 26, 30, 20);
                printedHeart++;
            }
        }

        gameUI.draw();

        if(player.lives < 0) {
            StateManager.setState(GameState.DEAD);
        }
        
        if (Mouse.next()) {
            boolean mouseClicked = Mouse.isButtonDown(0);
            if (mouseClicked) {
                if (towerPickerMenu.isButtonClick("TowerBlue")) {
                    player.pickTower(new TowerBlue(TowerType.BlueTower, grid.getTile(0, 0),
                            waveManager.getCurrentWave().getEnemyList()));
                }
                if (towerPickerMenu.isButtonClick("TowerIce")) {
                    player.pickTower(new TowerIce(TowerType.IceTower, grid.getTile(0, 0),
                            waveManager.getCurrentWave().getEnemyList()));
                }
                if (controllerMenu.isButtonClick("Pause")) {
                    StateManager.setState(GameState.PAUSE);
                }
                if (controllerMenu.isButtonClick("Stop")) {
                    StateManager.setState(GameState.MAINMENU);
                    waveManager = new WaveManager(
                            new Enemy(quickLoad("Ufo"), grid.getTile(2, 9), grid, TILE_SIZE, TILE_SIZE, 70, 25), 2, 10);
                    player = new Player(grid, waveManager);
                    player.setup();
                }
            }
        }
        
    }

    public void update() {
        drawQuadTex(quickLoad("tower_menu"), TILE_SIZE * 16, 0, 128, 960);
        grid.draw();
        waveManager.update();
        player.update();
        updateUI();
    }
}

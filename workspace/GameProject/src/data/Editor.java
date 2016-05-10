package data;

import static helpers.Artist.HEIGHT;
import static helpers.Artist.TILE_SIZE;
import static helpers.Artist.drawQuadTex;
import static helpers.Artist.quickLoad;
import static helpers.Leveler.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import UI.UI;
import UI.UI.Menu;
import helpers.StateManager;

public class Editor {

    private TileGrid grid;
    private int index;
    private TileType[] types;
    private UI editorUI;
    private Menu tilePickerMenu, controllerMenu;

    public Editor() {
        this.grid = loadMap("newMap1");
        this.index = 0;
        this.types = new TileType[3];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.Moutain;
        this.types[2] = TileType.Water;
        setupUI();
    }

    private void setupUI() {
        editorUI = new UI();
        editorUI.createMenu("TilePicker", TILE_SIZE * 16, 0, 128, 0, 2, 0);
        editorUI.createMenu("Controller", TILE_SIZE * 16, 500, 128, 128, 1, 0);
        tilePickerMenu = editorUI.getMenu("TilePicker");
        controllerMenu = editorUI.getMenu("Controller");
        tilePickerMenu.quickAdd("Grass", "grass");
        tilePickerMenu.quickAdd("Mount", "moutain");
        tilePickerMenu.quickAdd("Water", "water");
        controllerMenu.quickAdd("Save", "save");
    }

    public void update() {

        draw();
        // Handle Mouse Input
        if (Mouse.next()) {
            boolean mouseClicked = Mouse.isButtonDown(0);
            if (mouseClicked) {
                if (tilePickerMenu.isButtonClick("Grass")) {
                    index = 0;
                } else if (tilePickerMenu.isButtonClick("Mount")) {
                    index = 1;
                } else if (tilePickerMenu.isButtonClick("Water")) {
                    index = 2;
                } else if (controllerMenu.isButtonClick("Save")) {
                    saveMap("newMap1", grid);
                    StateManager.game = null;
                    StateManager.setMap(null);
                    StateManager.setState(StateManager.GameState.MAINMENU);
                } else {
                    setTile();
                }

            }
        }

        // Handle KeyBoard input
        while (Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()) {
                moveIndex();
            }

            if (Keyboard.getEventKey() == Keyboard.KEY_S && Keyboard.getEventKeyState()) {
                saveMap("newMap1", grid);

            }
        }
    }

    private void draw() {
        drawQuadTex(quickLoad("menu_background"), TILE_SIZE * 16, 0, 128, 960);
        grid.draw();
        editorUI.draw();
    }

    private void setTile() {
        grid.setTile((int) Math.floor(Mouse.getX() / 64), (int) Math.floor((HEIGHT - Mouse.getY() - 1) / 64),
                types[index]);
    }

    // allow editor to change which TileType is selected
    private void moveIndex() {
        index++;
        if (index > types.length - 1) {
            index = 0;
        }
    }
}

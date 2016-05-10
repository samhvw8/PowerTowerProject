package helpers;

import data.Dead;
import data.Editor;
import data.Game;
import data.MainMenu;
import data.Pause;
import data.TileGrid;
import static helpers.Leveler.*;

public class StateManager {

    /**
     * @param aMap the map to set
     */
    public static enum GameState {
        MAINMENU, GAME, EDITOR, PAUSE, DEAD;
    }

    public static GameState gameState = GameState.MAINMENU;
    public static MainMenu mainMenu;
    public static Game game;
    public static Editor editor;
    public static Pause pause;
    public static Dead dead;

    private static TileGrid map = loadMap("newMap1");

    public static void update() {
        switch (gameState) {
            case MAINMENU:
                if (mainMenu == null) {
                    mainMenu = new MainMenu();
                }
                mainMenu.update();
                break;
            case GAME:
                if (game == null) {
                    if (map == null) {
                        setMap(loadMap("newMap1"));
                    }
                    game = new Game(map);
                }
                game.update();
                break;
            case EDITOR:
                if (editor == null) {
                    editor = new Editor();
                }
                editor.update();
                break;
            case PAUSE:
                if (pause == null) {
                    pause = new Pause();
                }
                pause.update();
                break;
            case DEAD:
                if (dead == null) {
                    dead = new Dead();
                }
                dead.update();
                break;                
        }
    }

    public static void setState(GameState newState) {
        gameState = newState;
    }

    public static void setMap(TileGrid aMap) {
        map = aMap;
    }
}

package data;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import UI.UI;
import helpers.StateManager;

import helpers.StateManager.GameState;
import static helpers.Artist.*;

public class Dead {

    private Texture background;
    private UI menuUI;

    public Dead() {
        background = quickLoad("menu");
        menuUI = new UI();
        menuUI.addButton("Again", "again", WIDTH / 2 - 128, (int) (HEIGHT * 0.45f));
        menuUI.addButton("Menu", "menuB", WIDTH / 2 - 128, (int) (HEIGHT * 0.65f));
        
    }

    //check if button clicked
    private void updateButtons() {
        if (Mouse.isButtonDown(0)) {
            if (menuUI.isButtonClick("Again")) {
                
                StateManager.game = null;
                
                StateManager.setState(GameState.GAME);
                
            }
            if (menuUI.isButtonClick("Menu")) {
                StateManager.game = null;
                StateManager.setState(GameState.MAINMENU);
                
            }
        }
    }

    public void update() {
        drawQuadTex(background, 0, 0, 1850, 960);
        menuUI.draw();
        updateButtons();        
        drawYellowText((int) WIDTH / 2 - 50, (int) (HEIGHT * 0.35f), "You Dead");
    }

}

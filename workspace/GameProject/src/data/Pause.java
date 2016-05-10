package data;

import UI.UI;
import static helpers.Artist.*;
import helpers.StateManager;
import helpers.StateManager.GameState;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

public class Pause {

    private Texture background;
    private UI menuUI;

    public Pause() {
        
        background = quickLoad("menu");
        menuUI = new UI();
        menuUI.addButton("Continue", "continue", WIDTH / 2 - 128, (int) (HEIGHT * 0.45f));
        menuUI.addButton("Quit", "quit", WIDTH / 2 - 128, (int) (HEIGHT * 0.65f));
    }

    //check if button clicked
    private void updateButtons() {
        if (Mouse.isButtonDown(0)) {
            if (menuUI.isButtonClick("Continue")) {
//				System.out.println("Continue button click");
                
                StateManager.setState(GameState.GAME);
            }
            if (menuUI.isButtonClick("Quit")) {
                System.exit(0);
            }
        }
    }

    public void update() {
        drawQuadTex(background, 0, 0, 1850, 960);
        menuUI.draw();
        updateButtons();
    }

}

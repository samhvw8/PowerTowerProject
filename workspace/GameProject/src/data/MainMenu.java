package data;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import UI.UI;
//import UI.Button;
import helpers.StateManager;
import helpers.StateManager.GameState;

import static helpers.Artist.*;

public class MainMenu {
	
	private Texture background;
	private UI menuUI;
	
	public MainMenu(){
		background = quickLoad("mainmenu");
		menuUI = new UI();
		menuUI.addButton("Play", "play", WIDTH/2-128, (int) (HEIGHT*0.45f));
		menuUI.addButton("Editor", "editor", WIDTH/2 - 128, (int)(HEIGHT*0.65f));
		menuUI.addButton("Quit", "quit", WIDTH/2 - 128, (int)(HEIGHT*0.85f));
	}
	
	//check if button clicked
	private void updateButtons(){
		if(Mouse.isButtonDown(0)){
			if(menuUI.isButtonClick("Play")){
//				System.out.println("play button click");
				StateManager.setState(GameState.GAME);
			}
			if(menuUI.isButtonClick("Editor")){
				StateManager.setState(GameState.EDITOR);
			}
			if(menuUI.isButtonClick("Quit")){
				System.exit(0);
			}
		}
	}
	
	public void update(){
		drawQuadTex(background,0,0,1600,960);
		menuUI.draw();
		updateButtons();
	}

}

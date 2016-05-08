package data;

import static helpers.Artist.beginSession;
import org.lwjgl.opengl.Display;
import helpers.Clock;
import helpers.StateManager;

public class Boot {

	public Boot() {

		// call static method in Artist class to initialize openGL call
		beginSession();

		//Main Game loop
		while (!Display.isCloseRequested()) {
			Clock.update();
			StateManager.update();
			Display.update();
			Display.sync(60);
		}

		Display.destroy();
	}

	public static void main(String[] args) {
		new Boot();
	}
}

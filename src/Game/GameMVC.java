package Game;

import Controller.GameController;
import Model.GameModel;
import View.GameView;

public class GameMVC {
	public GameMVC() {
		
	}
	
	public void start() {
		GameModel gameModel = new GameModel();			
		GameView gameView = new GameView(gameModel);	//GUI of the game 
		GameController gameController = new GameController(gameModel, gameView);
		gameController.play();
	}
}

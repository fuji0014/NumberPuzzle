package Game;

import Controller.GameController;
import Model.GameModel;
import View.GameView;

/**
 * Class name: GameMVC
 * Methods list: start
 * Constant list: N/A
 * Purpose: GameMVC class contains the action listener for all the action taken by the user
 * @author Amy Fujimoto
 * @version 03 Dev 2022
 * @see javax.swing
 * @since JavaSE-17
 */
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

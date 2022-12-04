package Game;

import Controller.GameController;
import Model.GameModel;
import View.GameView;

/**
 * Class name: GameMVC
 * Methods list: start
 * Constant list: N/A
 * Purpose: GameMVC class contains method to start the numpuz game
 * @author Amy Fujimoto
 * @version 03 Dec 2022
 * @see javax.swing
 * @since JavaSE-17
 */
public class GameMVC {
	/**
	 * Default constructor
	 */
	public GameMVC() {		
	}
	
	/**
	 * Method name: start
	 * Purpose: Starts the game by instantiating Game objects
	 * Algorithm: Initialized objects and calls method to start mvc game
	 */
	public void start() {
		GameModel gameModel = new GameModel();			
		GameView gameView = new GameView(gameModel);	//GUI of the game 
		GameController gameController = new GameController(gameModel, gameView);
		gameController.play();
	}
}

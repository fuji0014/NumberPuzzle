package Model;

import Game.GameMVC;
import Game.GameServer;
import Game.GameClient;

/**
 * Class name: MainModel
 * Methods list: gameMode, getter and setter
 * Constant list: N/A
 * Purpose: MainModel class sets all the GUI for the main screen using Swing 
 * @author Amy Fujimoto
 * @version 04 Dec 2022
 * @see javax.swing
 * @since JavaSE-17
 */
public class MainModel {
	/**
	 * variable for storing game mode
	 */
	private String gameMode;
	/**
	 * GameMVC object
	 */
	private GameMVC mvc;
	/**
	 * GameServer object
	 */
	private GameServer server;
	/**
	 * GameClient object
	 */
	private GameClient client;
	
	/**
	 * Method name: Overloaded constructor 
	 * Purpose: Initializes game model object
	 */
	public MainModel() {
		mvc = new GameMVC();
		server = new GameServer();
		client = new GameClient();
		gameMode = "Standalone";
	}
	
	/**
	 * Method name: gameMode
	 * Purpose: Starts the game, server or client frames depending on game mode
	 */
	public void gameMode() {
		switch(gameMode) {
		case "Standalone":
			mvc.start();
			break;
		case "Server":
			server.start();
			break;
		case "Client":
			client.start();
			break;
		}
	}
	
	/**
	 * Method name: getGameMode
	 * Purpose: Return the gameMode
	 * @return gameMode in String form
	 */
	public String getGameMode() {
		return this.gameMode;
	}
	
	/**
	 * Method name: setGameModel
	 * Purpose: Sets gameMode 
	 * @param gameMode String 
	 */
	public void setGameModel(String gameMode) {
		this.gameMode = gameMode;
	}
	
}

package Model;

import Controller.GameController;
import View.GameView;
import Game.GameBasic;

/**
 * Class name: ClientModel
 * Methods list: startGame, getter and setter
 * Constant list: N/A
 * Purpose: ClientModel class is the model for the NumPuz game and stores the data for the client
 * @author Amy Fujimoto
 * @version 04 Dec 2022
 * @see javax.swing
 * @since JavaSE-17
 */
public class ClientModel {
	/**
	 * Username of the client
	 */
	private String user;
	/**
	 * server used by client
	 */
	private String server;
	/**
	 * port used by the client
	 */
	private String port;
	/**
	 * data sent and received by client to play game
	 */
	private String clientData;
	/**
	 * client id in integer
	 */
	private int id;
	
	/**
	 * GameModel object
	 */
	private GameModel gameModel;			
	/**
	 * GameView object
	 */
	private GameView gameView;	
	/**
	 * GameController object
	 */
	private GameController gameController;
	/**
	 * GameBasic object
	 */
	private GameBasic gameBasic;
	
	/**
	 * Method name: overloaded constructor 
	 * Purpose: Initializes default/starting values for the game and starts the client frames
	 */
	public ClientModel(){
		user = "Amy";
		server = "localhost";
		port = "2000";
		clientData = "";
		
		gameModel = new GameModel();			
		gameView = new GameView(gameModel);	
		gameController = new GameController(gameModel, gameView);
	}
	
	/**
	 * Method name: startGame 
	 * Purpose: sets the game setting with current data and triggers the game
	 */
	public void startGame() {
		gameView.setGameController(gameController);
		gameController.play(gameBasic);
	}
	
	/**
	 * Method name: getGameBasic
	 * Purpose: Return the JDialog object
	 * @return gameBasic object
	 */
	public GameBasic getGameBasic() {
		gameBasic = gameView.getGameBasic();
		return gameBasic;		
	}
	
	/**
	 * Method name: setGameBasic
	 * Purpose: Sets gameBasic
	 * @param gameBasic object
	 */
	public void setGameBasic(GameBasic gameBasic) {
		this.gameBasic = gameBasic;
	}
	
	/**
	 * Method name: getUser
	 * Purpose: Return the username entered by the user
	 * @return user in String form
	 */
	public String getUser() {
		return user;
	}
	
	/**
	 * Method name: setUser
	 * Purpose: Sets username to class variable
	 * @param user String that stores the text of user input
	 */
	public void setUser(String user) {
		this.user = user;
	}
	
	/**
	 * Method name: getServerName
	 * Purpose: Return the server entered by the user
	 * @return server in String form
	 */
	public String getServerName() {
		return server;
	}
	
	/**
	 * Method name: setServerName
	 * Purpose: Sets server to class variable
	 * @param server String that stores the text of user input
	 */
	public void setServerName(String server) {
		this.server = server;
	}
	
	/**
	 * Method name: getPort
	 * Purpose: Return the port entered by the user
	 * @return port in String form
	 */
	public String getPort() {
		return port;
	}
	
	/**
	 * Method name: setPort
	 * Purpose: Sets port to class variable
	 * @param port String that stores the text of user input
	 */
	public void setPort(String port) {
		this.port = port;
	}
	
	/**
	 * Method name: getId
	 * Purpose: Sets client id
	 * @return id in integer
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Method name: setId
	 * Purpose: Sets client id to class variable
	 * @param id integer 
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Method name: getClientData
	 * Purpose: Return the clientData from server
	 * @return clientData in String form
	 */
	public String getClientData() {
		return clientData;
	}
	
	/**
	 * Method name: setClientData
	 * Purpose: Sets clientData to class variable
	 * @param clientData String that stores the game data
	 */
	public void setClientData(String clientData) {
		this.clientData = clientData;
	}
	
	/**
	 * Method name: getModelData
	 * Purpose: Return the gameModel
	 * @return gameModel object
	 */
	public GameModel getModelData() {
		return gameModel;
	}
	
	/**
	 * Method name: setModelData
	 * Purpose: Sets modelData to class variable
	 * @param modelData object
	 */
	public void setModelData(GameModel modelData) {
		this.gameModel = modelData;
	}
	
}

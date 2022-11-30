package Model;

import Controller.GameController;
import View.GameView;

public class ClientModel {
	String user;
	String server;
	String port;
	int id;
	String clientData;
	
	GameModel gameModel;			
	GameView gameView;	
	GameController gameController;
	
	public ClientModel(){
		user = "Amy";
		server = "localhost";
		port = "2000";
		clientData = "";
		
		gameModel = new GameModel();			
		gameView = new GameView(gameModel);	
		gameController = new GameController(gameModel, gameView);
	}
	
	public void initializeGame() {
		//initialize here (ex. size, text, etc)
	}
	
	public void startGame() {
		gameController.play();
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getServerName() {
		return server;
	}
	
	public void setServerName(String server) {
		this.server = server;
	}
	
	public String getPort() {
		return port;
	}
	
	public void setPort(String port) {
		this.port = port;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getClientData() {
		return clientData;
	}
	
	public void setClientData(String clientData) {
		this.clientData = clientData;
	}
	
	public GameModel getModelData() {
		return gameModel;
	}
	
	public void setModelData(GameModel modelData) {
		this.gameModel = modelData;
	}
	
	
}

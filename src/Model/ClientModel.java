package Model;

import Controller.GameController;
import View.GameView;
import Game.GameBasic;

public class ClientModel {
	private String user;
	private String server;
	private String port;
	private String clientData;
	private int id;
	
	private GameModel gameModel;			
	private GameView gameView;	
	private GameController gameController;
	private GameBasic gameBasic;
	
	public ClientModel(){
		user = "Amy";
		server = "localhost";
		port = "2000";
		clientData = "";
		
		gameModel = new GameModel();			
		gameView = new GameView(gameModel);	
		gameController = new GameController(gameModel, gameView);
	}
	
	public void startGame() {
		gameView.setGameController(gameController);
		gameController.play(gameBasic);
	}
	
	public GameBasic getGameBasic() {
		gameBasic = gameView.getGameBasic();
		return gameBasic;		
	}
	
	public void setGameBasic(GameBasic gameBasic) {
		this.gameBasic = gameBasic;
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

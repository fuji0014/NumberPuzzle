package Game;

import Controller.ServerController;
import Model.ServerModel;
import View.ServerView;

public class GameServer {

	ServerModel serverModel;			
	ServerView serverView;	
	ServerController serverController;
	
	public GameServer(){}
	
	public void start() {
		serverModel = new ServerModel();			
		serverView = new ServerView(serverModel);	
		serverController = new ServerController(serverModel, serverView);
	}
}

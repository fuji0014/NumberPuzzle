package Model;

import Game.GameMVC;
import Game.GameServer;
import Game.GameClient;

public class MainModel {
	String gameMode;
	GameMVC mvc;
	GameServer server;
	GameClient client;
	
	public MainModel() {
		mvc = new GameMVC();
		server = new GameServer();
		client = new GameClient();
		gameMode = "Standalone";
	}
	
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
	
	public String getGameMode() {
		return this.gameMode;
	}
	
	public void setGameModel(String gameMode) {
		this.gameMode = gameMode;
	}
	
}

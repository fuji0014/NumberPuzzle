package Game;

import Controller.MainController;
import Model.MainModel;
import View.MainView;

public class Game {
	MainModel mainModel;			
	MainView mainView;	
	MainController main;
	
	public Game(){}
	
	public void start() {
		mainModel = new MainModel();			
		mainView = new MainView(mainModel);	//GUI of the game 
		main = new MainController(mainModel, mainView);
	}
		
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
}

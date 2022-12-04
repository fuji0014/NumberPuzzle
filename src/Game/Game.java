package Game;

import Controller.MainController;
import Model.MainModel;
import View.MainView;

/**
 * Class name: Game
 * Methods list: start, main
 * Constant list: N/A
 * Purpose: GameMVC class contains method to start the numpuz game and has main method to run the NumPuz 
 * @author Amy Fujimoto
 * @version 03 Dec 2022
 * @see javax.swing
 * @since JavaSE-17
 */
public class Game {
	/**
	 * MainModel object to be initialized in constructor
	 */
	private MainModel mainModel;		
	/**
	 * MainView object to be initialized in constructor
	 */
	private MainView mainView;	
	/**
	 * MainController object to be initialized in constructor
	 */
	private MainController main;
	
	/**
	 * Default Constructor
	 */
	public Game(){}
	
	/**
	 * Method name: start
	 * Purpose: Starts the game by instantiating Game objects
	 * Algorithm: Initialized objects and starts game
	 */
	public void start() {
		mainModel = new MainModel();			
		mainView = new MainView(mainModel);	//GUI of the game 
		main = new MainController(mainModel, mainView);
	}
		
	/**
	 * Method name: main
	 * Purpose: To run the server client NumPuz game program
	 * @param args an array of command line arguments for the application
	 * Algorithm: initializes the Game and calls starts method
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
}

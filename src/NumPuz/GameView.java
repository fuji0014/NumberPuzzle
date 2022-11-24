/**
 * File name: GameView.java
 * Identification: Amy Fujimoto, 040974489
 * Course: CST 8221 - JAP, Lab Section 301
 * Assignment: A22
 * Professor: Paulo Sousa
 * Date: November 8, 2022
 * Compiler Eclipse IDE for Java Developers
 * Purpose: This class contains constructor that initializes the window for the game and adds GUI to the frames 
 * and dialogs
 */
package NumPuz;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

/**
 * Class name: GameView
 * Methods list: addElementToIntroFrame, addElementToMainFrame, introGUI, menuBar, topGUI, leftGUI, mainGUI, rightGUI,
 * bottomGUI, printTitle, dropDown, radioButton, timerControlButton, gameButton
 * Constant list: mainBGColor, gameBGColor, gameButColor, gameButColorNull, menuColor
 * Purpose: GameView class sets all the GUI for the NumPuz game using Swing 
 * @author Amy Fujimoto
 * @version 08 Nov 2022
 * @see javax.swing
 * @since JavaSE-17
 */
public class GameView{
	//Colours
	/**
	 * Constant for main background color
	 */
	public static final Color mainBGColor = new Color(252,221,242);
	/**
	 * Constant for game button background color
	 */
	public static final Color gameBGColor = new Color(250,246,246);
	/**
	 * Constant for game button color that is not null
	 */
	public static final Color gameButColor = new Color(252,181,181);
	/**
	 * Constant for main background color
	 */
	public static final Color gameButColorNull = new Color(217,217,217);
	/**
	 * Constant for menu bar color
	 */
	public static final Color menuColor = new Color(145,105,83);
	/**
	 * Color chosen by user to change the look of the button game
	 */
	private Color currentColor = gameButColor;
	
	/**
	 * JFrame class variable to contain all the GUI and components 
	 */
	private JFrame frame;
	/**
	 * JPanel variable for top panel in the frame
	 */
	private JPanel topPanel;
	/**
	 * JPanel variable for left panel in the frame
	 */
	private JPanel leftPanel;
	/**
	 * JPanel variable for middle panel in the frame that contains the main button game
	 */
	private JPanel middlePanel;
	/**
	 * JPanel variable for right panel in the frame
	 */
	private JPanel rightPanel;
	/**
	 * JPanel variable for bottom panel in the frame
	 */
	private JPanel bottomPanel;
	
	/**
	 * 2D array of buttons for the main game containing button information 
	 */
	private JButton[][] puzzleButton;
	/**
	 * Button for selecting the main background color
	 */
	private JButton mainBGColorButton;	
	/**
	 * Button for selecting the game background color
	 */
	private JButton gameBGColorButton;
	/**
	 * Button for selecting the game button background color for those that are not null
	 */
	private JButton gameButColorButton;
	/**
	 * Button for selecting the game button background color that is set to null
	 */
	private JButton gameButColorNullButton;
	/**
	 * Button for selecting the menu background color
	 */
	private JButton menuColorButton;
	/**
	 * Button for closing the dialog
	 */
	private JButton closeButton;
	/**
	 * Button for triggering the action to open the dialog for color change
	 */
	private JButton colorButton;
	/**
	 * Button for starting the timer 
	 */
	private JButton startButton;
	/**
	 * Button for stopping the timer
	 */
	private JButton stopButton;
	/**
	 * Button for resetting the current game status
	 */
	private JButton resetButton;
	/**
	 * Button for starting a new game
	 */
	private JButton ngButton;	
	/**
	 * Button for showing the answer of the current game
	 */
	private JButton showButton;
	
	/**
	 * Menu item within the Game menu that initiates starting a new game
	 */
	private JMenuItem newGame;
	/**
	 * Menu item within the Game menu that initiates opening the solution window
	 */
	private JMenuItem solution;
	/**
	 * Menu item within the Game menu that initiates saving the current game to a file
	 */
	private JMenuItem saveGame;
	/**
	 * Menu item within the Game menu that initiates loading a from a file
	 */
	private JMenuItem loadGame;
	/**
	 * Menu item within the Game menu that triggers a dialog to exit the game
	 */
	private JMenuItem exit;	
	/**
	 * Menu item within the Help menu that triggers a dialog to view information about the game
	 */
	private JMenuItem about;
	/**
	 * Menu item within the Game menu that initiates color changing dialog
	 */
	private JMenuItem color;
	
	/**
	 * JComboBox with String that lists the different levels in the game
	 */
	private JComboBox<String> cbLevel;
	/**
	 * JComboBox with String that lists the 2 types in the game
	 */
	private JComboBox<String> cbType;
	/**
	 * JComboBox with String that lists the different dimension in the game
	 */
	private JComboBox<String> cbDim; 
	
	/**
	 * Label that is used to display the number of clicks the user used so far 
	 * to complete the game
	 */
	private JLabel countLabel;
	/**
	 * Label that is used to keep record of the score for the user
	 */
	private JLabel pointsCount;
	/**
	 * Label that displays the count down timer for the game
	 */
	private JLabel timeCount;

	/**
	 * GridLayout to display the game button in appropriate section
	 */
	private GridLayout gameTiles;
	
	/**
	 * String to store the current level the user selected to play the game
	 */
	private String currentLevel;
	/**
	 * String that stores the text input by user through the dialog
	 */
	private String text;
	
	/**
	 * Stores the index i location for the empty tile in the game
	 */
	private int emptyTile_i;
	/**
	 * Stores the index j location for the empty tile in the game
	 */
	private int emptyTile_j;
		
	/**
	 * Gamemodel object to retrieve data information required to display certain information
	 */
	private GameModel model;

	/**
	 * Timer object to start and stop the count down timer
	 */
	private Timer timer;
	
	/**
	 * Dialog to provide color change during the game for the buttons
	 */
	private JDialog dialog;
	
	
	/**
	 * Method name: Overloaded constructor 
	 * Purpose: Initializes class variables, calls local methods to set GUI for the window and set default values
	 * @param gameModel GameModel object for access to database
	 * Algorithm: Sets settings for splash screen, main game frame, GUI components within the frame, and color dialog.
	 */
	public GameView (GameModel gameModel) {
		this.model = gameModel;
		
		initPuzzleButton();
		//Splash Screen
		JWindow window = new JWindow();
		window.setBounds(500, 150, 300, 225);
		window.setVisible(true);
		
		//Main game frame
		frame = new JFrame("NumPuz - Amy Fujimoto");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//Only this window closes if x is clicked
		frame.setLayout(null);
		frame.setSize(665,690);
		frame.setResizable(false);
		
		//Frame GUIs
		splashScreenGUI(window);
		addElementsToMainFrame();
		
		//Set visibilities
		window.setVisible(false);
		frame.setVisible(true);
		
		//Default settings
		model.setDefaultValues();
		
		initColorDialog();
	}
	
	/**
	 * Method name: initPuzzleButton
	 * Purpose: Initialize the 2D button array with text/numbers
	 * Algorithm: Initialize the puzzle button with dimension size and loop over the button array
	 * to set text to each one, having the last button as null to have empty tile
	 */
	void initPuzzleButton() {	
		model.initPuzzleButtonValues();
		
		puzzleButton = new JButton[model.getDimSize()][model.getDimSize()];
		int count = 1;
		
		for (int i = 0; i < model.getDimSize(); ++i) {
			for (int j = 0; j < model.getDimSize(); ++j) {
				JButton button = new JButton();
				puzzleButton[i][j] = button;
				
				//Sets the last button to null as it is not necessary to be displayed on board
				//but required for displaying purposes
				if(count != (model.getDimSize() * model.getDimSize())) {
					if (model.isNumberGameType()) {
						puzzleButton[i][j].setText(String.valueOf(model.getIntPuzzleButtonValues()[i][j]));
					} else {
						puzzleButton[i][j].setText(model.getStringPuzzleButtonValues()[i][j]);
					}		
					count++;
				} else {					
					puzzleButton[i][j].setText(null);
				}
			}
		}
	}
	
	/**
	 * Method name: showAnswerWindow
	 * Purpose: Show the answer to the current puzzle the user is solving
	 * Algorithm: Open a new dialog which shows the correct location of the tiles and
	 * while looking at the answer, the time still ticks.
	 */
	void showAnswerWindow() {
		JDialog dialog = new JDialog(frame, "Answer sheet", Dialog.ModalityType.DOCUMENT_MODAL);
		JPanel panel = new JPanel();
		middlePanel.setBackground(gameBGColor);
		middlePanel.setBounds(100, 150, 450, 450);
		showAnswer(panel);
		
		dialog.add(panel);
		dialog.setSize(450, 450);
		dialog.setVisible(true);
		dialog.setResizable(false);
	}
	
	/**
	 * Method name: timerSettings
	 * Purpose: Sets the timer setting to appropriate values when timer hits 0
	 * Algorithm: When timer hits 0, timer stops and sets the value to 180, and 
	 * show a dialog indicating that the user lost the game
	 */
	void timerSettings() {
		if(model.getTime() == 0) {
			timer.stop();
			model.setTime(180);
			displayLostDialog();
		}
	}
	
	/**
	 * Method name: dialogLostDialog
	 * Purpose: Display a dialog that indicates that the user lost the game and provide user options to continue, new game, or exit
	 * Algorithm: Stops the timer and displays dialog with object options and restarts setting, starts new game, or exits system 
	 * when user selects try again, new game, or exit game respectively.
	 */
	void displayLostDialog() {
		timer.stop();
		//Custom button text
		Object[] options = {"Try Again",
							"New Game",
		                    "Exit game"};
		int gameStatus = JOptionPane.showOptionDialog(frame,
		    "Sorry, you have lost",
		    "You Lost",
		    JOptionPane.YES_NO_OPTION,
		    JOptionPane.PLAIN_MESSAGE,
		    null,
		    options,
		    options[0]);
		
		switch(gameStatus) {
		case 0:
			//Try again
			restartSettings();
			break;
		case 1:
			//New game
			newGame();
			break;
		default:
			//Exit game
			System.exit(0);
			break;
		}
	}
	
	/**
	 * Method name: dialogWinDialog
	 * Purpose: Display a dialog that indicates that the user won the game and provide user options to play again or exit
	 * Algorithm: Stops the timer and displays dialog with object options and new game or exits system 
	 * when user selects play again or exit game, respectively.
	 */
	void displayWinDialog() {
		timer.stop();
		//Custom button text
		Object[] options = {"Play Again",
		                    "Exit game"};
		int gameStatus = JOptionPane.showOptionDialog(frame,
		    "Congratulations! You won the game!",
		    "You Won",
		    JOptionPane.YES_NO_OPTION,
		    JOptionPane.PLAIN_MESSAGE,
		    null,
		    options,
		    options[0]);
		if (gameStatus == 0) {
			//Play again
			newGame();
		} else {
			//Exit game
			System.exit(0);
		}
	}
	
	/**
	 * Method name: displayErrorDialog
	 * Purpose: Display error message to the user when changing dimension in text mode
	 * Algorithm: Message with JOptionPane warning message 
	 */
	void displayErrorDialog() {
		JOptionPane.showMessageDialog(frame,
			    "You have changed the dimension while in text.\n"
				+ "The buttons has been put back to number type.",
			    "Warning notice",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Method name: displayShuffleErrorDialog
	 * Purpose: Display error message to the user when trying to start the game without shuffling
	 * Algorithm: Message with JOptionPane warning message 
	 */
	void displayShuffleErrorDialog() {
		JOptionPane.showMessageDialog(frame,
			    "Please start a \"New Game\" prior to moving the tiles \n"
				+ "or viewing the answer.",
			    "Warning notice",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Method name: aboutDialog
	 * Purpose: Display message to the user to provide "about" information to the user
	 * Algorithm: Show message with JOptionPane message dialog method
	 */
	void aboutDialog() {
		timer.stop();
		JOptionPane.showMessageDialog(frame,
			    "About game: \nAmy Fujimoto\n040974489\nFall 2022 JAP");
	}
	
	/**
	 * Method name: initColorDialog
	 * Purpose: Initializes the dialog for color setting dialog
	 * Algorithm: Initializes the dialog, label, color buttons, and adds components to dialog 
	 * in appropriate location
	 */
	void initColorDialog() {
		dialog = new JDialog(frame, "Color", Dialog.ModalityType.DOCUMENT_MODAL);
		JLabel label = new JLabel("Choose a color:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.TOP);
		
		//Set text for the buttons
		mainBGColorButton = new JButton ("Pink");
		gameBGColorButton = new JButton ("Off-White");
		gameButColorButton = new JButton ("Orange");
		gameButColorNullButton = new JButton ("Grey");
		menuColorButton = new JButton ("Brown");
		closeButton = new JButton ("Close");
		
		//Set size and location
		mainBGColorButton.setBounds(25, 25, 100, 20);
		gameBGColorButton.setBounds(25, 50, 100, 20);
		gameButColorButton.setBounds(25, 75, 100, 20);
		gameButColorNullButton.setBounds(25, 100, 100, 20);
		menuColorButton.setBounds(25, 125, 100, 20);
		closeButton.setBounds(25, 160, 100, 20);
		
		//Add buttons to dialog 
		dialog.add(mainBGColorButton);
		dialog.add(gameBGColorButton);
		dialog.add(gameButColorButton);
		dialog.add(gameButColorNullButton);
		dialog.add(menuColorButton);
		dialog.add(closeButton);
		dialog.add(label);		
	}
	
	/**
	 * Method name: colorDialog
	 * Purpose: Set the dialog settings for color dialog
	 * Algorithm: Stop the timer and set size, visibility and resizability of the dialog
	 */
	void colorDialog() {
		timer.stop();
		dialog.setSize(175,225);
		dialog.setVisible(true);
		dialog.setResizable(false);
	}
	
	/**
	 * Method name: exitDialog
	 * Purpose: Display confirmation message to the user when trying to exit the game
	 * Algorithm: Message with JOptionPane warning message and when user selects yes, it will exit the game
	 */
	void exitDialog() {
		int exit = JOptionPane.showOptionDialog(null, "Are you sure you want to Exit the game?", "Exit", 
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
		if (exit == 0) {
			//User chosen yes
			System.exit(0);
		}
	}
	
	/**
	 * Method name: textInputDialog
	 * Purpose: Display a prompt for use to enter a text for game buttons
	 * Algorithm: Message with JOptionPane showInputDialog method and saves the text in the model
	 * if the text is appropriate length, otherwise it will prompt an error dialog
	 */
	void textInputDialog() {
		text = (String)JOptionPane.showInputDialog(
	               frame,
	               "Enter a text for the game:", 
	               "Game Text",            
	               JOptionPane.PLAIN_MESSAGE,
	               null,            
	               null, 
	               "Enter here"
	            );	
		
		//Check whether the entered text input is long enough of the game
		if(text == null || text.length() < (model.getDimSize()*model.getDimSize())) {
			cbType.setSelectedIndex(0);
			model.setGameType("Numbers");
			inputErrorDialog();
		} else {
			model.setText(text);
		}
	}
	
	/**
	 * Method name: inputErrorDialog
	 * Purpose: Display error message to the user when user entered a text too short
	 * Algorithm: Message with JOptionPane warning message 
	 */
	void inputErrorDialog() {
		JOptionPane.showMessageDialog(frame,
			    "The text you entered is too short.\n"
			    + "Please try again.",
			    "Error notice",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Method name: successfulSaveDialog
	 * Purpose: Display message to the user when they successfully saved the game button to a file
	 * Algorithm: Message with JOptionPane showMessageDialog
	 */
    void successfulSaveDialog() {		
		JOptionPane.showMessageDialog(frame,
			    "Successfully saved to CSV.");
	}
	
    /**
	 * Method name: successfulLoadDialog
	 * Purpose: Display message to the user when they successfully loaded the game button from a file
	 * Algorithm: Message with JOptionPane showMessageDialog
	 */
	void successfulLoadDialog() {		
		JOptionPane.showMessageDialog(frame,
			    "Successfully loaded from CSV.");
	}
	
	/**
	 * Method name: saveErrorDialog
	 * Purpose: Display message to the user when they failed saved the game button to a file
	 * Algorithm: Message with JOptionPane showMessageDialog and Warning message method
	 */
	void saveErrorDialog() {
		JOptionPane.showMessageDialog(frame,
			    "Not able to save. Please try again.",
			    "Error notice",
			    JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Method name: loadErrorDialog
	 * Purpose: Display message to the user when they failed to load the game button from a file
	 * Algorithm: Message with JOptionPane showMessageDialog and Warning message method
	 */	
	void loadErrorDialog() {
		JOptionPane.showMessageDialog(frame,
			    "Not able to load. Please try again.",
			    "Error notice",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Method name: splashScreenGUI
	 * Purpose: Display a splash screen to user prior to starting the game
	 * Algorithm: Display icon on a window without any buttons and show it for only a certain amount of time
	 * @param window JWindow to display the label and title on
	 */
	void splashScreenGUI(JWindow window) {
		//ImageIcon title = new ImageIcon("C:\\Users\\Admin\\CST8221_workspace\\JAP_A22\\src\\NumPuz\\images\\SplashScreen.PNG");
		JLabel label = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("NumPuz/images/SplashScreen.PNG")));
		window.getContentPane().add(label);
		label.setBounds(0, -15, 300, 250);
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	}
		
	/**
	 * Method name: addElementToMainFrame
	 * Purpose: Adds multiple panel and menu bar to the main frame of the NumPuz game
	 * Algorithm: Initializes the each panels by setting background and bounds and adds it to the main frame
	 */
	void addElementsToMainFrame() {		
		menuBar();
		
		//Top panel
		topPanel = new JPanel();
		topPanel.setBackground(mainBGColor);
		topPanel.setBounds(0, 0, 650, 150);			//x, y, width, height
		topPanel.setLayout(null);
		topGUI();
		
		//Left Panel
		leftPanel = new JPanel();
		leftPanel.setBackground(mainBGColor);
		leftPanel.setBounds(0, 150, 100, 450);
		leftPanel.setLayout(null);
		leftGUI();
		
		//Middle Panel
		middlePanel = new JPanel();
		middlePanel.setBackground(gameBGColor);
		middlePanel.setBounds(100, 150, 450, 450);
		mainGUI();
		
		//Right Panel
		rightPanel = new JPanel();
		rightPanel.setBackground(mainBGColor);
		rightPanel.setBounds(550, 150, 100, 450);
		rightPanel.setLayout(null);
		rightGUI();
			
		//Bottom Panel
		bottomPanel = new JPanel();
		bottomPanel.setBackground(mainBGColor);
		bottomPanel.setBounds(0, 600, 650, 50);
		bottomPanel.setLayout(null);
		bottomGUI();
		
		//Add all to the frame
		frame.add(topPanel);
		frame.add(leftPanel);
		frame.add(rightPanel);
		frame.add(middlePanel);
		frame.add(bottomPanel);	
	}
	
	/**
	 * Method name: menuBar
	 * Purpose: To set the menu bar that will be located at the top of the game frame
	 * Algorithm: Initializes JMenuBar to create menu bar and adds components
	 */
	void menuBar() {
		JMenuBar menubar = new JMenuBar();

		//First menu selection
		JMenu game = new JMenu("Game");
		menubar.add(game);
		newGame = new JMenuItem("New Game", new ImageIcon(getClass().getClassLoader().getResource("NumPuz/images/shuffleIcon.PNG")));
		solution = new JMenuItem("Show Solution", new ImageIcon(getClass().getClassLoader().getResource("NumPuz/images/showAnswerIcon.PNG")));
		saveGame = new JMenuItem("Save", new ImageIcon(getClass().getClassLoader().getResource("NumPuz/images/saveIcon.PNG")));
		loadGame = new JMenuItem("Load", new ImageIcon(getClass().getClassLoader().getResource("NumPuz/images/loadIcon.PNG")));
		exit = new JMenuItem("Exit", new ImageIcon(getClass().getClassLoader().getResource("NumPuz/images/exitIcon.PNG")));
						
		//Add menu items to game menu tab
		game.add(newGame);
		game.add(solution);
		game.add(saveGame);
		game.add(loadGame);
		game.add(exit);
		
		//Second menu selection
		JMenu help = new JMenu("Help");
		menubar.add(help);
		about = new JMenuItem("About", new ImageIcon(getClass().getClassLoader().getResource("NumPuz/images/aboutIcon.PNG")));
		color = new JMenuItem("Color", new ImageIcon(getClass().getClassLoader().getResource("NumPuz/images/colorIcon.PNG")));
		
		//Add menu items to help menu tab
		help.add(color);
		help.add(about);
		
		//Menu bar settings
		menubar.setBackground(menuColor);
		menubar.setVisible(true);
		frame.setJMenuBar(menubar);
	}
		
	/**
	 * Method name: topGUI
	 * Purpose: Contains buttons for the top panel
	 * Algorithm: Initializes JButtons necessary for the panel
	 */
	void topGUI() {
		printTitle();
		
		//Reset
		resetButton = new JButton("Restart");
		resetButton.setBounds(220, 125, 98, 20);
		
		//New game
		ngButton = new JButton("New Game");
		ngButton.setBounds(330, 125, 98, 20);
		
		//Add to panel
		topPanel.add(resetButton);
		topPanel.add(ngButton);
	}
		
	/**
	 * Method name: leftGUI
	 * Purpose: Contains dropdown selectiong and buttons for the left panel
	 * Algorithm: Calls the methods that sets up the components
	 */
	void leftGUI() {
		dropDown();						//Level and Dimension selections
		colorButton();
		//radioButton(panel);						//Design or Play selection
		timerStartButton();		//Start button
	}
	
	/**
	 * Method name: mainGUI
	 * Purpose: Contains the method to display the main game feature
	 * Algorithm: Calls the gameButton method
	 */
	void mainGUI() {
		gameButton(currentColor);
	}
	
	/**
	 * Method name: rightGUI
	 * Purpose: Contains initialization of the labels that are to be displayed on the panel
	 * Algorithm: Initializes JLabel for each label and sets their positions
	 */
	void rightGUI() {
		//Moves
	    JLabel pointsLabel = new JLabel();
	    pointsLabel.setText("Points");
	    pointsLabel.setBounds(30, 15, 80, 20);	
	    pointsCount = new JLabel();
	    pointsCount.setText("0");
	    pointsCount.setBounds(45, 40, 80, 20); 
	    
		//Moves
	    JLabel moveLabel = new JLabel();
	    moveLabel.setText("Moves");
	    moveLabel.setBounds(30, 100, 80, 20);	
	    countLabel = new JLabel();
	    countLabel.setText("0");
	    countLabel.setBounds(45, 125, 80, 20); 
	    
	    //Time
	    JLabel timeLabel = new JLabel();
	    timeCount = new JLabel();
	    timeLabel.setText("Time");
	    timeCount.setText(Integer.toString(model.getTime()) + "s");
	    timeLabel.setBounds(35, 185, 80, 20);	
	    timeCount.setBounds(40, 210, 80, 20);
	    
	    //Stop button
	    timerStopButton();
	    
	    //Add to the panel
	    rightPanel.add(pointsLabel);
	    rightPanel.add(pointsCount);
	    rightPanel.add(moveLabel);
	    rightPanel.add(countLabel);
	    rightPanel.add(timeLabel);
	    rightPanel.add(timeCount);
	}

	/**
	 * Method name: buttomGUI
	 * Purpose: Include the GUI component for the bottom panel
	 * Algorithm: Initializes JButtons necessary for the panel
	 */
	void bottomGUI() {
		//Show answer button
		showButton = new JButton("Show Answer");
		showButton.setBounds(265, 5, 120, 20);
		
		//Add to the panel
		bottomPanel.add(showButton);
	}
	
	/**
	 * Method name: printTitle
	 * Purpose: Contains initialization of the labels that are to be displayed on the panel
	 * Algorithm: Initializes the panel and reads in the file to be used as the title located on the top panel
	 */
	void printTitle() {
		JLabel label = new JLabel();
		ImageIcon title = new ImageIcon(getClass().getClassLoader().getResource("NumPuz/images/Title.png"));
		label.setIcon(title);
		label.setBounds(110, 5, 500, 100);
		topPanel.add(label);
	}
	
	/**
	 * Method name: dropDown
	 * Purpose: Contains initialization of components that are to be displayed on the panel
	 * Algorithm: Initialize the objects and sets the components in the correct location and adds it to the panel
	 */
	void dropDown() {
		//Level
		JLabel levelLabel = new JLabel();
		levelLabel.setText("Level");
		levelLabel.setBounds(30, 30, 80, 20);	
		
		String level[] = {"Easy", "Medium", "Hard", "Expert"};			//List of difficulties	
	    cbLevel = new JComboBox<String>(level); 
	    cbLevel.setBounds(15, 50, 70, 20); 
	    
	    //Type
  		JLabel typeLabel = new JLabel();
  		typeLabel.setText("Type");
  		typeLabel.setBounds(35, 150, 80, 20);	
  		
  		String type[] = {"Numbers", "Text"};        					//Game type selection
  	    cbType = new JComboBox<String>(type); 
  	    cbType.setBounds(12, 170, 80, 20);
  	    
		//Dimension
		JLabel dimLabel = new JLabel();
		dimLabel.setText("Dimension");
		dimLabel.setBounds(20, 90, 80, 20);		
		
		String dim[] = {"3", "4", "5", "6"};        					//List of the game button dimension
	    cbDim = new JComboBox<String>(dim); 
	    cbDim.setBounds(30, 110, 40, 20); 
	    
  	    //Add to panel
	    leftPanel.add(levelLabel);
	    leftPanel.add(cbLevel);
	    leftPanel.add(dimLabel);
	    leftPanel.add(cbDim);
	    leftPanel.add(typeLabel);
	    leftPanel.add(cbType);
	}	
	
	/**
	 * Method name: colorButton
	 * Purpose: Contains initialization of color button that are to be displayed on the panel
	 * Algorithm: Initialize the objects and sets the button in the correct location and adds it to the panel
	 */
	void colorButton() {
		colorButton = new JButton("Color");
		colorButton.setBounds(15, 225, 75, 20);
  		
		//Add to panel
		leftPanel.add(colorButton);
	}
	
	/**
	 * Method name: timerStartButton
	 * Purpose: Contains initialization of start timer button that are to be displayed on the panel
	 * Algorithm: Initialize the objects and sets the button in the correct location and adds it to the panel
	 */
	void timerStartButton() {
  		startButton = new JButton("Start");
  		startButton.setBounds(15, 275, 70, 20);
  		
  		leftPanel.add(startButton);
	}
	
	/**
	 * Method name: timerStopButton
	 * Purpose: Contains initialization of stop timer button that are to be displayed on the panel
	 * Algorithm: Initialize the objects and sets the button in the correct location and adds it to the panel
	 */
	void timerStopButton() {
  		stopButton = new JButton("Stop");
  		stopButton.setBounds(15, 275, 70, 20);
  		
  		rightPanel.add(stopButton);
	}
		
	/**
	 * Method name: gameButton
	 * Purpose: Adds the main game button to the panel in the correct size and format
	 * @param mainColor Color object for current color update
	 * Algorithm: Reads the puzzleButton array to add the button to the panel
	 */
	void gameButton(Color mainColor) {
		int size = puzzleButton.length;
		gameTiles = new GridLayout(size,size);
		
		middlePanel.setLayout(gameTiles);
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				//Change last button(set to null) to grey
				if(puzzleButton[i][j].getText() == null){
					puzzleButton[i][j].setBackground(gameButColorNull);
					emptyTile_i = i;
					emptyTile_j = j;
				} else {
					puzzleButton[i][j].setBackground(mainColor);				
				}
				middlePanel.add(puzzleButton[i][j]);					
			}
		}
	}
	
	/**
	 * Method name: updateGameButton
	 * Purpose: Updates the main game button for when color, dimension, type changes
	 * Algorithm: Reads the puzzleButton array to adjust the color, dim, type if any of them changed
	 */
	void updateGameButton() {
		int size = puzzleButton.length;
		
		middlePanel.setLayout(gameTiles);
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				//Change last button(set to null) to grey
				if(puzzleButton[i][j].getText() == null){
					puzzleButton[i][j].setBackground(gameButColorNull);
				} else {
					puzzleButton[i][j].setBackground(currentColor);					
				}
				middlePanel.add(puzzleButton[i][j]);					
			}
		}
	}
	
	/**
	 * Method name: showAnswer
	 * Purpose: Adds the game button answers to the panel with the same format as the main game
	 * @param panel Object of JPanel to show the answer of the game on
	 * Algorithm: Reads the puzzleButton array to add the button to the panel
	 */
	void showAnswer(JPanel panel) {
		int size = model.getDimSize();
		GridLayout grid = new GridLayout(size,size);
		JButton tile;
		
		panel.setLayout(grid);
		
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				tile = new JButton();
				if(model.isNumberGameType()) {
					if(model.getNumberSolution()[i*model.getDimSize()+j] == null){
						tile.setBackground(gameButColorNull);
					} else {
						tile.setBackground(gameButColor);
						tile.setText(model.getNumberSolution()[i*model.getDimSize()+j]);
					}
				} else {
					if(model.getTextSolution()[i*model.getDimSize()+j] == null){
						tile.setBackground(gameButColorNull);
					} else {
						tile.setBackground(gameButColor);
						tile.setText(model.getTextSolution()[i*model.getDimSize()+j]);
					}
				}
				panel.add(tile);
			}
		}
	}
	
	/**
	 * Method name: moveButton
	 * Purpose: Updates the main game button as selected by the user and moves it to the empty tile
	 * @param button JButton array that contains the button object of the main game
	 * Algorithm: Collect the location of the tile clicked and swap the location of the button with the empty slot.
	 * The button is to be only moved if they are directly beside the empty slot.
	 */
	void moveButton(JButton button) {
		int clickedTile_i = -1;
		int clickedTile_j = -1;
		
		//Retrieve the indexes of the clicked tile
		for (int i = 0; i < model.getDimSize(); ++i) {
			for (int j = 0; j < model.getDimSize(); ++j) {
				//Change last button(set to null) to grey
				if(puzzleButton[i][j].getText() == button.getText()) {
					clickedTile_i = i;
					clickedTile_j = j;
				}
			}
		}
		
		//Check if the clicked tile is applicable to be moved
		if(clickedTile_i != -1) {
			if((clickedTile_i == emptyTile_i - 1 && clickedTile_j == emptyTile_j) || (clickedTile_i == emptyTile_i + 1 && clickedTile_j == emptyTile_j) ||
				(clickedTile_j == emptyTile_j - 1 && clickedTile_i == emptyTile_i) || (clickedTile_j == emptyTile_j + 1 && clickedTile_i == emptyTile_i) ) {
				//Swap location of clicked tile and empty tile
				JButton temp = puzzleButton[clickedTile_i][clickedTile_j];
				puzzleButton[clickedTile_i][clickedTile_j] = puzzleButton[emptyTile_i][emptyTile_j];
				puzzleButton[emptyTile_i][emptyTile_j] = temp;
				
				//Update information
				model.setCount(model.getCount() + 1);
				updateCountLabel();
				updateEmptyTile();
				updateGameButton();
			}
		}		
	}
	
	/**
	 * Method name: removeGameButton
	 * Purpose: Removes the game buttons from the panel
	 * Algorithm: Loops over each of the puzzle button displayed on the panel
	 * and removes them.
	 */
	void removeGameButton() {
		int size = puzzleButton.length;
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				middlePanel.remove(puzzleButton[i][j]);					
			}
		}
	}
	
	/**
	 * Method name: newGame
	 * Purpose: Start a new game by shuffling the game and resetting the time and counts
	 * Algorithm: Call methods to reset setting to default, remove button to avoid overlays, shuffle the tiles,
	 * update the button, and update the new empty tile location.
	 */
	void newGame() {
		restartSettings();
		removeGameButton();
		shufflePuzzle();
		updateGameButton();
		updateEmptyTile();
		
		//Update time and points
		restartSettings();
		
		middlePanel.revalidate();
		middlePanel.repaint();
	}
	
	/**
	 * Method name: restartSetings
	 * Purpose: Updates the count and time to restart the game from scratch
	 * Algorithm: Stops the timer and resets the count to 0 and time to the appropriate time
	 * as per dimension size and difficulty
	 */
	void restartSettings() {
		timer.stop();
		model.setCount(0);
		updateCountLabel();
		model.setTime(model.getLevelTime());
		updateTimeCount();
	}
	
	/**
	 * Method name: puzzleCompleted
	 * Purpose: Check if the current state of the puzzle is in the completed state
	 * @return true if puzzle matches the answer, false if puzzle does not match the answer
	 * Algorithm: Loops over the current puzzle button and compares it with the answer.
	 */
	public boolean puzzleCompleted() {
		for (int i = 0; i < model.getDimSize(); i++) {
			for (int j = 0; j < model.getDimSize(); j++) {
				if(model.isNumberGameType()) {
					if(puzzleButton[i][j].getText() != null) {
						if(!(puzzleButton[i][j].getText().equals(model.getNumberSolution()[i*model.getDimSize()+j])))
							return false;
					} else {
						if(model.getNumberSolution()[i*model.getDimSize()+j] != null)
							return false;
					}
				} else {
					if(puzzleButton[i][j].getText() != null) {
						if(!(puzzleButton[i][j].getText().equals(model.getTextSolution()[i*model.getDimSize()+j]))) {
							return false;
						}
						
					} else {
						if(model.getTextSolution()[i*model.getDimSize()+j] != null) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Method name: shufflePuzzle
	 * Purpose: Shuffle the buttons in a random order
	 * Algorithm: Loops over the 2D array of the puzzle buttons and randomize the button by
	 * utilizing the Random class.
	 */
	public void shufflePuzzle(){
		Random random = new Random();
		
		for (int i = model.getDimSize() - 1; i > 0; i--) {
	        for (int j = model.getDimSize() - 1; j > 0; j--) {
	            int m = random.nextInt(i + 1);
	            int n = random.nextInt(j + 1);

	            JButton temp = puzzleButton[i][j];
	            puzzleButton[i][j] = puzzleButton[m][n];
	            puzzleButton[m][n] = temp;
	        }
	    }
	}
	
	/**
	 * Method name: loadFromCSV
	 * Purpose: Load from the csv file and save it to the database
	 * Algorithm: Reads from csv file line by line and stores them as a puzzle button text where the 
	 * last row is saved as a text for answer of the puzzle
	 * @return true if loading from csv file was successful, otherwise return false
	 */
	public boolean loadFromCSV() {
		List<String> recordsList = new ArrayList<String>();
		String delimiter = ",";
		String currentLine;
		String filepath = "src/NumPuz/save-load_file/numpuzFile.csv";
		
		// TODO Make a deep copy of the button in case of error
 		try {
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			
			//Read the whole file
			while((currentLine = br.readLine()) != null)
				recordsList.add(currentLine);
			
			int recordCount = recordsList.size();
			model.setDimSize(recordCount - 1);
			
			puzzleButton = new JButton[model.getDimSize()][model.getDimSize()];
			String[] data;
			
			//Read column and row and save to button
			for(int i = 0; i < recordCount - 1; i++) {
				data = recordsList.get(i).split(delimiter);
				for(int j = 0; j < data.length; j++) {
					JButton button = new JButton();
					puzzleButton[i][j] = button;
					if(data[j].equals("null")) {
						puzzleButton[i][j].setText(null);
					} else {
						puzzleButton[i][j].setText(data[j]);
					}
				}
			}
			
			//Save last column as text solution
			model.setTextSolution(recordsList.get(recordCount-1).split(""));
			for(int i = 0; i < model.getTextSolution().length; i++) {
				if(model.getTextSolution()[i].equals("0"))
					model.setTextSolution(null, i);
			}
			
			model.setGameType("Text");

			return true;
		} catch (Exception e) {
			// TODO Reset the puzzleButton to what it was before method was called
			return false;
		}
	}
	
	/**
	 * Method name: updateEmptyTile
	 * Purpose: Update the location of the empty tile
	 * Algorithm: Loops over the puzzle buttons and retrieve the index of the empty tile 
	 */
	void updateEmptyTile() {
		for (int i = 0; i < model.getDimSize(); ++i) {
			for (int j = 0; j < model.getDimSize(); ++j) {
				if(puzzleButton[i][j].getText() == null) {
					emptyTile_i = i;
					emptyTile_j = j;
				}
			}
		}
	}
	
	/**
	 * Method name: updateCountLabel
	 * Purpose: Update the counter of the label
	 * Algorithm: Retrieve current count and set it to count label
	 */
	void updateCountLabel() {
		countLabel.setText(Integer.toString(model.getCount()));
	}
	
	/**
	 * Method name: updatePointLabel
	 * Purpose: Update the point of the label
	 * Algorithm: Retrieve current point and set it to point label
	 */
	void updatePointLabel() {
		pointsCount.setText(Integer.toString(model.getPoints()));
	}
	
	/**
	 * Method name: updateTimeLabel
	 * Purpose: Update the time of the label
	 * Algorithm: Retrieve current time and set it to time label
	 */
	void updateTimeCount() {
		timeCount.setText(Integer.toString(model.getTime()) + "s");		
	}
	
	/**
	 * Method name: getMainBGColorButton
	 * Purpose: Return the main background color button
	 * @return mainBGColorButton button
	 */
	public JButton getMainBGColorButton() {
		return mainBGColorButton;
	}
	
	/**
	 * Method name: setMainBGColorButton
	 * Purpose: Sets game color button for color dialog
	 * @param mainBGColorButton JButton object for choosing menu color button
	 */
	public void setMainBGColorButton(JButton mainBGColorButton) {
		this.mainBGColorButton = mainBGColorButton;
	}
	
	/**
	 * Method name: getGameBGColorButton
	 * Purpose: Return the game background color button
	 * @return gameBGColorButton button
	 */
	public JButton getGameBGColorButton() {
		return gameBGColorButton;
	}
	
	/**
	 * Method name: setGameBGColorButton
	 * Purpose: Sets game color button for color dialog
	 * @param gameBGColorButton JButton object for choosing menu color button
	 */
	public void setGameBGColorButton(JButton gameBGColorButton) {
		this.gameBGColorButton = gameBGColorButton;
	}
	
	/**
	 * Method name: getGameButColorButton
	 * Purpose: Return the game button color
	 * @return gameButColorButton button
	 */
	public JButton getGameButColorButton() {
		return gameButColorButton;
	}
	
	/**
	 * Method name: setGameButColorButton
	 * Purpose: Sets game color button for color dialog
	 * @param gameButColorButton JButton object for choosing menu color button
	 */
	public void setGameButColorButton(JButton gameButColorButton) {
		this.gameButColorButton = gameButColorButton;
	}
	
	/**
	 * Method name: getGameButColorNullButton
	 * Purpose: Return the game background color button for null
	 * @return gameButColorNullButton button
	 */
	public JButton getGameButColorNullButton() {
		return gameButColorNullButton;
	}
	
	/**
	 * Method name: setGameButColorNullButton
	 * Purpose: Sets game color button for color dialog
	 * @param gameButColorNullButton JButton object for choosing menu color button
	 */
	public void setGameButColorNullButton(JButton gameButColorNullButton) {
		this.gameButColorNullButton = gameButColorNullButton;
	}
	
	/**
	 * Method name: getMenuColorButton
	 * Purpose: Return the close button
	 * @return menuColorButton button
	 */
	public JButton getMenuColorButton() {
		return menuColorButton;
	}
	
	/**
	 * Method name: setMenuColorButton
	 * Purpose: Sets close button for color dialog
	 * @param menuColorButton JButton object for choosing menu color button
	 */
	public void setMenuColorButton(JButton menuColorButton) {
		this.menuColorButton = menuColorButton;
	}
	
	/**
	 * Method name: getCloseButton
	 * Purpose: Return the close button
	 * @return closeButton button
	 */
	public JButton getCloseButton() {
		return closeButton;
	}
	
	/**
	 * Method name: setCloseButton
	 * Purpose: Sets close button for color dialog
	 * @param closeButton JButton object for close button
	 */
	public void setCloseButton(JButton closeButton) {
		this.closeButton = closeButton;
	}
	
	/**
	 * Method name: getNewGame
	 * Purpose: Return the new game menu item selection
	 * @return newGame menu item
	 */
	public JMenuItem getNewGame() {
		return newGame;
	}
	
	/**
	 * Method name: setNewGame
	 * Purpose: Sets new game menu item
	 * @param newGame JMenuItem object for new game selection
	 */
	public void setNewGame(JMenuItem newGame) {
		this.newGame = newGame;
	}
	
	/**
	 * Method name: getSolution
	 * Purpose: Return the solution menu item selection
	 * @return solution menu item
	 */
	public JMenuItem getSolution() {
		return solution;
	}
	
	/**
	 * Method name: setSolution
	 * Purpose: Sets solution menu item
	 * @param solution JMenuItem object for solution selection
	 */
	public void setSolution(JMenuItem solution) {
		this.solution = solution;
	}
	
	/**
	 * Method name: getSaveGame
	 * Purpose: Return the save menu item selection
	 * @return save menu item
	 */
	public JMenuItem getSaveGame() {
		return saveGame;
	}
	
	/**
	 * Method name: setSaveGame
	 * Purpose: Sets save game menu item
	 * @param saveGame JMenuItem object for save game selection
	 */
	public void setSaveGame(JMenuItem saveGame) {
		this.saveGame = saveGame;
	}
	
	/**
	 * Method name: getLoadGame
	 * Purpose: Return the load menu item selection
	 * @return Load menu item
	 */
	public JMenuItem getLoadGame() {
		return loadGame;
	}
	
	/**
	 * Method name: setLoadGame
	 * Purpose: Sets load file menu item
	 * @param loadGame JMenuItem object for load game selection
	 */
	public void setLoadGame(JMenuItem loadGame) {
		this.loadGame = loadGame;
	}
	
	/**
	 * Method name: getExit
	 * Purpose: Return the exit menu item selection
	 * @return exit menu item
	 */
	public JMenuItem getExit() {
		return exit;
	}
	
	/**
	 * Method name: setExit
	 * Purpose: Sets exit menu item
	 * @param exit JMenuItem object for exit selection
	 */
	public void setExit(JMenuItem exit) {
		this.exit = exit;
	}
	
	/**
	 * Method name: getAbout
	 * Purpose: Return the about menu item selection
	 * @return About menu item
	 */
	public JMenuItem getAbout() {
		return about;
	}
	
	/**
	 * Method name: setAbout
	 * Purpose: Sets about menu item
	 * @param about JMenuItem object for about selection
	 */
	public void setAbout(JMenuItem about) {
		this.about = about;
	}
	
	/**
	 * Method name: getColor
	 * Purpose: Return the color menu item selection
	 * @return Color menu item
	 */
	public JMenuItem getColor() {
		return color;
	}
	
	/**
	 * Method name: setColor
	 * Purpose: Sets color menu item
	 * @param color JMenuItem object for color selection
	 */
	public void setColor(JMenuItem color) {
		this.color = color;
	}
	
	/**
	 * Method name: getResetButton
	 * Purpose: Return the reset button
	 * @return reset button
	 */
	public JButton getResetButton() {
		return resetButton;
	}
	
	/**
	 * Method name: setResetButton
	 * Purpose: Sets reset button 
	 * @param resetButton JButton object for reset button
	 */
	public void setResetButton(JButton resetButton) {
		this.resetButton = resetButton;
	}
	
	/**
	 * Method name: getNgButton
	 * Purpose: Return the new game button
	 * @return new game button
	 */
	public JButton getNgButton() {
		return ngButton;
	}
	
	/**
	 * Method name: setNgButton
	 * Purpose: Sets new game button 
	 * @param ngButton JButton object for new game button
	 */
	public void setNgButton(JButton ngButton) {
		this.ngButton = ngButton;
	}
	
	/**
	 * Method name: getShowButton
	 * Purpose: Return the button to show solution for the game
	 * @return Show solution button
	 */
	public JButton getShowButton() {
		return showButton;
	}
	
	/**
	 * Method name: setShowButton
	 * Purpose: Sets show button for answer solution
	 * @param showButton JButton object for show button
	 */
	public void setShowButton(JButton showButton) {
		this.showButton = showButton;
	}	
	
	/**
	 * Method name: getCbLevel
	 * Purpose: Return the JComboBox String for the different difficulty levels
	 * @return JComboBox with list of levels
	 */
	public JComboBox<String> getCbLevel() {
		return cbLevel;
	}
	
	/**
	 * Method name: setCbLevel
	 * Purpose: Sets combo box with list of levels
	 * @param cbLevel JComboBox of Strings with list of game levels
	 */
	public void setCbLevel(JComboBox<String> cbLevel) {
		this.cbLevel = cbLevel;
	}	
	
	/**
	 * Method name: getCbType
	 * Purpose: Return the JComboBox String for the different game types
	 * @return JComboBox with list of types
	 */
	public JComboBox<String> getCbType() {
		return cbType;
	}
	
	/**
	 * Method name: setCbType
	 * Purpose: Sets combo box with list of game types
	 * @param cbType JComboBox of Strings with list of game types
	 */
	public void setCbType(JComboBox<String> cbType) {
		this.cbType = cbType;
	}	
	
	/**
	 * Method name: getCbDim
	 * Purpose: Return the JComboBox String for the different game dimensions
	 * @return JComboBox with list of dimensions
	 */
	public JComboBox<String> getCbDim() {
		return cbDim;
	}
	
	/**
	 * Method name: setCbDim
	 * Purpose: Sets combo box with list of dimensions
	 * @param cbDim JComboBox of Strings with list of game dimensions
	 */
	public void setCbDim(JComboBox<String> cbDim) {
		this.cbDim = cbDim;
	}
	
	/**
	 * Method name: getColorButton
	 * Purpose: Return the color button for triggering color changing dialog
	 * @return color button
	 */
	public JButton getColorButton() {
		return colorButton;
	}
	
	/**
	 * Method name: setColorButton
	 * Purpose: Sets color button 
	 * @param colorButton JButton object for color button
	 */
	public void setColorButton(JButton colorButton) {
		this.colorButton = colorButton;
	}	
	
	/**
	 * Method name: getStartButton
	 * Purpose: Return the start button for timer
	 * @return start button
	 */
	public JButton getStartButton() {
		return startButton;
	}
	
	/**
	 * Method name: setStartButton
	 * Purpose: Sets start button 
	 * @param startButton JButton object for start timer button
	 */
	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}
	
	/**
	 * Method name: getStopButton
	 * Purpose: Return the stop button for timer
	 * @return stop button
	 */
	public JButton getStopButton() {
		return stopButton;
	}
	
	/**
	 * Method name: setStopButton
	 * Purpose: Sets stop button 
	 * @param stopButton JButton object for stop timer button
	 */
	public void setStopButton(JButton stopButton) {
		this.stopButton = stopButton;
	}
	
	/**
	 * Method name: getCountLabel
	 * Purpose: Return the label used for displaying counts
	 * @return count label
	 */
	public JLabel getCountLabel() {
		return countLabel;
	}
	
	/**
	 * Method name: setCountLabel
	 * Purpose: Sets current count of clicks the user has
	 * @param countLabel total count of user clicks in JLabel object
	 */
	public void setCountLabel(JLabel countLabel) {
		this.countLabel = countLabel;
	}
	
	/**
	 * Method name: getPointsCount
	 * Purpose: Return the label used for displaying points
	 * @return points count label
	 */
	public JLabel getPointsCount() {
		return pointsCount;
	}
	
	/**
	 * Method name: setPointsCount
	 * Purpose: Sets current point the user has
	 * @param pointsCount points the user has in JLabel object
	 */
	public void setPointsCount(JLabel pointsCount) {
		this.pointsCount = pointsCount;
	}
	
	/**
	 * Method name: getTimeCount
	 * Purpose: Return the label used for displaying time left
	 * @return time count label
	 */
	public JLabel getTimeCount() {
		return timeCount;
	}
	
	/**
	 * Method name: setTimeCount
	 * Purpose: Sets current time the user has
	 * @param timeCount time of the game in JLabel object
	 */
	public void getTimeCount(JLabel timeCount) {
		this.timeCount = timeCount;
	}
	
	/**
	 * Method name: getCurrentLevel
	 * Purpose: Return the string for current level
	 * @return String for current level
	 */
	public String getCurrentLevel() {
		return currentLevel;
	}
	
	/**
	 * Method name: setCurrentLevel
	 * Purpose: Sets current level of the game
	 * @param currentLevel Current level the game is playing in String 
	 */
	public void setCurrentLevel(String currentLevel) {
		this.currentLevel = currentLevel;
	}
	
	/**
	 * Method name: getText
	 * Purpose: Return the text entered by the user
	 * @return text in String form
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Method name: setText
	 * Purpose: Sets text to class variable
	 * @param text String that stores the text of user input
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Method name: getTimer
	 * Purpose: Return timer 
	 * @return timer of Timer object
	 */
	public Timer getTimer() {
		return timer;
	}
	
	/**
	 * Method name: setTimer
	 * Purpose: Sets timer as provided in param
	 * @param timer Initialized timer information
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	/**
	 * Method name: getCurrentColor
	 * Purpose: Return the current color used for the game
	 * @return current color
	 */
	public Color getCurrentColor() {
		return currentColor;
	}
	
	/**
	 * Method name: setCurrentColor
	 * Purpose: Sets current color to color indicated by the user
	 * @param color Color object that sets the current color 
	 */
	public void setCurrentColor(Color color) {
		this.currentColor = color;
	}
	
	/**
	 * Method name: getGameTiles
	 * Purpose: Return GridLayout game tiles for the game button layout
	 * @return game tiles GridLayout
	 */
	public GridLayout getGameTiles() {
		return gameTiles;
	}
	
	/**
	 * Method name: getDialog
	 * Purpose: Return the JDialog object
	 * @return dialog object
	 */
	public JDialog getDialog() {
		return dialog;
	}
	
	/**
	 * Method name: getCurrentColor
	 * Purpose: Return the current color used for the game
	 * @param location String for the the location of the panel
	 * @return panel located at given string
	 */
	public JPanel getPanel(String location) {
		switch(location) {
		case "top":
			return topPanel;
		case "left":
			return leftPanel;
		case "middle":
			return middlePanel;
		case "right":
			return rightPanel;
		case "bottom":
			return bottomPanel;
		}
		return null;
	}	
	
	/**
	 * Method name: getPuzzleButton
	 * Purpose: Return the puzzle button array
	 * @return Double array button object 
	 */
	public JButton[][] getPuzzleButton() {
		return puzzleButton;
	}
	
	/**
	 * Method name: setPuzzleButton
	 * Purpose: Sets the puzzleButton 2D array to the class variable
	 * @param puzzleButton 2D array JButton object with text information
	 */
	public void setPuzzleButton(JButton[][] puzzleButton) {
		this.puzzleButton = puzzleButton;
	}	
}

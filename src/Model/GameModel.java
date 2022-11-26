/**
 * File name: GameModel.java
 * Identification: Amy Fujimoto, 040974489
 * Course: CST 8221 - JAP, Lab Section 301
 * Assignment: A22
 * Professor: Paulo Sousa
 * Date: November 8, 2022
 * Compiler Eclipse IDE for Java Developers
 * Purpose: This class initializes the size of the puzzle and also runs the game through main function
 */
package Model;

/**
 * Class name: GameModel
 * Methods list: Getters, setters, initPuzzleButtons, calculateTotalPoints, splitText
 * Constant list: N/A
 * Purpose: GameModel class is the model for the NumPuz game and stores the data for the game
 * @author Amy Fujimoto
 * @version 08 Nov 2022
 * @see javax.swing
 * @since JavaSE-17
 */
public class GameModel {
	/**
	 * Constant value for identifying special value for number puzzle
	 */
	public static final int INT_EMPTY_SPECIAL_VALUE = -1;
	
	/**
	 * Constant value for identifying special string for text puzzle
	 */
	public static final String STRING_EMPTY_SPECIAL_VALUE = "EMPTY";
	
	/**
	 * Stores the numbers for the buttons
	 */
	private int[][] intButtonValues;
	/**
	 * Keeps track of the current time user took to play the game
	 */
	private int time;
	/**
	 * Dimension of the puzzle
	 */
	private int dimSize;
	/**
	 * Number of points the user earned
	 */
	private int points;
	/**
	 * Stores the number of clicks the user used to solve the puzzle
	 */
	private int count;
	
	/**
	 * Stores the string text for the buttons
	 */
	private String[][] stringButtonValues;
	/**
	 * Stores the number solution in 1D array
	 */
	private String[] numberSolution;
	/**
	 * Stores the text solution in 1D array as inputted by user
	 */
	private String[] textSolution;
	/**
	 * Text input by user through dialog
	 */
	private String text;
	/**
	 * Current game type of the puzzle
	 */
	private String gameType;
	/**
	 * Current game level of the puzzle
	 */
	private String gameLevel;
	
	/**
	 * Method name: default constructor 
	 * Purpose: Initializes default/starting values for the game
	 * Algorithm: Assigns default values for class variables
	 */
	public GameModel() {
	}
	
	public void play() {
		time = 180;
		text = null;
		dimSize = 3;
		points = 0;
		count = 0;
		gameType = "Numbers";
		gameLevel = "Easy";
	}
	
	/**
	 * Method name: initPuzzleButtonValues 
	 * Purpose: initializes the 2D array that stores text for the game buttons
	 * Algorithm: Assigns String of numbers or text depending on the game type each array element
	 */
	public void initPuzzleButtonValues() {
		intButtonValues = new int[dimSize][dimSize];
		stringButtonValues = new String[dimSize][dimSize];
		numberSolution = new String[dimSize*dimSize];
		int count = 1;
		
		for (int i = 0; i < dimSize; ++i) {
			for (int j = 0; j < dimSize; ++j) {
				//Sets the last button to null as it is not necessary to be displayed on board
				//but required for displaying purposes
				if(count != (dimSize * dimSize)) {
					if (gameType.equals("Numbers")) {
						numberSolution[i*dimSize+j] = Integer.toString(count);
						intButtonValues[i][j] = count++;
					} else {
						try {
							stringButtonValues[i][j] = textSolution[count - 1];
						} catch (Exception e) {
							stringButtonValues[i][j] = " ";
						}
						count++;
					}						
				} else {
					if(gameType.equals("Numbers")) 
						numberSolution[count-1] = null;
					else {
						textSolution[count-1] = null;
					}				
					intButtonValues[i][j] = INT_EMPTY_SPECIAL_VALUE;
					stringButtonValues[i][j] = STRING_EMPTY_SPECIAL_VALUE;
				}
			}
		}
	}
	
	/**
	 * Method name: calculateTotalPoints 
	 * Purpose: Calculates the amount of points user receives depending on dimension size
	 * Algorithm: Adds the current points and adds points by calculating points depending on 
	 * dimension size and level
	 */
	public void calculateTotalPoints() {
		int add = 0;
		switch(dimSize) {
		case 3:
			add = 10 + getLevelPoints();
			break;
		case 4:
			add = 20 + getLevelPoints();
			break;
		case 5:
			add = 30 + getLevelPoints();
			break;
		case 6:
			add = 40 + getLevelPoints();
			break;
		default:
			add = 0;
			break;
		}
		points = points + add;
	}
	
	/**
	 * Method name: getLevelPoints 
	 * Purpose: Additional points depending on game level chosen by user
	 * @return int of number of additional points 
	 * Algorithm: Returns addition points user can receive depending on how complex the level is
	 */
	private int getLevelPoints() {
		switch(gameLevel) {
		case "Easy":
			return 10;
		case "Medium":
			return 20;
		case "Hard":
			return 30;
		case "Expert":
			return 40;
		default:
			break;
		}
		return 0;
	}
	
	/**
	 * Method name: splitText 
	 * Purpose: Splits the text from user input to 1D string array
	 * Algorithm: Loops over the empty String array and stores the text
	 * letter by letter
	 */
	public void splitText() {
		textSolution = new String[dimSize*dimSize];
		String[] splitTextOriginal = text.split("");
		for(int i = 0; i < (dimSize*dimSize); i++) {
			if(splitTextOriginal[i] != null)
				textSolution[i] = splitTextOriginal[i];
			else if (i != (dimSize*dimSize))
				textSolution[i] = " ";
		}
	}
	
	/**
	 * Method name: getLevelTime 
	 * Purpose: Additional time depending on game level chosen by user
	 * @return int of number of additional time 
	 * Algorithm: Adds base time depending on game level and adds more time by depending on 
	 * dimension size
	 */
	public int getLevelTime() {
		switch(gameLevel) {
		case "Easy":
			return getDimTime()+180;
		case "Medium":
			return getDimTime()+90;
		case "Hard":
			return getDimTime()+60;
		case "Expert":
			return getDimTime()+2;
		default:
			break;
		}
		return 0;
	}
	
	/**
	 * Method name: getDimTime 
	 * Purpose: Additional time depending on game level chosen by user
	 * @return int of number of time 
	 * Algorithm: Returns depending dimension size selected by user
	 */
	private int getDimTime() {
		switch(dimSize) {
		case 3:
			return 0;
		case 4:
			return 10;
		case 5:
			return 20;
		case 6:
			return 30;
		default:
			break;
		}
		return 0;
	}
	
	/**
	 * Method name: setDefaultValues 
	 * Purpose: Sets default values for game level, dimension size, and game type
	 */
	public void setDefaultValues() {
	    setGameLevel("Easy");	
	    setDimSize(3);
		setGameType("Numbers");
	}
	
	/**
	 * Method name: setTime 
	 * Purpose: Sets time to time provided
	 * @param newTime Integer of the time to set
	 */
	public void setTime(int newTime) {
		this.time = newTime;
	}
	
	/**
	 * Method name: getTime 
	 * @return the time in int
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * Method name: setText
	 * Purpose: Convert text String into splited text string
	 * @param text String of text input by user
	 */
	public void setText(String text) {
		this.text = text;
		splitText();
	}
	
	/**
	 * Method name: getIntPuzzleButtonValues
	 * @return 2D integer array of intButtonValues
	 */
	public int[][] getIntPuzzleButtonValues(){
		return intButtonValues;
	}	
	
	/**
	 * Method name: getStringPuzzleButtonValues
	 * @return 2D String array of stringButtonValues
	 */
	public String[][] getStringPuzzleButtonValues(){
		return stringButtonValues;
	}	
	
	/**
	 * Method name: getTextSolution
	 * @return 1D String array of textSolution
	 */
	public String[] getTextSolution() {
		return textSolution;
	}	
	
	/**
	 * Method name: setDimSize
	 * Purpose: Sets the dimension of the puzzle as provided in parameter
	 * @param dimSize Dimension of the puzzle size in integer
	 */
	public void setDimSize(int dimSize) {
		this.dimSize = dimSize;
	}
	
	/**
	 * Method name: getDimSize
	 * @return dimension size in integer
	 */
	public int getDimSize() {
		return dimSize;
	}
	
	/**
	 * Method name: getNumberSolution
	 * @return number solution in String array
	 */
	public String[] getNumberSolution() {
		return numberSolution;
	}
	
	/**
	 * Method name: getPoints
	 * @return points in integer
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Method name: getCount
	 * @return count of clicks in integer
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Method name: setCount
	 * Purpose: Sets the count of clicks of puzzle buttons
	 * @param count Count of clicks in integer
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * Method name: isNumberGameType
	 * @return true if game type is Numbers and false if game type is Text
	 */
	public boolean isNumberGameType() {
		return gameType.equals("Numbers");
	}
	
	/**
	 * Method name: setGameType
	 * Purpose: Sets the type of game
	 * @param type Game type in String (Numbers or Text)
	 */
	public void setGameType(String type) {
		this.gameType = type;
	}
	
	/**
	 * Method name: setTextSolution
	 * Purpose: Sets the solution text
	 * @param solution text solution in 1d String array
	 */
	public void setTextSolution(String[] solution) {
		this.textSolution = solution;
	}
	
	/**
	 * Method name: setTextSolution
	 * Purpose: Sets the String to specified index location
	 * @param value String to set in the index text solution array
	 * @param index index of the text solution in integer
	 */
	public void setTextSolution(String value, int index) {
		this.textSolution[index] = value;
	}
	
	/**
	 * Method name: setNumberSolution
	 * Purpose: Sets the number solution
	 * @param solution number solution in 1d String array
	 */
	public void setNumberSolution(String[] solution) {
		this.numberSolution = solution;
	}
	
	/**
	 * Method name: setNumberSolution
	 * Purpose: Sets the String to specified index location
	 * @param value String to set in the index number solution array
	 * @param index index of the number solution in integer
	 */
	public void setNumberSolution(String value, int index) {
		this.numberSolution[index] = value;
	}
	
	/**
	 * Method name: getGameLevel
	 * @return game level in String
	 */
	public String getGameLevel() {
		return gameLevel;
	}
	
	/**
	 * Method name: setGameLevel
	 * Purpose: Sets the game level in String
	 * @param level game level in String
	 */
	public void setGameLevel(String level) {
		this.gameLevel = level;
	}
}

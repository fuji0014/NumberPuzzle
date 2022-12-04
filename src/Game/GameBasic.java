package Game;

import java.util.StringTokenizer;

/**
 * Class name: GameBasic
 * Methods list: start
 * Constant list: N/A
 * Purpose: GameBasic class contains protocol messages for client and server conversation
 * @author Amy Fujimoto
 * @version 03 Dec 2022
 * @see javax.swing
 * @since JavaSE-17
 */
public class GameBasic {
	/**
	 * Constant variable for protocol separator
	 */
	public static final String PROTOCOL_SEPARATOR = "@";		//separator
	/**
	 * Constant variable for field separator
	 */
	public static final String FIELD_SEPARATOR = ",";
	/**
	 * Constant variable for line separator
	 */
	public static final String LINE_SEPARATOR = ";";
	/**
	 * Constant variable for protocol end procedure
	 */
	private static final String PROTOCOL_END = "P0";			//ending connection
	/**
	 * Constant variable for protocol sending game procedure
	 */
	private static final String PROTOCOL_SENDGAME = "P1";		//sending game
	/**
	 * Constant variable for protocol receiving game procedure
	 */
	private static final String PROTOCOL_RECVGAME = "P2";		//receiving game
	/**
	 * Constant variable for protocol sending data procedure
	 */
	private static final String PROTOCOL_DATA = "P3";			//sending data (points, time, username)
	
	/**
	 * keeps note of the ID of the user
	 */
	private int clientId = 0;
	
	/**
	 * Default dimension of the game 
	 */
	private String dim = "3";
	/**
	 * Default game type of the game
	 */
	private String type = "Numbers";
	/**
	 * Default game data 
	 */
	private String gameData = "1,2,3,4,5,6,7,8,null";
	/**
	 * Points user earned
	 */
	private int points = 0;
	/**
	 * Time the user used
	 */
	private int time = 0;
	
	/**
	 * Default consturctor
	 */
	public GameBasic() {
		
	}	
	
	/**
	 * Method name: initialStart
	 * Purpose: Initial message to Server to provide user information
	 * @param user Username in String
	 * @return Formatted message to server in String
	 */
	public String initialStart(String user) {
		return "0" + PROTOCOL_SEPARATOR + user;
	}
	
	/**
	 * Method name: endGame
	 * Purpose: End message to Server to note client is ending
	 * @return Formatted message to server in String
	 */
	public String endGame() {
		return (Integer.toString(clientId) + PROTOCOL_SEPARATOR + PROTOCOL_END + PROTOCOL_SEPARATOR + " ");
	}
	
	/**
	 * Method name: sendGame
	 * Purpose: Game sending message to Server to provide current game data
	 * @return Formatted message to server in String
	 */
	public String sendGame() {		
		return (Integer.toString(clientId) + PROTOCOL_SEPARATOR + PROTOCOL_SENDGAME
				+ PROTOCOL_SEPARATOR + dim + FIELD_SEPARATOR + type
				+ LINE_SEPARATOR + gameData);
	}
	
	/**
	 * Method name: receiveGame
	 * Purpose: Game receiving message to Server to ask for game data server has
	 * @return Formatted message to server in String
	 */
	public String receiveGame() {
		return (Integer.toString(clientId) + PROTOCOL_SEPARATOR + PROTOCOL_RECVGAME + PROTOCOL_SEPARATOR + " ");
	}
	
	/**
	 * Method name: sendData
	 * Purpose: Data sending message to Server to provide time and points user has
	 * @return Formatted message to server in String
	 */
	public String sendData() {
		return (Integer.toString(clientId) + PROTOCOL_SEPARATOR + PROTOCOL_DATA + PROTOCOL_SEPARATOR +
				Integer.toString(points) + FIELD_SEPARATOR + Integer.toString(time));
	}
	
	/**
	 * Method name: gameDataToArray
	 * Purpose: Converts game data of String into array of string
	 * @return Returns game data to String[]
	 */
	public String[] gameDataToArray() {
		StringTokenizer st = new StringTokenizer(gameData, FIELD_SEPARATOR);	
		int intDim = Integer.valueOf(dim);
		String[] data = new String[intDim*intDim];
		
		for(int i = 0; i < data.length; i++) {
			data[i] = st.nextToken();
		}
		return data;
	}
	
	/**
	 * Method name: getInitClientId
	 * Purpose: Getter for default client id
	 * @return client id in int
	 */
	public int getInitClientId() {
		return clientId;
	}
	
	/**
	 * Method name: setDim
	 * Purpose: Setter for dimension
	 * @param dim of game in String
	 */
	public void setDim(String dim) {
		this.dim = dim;
	}
	
	/**
	 * Method name: getDim
	 * Purpose: Getter for dimension
	 * @return Dimension of game in String
	 */
	public String getDim() {
		return dim;
	}

	/**
	 * Method name: setType
	 * Purpose: Setter for game type
	 * @param type of game in String
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Method name: getType
	 * Purpose: Getter for game type
	 * @return Game type of game in String
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Method name: setGameData
	 * Purpose: Setter for game data
	 * @param gameData in String
	 */
	public void setGameData(String gameData) {
		this.gameData = gameData;
	}
	
	/**
	 * Method name: getGameData
	 * Purpose: Getter for game data
	 * @return Game data of game in String
	 */
	public String getGameData() {
		return gameData;
	}

	/**
	 * Method name: setTime
	 * Purpose: Setter for time
	 * @param time in integer
	 */
	public void setTime(int time) {
		this.time = time;
	}
	
	/**
	 * Method name: getTime
	 * Purpose: Getter for time
	 * @return User's time in integer
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * Method name: setPoints
	 * Purpose: Setter for points
	 * @param points in integer
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	
	/**
	 * Method name: getPoints
	 * Purpose: Getter for points
	 * @return User's points in integer
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Method name: setClientId
	 * Purpose: Setter for client id
	 * @param clientId in integer
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	/**
	 * Method name: getClientId
	 * Purpose: Getter for client id
	 * @return Client Id in integer
	 */
	public int getClientId() {
		return clientId;
	}

}

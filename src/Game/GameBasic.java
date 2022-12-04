package Game;

import java.util.StringTokenizer;

public class GameBasic {
	final String solution1 = "";
	
	public static final String PROTOCOL_SEPARATOR = "@";		//separator
	public static final String FIELD_SEPARATOR = ",";
	public static final String LINE_SEPARATOR = ";";
	static final String PROTOCOL_END = "P0";			//ending connection
	static final String PROTOCOL_SENDGAME = "P1";		//sending game
	static final String PROTOCOL_RECVGAME = "P2";		//receiving game
	static final String PROTOCOL_DATA = "P3";			//sending data (points, time, username)
	static String DEFAULT_USER = "Amy";
	static String DEFAULT_ADDR = "localhost";
	static int DEFAULT_PORT = 12345;
	
	private int clientId = 0;
	
	private String dim = "3";
	private String type = "Numbers";
	private String gameData = "1,2,3,4,5,6,7,8,null";
	private int points = 0;
	private int time = 0;
	
	public GameBasic() {
		
	}
	
	public int getInitClientId() {
		return clientId;
	}
	
	public String initialStart(String user) {
		return "0" + PROTOCOL_SEPARATOR + user;
	}
	
	public String endGame() {
		return (Integer.toString(clientId) + PROTOCOL_SEPARATOR + PROTOCOL_END + PROTOCOL_SEPARATOR + " ");
	}
	
	public String sendGame() {		
		return (Integer.toString(clientId) + PROTOCOL_SEPARATOR + PROTOCOL_SENDGAME
				+ PROTOCOL_SEPARATOR + dim + FIELD_SEPARATOR + type
				+ LINE_SEPARATOR + gameData);
	}
	
	public String receiveGame() {
		return (Integer.toString(clientId) + PROTOCOL_SEPARATOR + PROTOCOL_RECVGAME + PROTOCOL_SEPARATOR + " ");
	}
	
	public String sendData() {
		return (Integer.toString(clientId) + PROTOCOL_SEPARATOR + PROTOCOL_DATA + PROTOCOL_SEPARATOR +
				Integer.toString(points) + FIELD_SEPARATOR + Integer.toString(time));
	}
	
	public String[] gameDataToArray() {
		StringTokenizer st = new StringTokenizer(gameData, FIELD_SEPARATOR);	
		int intDim = Integer.valueOf(dim);
		String[] data = new String[intDim*intDim];
		
		for(int i = 0; i < data.length; i++) {
			data[i] = st.nextToken();
		}
		return data;
	}
	
	public void setDim(String dim) {
		this.dim = dim;
	}
	
	public String getDim() {
		return dim;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setGameData(String gameData) {
		this.gameData = gameData;
	}
	
	public String getGameData() {
		return gameData;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	public int getClientId() {
		return clientId;
	}

}

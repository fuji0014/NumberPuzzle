package Game;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Model.ClientModel;

public class GameBasic {
	final String solution1 = "";
	
	static final String PROTOCOL_SEPARATOR = "@";		//separator
	public static final String FIELD_SEPARATOR = ",";
	static final String LINE_SEPARATOR = ";";
	static final String PROTOCOL_END = "P0";			//ending connection
	static final String PROTOCOL_SENDGAME = "P1";		//sending game
	static final String PROTOCOL_RECVGAME = "P2";		//receiving game
	static final String PROTOCOL_DATA = "P3";			//sending data (points, time, username)
	static String DEFAULT_USER = "Amy";
	static String DEFAULT_ADDR = "localhost";
	static int DEFAULT_PORT = 12345;
	
	private int clientId = 0;
	
	String dim;
	String type;
	String gameData = "";
	int points;
	int marks;
	
	public GameBasic() {
		
	}
	
	public int getInitClientId() {
		return clientId;
	}
	
	public String initialStart(String user) {
		return "0" + PROTOCOL_SEPARATOR + user;
	}
	
	public String endGame(int clientdId) {
		return (Integer.toString(clientdId) + PROTOCOL_SEPARATOR + PROTOCOL_END + PROTOCOL_SEPARATOR + " ");
	}
	
	public String sendGame(int clientdId, JFrame frame) {		
		return (Integer.toString(clientdId) + PROTOCOL_SEPARATOR + PROTOCOL_SENDGAME
				+ PROTOCOL_SEPARATOR + dim+ FIELD_SEPARATOR + type
				+ LINE_SEPARATOR + gameData);
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

}

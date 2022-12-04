package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Controller.ServerController;
import Model.ServerModel;
import View.ServerView;

/**
 * Class name: GameServer
 * Methods list: start, main, run, showResults
 * Constant list: N/A
 * Purpose: GameServer class contains main method to start the server window and has static Player class
 * @author Amy Fujimoto
 * @version 03 Dec 2022
 * @see javax.swing
 * @since JavaSE-17
 */
public class GameServer implements Runnable{
	/**
	 * ServerModel object to be initialized in constructor
	 */
	private ServerModel serverModel;	
	/**
	 * ServerView object to be initialized in constructor
	 */
	private ServerView serverView;	
	/**
	 * ServerController object to be initialized in constructor
	 */
	private ServerController serverController;
	
	/**
	 * Static ServerSocket object
	 */
	private static ServerSocket serverSocket;
	/**
	 * Static Socket object
	 */
	private static Socket clientSocket;     
	/**
	 * Static BufferedReader object for client reading
	 */
	private static PrintWriter out;                   
	/**
	 * Static BufferedReader object for client to server
	 */
	private static BufferedReader in;
	
	/**
	 * Static integer for default port value
	 */
	private static int PORT = 2000;
	/**
	 * Static integer for port value provided by user
	 */
	private static int portNumber;
	
	/**
	 * Variable for client id generating and number of client connected
	 */
	private static int nclient = 0, nclients = 0;
	/**
	 * Default static String for game data
	 */
	private static String gameData = "3,Numbers;1,2,3,4,5,6,7,8,null";
	
	/**
	 * Default constructor
	 */
	public GameServer(){
	}
	
	/**
	 * Class name: Player
	 * Purpose: Player class containing variables each user has
	 * @author Amy Fujimoto
	 */
	static class Player{
		/**
		 * Name of the user
		 */
		String name;
		/**
		 * Unique client id of the user
		 */
		int clientID;
		/**
		 * Points earned by user
		 */
		int points;
		/**
		 * Time user used
		 */
		int time;
	}
	
	/**
	 * List Player objects
	 */
	static ArrayList<Player> playerList = new ArrayList<Player>();
	
	/**
	 * Method name: start
	 * Purpose: Starts the game by instantiating ServerModel, ServerView, and ServerController objects
	 * Algorithm: Initialized objects and calls method to start client window
	 */
	public void start() {
		serverModel = new ServerModel();			
		serverView = new ServerView(serverModel);	
		serverController = new ServerController(serverModel, serverView);
	}
	
	/**
	 * Method name: showResults
	 * Purpose: Loops over the Arraylist to append all the information of the existing user
	 * @return Appended message in String
	 */
	public StringBuilder showResults() {
		StringBuilder sb = new StringBuilder("");
		if(playerList.isEmpty()) {
			sb.append("No Players.");
		} else {
			sb.append("Game results: \n");
			for(Player player : playerList) {
				sb.append("Player " + player.clientID + ": \n" 
						+ "Name: " + player.name + ", points: " + player.points
						+ ", time: " + player.time + "\n");
			}
		}		
		return sb;
	}
	
	/**
	 * Method name: main
	 * Purpose: To run the server program
	 * @param args an array of command line arguments for the application
	 * @param serverView ServerView object
	 * Algorithm: Reads objects and values from parameter and starts server depending on provided value
	 */
	public static void main(String[] args, ServerView serverView){
		GameServer server = new GameServer();
    	if (args == null) {
            portNumber = PORT;
    	} else if (args.length < 1) {
            portNumber = PORT;
        } else {
            portNumber = Integer.parseInt(args[0]);
        }
    	
        try {
        	serverSocket = new ServerSocket(portNumber);
			Thread servDaemon = new Thread(server);
			servDaemon.start();
			
    		serverView.getResultsButton().setEnabled(true);
			serverView.getStartButton().setEnabled(false);			
			
			//When to turn on result button
			serverView.getResultsButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					serverView.resultDialog(server.showResults());
				}
			});
			
			serverView.getEndButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(nclients == 0 ) {
						try {
							serverSocket.close();
							serverView.getFrame().dispose();
						} catch (IOException e1) {
							System.out.print("Error [end button]: ");
							e1.printStackTrace();
						}
					} else {
						serverView.clientErrorDialog();
					}
					
				}
			});
    		
        } catch (IOException e) {
        	System.out.println("Exception caught when trying to listen on port "
        		    + portNumber + " or listening for a connection" + "\n");
    		System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Error main: " + e);
		}
	}
	
	/**
	 * Method name: run
	 * Purpose: Starting server with port number and increment client number
	 */
	public void run() {
		for (;;) {
			try {
				clientSocket = serverSocket.accept();
				System.out.println("Starting server on port " + portNumber);
	    		System.out.println("Client connected!");
				nclient += 1;
				nclients += 1;
				System.out.println("Connecting " + clientSocket.getInetAddress() + " at port " + clientSocket.getPort() + ".");
				System.out.println("Current client number: " + nclients);
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
			Worked w = new Worked(clientSocket);
			w.start();
		}
	}
	
	/**
	 * Class name: Worked
	 * Methods list: run
	 * Constant list: N/A
	 * Purpose: Worked class contains run method to start accepting client message
	 * @author Amy Fujimoto
	 */
	class Worked extends Thread {	
		/**
		 * Socket object
		 */
		private Socket sock;

		/**
		 * Overloaded contructor
		 * @param s Socket object
		 */
		public Worked(Socket s) {
			sock = s;
		}
		
		/**
		 * Method name: run
		 * Purpose: Starting server with port number and accept and analyze client messages
		 */
		public void run() {
			PrintStream out = null;
			BufferedReader in;
			String clientData = "";
			String strCliId = "";
			StringTokenizer st;
			String strProtocol = "";
			String strData = "";
			
			try {
				out = new PrintStream(sock.getOutputStream());
				in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				clientData = in.readLine();
				st = new StringTokenizer(clientData, GameBasic.PROTOCOL_SEPARATOR);	//game basic separator
				strCliId = st.nextToken();
				strProtocol = st.nextToken();
				Player newPlayer = new Player();
				newPlayer.clientID = nclient;
				newPlayer.name = strProtocol;
				playerList.add(newPlayer);
				System.out.println("Saving player: Client Id = " + newPlayer.clientID + ",  User = " + newPlayer.name);
				System.out.println("Sending ID to client.");
				out.println(nclient + GameBasic.PROTOCOL_SEPARATOR + strProtocol);
				out.flush();
				
				while(true) {
					clientData = in.readLine();
					st = new StringTokenizer(clientData, GameBasic.PROTOCOL_SEPARATOR);	//game basic separator
					strCliId = st.nextToken();
					strProtocol = st.nextToken();
					strData = st.nextToken();
					
					if(strProtocol.equals("P0")) {
						//Ending
						System.out.println("Client [" + strCliId+ "] Recieving message: " + clientData);
						System.out.println("Processing Client [" + strCliId+ "] disconnect...");
						
						for(Player player:playerList) {
							System.out.println("ids: " + player.clientID + ", " + strCliId);
							if(player.clientID == Integer.valueOf(strCliId)) {
								playerList.remove(player);
								break;
							}
						}
						
						break;
					} else if(strProtocol.equals("P1")) {
						//Receiving game
						System.out.println("Client [" + strCliId+ "] Recieving message: " + clientData);
						System.out.println("Client [" + strCliId+ "] Game received. ");
						GameServer.gameData = strData;
					} else if (strProtocol.equals("P2")) {
						//Sending game
						System.out.println("Client [" + strCliId+ "] Game data request received. ");
						out.println(strCliId + GameBasic.PROTOCOL_SEPARATOR + GameServer.gameData);
						System.out.println("Client [" + strCliId+ "] Game sent. ");
						out.flush();
					} else if (strProtocol.equals("P3")) {
						//Receiving user data
						System.out.println("Client [" + strCliId+ "] Recieving message: " + clientData);
						System.out.println("Client [" + strCliId+ "] User data received. ");
						for(Player p : playerList) {
							if(p.clientID == Integer.valueOf(strCliId)) {
								st = new StringTokenizer(strData, GameBasic.FIELD_SEPARATOR);
								p.points = Integer.valueOf(st.nextToken());
								p.time = Integer.valueOf(st.nextToken());
								System.out.println("Data: Client =  " + p.clientID + ", points: " + p.points
										+ ", time = " + p.time);
							}
						}
					}
					
				}
				
				System.out.println("Disconnecting " + sock.getInetAddress() + "!");
				nclients -= 1;
				System.out.println("Current client number: " + nclients);
				if (nclients == 0) {
					if(ServerController.isFinalized) {
						System.out.println("Ending server...");
						sock.close();
						System.exit(0);
					}
					System.out.println("Not Finalized so not ending server...");
				}
								
			} catch (IOException ioe) {
				System.out.println("Error [run]: " + ioe);
			}
		}
	}

}

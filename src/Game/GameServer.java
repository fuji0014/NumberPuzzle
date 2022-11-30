package Game;

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

public class GameServer implements Runnable{
	ServerModel serverModel;			
	ServerView serverView;	
	static ServerController serverController;
	
	static ServerSocket serverSocket;
	static Socket clientSocket;     
	static PrintWriter out;                   
	static BufferedReader in;
	
	static int PORT = 2000;
	static int portNumber;
	
	static int nclient = 0, nclients = 0;
	
	public GameServer(){}
	
	static class Player{
		String name;
		int clientID;
		int points;
		int time;
	}
	
	static ArrayList<Player> playerList = new ArrayList<Player>();
	
	public void start() {
		serverModel = new ServerModel();			
		serverView = new ServerView(serverModel);	
		serverController = new ServerController(serverModel, serverView);
	}
	
	public void showResults() {
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
	}
	
	public static void main(String[] args, ServerView serverView){
		GameServer server = new GameServer();
    	if (args == null) {
            portNumber = PORT;
    	} else if (args.length < 1) {
            //System.err.println("Usage: java KnockKnockServer <port number>");
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
    		
        } catch (IOException e) {
        	System.out.println("Exception caught when trying to listen on port "
        		    + portNumber + " or listening for a connection" + "\n");
    		System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Error main: " + e);
		}
	}
	
	/**
	 * Run method.
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
			Worked w = new Worked(clientSocket, nclient);
			w.start();
		}
	}
	
	class Worked extends Thread {
		
		Socket sock;
		int clientid, poscerq;
		String strcliid, dadosCliente;

		public Worked(Socket s, int nclient) {
			sock = s;
			clientid = nclient;
		}
		
		public void run() {
			String data;
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
				st = new StringTokenizer(clientData, "@");	//game basic separator
				strCliId = st.nextToken();
				strProtocol = st.nextToken();
				System.out.println("Saving player: ");
				Player newPlayer = new Player();
				newPlayer.clientID = Integer.valueOf(strCliId);
				newPlayer.name = strProtocol;
				playerList.add(newPlayer);
				out.println(nclient + "@" + strProtocol);
				
				while(true) {
					clientData = in.readLine();
					st = new StringTokenizer(clientData, "@");	//game basic separator
					strCliId = st.nextToken();
					strProtocol = st.nextToken();
					strData = st.nextToken();
					System.out.println("Client [" + strCliId+ "] Recieving message: " + clientData);
					
					if(strProtocol.equals("P0")) {
						System.out.println("Client [" + strCliId+ "] disconnected.");
						break;
					} else if(strProtocol.equals("P1")) {
						out.println(strCliId + "@" + strData);
						
					} else if (strProtocol.equals("P2")) {
						//
						
					} else if (strProtocol.equals("P3")) {
						
					}
					
				}
				
				System.out.println("Disconecting " + sock.getInetAddress() + "!");
				nclients -= 1;
				System.out.println("Current client number: " + nclients);
				if (nclients == 0) {
					System.out.println("Ending server...");
					sock.close();
					System.exit(0);
				}
				
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		}
		
		/**
		 * public void run() {
		String clientData = "";
		String strCliId = "";
		StringTokenizer st;
		String strProtocol = "";
		String strData = "";
		
		try {   
			clientData = in.readLine();
			st = new StringTokenizer(clientData, "@");	//game basic separator
			strCliId = st.nextToken();
			strProtocol = st.nextToken();
			System.out.println("Saving player: ");
			Player newPlayer = new Player();
			newPlayer.clientID = Integer.valueOf(strCliId);
			newPlayer.name = strProtocol;
			playerList.add(newPlayer);
			
			while(true) {
				clientData = in.readLine();
				st = new StringTokenizer(clientData, "@");	//game basic separator
				strCliId = st.nextToken();
				strProtocol = st.nextToken();
				strData = st.nextToken();
				System.out.println("Client [" + strCliId+ "] Recieving message: " + clientData);
				
				if(strProtocol.equals("P0")) {
					System.out.println("Client [" + strCliId+ "] disconnected.");
					out.close();
					in.close();
					clientSocket.close(); 
					serverSocket.close(); 
					break;
				} else if(strProtocol.equals("P1")) {
					out.println(strCliId + "@" + strData);
					
				} else if (strProtocol.equals("P2")) {
					//
					
				} else if (strProtocol.equals("P3")) {
					
				}
			}
		} catch (Exception e) {
			System.out.println("Error protocol: " + e);
		}
		
		
	}
		 */
	}

}

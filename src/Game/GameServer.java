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

public class GameServer implements Runnable{
	private ServerModel serverModel;			
	private ServerView serverView;	
	private ServerController serverController;
	
	private static ServerSocket serverSocket;
	private static Socket clientSocket;     
	static PrintWriter out;                   
	static BufferedReader in;
	
	static int PORT = 2000;
	static int portNumber;
	
	static int nclient = 0, nclients = 0;
	
	static String gameData = "3,Numbers;1,2,3,4,5,6,7,8,null";
	
	public GameServer(){
	}
	
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
		String gameData;

		public Worked(Socket s, int nclient) {
			sock = s;
			clientid = nclient;
		}
		
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
				st = new StringTokenizer(clientData, "@");	//game basic separator
				strCliId = st.nextToken();
				strProtocol = st.nextToken();
				Player newPlayer = new Player();
				newPlayer.clientID = nclient;
				newPlayer.name = strProtocol;
				playerList.add(newPlayer);
				System.out.println("Saving player: Client Id = " + newPlayer.clientID + ",  User = " + newPlayer.name);
				System.out.println("Sending ID to client.");
				out.println(nclient + "@" + strProtocol);
				out.flush();
				
				while(true) {
					clientData = in.readLine();
					st = new StringTokenizer(clientData, GameBasic.PROTOCOL_SEPARATOR);	//game basic separator
					strCliId = st.nextToken();
					strProtocol = st.nextToken();
					strData = st.nextToken();
					
					if(strProtocol.equals("P0")) {
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
						System.out.println("Client [" + strCliId+ "] Recieving message: " + clientData);
						System.out.println("Client [" + strCliId+ "] Game received. ");
						GameServer.gameData = strData;
					} else if (strProtocol.equals("P2")) {
						System.out.println("Client [" + strCliId+ "] Game data request received. ");
						out.println(strCliId + "@" + GameServer.gameData);
						System.out.println("Client [" + strCliId+ "] Game sent. ");
						out.flush();
					} else if (strProtocol.equals("P3")) {
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

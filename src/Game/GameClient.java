package Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import Controller.ClientController;
import Model.ClientModel;
import View.ClientView;

/**
 * Class name: GameClient
 * Methods list: start, main, protocol, close
 * Constant list: N/A
 * Purpose: GameClient class contains main method to start the client window and has close method for AutoCloseable implementation
 * @author Amy Fujimoto
 * @version 03 Dec 2022
 * @see javax.swing
 * @since JavaSE-17
 */
public class GameClient implements AutoCloseable{
	/**
	 * ClientModel object to be initialized in constructor
	 */
	private ClientModel clientModel;	
	/**
	 * ClientView object to be initialized in constructor
	 */
	private ClientView clientView;	
	/**
	 * Static ClientController object to be initialized in constructor
	 */
	private static ClientController clientController;
	/**
	 * GameBasic object to be initialized in constructor
	 */
	private GameBasic gameBasic;
	
	/**
	 * Static Socket object
	 */
	private static Socket socket;
	/**
	 * Static PrintWriter object
	 */
	private static PrintWriter print;
	/**
	 * Static BufferedReader object for server reading
	 */
	private static BufferedReader inFromServer;
	/**
	 * Static BufferedReader object for server to client
	 */
	private static BufferedReader outToServer;
	
	/**
	 * Static string for default host name
	 */
	private static String HOSTNAME = "localhost";
	/**
	 * Static string for hostname provided by user
	 */
	private static String hostName;
	/**
	 * Static integer for default port value
	 */
	private static int PORT = 2000;
	/**
	 * Static integer for port value provided by user
	 */
	private static int portNumber;
	
	/**
	 * Default constructor
	 */
	public GameClient(){}
	
	/**
	 * Method name: start
	 * Purpose: Starts the game by instantiating ClientModel, ClientView, ClientController, and GameBasic objects
	 * Algorithm: Initialized objects and calls method to start client window
	 */
	public void start() {
		gameBasic = new GameBasic();
		clientModel = new ClientModel();			
		clientView = new ClientView(clientModel);	
		clientController = new ClientController(clientModel, clientView, gameBasic);
	}
	
	/**
	 * Method name: main
	 * Purpose: To run the client program
	 * @param args an array of command line arguments for the application
	 * @param clientView ClientView object
	 * @param clientModel ClientModel object
	 * Algorithm: Reads objects and values from parameter and starts client depending on provided value
	 */
	public static void main(String[] args, ClientView clientView, ClientModel clientModel) {
		GameClient client = new GameClient();
		if (args == null) {
            //System.err.println("Usage: java EchoClient <host name> <port number>");
            hostName = HOSTNAME;
            portNumber = PORT;
		} else if (args.length != 2) {
            //System.err.println("Usage: java EchoClient <host name> <port number>");
            hostName = HOSTNAME;
            portNumber = PORT;
        } else {
	        hostName = args[0];
	        portNumber = Integer.parseInt(args[1]);
        }
		System.out.println("Connecting with server on " + hostName + " at port " + portNumber);

		try {
            socket = new Socket(hostName, portNumber);
            print = new PrintWriter(socket.getOutputStream(), true);
            inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outToServer = new BufferedReader(new InputStreamReader(System.in));
            
            client.protocol(clientView, clientModel);
            
            clientView.getConnectButton().setEnabled(false);
            
            clientView.getNewGameButton().setEnabled(true);
            clientView.getSendGameButton().setEnabled(true);
            clientView.getReceiveButton().setEnabled(true);
            clientView.getSendDataButton().setEnabled(true);
            clientView.getPlayButton().setEnabled(true);
            
            clientController.addListeners(socket);
        } catch (UnknownHostException e) {
        	System.out.println("Don't know about host " + hostName + "\n");
        	clientView.serverErrorDialog();
            //System.exit(1);
        } catch (IOException e) {
        	System.out.println("Couldn't get I/O for the connection to " + hostName + "\n");
        	clientView.serverErrorDialog();
            //System.exit(1);
        } catch (Exception e) {
        	System.out.println("Error [main]:  " + e + "\n");
        }
	}
	
	/**
	 * Method name: protocol
	 * Purpose: Protocol method to read in and analyze protocol message sent by server
	 * @param clientView ClientView object
	 * @param clientModel ClientModel object
	 */
	private void protocol(ClientView clientView, ClientModel clientModel) {
		String clientData = "";
		try {			
			clientData = clientModel.getClientData();
			System.out.println("Sending initial message...");
			print.println(clientData);
			String output = inFromServer.readLine();
			System.out.println("Receiving message: " + output);
			StringTokenizer st = new StringTokenizer(output, GameBasic.PROTOCOL_SEPARATOR);	//game basic separator
			clientModel.setId(Integer.valueOf(st.nextToken()));
			System.out.println("Client Id: " + clientModel.getId());
			print.flush();			
		} catch (Exception e) {
			System.out.println("Error [protocol]:  " + e + "\n");
		}
	}

	/**
	 * Method name: close
	 * Purpose: Closes objects opened and used by client
	 */
	@Override
	public void close() throws Exception {
		socket.close();
        print.close();
        inFromServer.close();
        outToServer.close();			
	}
}

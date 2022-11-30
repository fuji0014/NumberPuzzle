package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class GameClient implements AutoCloseable{
	ClientModel clientModel;			
	ClientView clientView;	
	static ClientController clientController;
	
	GameBasic gameBasic;
	
	static Socket socket;
	static PrintWriter print;
	static BufferedReader inFromServer;
	static BufferedReader outToServer;
	
	static String HOSTNAME = "localhost";
    static String hostName;
    static int PORT = 2000;
    static int portNumber;
	
	public GameClient(){}
	
	public void start() {
		gameBasic = new GameBasic();
		clientModel = new ClientModel();			
		clientView = new ClientView(clientModel);	
		clientController = new ClientController(clientModel, clientView, gameBasic);
	}
	
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
            //System.exit(1);
        } catch (IOException e) {
        	System.out.println("Couldn't get I/O for the connection to " + hostName + "\n");
            //System.exit(1);
        } catch (Exception e) {
        	
        }
	}
	
	private void protocol(ClientView clientView, ClientModel clientModel) {
		String clientData = "";
		try {			
			clientData = clientModel.getClientData();
			System.out.println("Sending message: " + clientData);
			System.out.println("Client: " + clientModel.getId());
			print.println(clientData);
			System.out.println("client data: " + clientData);
			String output = inFromServer.readLine();
			System.out.println("Receiving message: " + output);
			StringTokenizer st = new StringTokenizer(output, "@");	//game basic separator
			clientModel.setId(Integer.valueOf(st.nextToken()));
						
		} catch (Exception e) {
		}
		
	}

	@Override
	public void close() throws Exception {
		socket.close();
        print.close();
        inFromServer.close();
        outToServer.close();			
	}
}

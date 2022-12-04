package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import Model.ClientModel;
import View.ClientView;
import Game.GameBasic;
import Game.GameClient;

/**
 * Class name: ClientController
 * Methods list: connectListener, addListener
 * Constant list: N/A
 * Purpose: ClientController class contains the action listener for all the action taken by the user in client window
 * @author Amy Fujimoto
 * @version 03 Dec 2022
 * @see javax.swing
 * @since JavaSE-17
 */
public class ClientController {
	/**
	 * ClientModel object to be initialized in constructor
	 */
	private ClientModel clientModel;
	/**
	 * ClientView object to be initialized in constructor
	 */
	private ClientView clientView;
	/**
	 * ClientBasic object to be initialized in constructor
	 */
	private GameBasic gameBasic;

	/**
	 * Default constructor
	 */
	public ClientController() {}
	
	/**
	 * Method name: ClientController, Overloaded constructor 
	 * Purpose: Initialize objects add listeners to buttons
	 * Algorithm: Takes in ClientModel, ClientView and GameBasic parameter to initalize class variable objects and calls methods
	 * to add listeners
	 * @param clientModel GameModel object 
	 * @param clientView GameView object
	 * @param gameBasic GameBasic object
	 */
	public ClientController(ClientModel clientModel, ClientView clientView, GameBasic gameBasic) {
		this.clientModel = clientModel;
		this.clientView = clientView;
		this.gameBasic = gameBasic;
		connectListener();
	}
	
	/**
	 * Method name: connectListener
	 * Purpose: Contains action listener and actionPerformed methods to trigger actions by user
	 * Algorithm: Retrieves button object from game view object and adds listener
	 */
	public void connectListener() {
		clientView.getConnectButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientModel.setPort(clientView.getPortText().getText());
				clientModel.setUser(clientView.getUserText().getText());
				clientModel.setServerName(clientView.getServerText().getText());
				clientModel.setClientData(gameBasic.initialStart(clientModel.getUser()));
				clientModel.setId(gameBasic.getInitClientId());

				System.out.println("Connection button...");
				//Enable all buttons on 2nd line if connection is successful
				
				//Send Id to server
		    	String[] args = {clientModel.getServerName(), String.valueOf(clientModel.getPort())};
				GameClient.main(args, clientView, clientModel);
				gameBasic.setClientId(clientModel.getId());
			}
		});
	}
	
	/**
	 * Method name: addListeners
	 * Purpose: Contains action listener and actionPerformed methods to trigger actions by user
	 * Algorithm: Retrieves button object from game view object and adds listener
	 * @param socket Socket object from Client
	 */
	public void addListeners(Socket socket) {				
		clientView.getNewGameButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("New Game button...");					
				clientView.newGameDialog();	
				System.out.println("New game set: Dim = " + gameBasic.getDim() + ", Type = " + gameBasic.getType());
				System.out.println("Game data = " + gameBasic.getGameData());
			}
		});
		
		clientView.getEndButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//send message to server that client ended
				System.out.println("End button...");
				PrintWriter print;
				BufferedReader inFromServer;
				try {
					print = new PrintWriter(socket.getOutputStream(), true);
					inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					String message = gameBasic.endGame();
					clientModel.setClientData(message);
					System.out.println("Sending message: " + message);
					print.println(message);
					print.close();
					inFromServer.close();
					socket.close();
					System.out.println("Disconnected!");
					clientView.getFrame().dispose();
				} catch (Exception e1) {
					System.out.println("Disconnected. Closing." + e1);
					
				}
				
			}
		});
		
		clientView.getOkButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String gameData = "";
				gameBasic.setDim(clientView.getCbDim().getSelectedItem().toString());
				gameBasic.setType(clientView.getCbType().getSelectedItem().toString());
				int dimInt = Integer.valueOf(gameBasic.getDim());
				if(gameBasic.getType().equals("Text")) {
					clientView.textInputDialog(dimInt);
					for(int i = 0; i < (dimInt*dimInt); i++) {
						gameData = gameData + clientView.getText().charAt(i) + GameBasic.FIELD_SEPARATOR;
						if(i == (dimInt*dimInt - 1)) {
							gameData = gameData + "null";
						}
					}
				} else {
					for(int i = 1; i < (dimInt*dimInt); i++) {
						gameData = gameData + String.valueOf(i) + GameBasic.FIELD_SEPARATOR;
						if(i == (dimInt*dimInt - 1)) {
							gameData = gameData + null;
						}
					}
				}
				gameBasic.setGameData(gameData);
				clientView.getDialog().dispose();
			}
		});
		
		
		clientView.getSendGameButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrintWriter print;
				try {
					print = new PrintWriter(socket.getOutputStream(), true);
					String message = gameBasic.sendGame();
					clientModel.setClientData(message);
					System.out.println("Sending message: " + message);
					print.println(message);
				} catch (IOException e1) {
					System.out.println("Error [send game]...");
					e1.printStackTrace();
				}
				
			}
		});
		
		clientView.getReceiveButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					PrintWriter print = new PrintWriter(socket.getOutputStream(), true);
					BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					String message = gameBasic.receiveGame();
					clientModel.setClientData(message);
					//Sending message to server to receive game information
					print.println(message);
					String output = inFromServer.readLine();
					System.out.println("Receiving message: " + output);
					StringTokenizer st = new StringTokenizer(output, GameBasic.PROTOCOL_SEPARATOR);	//game basic separator					
					st.nextToken();
					output = st.nextToken();
					clientModel.setClientData(output);
					st = new StringTokenizer(output, GameBasic.LINE_SEPARATOR);
					output = st.nextToken();
					gameBasic.setGameData(st.nextToken());
					st = new StringTokenizer(output, GameBasic.FIELD_SEPARATOR);
					gameBasic.setDim(st.nextToken());
					gameBasic.setType(st.nextToken());
					//Need to set 
					print.flush();	
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		clientView.getPlayButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientModel.setGameBasic(gameBasic);
				clientModel.startGame();
				gameBasic = clientModel.getGameBasic();
			}
		});
		
		clientView.getSendDataButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrintWriter print;
				try {
					print = new PrintWriter(socket.getOutputStream(), true);
					String message = gameBasic.sendData();
					clientModel.setClientData(message);
					System.out.println("Sending message: " + message);
					print.println(message);
				} catch (IOException e1) {
					System.out.println("Error [send data]...");
					e1.printStackTrace();
				}
				
			}
		});
	}
}

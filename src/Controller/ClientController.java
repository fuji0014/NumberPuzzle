package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.SwingUtilities;

import Model.ClientModel;
import View.ClientView;
import Game.GameClient;
import Game.GameBasic;

public class ClientController {
	private ClientModel clientModel;
	private ClientView clientView;
	private GameBasic gameBasic;
	private GameClient client;

	public ClientController() {}
	
	public ClientController(ClientModel clientModel, ClientView clientView, GameBasic gameBasic) {
		this.clientModel = clientModel;
		this.clientView = clientView;
		this.gameBasic = gameBasic;
		connectListener();
	}
	
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
			}
		});
	}
	
	public void addListeners(Socket socket) {				
		clientView.getNewGameButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("New Game button...");
				try {
					PrintWriter print = new PrintWriter(socket.getOutputStream(), true);
					BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
					int clientId = clientModel.getId();
					clientView.newGameDialog();
					
					String message = gameBasic.sendGame(clientId, clientView.getFrame());
					clientModel.setClientData(message);
					System.out.println("Sending message: " + message);
					print.println(message);
					String output = inFromServer.readLine();
					System.out.println("Receiving message: " + output);

				} catch (IOException e1) {
					System.out.println("Error [new game]...");
					e1.printStackTrace();
				} 
	        	
			}
		});
		
		clientView.getEndButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//send message to server that client ended
				System.out.println("End button...");
				try {
					PrintWriter print = new PrintWriter(socket.getOutputStream(), true);
					BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					int clientId = clientModel.getId();
					String message = gameBasic.endGame(clientId);
					clientModel.setClientData(message);
					System.out.println("Sending message: " + message);
					print.println(message);
//					String output = inFromServer.readLine();
//					System.out.println("Receiving message: " + output);
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
				System.out.println("ok click");
				String gameData = "";
				gameBasic.setDim(clientView.getCbDim().getSelectedItem().toString());
				gameBasic.setType(clientView.getCbType().getSelectedItem().toString());
				int dimInt = Integer.valueOf(gameBasic.getDim());
				if(gameBasic.getType().equals("Text")) {
					//clientView.textInputDialog();
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
		
		
//		clientView.getSendGameButton().addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
//		
//		clientView.getReceiveButton().addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
//		
//		clientView.getPlayButton().addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
//		
//		clientView.getSendDataButton().addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
	}
}

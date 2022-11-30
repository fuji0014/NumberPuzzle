package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import Model.ServerModel;
import View.ServerView;
import Game.GameServer;

public class ServerController {
	private ServerModel serverModel;
	private ServerView serverView;
	
	public ServerController() {}
	
	public ServerController(ServerModel serverModel, ServerView serverView) {
		this.serverModel = serverModel;
		this.serverView = serverView;
		addListeners();
	}
	
	public void addListeners() {
		serverView.getStartButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				serverModel.setPort(Integer.valueOf(serverView.getPortText().getText()));
				System.out.println("Start button...");
				System.out.println("port = " +  serverModel.getPort());
				System.out.println("Waiting for clients to connect...");	
				
		    	String[] args = {String.valueOf(serverModel.getPort())};	//change to user input
				GameServer.main(args, serverView);
				//Finalize?
			}
		});
		
		serverView.getEndButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					serverView.getFrame().dispose();
					//server.close();					
				} catch (Exception e1) {
					System.out.println("Disconnected. Closing." + e1);
				}
				
			}
		});
		
		//When to turn on result button
		serverView.getResultsButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
	}
	
}

package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.ServerModel;
import View.ServerView;
import Game.GameServer;

/**
 * Class name: ServerController
 * Methods list: addListeners
 * Constant list: N/A
 * Purpose: ServerController class contains the action listener for all the action taken by the user
 * @author Amy Fujimoto
 * @version 03 Dec 2022
 * @see javax.swing
 * @since JavaSE-17
 */
public class ServerController {
	/**
	 * ServerModel object to be initialized in constructor
	 */
	private ServerModel serverModel;
	/**
	 * ServerView object to be initialized in constructor
	 */
	private ServerView serverView;
	
	/**
	 * Static boolean value for finalized check box
	 */
	public static boolean isFinalized = false;
	
	/**
	 * Default constructor
	 */
	public ServerController() {}
	
	/**
	 * Method name: ServerController, Overloaded constructor 
	 * Purpose: Initialize objects add listeners to buttons
	 * Algorithm: Takes in ServerModel and ServerView parameter to initialize class variable objects and calls methods
	 * to add listeners
	 * @param serverModel ServerModel object 
	 * @param serverView ServerModel object
	 */
	public ServerController(ServerModel serverModel, ServerView serverView) {
		this.serverModel = serverModel;
		this.serverView = serverView;
		addListeners();
	}
	
	/**
	 * Method name: addListeners
	 * Purpose: Contains action listener and actionPerformed methods to trigger actions by user
	 * Algorithm: Retrieves button object from game view object and adds listener to each button
	 */
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
			}
		});
		
		serverView.getFinalizeBox().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(serverView.getFinalizeBox().isSelected())
					isFinalized = true;
				else
					isFinalized = false;
			}
		});
	}
	
}

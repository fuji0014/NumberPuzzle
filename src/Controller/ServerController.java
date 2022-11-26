package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.ServerModel;
import View.ServerView;

public class ServerController {
	private ServerModel serverModel;
	private ServerView serverView;
	
	public ServerController() {}
	
	public ServerController(ServerModel serverModel, ServerView serverView) {
		this.serverModel = serverModel;
		this.serverView = serverView;
		addListeners();
	}
	
	private void addListeners() {
		serverView.getEndButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				serverView.getFrame().dispose();
			}
		});
		
		serverView.getStartButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Finalize?
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

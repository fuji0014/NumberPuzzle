package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import Model.ClientModel;
import View.ClientView;

public class ClientController {
	private ClientModel clientModel;
	private ClientView clientView;
	
	public ClientController() {}
	
	public ClientController(ClientModel clientModel, ClientView clientView) {
		this.clientModel = clientModel;
		this.clientView = clientView;
		addListeners();
	}
	
	private void addListeners() {
		clientView.getConnectButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// save userText, serverText, portText
			}
		});
		
		clientView.getEndButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientView.getFrame().dispose();
			}
		});
		
		clientView.getNewGameButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		clientView.getSendGameButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		clientView.getReceiveButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		clientView.getPlayButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		clientView.getSendDataButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}

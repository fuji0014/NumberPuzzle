package View;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.ClientModel;

public class ClientView {
	JFrame frame;
	JPanel panel;
	
	JTextField textField;
	JTextField userText;
	JTextField serverText;
	JTextField portText;
	
	JButton connectButton;
	JButton endButton;
	JButton newGameButton;
	JButton sendGameButton;
	JButton receiveButton;
	JButton playButton;
	JButton sendDataButton;
	
	public static final Color mainBGColor = new Color(224,227,244);
	
	ClientModel model;
	
	public ClientView(ClientModel model) {
		this.model = model;
		
		frame = new JFrame("Client");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		frame.setLayout(null);
		frame.setSize(750,500);
		frame.setResizable(false);
		initServerFrame();
		frame.setVisible(true);
	}
	
	private void initServerFrame() {
		JLabel label = new JLabel();
		ImageIcon title = new ImageIcon(getClass().getClassLoader().getResource("images/client.png"));
		label.setIcon(title);
		label.setLayout(null);
		label.setBounds(-85, -150, 1000, 500);
		frame.add(label);
		
		panel = new JPanel();
		panel.setBackground(mainBGColor);
		panel.setBounds(0,215, 750, 250);		
		panel.setLayout(null);
		frame.add(panel);
		
		initGUI();
	}
	
	public void initGUI() {
		JLabel userLabel = new JLabel("User:");
		userLabel.setBounds(90, 5, 75, 20);
		
		userText = new JTextField();
		userText.setBounds(125, 5, 75, 20);
		
		JLabel serverLabel = new JLabel("Server:");
		serverLabel.setBounds(215, 5, 75, 20);
		
		serverText = new JTextField();
		serverText.setBounds(260, 5, 75, 20);
		
		JLabel portLabel = new JLabel("Port:");
		portLabel.setBounds(350, 5, 75, 20);
		
		portText = new JTextField();
		portText.setBounds(380, 5, 75, 20);
		
		connectButton = new JButton("Connect");
	    connectButton.setBounds(480, 5, 90, 20);
	    
	    endButton = new JButton("End");
	    endButton.setBounds(580, 5, 75, 20);
		
		//grey out by default
	    newGameButton = new JButton("New game");
	    newGameButton.setBounds(100, 50, 100, 20);
	    newGameButton.setEnabled(false);
	    
	    sendGameButton = new JButton("Send game");
	    sendGameButton.setBounds(215, 50, 100, 20);
	    sendGameButton.setEnabled(false);
	    
	    receiveButton = new JButton("Receive game");
	    receiveButton.setBounds(330, 50, 120, 20);
	    receiveButton.setEnabled(false);
	    
	    sendDataButton = new JButton("Send data");
	    sendDataButton.setBounds(465, 50, 95, 20);
	    sendDataButton.setEnabled(false);
	    
	    playButton = new JButton("Play");
	    playButton.setBounds(575, 50, 70, 20);
	    playButton.setEnabled(false);
	    
	    panel.add(userLabel);
	    panel.add(userText);
	    panel.add(serverLabel);
	    panel.add(serverText);
	    panel.add(portLabel);
	    panel.add(portText);
	    panel.add(connectButton);
	    panel.add(endButton);
	    panel.add(newGameButton);
	    panel.add(sendGameButton);
	    panel.add(receiveButton);
	    panel.add(sendDataButton);
	    panel.add(playButton);
		
		textField = new JTextField();
		textField.setBounds(50, 80, 640, 150);
		textField.setEditable(false);
		textField.setVisible(true);
		panel.add(textField);
	}
	
	//frame
	public JFrame getFrame() {
		return this.frame;
	}
	
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	//panel
	public JPanel getPanel() {
		return this.panel;
	}
	
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	
	//text field
	public JTextField getTextField() {
		return this.textField;
	}
	
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	//user text 
	public JTextField getuserText() {
		return this.userText;
	}
	
	public void setuserText(JTextField userText) {
		this.userText = userText;
	}
	
	//server text
	public JTextField getServerText() {
		return this.serverText;
	}
	
	public void setServerText(JTextField serverText) {
		this.serverText = serverText;
	}
	
	//port text
	public JTextField getPortText() {
		return this.portText;
	}
	
	public void setPortText(JTextField portText) {
		this.portText = portText;
	}
	
	//connect button
	public JButton getConnectButton(){
		return this.connectButton;
	}
	
	public void setConnectButton(JButton connectButton) {
		this.connectButton = connectButton;
	}
	
	//New Game button
	public JButton getNewGameButton(){
		return this.newGameButton;
	}
	
	public void setNewGameButton(JButton newGameButton) {
		this.newGameButton = newGameButton;
	}	
	
	//End button
	public JButton getEndButton(){
		return this.endButton;
	}
	
	public void setEndButton(JButton endButton) {
		this.endButton = endButton;
	}	
	
	//Send game button
	public JButton getSendGameButton(){
		return this.sendGameButton;
	}
	
	public void setSendGameButton(JButton sendGameButton) {
		this.sendGameButton = sendGameButton;
	}	
	
	//Receive game button
	public JButton getReceiveButton(){
		return this.receiveButton;
	}
	
	public void setReceiveButton(JButton receiveButton) {
		this.receiveButton = receiveButton;
	}	
	
	//send data button
	public JButton getSendDataButton(){
		return this.sendDataButton;
	}
	
	public void setSendDataButton(JButton sendDataButton) {
		this.sendDataButton = sendDataButton;
	}	
	
	//play button
	public JButton getPlayButton(){
		return this.playButton;
	}
	
	public void setplayButton(JButton playButton) {
		this.playButton = playButton;
	}	
}
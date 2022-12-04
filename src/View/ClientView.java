package View;

import java.awt.Color;
import java.awt.Dialog;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Model.ClientModel;

/**
 * Class name: ClientView
 * Methods list: initGameDialog, initGUI, initClientFrame, dialogs, and getter and setters
 * Constant list: mainBGColor
 * Purpose: ClientView class sets all the GUI for the client using Swing 
 * @author Amy Fujimoto
 * @version 03 Dec 2022
 * @see javax.swing
 * @since JavaSE-17
 */
public class ClientView {
	/**
	 * JFrame class variable to contain all the GUI and components 
	 */
	private JFrame frame;
	/**
	 * JPanel variable for panel in the frame
	 */
	private JPanel panel;
	
	/**
	 * JTextArea variable for console on client gui
	 */
	private JTextArea textField;
	/**
	 * JTextField variable for user text field
	 */
	private JTextField userText;
	/**
	 * JTextField variable for server text field
	 */
	private JTextField serverText;
	/**
	 * JTextField variable for port text field
	 */
	private JTextField portText;
	
	/**
	 * Button for connection
	 */
	private JButton connectButton;
	/**
	 * Button for ending the connection
	 */
	private JButton endButton;
	/**
	 * Button for new game
	 */
	private JButton newGameButton;
	/**
	 * Button for sending game to server
	 */
	private JButton sendGameButton;
	/**
	 * Button for receiving game from server
	 */
	private JButton receiveButton;
	/**
	 * Button for playing game
	 */
	private JButton playButton;
	/**
	 * Button for sending data to server
	 */
	private JButton sendDataButton;
	/**
	 * Button for ok for dialog
	 */
	private JButton okButton;
	
	/**
	 * JDialog object for new game
	 */
	private JDialog dialog;
	/**
	 * JComboBox with String that lists the different levels in the game
	 */
	private JComboBox<String> cbType;
	/**
	 * JComboBox with String that lists the different dimension in the game
	 */
	private JComboBox<String> cbDim;
	/**
	 * String that stores the text input by user through the dialog
	 */
	private String text;
	/**
	 * Constant for main background color
	 */
	public static final Color mainBGColor = new Color(224,227,244);
	/**
	 * ClientModel object
	 */
	private ClientModel model;
	
	/**
	 * Method name: Overloaded constructor 
	 * Purpose: Initializes class variables, calls local methods to set GUI for the window and set default values
	 * @param model ClientView object for access to database
	 * Algorithm: Sets settings server frame, GUI components within the frame
	 */
	public ClientView(ClientModel model) {
		this.model = model;
		
		frame = new JFrame("Client");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		frame.setLayout(null);
		frame.setSize(750,500);
		frame.setResizable(false);
		initClientFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(0);

		initGameDialog();
	}
	
	/**
	 * Method name: initClientFrame
	 * Purpose: Initialize frame and labels and calls initGUI
	 */
	private void initClientFrame() {
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
	
	/**
	 * Method name: initGUI
	 * Purpose: Adds multiple panel and menu bar to the main frame of the client view
	 * Algorithm: Initializes the each panels by setting background and bounds and adds it to the main frame
	 */
	private void initGUI() {
		JLabel userLabel = new JLabel("User:");
		userLabel.setBounds(90, 5, 75, 20);
		
		userText = new JTextField();
		userText.setBounds(125, 5, 75, 20);
		userText.setText(model.getUser());
		
		JLabel serverLabel = new JLabel("Server:");
		serverLabel.setBounds(215, 5, 75, 20);
		
		serverText = new JTextField();
		serverText.setBounds(260, 5, 75, 20);
		serverText.setText(model.getServerName());
		
		JLabel portLabel = new JLabel("Port:");
		portLabel.setBounds(350, 5, 75, 20);
		
		portText = new JTextField();
		portText.setBounds(380, 5, 75, 20);
		portText.setText(model.getPort());
		
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
		
		textField = new JTextArea();
		textField.setBounds(50, 80, 640, 150);
		textField.append("Creating new MVC Game...\n");
		textField.setEditable(false);
		textField.setVisible(true);
		JScrollPane scroll = new JScrollPane(textField);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(50, 80, 640, 150);
		scroll.setVisible(true);
		PrintStream printStream = new PrintStream(new CustomOutputStream(textField));
		System.setOut(printStream);
		System.setErr(printStream);
		panel.add(scroll);
	}
	
	
	/**
	 * Method name: initGameDialog
	 * Purpose: Adds multiple panel and menu bar to the dialog of the server view
	 * Algorithm: Initializes the each panels by setting background and bounds and adds it to the main frame
	 */
	private void initGameDialog() {
		dialog = new JDialog(frame, "New Game", Dialog.ModalityType.DOCUMENT_MODAL);
		JLabel label = new JLabel("Design for New Game:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.TOP);
		
		//Type
  		JLabel typeLabel = new JLabel();
  		typeLabel.setText("Type");
  		typeLabel.setBounds(15, 20, 80, 20);	
  		
  		String types[] = {"Numbers", "Text"};        					//Game type selection
  		cbType = new JComboBox<String>(types); 
  	    cbType.setBounds(15, 50, 80, 20);
  	    
		//Dimension
		JLabel dimLabel = new JLabel();
		dimLabel.setText("Dimension");
		dimLabel.setBounds(100, 20, 80, 20);		
		
		String dims[] = {"3", "4", "5", "6"};        					//List of the game button dimension
		cbDim = new JComboBox<String>(dims); 
		cbDim.setBounds(100, 50, 40, 20); 
	    
	    okButton = new JButton("Create new game");
	    okButton.setBounds(20, 75, 150, 20);
	    
	    //Add buttons to dialog   		
  		dialog.add(cbType);  		
  		dialog.add(cbDim);
  		dialog.add(okButton);
  		dialog.add(typeLabel);
  		dialog.add(dimLabel);
  		dialog.add(label);
	}
	
	/**
	 * Method name: newGameDialog
	 * Purpose: Set size and visibility
	 */
	public void newGameDialog() {
		dialog.setSize(200,150);
  		dialog.setVisible(true);
  		dialog.setResizable(false);
	}
	
	/**
	 * Method name: portErrorDialog
	 * Purpose: Display error message to the user when user tries start without port
	 * Algorithm: Message with JOptionPane warning message 
	 */
	public void portErrorDialog() {
		JOptionPane.showMessageDialog(frame,
				"Please enter all values.\n Try again.", 
			    "Error notice",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Method name: textInputDialog
	 * Purpose: Display a prompt for use to enter a text for game buttons
	 * Algorithm: Message with JOptionPane showInputDialog method and saves the text in the model
	 * if the text is appropriate length, otherwise it will prompt an error dialog
	 * @param dim Dimension of the new game
	 */
	public void textInputDialog(int dim) {
		while(true) {
			text = (String)JOptionPane.showInputDialog(
	               frame,
	               "Enter a text for the game:", 
	               "Game Text",            
	               JOptionPane.PLAIN_MESSAGE,
	               null,            
	               null, 
	               "Enter here"
	            );	
		
			//Check whether the entered text input is long enough of the game
			if(text == null || text.length() < (dim*dim)) {
				inputErrorDialog();
			} else {
				break;
			}
		}
	}
		
	/**
	 * Method name: inputErrorDialog
	 * Purpose: Display error message to the user when user entered a text too short
	 * Algorithm: Message with JOptionPane warning message 
	 */
	public void inputErrorDialog() {
		JOptionPane.showMessageDialog(frame,
			    "The text you entered is too short.\n"
			    + "Please try again.",
			    "Error notice",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Method name: serverErrorDialog
	 * Purpose: Display error message to the user when trying to connect without server
	 * Algorithm: Message with JOptionPane warning message 
	 */
	public void serverErrorDialog() {
		JOptionPane.showMessageDialog(frame,
				"Unable to connect as Server has not started.", 
			    "Error notice",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Method name: getFrame
	 * Purpose: Return the main frame
	 * @return frame
	 */
	public JFrame getFrame() {
		return this.frame;
	}
	
	/**
	 * Method name: setFrame
	 * Purpose: Sets frame 
	 * @param frame JFrame object 
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * Method name: getPanel
	 * Purpose: Return the panel
	 * @return panel
	 */
	public JPanel getPanel() {
		return this.panel;
	}
	
	/**
	 * Method name: setPanel
	 * Purpose: Sets panel 
	 * @param panel JPanel object 
	 */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	
	/**
	 * Method name: getTextField
	 * Purpose: Return the textField
	 * @return textField
	 */
	public JTextArea getTextField() {
		return this.textField;
	}
	
	/**
	 * Method name: setTextField
	 * Purpose: Sets textField 
	 * @param textField JTextArea object 
	 */
	public void setTextField(JTextArea textField) {
		this.textField = textField;
	}

	/**
	 * Method name: getUserText
	 * Purpose: Return the userText
	 * @return userText
	 */
	public JTextField getUserText() {
		return this.userText;
	}
	
	/**
	 * Method name: setUserText
	 * Purpose: Sets userText 
	 * @param userText JTextField object 
	 */
	public void setUserText(JTextField userText) {
		this.userText = userText;
	}
	
	/**
	 * Method name: getServerText
	 * Purpose: Return the serverText
	 * @return serverText
	 */
	public JTextField getServerText() {
		return this.serverText;
	}
	
	/**
	 * Method name: setServerText
	 * Purpose: Sets serverText 
	 * @param serverText JTextField object 
	 */
	public void setServerText(JTextField serverText) {
		this.serverText = serverText;
	}
	
	/**
	 * Method name: getPortText
	 * Purpose: Return the portText
	 * @return portText
	 */
	public JTextField getPortText() {
		return this.portText;
	}
	
	/**
	 * Method name: setPortText
	 * Purpose: Sets portText 
	 * @param portText JTextField object 
	 */
	public void setPortText(JTextField portText) {
		this.portText = portText;
	}
	
	/**
	 * Method name: getConnectButton
	 * Purpose: Return the connectButton
	 * @return connectButton
	 */
	public JButton getConnectButton(){
		return this.connectButton;
	}
	
	/**
	 * Method name: setConnectButton
	 * Purpose: Sets connectButton 
	 * @param connectButton JButton object 
	 */
	public void setConnectButton(JButton connectButton) {
		this.connectButton = connectButton;
	}
	
	/**
	 * Method name: getNewGameButton
	 * Purpose: Return the newGameButton
	 * @return newGameButton
	 */
	public JButton getNewGameButton(){
		return this.newGameButton;
	}
	
	/**
	 * Method name: setNewGameButton
	 * Purpose: Sets newGameButton 
	 * @param newGameButton JButton object 
	 */
	public void setNewGameButton(JButton newGameButton) {
		this.newGameButton = newGameButton;
	}	
	
	/**
	 * Method name: getEndButton
	 * Purpose: Return the endButton
	 * @return endButton
	 */
	public JButton getEndButton(){
		return this.endButton;
	}
	
	/**
	 * Method name: setNewGameButton
	 * Purpose: Sets endButton 
	 * @param endButton JButton object 
	 */
	public void setEndButton(JButton endButton) {
		this.endButton = endButton;
	}	
	
	/**
	 * Method name: getSendGameButton
	 * Purpose: Return the sendGameButton
	 * @return sendGameButton
	 */
	public JButton getSendGameButton(){
		return this.sendGameButton;
	}
	
	/**
	 * Method name: setSendGameButton
	 * Purpose: Sets sendGameButton 
	 * @param sendGameButton JButton object 
	 */
	public void setSendGameButton(JButton sendGameButton) {
		this.sendGameButton = sendGameButton;
	}	
	
	/**
	 * Method name: getReceiveButton
	 * Purpose: Return the receiveButton
	 * @return receiveButton
	 */
	public JButton getReceiveButton(){
		return this.receiveButton;
	}
	
	/**
	 * Method name: setReceiveButton
	 * Purpose: Sets receiveButton 
	 * @param receiveButton JButton object 
	 */
	public void setReceiveButton(JButton receiveButton) {
		this.receiveButton = receiveButton;
	}	
	
	/**
	 * Method name: getSendDataButton
	 * Purpose: Return the sendDataButton
	 * @return sendDataButton
	 */
	public JButton getSendDataButton(){
		return this.sendDataButton;
	}
	
	/**
	 * Method name: setSendDataButton
	 * Purpose: Sets sendDataButton 
	 * @param sendDataButton JButton object 
	 */
	public void setSendDataButton(JButton sendDataButton) {
		this.sendDataButton = sendDataButton;
	}	
	
	/**
	 * Method name: getPlayButton
	 * Purpose: Return the playButton
	 * @return playButton
	 */
	public JButton getPlayButton(){
		return this.playButton;
	}
	
	/**
	 * Method name: setplayButton
	 * Purpose: Sets playButton 
	 * @param playButton JButton object 
	 */
	public void setplayButton(JButton playButton) {
		this.playButton = playButton;
	}	
	
	/**
	 * Method name: getOkButton
	 * Purpose: Return the okButton
	 * @return okButton
	 */
	public JButton getOkButton(){
		return this.okButton;
	}
	
	/**
	 * Method name: setOkButton
	 * Purpose: Sets okButton 
	 * @param okButton JButton object 
	 */
	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}	
	
	/**
	 * Method name: getCbDim
	 * Purpose: Return the JComboBox String for the different game dimensions
	 * @return JComboBox with list of dimensions
	 */
	public JComboBox<String> getCbDim(){
		return this.cbDim;
	}
	
	/**
	 * Method name: setCbDim
	 * Purpose: Sets combo box with list of dimensions
	 * @param cbDim JComboBox of Strings with list of game dimensions
	 */
	public void setCbDim(JComboBox<String> cbDim) {
		this.cbDim = cbDim;
	}	
	
	/**
	 * Method name: getCbType
	 * Purpose: Return the JComboBox String for the different game types
	 * @return JComboBox with list of types
	 */
	public JComboBox<String> getCbType(){
		return this.cbType;
	}
	
	/**
	 * Method name: setCbType
	 * Purpose: Sets combo box with list of game types
	 * @param cbType JComboBox of Strings with list of game types
	 */
	public void setCbType(JComboBox<String> cbType) {
		this.cbType = cbType;
	}	
	
	/**
	 * Method name: getText
	 * Purpose: Return the text entered by the user
	 * @return text in String form
	 */
	public String getText(){
		return text;
	}
	
	/**
	 * Method name: setText
	 * Purpose: Sets text to class variable
	 * @param text String that stores the text of user input
	 */
	public void setText(String text) {
		this.text = text;
	}	
	
	/**
	 * Method name: getDialog
	 * Purpose: Return the JDialog object
	 * @return dialog object
	 */
	public JDialog getDialog(){
		return dialog;
	}
	
	/**
	 * Method name: setDialog
	 * Purpose: Sets dialog
	 * @param dialog object
	 */
	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}	
}
package View;

import java.awt.Color;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.ServerModel;

/**
 * Class name: ServerView
 * Methods list: resultDialog, clientErrorDialog, and getter and setters
 * Constant list: mainBGColor
 * Purpose: ServerView class sets all the GUI for the client using Swing 
 * @author Amy Fujimoto
 * @version 03 Dec 2022
 * @see javax.swing
 * @since JavaSE-17
 */
public class ServerView {
	/**
	 * JFrame class variable to contain all the GUI and components 
	 */
	private JFrame frame;
	/**
	 * JPanel variable for panel in the frame
	 */
	private JPanel panel;
	/**
	 * JTextArea variable for console on server gui
	 */
	private JTextArea textField;
	/**
	 * JTextField variable for port text field
	 */
	private JTextField portText;
	
	/**
	 * Button for start the connection
	 */
	private JButton startButton;
	/**
	 * Button for result the dialog
	 */
	private JButton resultsButton;
	/**
	 * Button for ending connection
	 */
	private JButton endButton;
	
	/**
	 * JCheckBox for finalizing server shutdown
	 */
	private JCheckBox finalizeBox;	
	
	/**
	 * Constant for main background color
	 */
	public static final Color mainBGColor = new Color(224,227,244);
	
	/**
	 * ServerModel object
	 */
	private ServerModel model;
	
	/**
	 * Method name: Overloaded constructor 
	 * Purpose: Initializes class variables, calls local methods to set GUI for the window and set default values
	 * @param model ServerModel object for access to database
	 * Algorithm: Sets settings server frame, GUI components within the frame
	 */
	public ServerView(ServerModel model) {
		this.model = model;
		
		frame = new JFrame("Server");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		//Only this window closes if x is clicked
		frame.setLayout(null);
		frame.setSize(750,450);
		frame.setResizable(false);
		initServerFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(0);
	}
	
	/**
	 * Method name: initServerFrame
	 * Purpose: Initialize labels and frames
	 */
	private void initServerFrame() {
		JLabel label = new JLabel();
		ImageIcon title = new ImageIcon(getClass().getClassLoader().getResource("images/server.png"));
		label.setIcon(title);
		label.setLayout(null);
		label.setBounds(-50, -150, 1000, 500);
		frame.add(label);
		
		panel = new JPanel();
		panel.setBackground(mainBGColor);
		panel.setBounds(0,210, 750, 200);			//x, y, width, height
		panel.setLayout(null);
		frame.add(panel);
		
		initGUI();
	}
	
	/**
	 * Method name: initGUI
	 * Purpose: Adds multiple panel and menu bar to the main frame of the server view
	 * Algorithm: Initializes the each panels by setting background and bounds and adds it to the main frame
	 */
	private void initGUI() {
		JLabel portLabel = new JLabel("Port:");
		portLabel.setBounds(125, 5, 75, 20);
		
		portText = new JTextField();
		portText.setBounds(155, 5, 75, 20);
		portText.setText("2000");
		
		startButton = new JButton("Start");
	    startButton.setBounds(240, 5, 75, 20);
		
		//grey out by default
	    resultsButton = new JButton("Results");
	    resultsButton.setBounds(323, 5, 80, 20);
	    resultsButton.setEnabled(false);
	    
	    finalizeBox = new JCheckBox("Finalize");  
	    finalizeBox.setBounds(415, 5, 75, 20);  
	    
	    endButton = new JButton("End");
	    endButton.setBounds(500, 5, 75, 20);
	    
	    panel.add(portLabel);
	    panel.add(portText);
	    panel.add(startButton);
	    panel.add(resultsButton);
	    panel.add(finalizeBox);
	    panel.add(endButton);
		
		textField = new JTextArea();
		textField.setBounds(50, 40, 640, 150);
		textField.setEditable(false);
		textField.setVisible(true);
		JScrollPane scroll = new JScrollPane(textField);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(50, 40, 640, 150);
		scroll.setVisible(true);
		PrintStream printStream = new PrintStream(new CustomOutputStream(textField));
		System.setOut(printStream);
		System.setErr(printStream);
		panel.add(scroll);
	}
	
	/**
	 * Method name: resultDialog
	 * Purpose: Display message to the user with results
	 * Algorithm: Message with JOptionPane message dialog
	 * @param Message to be displayed on the dialog in StringBuilder
	 */
	public void resultDialog(StringBuilder sb) {
		JOptionPane.showMessageDialog(frame, sb);
	}
	
	/**
	 * Method name: clientErrorDialog
	 * Purpose: Display error message to the user when user tries to exit 
	 * Algorithm: Message with JOptionPane warning message 
	 */
	public void clientErrorDialog() {
		JOptionPane.showMessageDialog(frame,
				"Clients still connected.\n Please end client before ending server.", 
			    "Error notice",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Method name: portErrorDialog
	 * Purpose: Display error message to the user when user tries start without port
	 * Algorithm: Message with JOptionPane warning message 
	 */
	public void portErrorDialog() {
		JOptionPane.showMessageDialog(frame,
				"No Port Entered.\n Try again.", 
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
	 * Method name: getStartButton
	 * Purpose: Return the startButton
	 * @return startButton
	 */
	public JButton getStartButton(){
		return this.startButton;
	}
	
	/**
	 * Method name: setStartButton
	 * Purpose: Sets startButton 
	 * @param startButton JButton object 
	 */
	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}
	
	/**
	 * Method name: getResultsButton
	 * Purpose: Return the resultsButton
	 * @return resultsButton
	 */
	public JButton getResultsButton(){
		return this.resultsButton;
	}
	
	/**
	 * Method name: setResultsButton
	 * Purpose: Sets resultsButton 
	 * @param resultsButton JButton object 
	 */
	public void setResultsButton(JButton resultsButton) {
		this.resultsButton = resultsButton;
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
	 * Method name: setEndButton
	 * Purpose: Sets endButton 
	 * @param endButton JButton object 
	 */
	public void setEndButton(JButton endButton) {
		this.endButton = endButton;
	}	
	
	/**
	 * Method name: getFinalizeBox
	 * Purpose: Return the finalizeBox
	 * @return finalizeBox
	 */
	public JCheckBox getFinalizeBox(){
		return this.finalizeBox;
	}
	
	/**
	 * Method name: setFinalizeBox
	 * Purpose: Sets finalizeBox 
	 * @param finalizeBox JCheckBox object 
	 */
	public void setFinalizeBox(JCheckBox finalizeBox) {
		this.finalizeBox = finalizeBox;
	}	
}

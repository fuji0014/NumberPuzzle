package View;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.ServerModel;

public class ServerView {
	JFrame frame;
	JPanel panel;
	JTextField textField;
	JTextField portText;
	
	JButton startButton;
	JButton resultsButton;
	JButton endButton;
	
	JCheckBox finalizeBox;	
	
	public static final Color mainBGColor = new Color(224,227,244);
	
	ServerModel model;
	
	public ServerView(ServerModel model) {
		this.model = model;
		
		frame = new JFrame("Server");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		//Only this window closes if x is clicked
		frame.setLayout(null);
		frame.setSize(750,450);
		frame.setResizable(false);
		initServerFrame();
		frame.setVisible(true);
	}
	
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
	
	public void initGUI() {
		JLabel portLabel = new JLabel("Port:");
		portLabel.setBounds(125, 5, 75, 20);
		
		portText = new JTextField();
		portText.setBounds(155, 5, 75, 20);
		
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
		
		textField = new JTextField();
		textField.setBounds(50, 40, 640, 150);
		textField.setEditable(false);
		textField.setVisible(true);
		panel.add(textField);
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public JPanel getPanel() {
		return this.panel;
	}
	
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	
	public JTextField getTextField() {
		return this.textField;
	}
	
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JTextField getPortText() {
		return this.portText;
	}
	
	public void setPortText(JTextField portText) {
		this.portText = portText;
	}
	
	public JButton getStartButton(){
		return this.startButton;
	}
	
	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}
	
	public JButton getResultsButton(){
		return this.resultsButton;
	}
	
	public void setResultsButton(JButton resultsButton) {
		this.resultsButton = resultsButton;
	}	
	
	public JButton getEndButton(){
		return this.endButton;
	}
	
	public void setEndButton(JButton endButton) {
		this.endButton = endButton;
	}	
	
	public JCheckBox getFinalizeBox(){
		return this.finalizeBox;
	}
	
	public void setFinalizeBox(JCheckBox finalizeBox) {
		this.finalizeBox = finalizeBox;
	}	
}

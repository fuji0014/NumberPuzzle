package View;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Model.MainModel;

/**
 * Class name: MainView
 * Methods list: initGameFrame, getter and setters
 * Constant list: N/A
 * Purpose: MainView class sets all the GUI for the first frame using Swing 
 * @author Amy Fujimoto
 * @version 04 Dec 2022
 * @see javax.swing
 * @since JavaSE-17
 */
public class MainView {
	/**
	 * JFrame class variable to contain all the GUI and components 
	 */
	private JFrame frame;
	/**
	 * JComboBox with String that lists the play mode
	 */
	private JComboBox<String> cb;
	/**
	 * Button for ok for frame
	 */
	private JButton okButton;
	/**
	 * Button for cancel for frame
	 */
	private JButton cancelButton;
	/**
	 * MainModel object
	 */
	private MainModel model;
	
	/**
	 * Method name: Overloaded constructor 
	 * Purpose: Initializes class variables, calls local methods to set GUI for the window and set default values
	 * @param model MainModel object for access to database
	 * Algorithm: Sets settings server frame, GUI components within the frame
	 */
	public MainView(MainModel model) {
		this.model = model;
		
		frame = new JFrame("NumPuz");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//Only this window closes if x is clicked
		frame.setLayout(null);
		frame.setSize(250,150);
		frame.setResizable(false);
		initGameFrame();
		frame.setVisible(true);
	}
	
	/**
	 * Method name: initGameFrame
	 * Purpose: Initialize frame and labels
	 */
	private void initGameFrame() {
		JLabel titleLabel = new JLabel();
		titleLabel.setText("NumPuz");
		titleLabel.setBounds(30, 30, 80, 20);
		frame.add(titleLabel);
		
		String mode[] = {"Standalone", "Server", "Client"};
	    cb = new JComboBox<String>(mode); 
	    cb.setBounds(125, 30, 100, 20); 
	    frame.add(cb);
	    
	    okButton = new JButton("OK");
	    okButton.setBounds(30, 70, 75, 20);
	    cancelButton = new JButton("Cancel");
	    cancelButton.setBounds(130, 70, 75, 20);
	    
	    frame.add(okButton);
	    frame.add(cancelButton);
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
	 * Method name: getCbMode
	 * Purpose: Return the JComboBox String for the different game methods
	 * @return JComboBox with list of methods
	 */
	public JComboBox<String> getCbMode(){
		return this.cb;
	}
	
	/**
	 * Method name: setCbMode
	 * Purpose: Sets combo box with list of methods
	 * @param cb JComboBox of Strings with list of game methods
	 */
	public void setCbMode(JComboBox<String> cb) {
		this.cb = cb;
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
	 * Method name: getCancelButton
	 * Purpose: Return the cancelButton
	 * @return cancelButton
	 */
	public JButton getCancelButton(){
		return this.cancelButton;
	}
	
	/**
	 * Method name: setCancelButton
	 * Purpose: Sets cancelButton 
	 * @param cancelButton JButton object 
	 */
	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}	
}

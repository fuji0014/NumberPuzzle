package View;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Model.MainModel;

public class MainView {
	JFrame frame;
	JComboBox<String> cb;
	JButton okButton;
	JButton cancelButton;
	
	MainModel model;
	
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
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public JComboBox<String> getCbMode(){
		return this.cb;
	}
	
	public void setCbMode(JComboBox<String> cb) {
		this.cb = cb;
	}

	public JButton getOkButton(){
		return this.okButton;
	}
	
	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}
	
	public JButton getCancelButton(){
		return this.cancelButton;
	}
	
	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}	
}

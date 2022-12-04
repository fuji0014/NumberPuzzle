package Model;

/**
 * Class name: ServerModel
 * Methods list: getter and setters
 * Constant list: N/A
 * Purpose: ServerModel class is the model for the server  and stores the data for the user
 * @author Amy Fujimoto
 * @version 04 Dec 2022
 * @see javax.swing
 * @since JavaSE-17
 */
public class ServerModel {
	/**
	 * Port used for server
	 */
	private int port;
	/**
	 * Identifying finalization
	 */
	private boolean finalize;
	
	/**
	 * Method name: Overloaded constructor 
	 * Purpose: Initializes class variables and set default values
	 * Algorithm: Sets settings server frame, GUI components within the frame
	 */
	public ServerModel(){
		port = 0;
		finalize = false;
	}
	
	/**
	 * Method name: getPort
	 * Purpose: Return the port entered by the user
	 * @return port in integer form
	 */
	public int getPort() {
		return this.port;
	}
	
	/**
	 * Method name: setPort
	 * Purpose: Sets port to class variable
	 * @param port integer that stores the text of user input
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
	/**
	 * Method name: isFinalize
	 * Purpose: Return the finalize status
	 * @return finalize in boolean
	 */
	public boolean isFinalize() {
		return this.finalize;
	}
	
	/**
	 * Method name: setFinalize
	 * Purpose: Sets finalize status
	 * @param finalize status of finalization in boolean
	 */
	public void setFinalize(boolean finalize) {
		this.finalize = finalize;
	}
	
}

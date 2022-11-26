package Model;

public class ServerModel {
	int port;
	boolean finalize;
	
	public ServerModel(){
		port = 0;
		finalize = false;
	}
	
	public int getPort() {
		return this.port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public boolean isFinalize() {
		return this.finalize;
	}
	
	public void setFinalize(boolean finalize) {
		this.finalize = finalize;
	}
}

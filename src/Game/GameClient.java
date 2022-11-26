package Game;

import Controller.ClientController;
import Model.ClientModel;
import View.ClientView;

public class GameClient {
	ClientModel clientModel;			
	ClientView clientView;	
	ClientController clientController;
	
	public GameClient(){}
	
	public void start() {
		clientModel = new ClientModel();			
		clientView = new ClientView(clientModel);	
		clientController = new ClientController(clientModel, clientView);
	}
}

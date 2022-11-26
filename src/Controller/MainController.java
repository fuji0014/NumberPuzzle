package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import Model.MainModel;
import View.MainView;

public class MainController {
	private MainModel model;
	private MainView view;
	
	public MainController() {}
	
	public MainController(MainModel model, MainView view) {
		this.model = model;
		this.view = view;
		addListeners();
	}
	
	private void addListeners() {
		view.getOkButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setGameModel(view.getCbMode().getSelectedItem().toString());
				model.gameMode();
			}
		});
		
		view.getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}

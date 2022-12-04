package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.MainModel;
import View.MainView;

/**
 * Class name: MainController
 * Methods list: addListeners
 * Constant list: N/A
 * Purpose: MainController class contains the action listener for all the action taken by the user
 * @author Amy Fujimoto
 * @version 03 Dec 2022
 * @see javax.swing
 * @since JavaSE-17
 */
public class MainController {
	/**
	 * MainModel object to be initialized in constructor
	 */
	private MainModel model;
	/**
	 * MainView object to be initialized in constructor
	 */
	private MainView view;
	
	/**
	 * Default constructor
	 */
	public MainController() {}
	
	/**
	 * Method name: MainController, Overloaded constructor 
	 * Purpose: Initialize objects add listeners to buttons
	 * Algorithm: Takes in MainModel and MainView parameter to initialize class variable objects and calls methods
	 * to add listeners
	 * @param model MainModel object 
	 * @param view MainView object
	 */
	public MainController(MainModel model, MainView view) {
		this.model = model;
		this.view = view;
		addListeners();
	}
	
	/**
	 * Method name: addListeners
	 * Purpose: Contains action listener and actionPerformed methods to trigger actions by user
	 * Algorithm: Retrieves button object from game view object and adds listener to each button
	 */
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

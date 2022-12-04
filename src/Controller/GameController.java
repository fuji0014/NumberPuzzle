package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import javax.swing.Timer;

import Model.GameModel;
import View.GameView;
import Game.GameBasic;

import javax.swing.JButton;

/**
 * Class name: GameController
 * Methods list: addListener, puzzleButtonListener, updateGaemButton, saveToCSV
 * Constant list: N/A
 * Purpose: GameController class contains the action listener for all the action taken by the user
 * @author Amy Fujimoto
 * @version 08 Nov 2022
 * @see javax.swing
 * @since JavaSE-17
 */
public class GameController implements ActionListener{
	/**
	 * GameModel object to be initialized in constructor
	 */
	private GameModel model;
	/**
	 * GameView object to be initialized in constructor
	 */
	private GameView view;
	
	/**
	 * Default constructor
	 */
	public GameController() {}
	
	/**
	 * Method name: GameController, Overloaded constructor 
	 * Purpose: Initialize objects and Timer and add listeners to buttons, menu, and puzzleButtons
	 * Algorithm: Takes in GameModel and GameView parameter to initialize class variable objects and calls methods
	 * to add listeners
	 * @param model GameModel object 
	 * @param view GameView object
	 */
	public GameController(GameModel model, GameView view) {
		GameController controller = new GameController();
		this.model = model;
		this.view = view;
		view.setGameController(controller);
	}
	
	/**
	 * Method name: play
	 * Purpose: Starts the game and adding listeners to the buttons
	 * Algorithm: Calls methods from view class and initialize the action listeners
	 */
	public void play() {
		view.play();
		view.setTimer(new Timer(1000, this));
		addButtonListeners();
		addMenuListeners();
		addListeners();
		puzzleButtonListener();
	}
	
	/**
	 * Method name: play
	 * Purpose: Starts the game and sets the game values from parameter
	 * Algorithm: Calls local play method and sets game setting and loads game from the provided data
	 * @param gameBasic GameBasic object
	 */
	public void play(GameBasic gameBasic) {
		play();
		view.setGameBasic(gameBasic);
		view.loadGameFromData();
	}
	
	/**
	 * Method name: addButtonListeners
	 * Purpose: Contains action listener and actionPerformed methods to trigger actions by user
	 * Algorithm: Retrieves button object from game view object and adds listener to each button
	 */
	private void addButtonListeners() {	
		//Color dialog buttons
		view.getMainBGColorButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setCurrentColor(GameView.mainBGColor);
				updateGameButton();
			}
		});
		
		view.getGameBGColorButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setCurrentColor(GameView.gameBGColor);
				updateGameButton();
			}
		});
		
		view.getGameButColorButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setCurrentColor(GameView.gameButColor);
				updateGameButton();
			}
		});
		
		view.getGameButColorNullButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setCurrentColor(GameView.gameButColorNull);
				updateGameButton();
			}
		});
		
		view.getMenuColorButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setCurrentColor(GameView.menuColor);
				updateGameButton();
			}
		});
		
		view.getCloseButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getDialog().dispose();
			}
		});
	}
	
	/**
	 * Method name: addMenuListeners
	 * Purpose: Contains action listener and actionPerformed methods to trigger actions by user
	 * Algorithm: Retrieves menu item objects from game view object and adds listener to each menu items
	 */
	private void addMenuListeners() {
		view.getNewGame().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.newGame();
			}
		});
		
		view.getSolution().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!view.puzzleCompleted())
					view.showAnswerWindow();
				else
					view.displayShuffleErrorDialog();
			}
		});
		
		view.getSaveGame().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(saveToCSV()) 
					view.successfulSaveDialog();
				else 
					view.saveErrorDialog();
			}
		});
		
		view.getLoadGame().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.removeGameButton();
				if(view.loadFromCSV()) {
					view.successfulLoadDialog();
					view.gameButton(view.getCurrentColor());
					puzzleButtonListener();
		    		view.updateEmptyTile();
										
					//Update time and points
					view.restartSettings();
					
					view.getPanel("middle").revalidate();
		    		view.getPanel("middle").repaint();
					
				} else {
					// TODO Redraw the buttons
					view.loadErrorDialog();
				}
			}
		});
		
		view.getExit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.exitDialog();
			}
		});
		
		view.getColor().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("color");
				view.colorDialog();
			}
		});
		
		view.getAbout().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.aboutDialog();
			}
		});
	}
	
	/**
	 * Method name: addListeners
	 * Purpose: Contains action listener and actionPerformed methods to trigger actions by user
	 * Algorithm: Retrieves different component objects in the frame from game view object 
	 * and adds listener to each menu items 
	 */
	private void addListeners() {	
		view.getColorButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				view.getTimer().stop();
				view.colorDialog();
			}
		});
		
		view.getShowButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("here");
				if(!view.puzzleCompleted())
					view.showAnswerWindow();
				else
					view.displayShuffleErrorDialog();
			}
		});
		
  	  	view.getCbType().addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {	
	    		view.getTimer().stop();
	    		model.setGameType(view.getCbType().getSelectedItem().toString());
	    		view.removeGameButton();
	    		if(!model.isNumberGameType())
	    			view.textInputDialog();
	    			
		    	view.initPuzzleButton();
	    		view.gameButton(view.getCurrentColor());
	    		puzzleButtonListener();
	    		
	    		//Update time and points
	    		view.restartSettings();
	    		
	    		view.getPanel("middle").revalidate();
	    		view.getPanel("middle").repaint();
		    	
			}
	    });
  	  	
  	  	view.getCbDim().addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {	
	    		if(!model.isNumberGameType()) {
	    			view.getCbType().setSelectedIndex(0);
	    			view.displayErrorDialog();
	    		}
	    		
	    		//Update button size
	    		view.removeGameButton();
	    		model.setDimSize(Integer.parseInt(view.getCbDim().getSelectedItem().toString()));
	    		model.setDimSize(model.getDimSize());
	    		view.initPuzzleButton();
	    		view.gameButton(view.getCurrentColor());
	    		puzzleButtonListener();
	    		
	    		//Update time and points
	    		view.restartSettings();
	    		
	    		view.getPanel("middle").revalidate();
	    		view.getPanel("middle").repaint();
			}
	    });
  	  
	    view.getCbLevel().addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
	    		model.setGameLevel(view.getCbLevel().getSelectedItem().toString());
	    		view.restartSettings();
			}
	    });
  	  
		view.getStartButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				view.getTimer().start();
			}
		});
		
		view.getStopButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				view.getTimer().stop();
			}
		});
		
		view.getResetButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.restartSettings();
			}
		});
		
		view.getNgButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.newGame();
			}
		});

	}
	
	/**
	 * Method name: puzzleButtonListener
	 * Purpose: Contains action listener and actionPerformed methods to trigger actions by user
	 * Algorithm: Loops over the retrieved puzzle button array objects from game view object 
	 * and adds listener to each button  
	 */
	public void puzzleButtonListener() {
		for (int i = 0; i < model.getDimSize(); ++i) {
			for (int j = 0; j < model.getDimSize(); ++j) {
				if(view.getPuzzleButton()[i][j].getText() != null){
					view.getPuzzleButton()[i][j].addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {							
							view.getTimer().start();
							if(!view.puzzleCompleted()) {
								view.moveButton((JButton)e.getSource());
								if(view.puzzleCompleted()) {
									model.calculateTotalPoints();
									view.updatePointLabel();
									view.displayWinDialog();
								}
							} else {
								view.displayShuffleErrorDialog();
							}
						}
					});	
				}
			}
		}
	}
	
	public void puzzleButtonListenerForData(int dimSize) {
		for (int i = 0; i < dimSize; ++i) {
			for (int j = 0; j < dimSize; ++j) {
				if(view.getPuzzleButton()[i][j].getText() != null){
					view.getPuzzleButton()[i][j].addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {							
							view.getTimer().start();
							if(!view.puzzleCompleted()) {
								view.moveButton((JButton)e.getSource());
								if(view.puzzleCompleted()) {
									model.calculateTotalPoints();
									view.updatePointLabel();
									view.displayWinDialog();
								}
							} else {
								view.displayShuffleErrorDialog();
							}
						}
					});	
				}
			}
		}
	}

	/**
	 * Method name: updateGameButton
	 * Purpose: Contains action listener and actionPerformed methods to trigger actions by user
	 * Algorithm: Loops over the retrieved puzzle button array objects from game view object 
	 * and adds listener to each button   
	 */
	private void updateGameButton() {
		int size = view.getPuzzleButton().length;
		
		view.getPanel("middle").setLayout(view.getGameTiles());
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				//Change last button(set to null) to grey
				if(view.getPuzzleButton()[i][j].getText() == null){
					view.getPuzzleButton()[i][j].setBackground(GameView.gameButColorNull);
				} else {
					view.getPuzzleButton()[i][j].setBackground(view.getCurrentColor());					
				}
				view.getPanel("middle").add(view.getPuzzleButton()[i][j]);					
			}
		}
	}
	
	/**
	 * Method name: saveToCSV
	 * Purpose: Contains method to save the current puzzle game to csv file
	 * Algorithm: Loops over the retrieved puzzle button array objects from game view object 
	 * and saves it in to the file letter by letter
	 * @return true if saving was successful, else return false
	 */
	public boolean saveToCSV(){
		try {
			FileWriter writer = new FileWriter("C:\\Users\\Admin\\CST8221_workspace\\JAP_A22\\src\\save-load_file\\numpuzFile.csv");
	
			for(int i = 0; i < view.getPuzzleButton().length; i++) {
				for (int j = 0; j < view.getPuzzleButton()[i].length; j++) {
				    writer.append(view.getPuzzleButton()[i][j].getText());
				    writer.append(",");
				}
				writer.append("\n");
			}
			
			if(view.getText() != null) {
				writer.append(view.getText());
			} else {
				for(int i = 1; i < (model.getDimSize()*model.getDimSize()); i++)
					writer.append(Integer.toString(i));
				writer.append("0");
			}
			
			writer.close();
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	/**
	 * Method name: actionPerformed
	 * Purpose: actionPerformed to trigger actions by user
	 * Algorithm: Sets time and updates time count  
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		model.setTime(model.getTime() - 1);
		view.updateTimeCount();
		view.timerSettings();
	}
	
}



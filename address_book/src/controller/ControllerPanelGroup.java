package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ModelAddressBook;
import view.ViewPanelGroup;

/**
 * Action listener for group displaying.
 * 
 * <p>This class follows the MVC pattern concepts.
 * This class represents group displaying panel controller implementing ActionListener interface</p>
 * 
 * @author Julien ADAM
 * @version 1.0
 * @see ActionListener
 */
public class ControllerPanelGroup implements ActionListener {

	/**
	 * model representing view data
	 */
	private ModelAddressBook model;
	/**
	 * represents displaying to the user
	 */
	private ViewPanelGroup view;

	/**
	 * Construct a panel block listener
	 * @param model : data on which the view is based
	 * @param view : the displayed content
	 */
	public ControllerPanelGroup(ModelAddressBook model, ViewPanelGroup view) {
		this.model = model;
		this.view = view;
	}

	@Override
	/**
	 * Catch actions raised by group displaying panel
	 * @param e : the action raised by the view
	 * @see ActionListener
	 */
	public void actionPerformed(ActionEvent e) {
		//re-initialization of contacts list in order to apply a new group filter 
		model.filterByContactInit();

		//switch according to actions raised by user
		if(e.getSource() == view.buttonAll) model.filterByGroup("ALL");
		else if(e.getSource() == view.buttonFamily) model.filterByGroup("FAMILY");
		else if(e.getSource() == view.buttonFriends) model.filterByGroup("FRIENDS");
		else if(e.getSource() == view.buttonOthers) model.filterByGroup("OTHERS");
	}

}

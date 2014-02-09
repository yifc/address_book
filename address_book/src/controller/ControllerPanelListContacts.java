package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ModelAddressBook;
import view.ViewPanelListContacts;

/**
 * Action listener for the contacts list panel.
 * 
 * <p>This class follows the MVC pattern concepts.
 * This class represents the contacts list controller implementing ActionListener interface</p>
 * @author Julien ADAM
 * @version 1.0
 */
public class ControllerPanelListContacts implements ActionListener {

	/**
	 * represents data on which view is based
	 */
	private ModelAddressBook model;
	/**
	 * represents the display
	 */
	private ViewPanelListContacts view;

	/**
	 * Construct a contacts list panel Controller
	 * @param model the model attached to this controller
	 * @param view the view where the controller listens
	 */
	public ControllerPanelListContacts(ModelAddressBook model, ViewPanelListContacts view) {
		this.model = model;
		this.view = view;
	}

	@Override
	/**
	 * Define actions to do for each action raised by the user.
	 * More specifically, the controller display contacts according to filter group
	 * @param e : the event raised by the user
	 * @see ActionListener
	 */
	public void actionPerformed(ActionEvent e) {
		for(int i=0; i< view.tabContacts.size(); i++){
			if(e.getSource() == view.tabContacts.get(i))
				model.filterByContact(view.tabContacts.get(i).getText());
		}
	}
}

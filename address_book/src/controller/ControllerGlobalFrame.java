package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ModelAddressBook;
import modelSingleThread.ModelContactSingleThread;

import view.ViewGlobalFrame;

/**
 * Action listener for the main frame.
 * 
 * <p>This class follows the MVC pattern concepts.
 * This class represents the main frame controller implementing ActionListener interface</p>
 * @author Julien ADAM
 * @version 1.0
 * @see ActionListener
 */
public class ControllerGlobalFrame implements ActionListener {

	/**
	 * view of the main frame
	 */
	private ViewGlobalFrame mainView;
	/**
	 * modelSingleThread used by main frame
	 */
	private ModelAddressBook mainModel;

	/**
	 * Construct a Main Frame Controller
	 * @param model the model attached to this controller
	 * @param view the view where the controller listens
	 */
	public ControllerGlobalFrame(ModelAddressBook model, ViewGlobalFrame view){
		mainView = view;
		mainModel = model;
	}

	@Override
	/**
	 * This function triggers some events raised by user when he press main frame buttons
	 * @param arg0 : Event raised and triggered by controller
	 * @see ActionListener
	 */
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == mainView.alphabeticalSort) //Alphabetical sorting button
			mainModel.sortPerAlphabeticalOrder();
		else if(arg0.getSource() == mainView.lastUpdatedSort) //last updated sorting button
			mainModel.sortPerLastUpdated();
		else if(mainView.panelContact.isInitied() == true){ // action raising a contact modification
			ModelContactSingleThread current = new ModelContactSingleThread( //contact creation
					mainView.panelContact.fieldGroup.getSelectedItem().toString(),
					mainView.panelContact.fieldName.getText(),
					mainView.panelContact.fieldFirstName.getText(),
					mainView.panelContact.fieldHomeAddress.getText(),
					mainView.panelContact.fieldCompanyAddress.getText(),
					mainView.panelContact.fieldPersPhone.getText(),
					mainView.panelContact.fieldWorkPhone.getText(),
					mainView.panelContact.fieldEmail.getText(),
					mainView.panelContact.fieldHomePage.getText(),
					mainView.panelContact.fieldLink.getText());

			//marked as present in contact list (as this contact is modified, it is present in contact list)
			current.setRequired(true);

			//selecting case according to operation chosen by user
			if(arg0.getSource() == mainView.saveContact)
				mainModel.updateContact(current);
			else if(arg0.getSource() == mainView.addContact)
				mainModel.addContact(current);
			else if(arg0.getSource() == mainView.deleteContact)
				mainModel.deleteContact(current);
			else if(arg0.getSource() == mainView.saveBook){
				mainModel.saveContact();
			}
		}
	}
}

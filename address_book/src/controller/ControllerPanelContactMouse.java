package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.ViewPanelContact;

import model.ModelAddressBook;
/**
 * Action listener for contact displaying, especially about mouse actions.
 * 
 * <p>This class follows the MVC pattern concepts.
 * This class represents contact displaying panel controller implementing MouseListener interface</p>
 * 
 * @author Julien ADAM
 * @version 1.0
 * @see MouseListener
 */
public class ControllerPanelContactMouse implements MouseListener {

	/**
	 * Represents internal object on which address book is used. THis modelSingleThread contains contacts.
	 */
	private ModelAddressBook model;
	/**
	 * Represents contact displaying to the user
	 */
	private ViewPanelContact view;

	/**
	 * Construct a MouseListener on contact displaying panel
	 * @param modelSingleThread : modelSingleThread on which the view is based
	 * @param view : the view displayed to the user
	 */
	public ControllerPanelContactMouse(ModelAddressBook model, ViewPanelContact view){
		this.model = model;
		this.view = view;

	}

	@Override
	/**
	 * Catch actions raised by contact displaying panel
	 * @param arg0 : the action raised and triggered by controller
	 * @see MouseListener
	 */
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getButton() == MouseEvent.BUTTON1){
			if(arg0.getSource() == view.fieldEmail){
				model.openEmail(view.fieldEmail.getText());
			}
			else if(arg0.getSource() == view.fieldHomePage){
				model.openHomePage(view.fieldHomePage.getText());
			}
		}

	}

	@Override
	/**
	 * Implements MouseListener interface, not used here
	 * @see MouseListener
	 */
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Implements MouseListener interface, not used here
	 * @see MouseListener
	 */
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Implements MouseListener interface, not used here
	 */
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Implements MouseListener interface, not used here
	 */
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}

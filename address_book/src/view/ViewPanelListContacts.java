package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import controller.ControllerPanelListContacts;

import model.ModelAddressBook;

/**
 * Represent contacts list panel
 * @author Julien ADAM
 * @version 1.0
 *
 */
public class ViewPanelListContacts extends JPanel {

	/**
	 * Serial ID not used, but required by inheritance
	 * @see JPanel
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Contains all contact's action buttons
	 */
	public List<JButton> tabContacts;
	private final Dimension sizeButton = new Dimension(180,20);

	/**
	 * Constructs a contacts list panel
	 * @param model model used to load contacts' data
	 */
	public ViewPanelListContacts(ModelAddressBook model) {
		super();
		ControllerPanelListContacts controller = new ControllerPanelListContacts(model, this);
		tabContacts = new ArrayList<JButton>();
		setLayout(new GridLayout(model.getNbRequired(),1));

		for(int i = 0; i < model.getNbContacts(); i++){

			if(model.getBook().get(i).isRequired()){//if filter group have chosen to keep this contact
				JButton current = new JButton(model.getBook().get(i).getLastName() + " " + model.getBook().get(i).getFirstName());

				current.setPreferredSize(sizeButton);
				current.addActionListener(controller);
				add(current);

				tabContacts.add(current);
			}
		}
	}
}

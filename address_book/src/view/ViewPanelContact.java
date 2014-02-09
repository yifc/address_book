package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.ModelAddressBook;
import modelSingleThread.ModelContactSingleThread;
import controller.ControllerPanelContactMouse;

/**
 * Represents contact panel displaying
 * @author Julien ADAM
 * @version 1.0
 *
 */
public class ViewPanelContact extends JPanel {

	/**
	 * Serial ID, not used but required by inheritance
	 * @see JPanel
	 */
	private static final long serialVersionUID = 1L;
	private final Dimension sizePanel = new Dimension(400,200);
	private final JLabel name = new JLabel("Name :");
	public JTextField fieldName;
	private final JLabel firstName = new JLabel("First Name :");
	public JTextField fieldFirstName;
	private final JLabel homeAddress = new JLabel("Home Address :");
	public JTextField fieldHomeAddress;
	private final JLabel companyAddress = new JLabel("Work Address :");
	public JTextField fieldCompanyAddress;
	private final JLabel persPhone = new JLabel("Phone :");
	public JTextField fieldPersPhone;
	private final JLabel workPhone = new JLabel("Phone (work) :");
	public JTextField fieldWorkPhone;
	private final JLabel homePage = new JLabel("Homepage :");
	public JLabel fieldHomePage;
	private final JLabel email = new JLabel("Email :");
	public JLabel fieldEmail;
	private final JLabel link = new JLabel("Link :");
	public JTextField fieldLink;
	private final JLabel group = new JLabel("Group :");
	public JComboBox<String> fieldGroup;
	/**
	 * Check if a contact have been initialized or not
	 */
	private boolean init;

	/**
	 * constructs a new contact panel
	 * @param model model used to find contact's data
	 */
	public ViewPanelContact(ModelAddressBook model) {
		super();
		ModelContactSingleThread contact = null;
		ControllerPanelContactMouse controllerMouse = new ControllerPanelContactMouse(model, this);
		init = false;
		setPreferredSize(sizePanel);
		setLayout(new GridLayout(10, 2));
		//find contact in address book
		for(int i = 0; i < model.getNbContacts(); i++){
			if(model.getBook().get(i).isDisplayed()){
				contact = model.getBook().get(i);
			}
		}
		//if not found
		if(contact == null){
			return;
		}
		add(name);
		fieldName = new JTextField(contact.getLastName());
		add(fieldName);

		add(firstName);
		fieldFirstName = new JTextField(contact.getFirstName());
		add(fieldFirstName);

		add(homeAddress);
		fieldHomeAddress = new JTextField(contact.getHomeAddress());
		add(fieldHomeAddress);

		add(companyAddress);
		fieldCompanyAddress = new JTextField(contact.getCompanyAddress());
		add(fieldCompanyAddress);

		add(persPhone);
		fieldPersPhone = new JTextField(contact.getPersPhone());
		add(fieldPersPhone);

		add(workPhone);
		fieldWorkPhone = new JTextField(contact.getWorkPhone());
		add(fieldWorkPhone);

		add(homePage);
		fieldHomePage = new JLabel(contact.getHomePage());
		fieldHomePage.addMouseListener(controllerMouse);
		add(fieldHomePage);

		add(email);
		fieldEmail = new JLabel(contact.getEmail());
		fieldEmail.addMouseListener(controllerMouse);
		add(fieldEmail);

		add(link);
		fieldLink = new JTextField(contact.getLink());
		add(fieldLink);

		add(group);
		fieldGroup = new JComboBox<String>();
		fieldGroup.addItem("FAMILY");
		fieldGroup.addItem("OTHERS");
		fieldGroup.addItem("FRIENDS");
		fieldGroup.setSelectedItem(contact.getGroup());
		add(fieldGroup);

		init = true;
	}	

	/**
	 * Check if a contact have been loaded in frame
	 * @return true if a contact is loaded, false otherwise
	 */
	public boolean isInitied(){
		return init;
	}
}

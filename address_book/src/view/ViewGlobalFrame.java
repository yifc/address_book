package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.ControllerGlobalFrame;

import model.ModelAddressBook;

/**
 * Represents the main frame, displayed to the user
 * @author Julien ADAM
 * @version 1.0
 */
public class ViewGlobalFrame extends JFrame implements Observer {

	/**
	 * Serial ID, not used by the class but imposed by inheritance
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Frame size
	 */
	private final Dimension sizeFrame = new Dimension(850,400);
	/**
	 * Menu size (included in frame size)
	 */
	private final Dimension sizeMenu = new Dimension(850,80);
	/**
	 * Contents size (included in frame size)
	 */
	private final Dimension sizeContent = new Dimension(850,200);
	
	/**
	 * modelSingleThread to observe in order to update on each change
	 */
	private ModelAddressBook model;
	private ViewPanelGroup panelGroup;
	private ViewPanelListContacts panelList;
	private JPanel contents;
	private JPanel menu;
	
	/**
	 * contact displaying panel
	 */
	public ViewPanelContact panelContact;

	public JButton alphabeticalSort;
	public JButton lastUpdatedSort;
	public JButton saveContact;
	public JButton addContact;
	public JButton deleteContact;
	public JButton saveBook;
	
	/**
	 * Construct a main frame
	 * @param modelSingleThread : modelSingleThread on which main frame is based
	 */
	public ViewGlobalFrame(ModelAddressBook model){
		super("Address Book");
		this.model = model;
		this.model.trackObservable(this);
		this.setSize(sizeFrame);
		this.setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//move frame on the center of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
		
		//draw the frame
		drawFrame();
		//active displaying
		setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		//delete all contents
		getContentPane().removeAll();
		//recreate all contents
		drawFrame();
		//save
		revalidate();
		//redraw
		repaint();
	}
	
	/**
	 * Main function, which draws the frame
	 */
	private void drawFrame(){
		
		//GROUP
		panelGroup = new ViewPanelGroup(model);
		TitledBorder titleGroup;
		titleGroup = BorderFactory.createTitledBorder("Group");
		panelGroup.setBorder(titleGroup);

		//LIST
		panelList = new ViewPanelListContacts(model);
		TitledBorder titleContacts;
		titleContacts = BorderFactory.createTitledBorder("List");		
		JScrollPane scrolled = new JScrollPane(panelList);
		Dimension sizeScrolled = new Dimension(220,200);
		scrolled.setPreferredSize(sizeScrolled);
		scrolled.setBorder(titleContacts);

		//CONTACT
		panelContact = new ViewPanelContact(model);
		TitledBorder titleContact;
		titleContact = BorderFactory.createTitledBorder("Contact");
		panelContact.setBorder(titleContact);

		//MENU
		menu = new JPanel();
		menu.setLayout(new FlowLayout());
		
		ControllerGlobalFrame menuController = new ControllerGlobalFrame(model, this);
		menu.setPreferredSize(sizeMenu);
		TitledBorder titleMenu;
		titleMenu = BorderFactory.createTitledBorder("Menu");
		menu.setBorder(titleMenu);
		setLayout(new FlowLayout());
		alphabeticalSort = new JButton("Alphabetical Sort");
		lastUpdatedSort = new JButton("Last Updated Sort");
		saveContact = new JButton("Save Contact");
		addContact = new JButton("Add Contact");
		deleteContact = new JButton("Delete Contact");
		saveBook = new JButton("Save Book");
		
		alphabeticalSort.addActionListener(menuController);
		lastUpdatedSort.addActionListener(menuController);
		saveContact.addActionListener(menuController);
		addContact.addActionListener(menuController);
		deleteContact.addActionListener(menuController);
		saveBook.addActionListener(menuController);	
		menu.add(alphabeticalSort, BorderLayout.WEST);
		menu.add(lastUpdatedSort, BorderLayout.WEST);
		menu.add(saveBook, BorderLayout.WEST);
		menu.add(saveContact, BorderLayout.EAST);
		menu.add(addContact, BorderLayout.EAST);
		menu.add(deleteContact, BorderLayout.EAST);
		
		//CONTENTS
		contents = new JPanel();
		contents.setLayout(new FlowLayout());
		contents.setPreferredSize(sizeContent);
		contents.add(panelGroup, BorderLayout.NORTH);
		contents.add(scrolled, BorderLayout.NORTH);
		contents.add(panelContact, BorderLayout.NORTH);

		//ADDING
		setLayout(new FlowLayout());
		add(menu);
		add(contents);
	}
}

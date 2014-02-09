package view;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ControllerPanelGroup;
import model.ModelAddressBook;

/**
 * Represents a group panel
 * @author Julien ADAM
 * @version 1.0
 *
 */
public class ViewPanelGroup extends JPanel {

	/**
	 * Serial ID not used, but required by inheritance
	 * @see JPanel
	 */
	private static final long serialVersionUID = 1L;

	private final Dimension sizePanel = new Dimension(200,200);
	private final Dimension sizeButton = new Dimension(180,20);
	public JButton buttonFriends;
	public JButton buttonAll;
	public JButton buttonFamily;
	public JButton buttonOthers;
	
	/**
	 * Construct a group panel
	 * @param model model used to load group data
	 */
	public ViewPanelGroup(ModelAddressBook model) {
		super();
		ControllerPanelGroup controller = new ControllerPanelGroup(model, this);
		
		setPreferredSize(sizePanel);
		
		buttonAll = new JButton("All");
		buttonFriends= new JButton("Friends");
		buttonFamily = new JButton("Family");
		buttonOthers = new JButton("Others");
		
		buttonAll.addActionListener(controller);
		buttonFamily.addActionListener(controller);
		buttonFriends.addActionListener(controller);
		buttonOthers.addActionListener(controller);
		
		buttonAll.setPreferredSize(sizeButton);
		buttonFamily.setPreferredSize(sizeButton);
		buttonFriends.setPreferredSize(sizeButton);
		buttonOthers.setPreferredSize(sizeButton);
		
		add(buttonAll);
		add(buttonFamily);
		add(buttonFriends);
		add(buttonOthers);
	}
}

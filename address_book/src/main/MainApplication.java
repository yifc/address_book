package main;

import controller.*;
import view.*;
import model.*;

/**
 * Launcher of the application
 * @author Julien ADAM
 * @version 1.0
 */
public class MainApplication {

	/**
	 * Main function
	 * @param args : not used
	 */
	public static void main(String[] args) {
		ModelAddressBook mainModel = new ModelAddressBook("/home/adamj/file.txt");
		ViewGlobalFrame mainView = new ViewGlobalFrame(mainModel);
		@SuppressWarnings("unused")
		ControllerGlobalFrame mainController = new ControllerGlobalFrame(mainModel, mainView);
	}

}

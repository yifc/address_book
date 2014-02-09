package model;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import modelSingleThread.*;

/**
 * Class representing an interface between single thread model and the view. This class
 * have to create a thread before to interact with model. Its our multi-threaded version
 * of the address book
 * @author Julien ADAM
 * @version 1.0
 * @see Observable
 */
public final class ModelAddressBook {

	/**
	 * The single thread model to protect
	 */
	private modelSingleThread.ModelAddressBookSingleThread model;
	private ExecutorService launcher;
	private int nbThreads;

	/**
	 * Constructs an address book
	 * @param filename : the path to the CSV file where contacts are stored
	 */
	public ModelAddressBook(String filename){

		model = new modelSingleThread.ModelAddressBookSingleThread(filename);
		nbThreads = (int)(Runtime.getRuntime().availableProcessors()/(1 - 0.1));
		launcher = Executors.newFixedThreadPool(nbThreads);
	}

	public void trackObservable(Observer o){
		model.addObserver(o);
	}
	
	@Override
	public String toString() {
		return model.toString();
	}

	/**
	 * looks for a contact in the address book according to the contact last name
	 * @param contact to find in address book
	 * @return if succeeded, the list indice where contact is stored in database. Otherwise, -1
	 */
	public int findContact(final ModelContactSingleThread contact){
		Future<Integer> res = launcher.submit(new Callable<Integer>(){

			@Override
			public Integer call() throws Exception {
				return model.findContact(contact);
			}
			
		});
		try {
			return res.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Update a contact in the address book. If the contact is not found in the base, he is added instead.
	 * @param contact : the contact to update
	 */
	public void updateContact(final ModelContactSingleThread contact){

		launcher.submit(new Callable<Object>(){

			@Override
			public Object call() throws Exception {
				model.updateContact(contact);
				return null;
			}
			
		});
	}

	/**
	 * Delete given contact from the database.
	 * @param contact : the contact to delete
	 */
	public void deleteContact(final ModelContactSingleThread contact){

		launcher.submit(new Callable<Object>(){

			@Override
			public Object call() throws Exception {
				model.deleteContact(contact);
				return null;
			}
		});
	}

	/**
	 * Add a contact to the address book
	 * @param contact : contact to add
	 */
	public void addContact(final ModelContactSingleThread contact){
		launcher.submit(new Callable<Object>(){

			@Override
			public Object call() throws Exception {
				model.addContact(contact);
				return null;
			}
			
		});
	}

	/**
	 * Reorder the address book to sort contacts per alphabetical order
	 */
	public void sortPerAlphabeticalOrder(){
		launcher.submit(new Callable<Object>(){

			@Override
			public Object call() throws Exception {
				model.sortPerAlphabeticalOrder();
				return null;
			}
			
		});
	}

	/**
	 * Reorder the address book to sort contacts per Last Updated, from the last updated to the first updated.
	 */
	public void sortPerLastUpdated(){
		launcher.submit(new Callable<Object>(){

			@Override
			public Object call() throws Exception {
				model.sortPerLastUpdated();
				return null;
			}
			
		});
	}

	/**
	 * Get the number of contacts registered in the address book
	 * @return the number of contacts
	 */
	public int getNbContacts(){
		return model.getBook().size();
	}

	/**
	 * Save the address book content in CSV file (the same as used to load address book)
	 */
	public void saveContact(){
		launcher.submit(new Callable<Object>(){

			@Override
			public Object call() throws Exception {
				model.saveContact();
				return null;
			}
			
		});
	}

	/**
	 * Apply a filter in order to mark contact which have to be displayed according to the given group filter
	 * @param group : group used to filter contacts. If group is "ALL", all contacts will be kept.
	 */
	public void filterByGroup(final String group){
		
		launcher.submit(new Callable<Object>(){

			@Override
			public Object call() throws Exception {
				model.filterByGroup(group);
				return null;
			}
			
		});
	}

	/**
	 * Apply a filter in order to find the contact to display according to the id selected by the user
	 * @param id : a chain constituted by last and first name from the selected contact 
	 */
	public void filterByContact(final String id){
		
		launcher.submit(new Callable<Object>(){

			@Override
			public Object call() throws Exception {
				model.filterByContact(id);
				return null;
			}
		});
	}

	/**
	 * Re-initialization of contact filter
	 */
	public void filterByContactInit(){
		launcher.submit(new Callable<Object>(){

			@Override
			public Object call() throws Exception {
				model.filterByContactInit();
				return null;
			}
		});
	}

	public List<modelSingleThread.ModelContactSingleThread> getBook() {
		return model.getBook();
	}

	public void setBook(List<ModelContactSingleThread> book) {
		model.setBook(book);
	}

	/**
	 * give the number of contact to keep after group filter application
	 * @return : the number of contacts selected
	 */
	public int getNbRequired() {
		Future<Integer> res = launcher.submit(new Callable<Integer>(){

			@Override
			public Integer call() throws Exception {
				return model.getNbRequired();
			}
		});
		try {
			return res.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Open a web browser (if supported) and access to a specifing URL
	 * @param url : url to access
	 * @return True if browser opening succeeded. False otherwise.
	 */
	public boolean openHomePage(String url) {
		return model.openHomePage(url);
	}

	/**
	 * Open the mail client and start e-mail redaction for the given email address
	 * @param mail : email to send an e-mail
	 * @return : True if mail client opening succeeded. False otherwise.
	 */
	public boolean openEmail(String mail){
		return model.openEmail(mail);
	}
}

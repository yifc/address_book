package model;

import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Class representing an address book with contacts
 * @author Julien ADAM
 * @version 1.0
 * @see Observable
 */
public final class ModelAddressBook extends Observable {

	/**
	 * the list of contacts
	 */
	private List<ModelContact> book;
	/**
	 * the CSV filename where contacts are stored on filesystem
	 */
	private String filename;

	/**
	 * Constructs an address book
	 * @param filename : the path to the CSV file where contacts are stored
	 */
	public ModelAddressBook(String filename){

		String line;
		book = new ArrayList<ModelContact>();
		this.filename = filename;
		try{ //File reading
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while((line = br.readLine()) != null){
				String[] tabArgs = line.split(",");
				book.add(new ModelContact(tabArgs[0], tabArgs[1], tabArgs[2], tabArgs[3], tabArgs[4], tabArgs[5], tabArgs[6], tabArgs[7], tabArgs[8], tabArgs[9]));
			}
			br.close();
		}
		catch(Exception e){
			System.out.println("Error : "+ e);
		}
		//Notify observer the list have been updated
		toUpdate();
	}

	/**
	 * Notifying function to the Observer objects, transmitting information that the address book have been updated
	 */
	public void toUpdate(){
		setChanged();
		notifyObservers();
	}

	@Override
	public String toString() {
		String chain = "";
		for(int i = 0; i < book.size(); i++)
			chain += " * " + book.get(i).toString() + "\n";
		return chain;
	}

	/**
	 * looks for a contact in the address book according to the contact last name
	 * @param contact to find in address book
	 * @return if succeeded, the list indice where contact is stored in database. Otherwise, -1
	 */
	public int findContact(ModelContact contact){
		for(int i=0; i < book.size(); i++){
			if(book.get(i).getLastName().compareTo(contact.getLastName()) == 0) return i;
		}
		return -1;
	}

	/**
	 * Update a contact in the address book. If the contact is not found in the base, he is added instead.
	 * @param contact : the contact to update
	 */
	public void updateContact(ModelContact contact){

		int indice = this.findContact(contact);
		if(indice < 0){
			addContact(contact);
			return;
		}

		book.set(indice, contact);
		toUpdate();
	}

	/**
	 * Delete given contact from the database.
	 * @param contact : the contact to delete
	 */
	public void deleteContact(ModelContact contact){
		int indice = this.findContact(contact);
		if(indice < 0){
			System.out.println("Contact " + contact.getLastName() + " " + contact.getFirstName() + " doesn't exist !");
			return;
		}
		book.remove(indice);
		toUpdate();
	}

	/**
	 * Add a contact to the address book
	 * @param contact : contact to add
	 */
	public void addContact(ModelContact contact){
		book.add(contact);
	}

	/**
	 * Reorder the address book to sort contacts per alphabetical order
	 */
	public void sortPerAlphabeticalOrder(){
		Collections.sort(book, new Comparator<ModelContact>(){
			public int compare(final ModelContact m1, final ModelContact m2){
				return m1.getLastName().compareTo(m2.getLastName());
			}
		});
		toUpdate();
	}

	/**
	 * Reorder the address book to sort contacts per Last Updated, from the last updated to the first updated.
	 */
	public void sortPerLastUpdated(){
		List<ModelContact> sorted = new ArrayList<ModelContact>(book);
		Collections.sort(sorted, new Comparator<ModelContact>(){
			public int compare(ModelContact m1, ModelContact m2) {
				//reverse order : X (-1)
				return (-1) * (m1.getUpdated().compareTo(m2.getUpdated()));
			}
		});
		toUpdate();
	}

	/**
	 * Get the number of contacts registered in the address book
	 * @return the number of contacts
	 */
	public int getNbContacts(){
		return book.size();
	}

	/**
	 * Save the address book content in CSV file (the same as used to load address book)
	 */
	public void saveContact(){
		try{ //file writing
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			for(int i = 0; i < book.size(); i++)
				bw.write(book.get(i).formatContact());
			bw.flush();
			bw.close();
		}
		catch(Exception e){
			System.out.println("Error : "+ e);
		}
		toUpdate();
	}

	/**
	 * Apply a filter in order to mark contact which have to be displayed according to the given group filter
	 * @param group : group used to filter contacts. If group is "ALL", all contacts will be kept.
	 */
	public void filterByGroup(String group){
		for(int i=0; i < book.size(); i++){
			if(book.get(i).getGroup().compareTo(group) == 0 || group.compareTo("ALL") == 0){
				book.get(i).setRequired(true);
			}
			else book.get(i).setRequired(false);
		}
		toUpdate();
	}

	/**
	 * Re-initialization of group filter.
	 */
	public void filterByGroupInit(){
		for(int i=0; i < book.size(); i++)
			book.get(i).setRequired(false);
	}

	/**
	 * Apply a filter in order to find the contact to display according to the id selected by the user
	 * @param id : a chain constituted by last and first name from the selected contact 
	 */
	public void filterByContact(String id){
		for(int i=0; i < book.size(); i++){
			String chain = book.get(i).getLastName() + " " + book.get(i).getFirstName();
			if(chain.compareTo(id) == 0){
				book.get(i).setDisplayed(true);
			}
			else book.get(i).setDisplayed(false);
		}
		toUpdate();
	}

	/**
	 * Re-initialization of contact filter
	 */
	public void filterByContactInit(){
		for(int i=0; i < book.size(); i++)
			book.get(i).setDisplayed(false);
	}

	public List<ModelContact> getBook() {
		return book;
	}

	public void setBook(List<ModelContact> book) {
		this.book = book;
	}

	/**
	 * give the number of contact to keep after group filter application
	 * @return : the number of contacts selected
	 */
	public int getNbRequired() {
		int cpt=0;
		for(int i= 0; i<book.size();i++)
			if(book.get(i).isRequired())
				cpt++;
		return cpt;
	}

	/**
	 * Open a web browser (if supported) and access to a specifing URL
	 * @param url : url to access
	 * @return True if browser opening succeeded. False otherwise.
	 */
	public boolean openHomePage(String url) {
		try{
			if(Desktop.isDesktopSupported() == true){
				Desktop.getDesktop().browse(new URI("http://"+url));
			}
			else throw new IOException();
		} catch( IOException | URISyntaxException e){
			System.out.println("Trace : " + e);
			return false;
		}
		return true;
	}

	/**
	 * Open the mail client and start e-mail redaction for the given email address
	 * @param mail : email to send an e-mail
	 * @return : True if mail client opening succeeded. False otherwise.
	 */
	public boolean openEmail(String mail){
		String url = "mailto:"+mail;
		try {
			Desktop.getDesktop().mail(new URI(url));
		}catch( IOException | URISyntaxException e){
			System.out.println("Trace : " + e);
			return false;
		}
		return true;
	}
}

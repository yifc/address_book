package modelSingleThread;

import java.util.Date;

/**
 * Class representing a contact with all these fields
 * @author Julien ADAM
 * @version 1.0
 */
public final class ModelContactSingleThread {
	private String lastName;
	private String firstName;
	private String homeAddress;
	private String companyAddress;
	private String persPhone;
	private String workPhone;
	private String homePage;
	private String email;
	private String link;
	private Date updated;
	private String group;
	private boolean isKept;
	private boolean isDisplayed;
	
	/**
	 * Construct a contact, according given values
	 * @param group : its group
	 * @param last : its last name (contacts unique key)
	 * @param first : its first name
	 * @param home : its home address
	 * @param company : its company address
	 * @param pers : its personal phone number
	 * @param work : its company phone number
	 * @param email : its e-mail address
	 * @param homePage : its home page
	 * @param link : its link
	 */
	public ModelContactSingleThread(String group, String last, String first, String home, String company, String pers, String work, String email, String homePage, String link){
		this.group = group;
		lastName = last;
		firstName = first;
		homeAddress = home;
		companyAddress = company;
		persPhone = pers;
		workPhone = work;
		this.homePage = homePage;
		this.email = email;
		this.link = link;
		updated = new Date();
	}

	public void setRequired(boolean isKept) {
		this.isKept = isKept;
	}
	
	public boolean isRequired() {
		return isKept;
	}
	
	public void setDisplayed(boolean display){
		this.isDisplayed = display;
	}
	
	public boolean isDisplayed(){
		return isDisplayed;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((homeAddress == null) ? 0 : homeAddress.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelContactSingleThread other = (ModelContactSingleThread) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (homeAddress == null) {
			if (other.homeAddress != null)
				return false;
		} else if (!homeAddress.equals(other.homeAddress))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getPersPhone() {
		return persPhone;
	}
	public void setPersPhone(String persPhone) {
		this.persPhone = persPhone;
	}
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public String getHomePage() {
		return homePage;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public String toString(){
		String chain = "Contact : " + firstName + " " + lastName
				+ "\n\t- Home Address: " + homeAddress  
				+ "\n\t- Company Address: " + companyAddress 
				+ "\n\t- Personal Phone: " + persPhone 
				+ "\n\t- Pro Phone : " + workPhone 
				+ "\n\t- Home Page : " + homePage  
				+ "\n\t- Email : " + email 
				+ "\n\t- Link : " + link
				+ "\n\t- Group : " + group
				+ "\n\t- Last Updated : " + updated
				+ "\n";
		return chain;
	}
	
	/**
	 * Generate format line, required by CSV file format
	 * @return : chain to insert info CSV file
	 */
	public String formatContact(){
		return group+","+lastName+","+firstName+","+homeAddress+","+companyAddress+","+persPhone+","+workPhone+","+email+","+homePage+","+link+"\n";
	}
}

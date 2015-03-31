package D5;

import java.io.Serializable;

public class Control implements Serializable {

	private user name;

	public Control()
	{
	name = new user();
	}
	public void loginButton(String userName, String password)
	{
		name.setName(userName);
		//check password here? or face book will do this for us
		//next check password? add user to the user array if its not there, otherwise log in. if new user send to new user menu, otherwise go to driver/passenger choice screen
		//the check should probably be done in user?
	}
	public void driverButton()
	{
		name.setDriver();
	}
	
	public void passengerButton()
	{
		name.setPassenger();
	}
	
	public void editBioButton(String newBio)
	{
		name.setBio(newBio);
	}
	
	public void logoutButton()
	{
		name.offline();
	}
	public void userRating(int rating)
	{
		name.addRating(rating);
	}
	
	public void nextButton(String newBio)
	{
		name.setBio(newBio);
	}
	
	public conversation passengerSend (String message, user driver, conversation currConvo)
	{
		if(currConvo.getConvo() == null) //figure out how we know if this is the first instance of a convo
		{
		conversation newConvo = new conversation(driver, name);
		newConvo.passengerMessage(message);
		return newConvo;
		}
		else
		{
			currConvo.passengerMessage(message);
		}
		return currConvo;
	}
	public conversation driverSend(String message, conversation currConvo)
	{
		currConvo.driverMessage(message);
		return currConvo;
	}
	/*
	 * TODO this is probably not part of control?
	 */
	public String printConvo(conversation currConvo)
	{
		message fullConvo = null;
		fullConvo = currConvo.getConvo();
		String printConvo = "";
		while(fullConvo != null)
		{
		printConvo = fullConvo.getsender().getName() + ": " + printConvo + " \n" + fullConvo.getContents();
		fullConvo = fullConvo.getNext();
		}
		return printConvo;
	}
	public user getUser()
	{
		return name;
	}
	public double getRating() {
		return name.getRating();
	}
	public void setRating(double rating) {
		name.addRating(rating);
	}
	public String getUserName() {
		return name.getName();
	}
	public void setUserName(String userName) {
		name.setName(userName);
	}
	public String getBio() {
		return name.getBio();
	}
	public void setBio(String bio) {
		name.setBio(bio);
	}
}

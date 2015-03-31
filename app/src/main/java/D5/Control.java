package D5;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Control implements Serializable {

	private user name;
	public userArray uArray;						//creates an array in this control instead of having an array on the server
	public fakeData fakeIt;							//fake data because no server

	public Control()
	{
	name = new user();
	uArray = new userArray();
	fakeIt = new fakeData(uArray);
	}
	
	public Control(user fakeUser, userArray realArray)
	{
		name = fakeUser;
		uArray = realArray;
		fakeIt = null;
	}
	
	public boolean loginButton(String userName, String password)
	{
	
		boolean passCheck = false;
		if(uArray.findUser(userName) == null){
			name.setName(userName);
			name.setPassword(password);
			uArray.addUser(name);
			passCheck = true;
		}
		else{
			passCheck = passCheck(password, userName);
			if(passCheck)
				name = uArray.findUser(userName);
		}
		return passCheck;
		//check password here? or face book will do this for us
		//next check password? add user to the user array if its not there, otherwise log in. if new user send to new user menu, otherwise go to driver/passenger choice screen
		//the check should probably be done in user?
		
	}
	public boolean passCheck(String password, String userName)
	{
		user temp = uArray.findUser(userName);
		if(temp != null && temp.getPassword().equals(password))
			return true;
		
		return false;
	}
	public void driverButton()
	{
		name.setDriver();
		fakeIt.fakeDriver(name.getName());
	}
	
	public void passengerButton()
	{
		name.setPassenger();
		fakeIt.fakePassenger(name.getName());
	}
	
	public void editBioButton(String newBio)
	{
		name.setBio(newBio);
	}
	
	public void logoutButton()
	{
		name.offline();
	}
	public void userRating(double rating)
	{
		name.addRating(rating);
	}
	
	public void nextButton(String newBio)
	{
		name.setBio(newBio);
	}
		
	public String passengerSend (String message, String driver)							//sends a message to the driver (you're the passenger)
	{
		user temp = uArray.findUser(driver);		///for finding driver user
		
		if(name.findConvo(temp) == null) //figure out how we know if this is the first instance of a convo
		{
			conversation newConvo = new conversation(temp, name);
			newConvo.passengerMessage(message);
			name.addConvo(newConvo);
			return printConvo(name.getName(), driver);
		}
		else
		{
			conversation tempConvo = name.findConvo(temp);
			tempConvo.passengerMessage(message);
			name.updateConvo(tempConvo, temp);
			return printConvo(name.getName(), driver);
		}
		
	}
	public String driverSend(String message, String passenger)						//sends a message to the passenger (you're the driver)
	{
		user temp = uArray.findUser(passenger);
		
		conversation tempConvo = temp.findConvo(name);
		tempConvo.driverMessage(message);
		temp.updateConvo(tempConvo, name);
		return printConvo(passenger, name.getName());
	}
	
	public String printConvo(String passenger, String driver)					//returns a string with all the messages in a conversation
	{
		user tempPassenger = uArray.findUser(passenger);
		user tempDriver = uArray.findUser(driver);
		conversation tempConvo = tempPassenger.findConvo(tempDriver);
		message fullConvo = null;
		fullConvo = tempConvo.getConvo();
		String printConvo = "";
		while(fullConvo != null)
		{
			if (printConvo.equals(""))
			{
				printConvo = fullConvo.getsender().getName() + ": " + printConvo + " \n" + fullConvo.getContents();
				fullConvo = fullConvo.getNext();
			}
			else
			{
				printConvo = printConvo + "\n" + fullConvo.getsender().getName() + ": " + "\n" + fullConvo.getContents();
				fullConvo = fullConvo.getNext();
			}
			}
		return printConvo;
	}
	
	public String[] checkDriverMessages(){							//returns String[] of passenger names who have messaged you (the driver)
		user[] passengers = uArray.getPassengers();
		String[] passengerNames = new String[300];
		int i = 0;
		int j=0;
		while(passengers[i] !=null){
			if(passengers[i].findConvo(name) != null){
				passengerNames[j] = passengers[i].getName();
				j++;
			}
			i++;
		}
		return passengerNames;
	}
	
	public String[] getDrivers(){							//Returns string[] of all drivers on the app at the moment
		user[] drivers = uArray.getDrivers();
		int i = 0;
		String[] driverNames = new String[300];
		while(drivers[i] != null){
			driverNames[i] = drivers[i].getName();
		}
		return driverNames;
	}
			
	public String[] getPassengers(){								//returns string[] of all passengers on the app at the moment
		user[] passengers = uArray.getPassengers();
		int i = 0;
		String[] passengerNames = new String[300];
		while(passengers[i] != null){
			passengerNames[i] = passengers[i].getName();
		}
		return passengerNames;
	}
	
	public void sendInvite(String otherUser){						//Sends an invite, takes the name of the driver
		user temp = uArray.findUser(otherUser);
		name.setInvite(true);
		temp.setInvite(true);
	}
	
	public void denyInvite(){										//denies an invite, call if you're a driver
		name.setInvite(false);
	}
	
	public void acceptInvite(String otherUser){					//accepts an invite, call if you're a driver
		name.setInRide(true);
		name.setInvite(false);
		user temp = uArray.findUser(otherUser);
		temp.setInRide(true);
	}
	
	public void cancelInvite(String otherUser){						//cancels an invite, call if you're a passenger
		user temp = uArray.findUser(otherUser);
		name.setInvite(false);
		temp.setInvite(false);
	}
	
	public void endRide(String otherUser){
		user temp = uArray.findUser(otherUser);
		name.setInRide(false);
		temp.setInRide(false);
		fakeIt.fakeEndRide(name);
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

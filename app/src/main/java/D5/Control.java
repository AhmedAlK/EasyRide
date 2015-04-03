package D5;




public class Control {

	private user name;
	public userArray uArray;						//creates an array in this control instead of having an array on the server
	public fakeData fakeIt;							//fake data because no server

	/*
	 * Used for Passing info from the view to the model, and passing info from the model to the view.
	 */
	public Control()
	{
	name = new user();
	uArray = new userArray();
	fakeIt = new fakeData(uArray);
	}
	/*
	 * Used for creating the control for the fake users.
	 */
	public Control(user fakeUser, userArray realArray)
	{
		name = fakeUser;
		uArray = realArray;
		fakeIt = null;
	}
	/*
	 *  Checks to see if the user exists, if not creates new one with the password entered.  If the user does exist the password for that user is verified and
	 *  is accepted if true and fails if the password is not the same.
	 */
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
		
	}
	
	/*
	 * Tests to see if the password entered matches the password the user has saved to there account.
	 */
	public boolean passCheck(String password, String userName)
	{
		user temp = uArray.findUser(userName);
		if(temp != null && temp.getPassword().equals(password))
			return true;
		
		return false;
	}
	/*
	 * Changes the user to be seen as a driver.
	 */
	public void driverButton()
	{
		name.setDriver();
		fakeIt.fakeDriver(name.getName());
	}
	/*
	 * Changes the user to be seen as a passenger.
	 */
	public void passengerButton()
	{
		name.setPassenger();
	}
	/*
	 * Updates the users bio to the newBio.
	 */
	public void editBioButton(String newBio)
	{
		name.setBio(newBio);
	}
	/*
	 * Changes the user to be neither a driver or a passenger (so offline).
	 */
	public void logoutButton()
	{
		name.offline();
		
	}
	/*
	 * Passes the new rating to the users rating.
	 */
	public void userRating(double rating)
	{
		name.addRating(rating);
	}
	/*
	 * Sets the users bio after the first time logging in.
	 */
	public void firstNextButton(String newBio)
	{
		if(newBio.equals(""))
			newBio = "I have not set my bio!";
		
		name.setBio(newBio);
	}
		/*
		 * Checks if the conversation between the passed in driver and the passenger exists.  If it does not exist, creates a new conversation
		 * and returns a string of the conversation.  If it does exist, it finds the conversation, updates it with the new message, and returns
		 * a string of the entire conversation.
		 */
	public String passengerSend (String message, String driver)							
	{
		user temp = uArray.findUser(driver);		///for finding driver user
		
		if(name.findConvo(temp) == null) 
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

	public String driverSend(String message, String passenger)						//sends a message to the passenger (you're the driver) (conversation already exists because only passengers can start conversations).
	{
		user temp = uArray.findUser(passenger);
		
		conversation tempConvo = temp.findConvo(name);
		tempConvo.driverMessage(message);
		temp.updateConvo(tempConvo, name);
		return printConvo(passenger, name.getName());
	}

	public String printConvo(String user1, String user2)					//returns a string with all the messages in a conversation
	{
		user tempPassenger;
		user tempDriver;
		user temp1 = uArray.findUser(user1);                               //used for figuring out who the driver and who the passenger is
		user temp2 = uArray.findUser(user2);
		if(temp1.role() == 1)
		{
			tempPassenger = temp1;
			tempDriver = temp2;
		}
		else
		{
			tempPassenger = temp2;
			tempDriver = temp1;
		}
		conversation tempConvo = tempPassenger.findConvo(tempDriver);
		message fullConvo = null;
		if(tempConvo != null)
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
		while(i < passengers.length && passengers[i] != null  ){
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
		String[] driverNames = new String[drivers.length];
		while(i < drivers.length && drivers[i] != null){
			driverNames[i] = drivers[i].getName();
			i++;
		}
		return driverNames;
	}
			
	public String[] getPassengers(){								//returns string[] of all passengers on the app at the moment
		user[] passengers = uArray.getPassengers();
		int i = 0;
		String[] passengerNames = new String[passengers.length];
		while(i < passengers.length && passengers[i] != null){
			passengerNames[i] = passengers[i].getName();
			i++;
		}
		return passengerNames;
	}
	
	public void sendInvite(String otherUser){						//Sends an invite, takes the name of the driver
		user temp = uArray.findUser(otherUser);
		temp.addInvite(name.getName());
	
		fakeIt.fakePassenger(name.getName());
	}
	
	public void fakeSendInvite(String otherUser){						//Sends an invite for a fake user, takes the name of the driver
		user temp = uArray.findUser(otherUser);
		temp.addInvite(name.getName());
		
	}
	
	public boolean checkForInvites(String passenger){
		
		return name.findInvite(passenger);
	}
	
	public void acceptInvite(String passenger){					//accepts an invite, call if you're a driver
		name.setInRide(true);
		
		user temp = uArray.findUser(passenger);
		name.removeInvite(passenger);
		temp.setInRideWith(name.getName());
		name.setInRideWith(passenger);
		temp.setInRide(true);
	}
	
	public void cancelInvite(String otherUser){						//cancels an invite, call if you're a passenger
		user temp = findUser(otherUser);
		if(temp.role()==1)
			name.removeInvite(otherUser);
		else
			temp.removeInvite(name.getName());
	}
	public boolean checkInRideWith(String otherUser)
	{
		
		if(otherUser.equals(name.getInRideWith()))
				return true;
		
		return false;
	}
	public void endRide(String otherUser){
		user temp = uArray.findUser(otherUser);
		name.setInRide(false);
		temp.setInRide(false);
		fakeIt.fakeEndRide(name);
		temp.setInRideWith("");
		name.setInRideWith("");
	}
	public user findUser(String name)
	{
		return uArray.findUser(name);
	}
	public user getUser()
	{
		return name;
	}
	public double getRating() {
		return name.getRating();
	}

	public String getUserName() {
		return name.getName();
	}
	
	public String getBio() {
		return name.getBio();
	}

}

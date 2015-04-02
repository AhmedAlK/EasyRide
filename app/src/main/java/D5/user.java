package D5;

import java.io.Serializable;

public class user implements Serializable{
	
	private String name;
	private int id;
	private String password;
	private double rating;
	private double numRatings;
	private String bio;
	private Boolean isDriver;
	private Boolean isPassenger;
	private conversation[] convo;
	private int numConvo = 0;
	private boolean recieveInvite;
	private boolean inRide;
	
	public user(){
		name = "";
		id = 0;           //we can use something else for IDs later, an int works for now, start at 0 and add 1 for each user
		rating = 0;
		password = "";
		bio = "";
		numRatings = 0;
		isDriver = false;
		isPassenger = false;
		convo = new conversation[500];
		recieveInvite = false;
		inRide = false;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public void setInvite(boolean flag){
		recieveInvite = flag;
	}
	
	public boolean getInvite(){
		return recieveInvite;
	}
	
	public void setInRide(boolean flag){
		inRide = flag;
	}
	
	public boolean getInRide(){
		return inRide;
	}
	
	public String getName(){
		return name;
	}
   
   public int getID(){
      return id;
   }
   
   public void setID(int id){
      this.id = id;
   }
	
	public double getRating(){
		return Math.floor(rating*10)/10;
	}
	
	public void addRating(double newRating){
		if (numRatings == 0){
			rating = newRating;
		}
		else if (numRatings == 1){
			rating = (newRating + rating)/2;
		}
		else {
			rating = (rating*(numRatings) + newRating)/(numRatings+1);
		}
		numRatings++;
	}
	
	public String getBio(){
		return bio;
	}
	
	public void setBio(String bio){
		this.bio = bio;
	}
	
	public void setPassenger(){
		isPassenger = true;
		isDriver = false;
	}
	
	public void setDriver(){
		isDriver = true;
		isPassenger = false;
	}
   
   public void offline(){
      isDriver = false;
      isPassenger = false;
   }
	
	//Returns True if Passenger or False if Driver, or null if neither
	public int role(){
		if(isPassenger == true)
			return 1;
		else if(isDriver == true)
			return 2;
		else
			return 0;
	}
	
	public void addConvo(conversation converse){
		convo[numConvo] = converse;
		numConvo++;
	}
	
	public conversation findConvo(user otherUser){
		for(int i = 0; i < numConvo; i++){
			if(convo[i].getDriver().getName().equals(otherUser.getName())){
				return convo[i];
			}
		}
		return null;
	}
	
	public void updateConvo(conversation updated, user otherUser){
		for(int i = 0; i < numConvo; i++){
			if(convo[i].getDriver().getName().equals(otherUser.getName())){
				convo[i] = updated;
			}
		}
	}
}
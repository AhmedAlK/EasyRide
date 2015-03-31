package D5;

import java.util.*;

public class userArray{

   private user[] uArray = new user[500];         //Array of 500 because this app won't go live
   private List<user> driverList = new ArrayList<user>();      //ArrayList for drivers because it's easier
   private List<user> passengerList = new ArrayList<user>();   //ditto ^^
   private int length = 0;

   
   public void addUser(user newUser){
      uArray[length] = newUser;                     //Put user in
      newUser.setID(length);                        //set ID
      length++;
   }
   
   public user findUser(String userName){
	   for(int i=0; i < length; i++){
		   if(uArray[i].getName().equals(userName)){
			   return uArray[i];
		   }
	   }
	   return null;
   }
   
   public void removeUser(user rUser){				//remove user from userArray
      int id = rUser.getID();
      int counter = id;
      
      uArray[id] = null;                                 //remove user from Array
      while(uArray[counter+1] != null){                //Move all users after removed user down a slot
         uArray[counter] = uArray[counter + 1];
         uArray[counter+1].setID(counter);              //set new user's ID's
         counter++;
      }
      uArray[counter] = null;							//delete end user who was duplicated
   }
   
   public void addDriver(user newDriver){
      driverList.add(newDriver);                            //add drivers id to driver list
   }
   
   public void addPassenger(user newPassenger){
      passengerList.add(newPassenger);                            //add passengers id to passenger list
   }
   
   public user[] getUsers(){											//gets the array of users
	   return uArray;
   }
   
   public user[] getDrivers(){											//returns a user array of all drivers
	   driverList.clear();
	   for(int i = 0; i < length; i++){
		   if(uArray[i].role() == 2)								//if user == driver add to driverList
			   addDriver(uArray[i]);
	   }
	   user[] list =  driverList.toArray(new user[driverList.size()]);		//convert to user array
	   return list;
   }
   
   public user[] getPassengers(){										//returns a user array of all passengers
	   passengerList.clear();
	   for(int i = 0; i < length; i++){					
		   if(uArray[i].role() == 1)									//if user == passenger and to passengerList
			   addPassenger(uArray[i]);
	   }
	   user[] list =  passengerList.toArray(new user[passengerList.size()]);		//convert to user array
	   return list;
   }
}
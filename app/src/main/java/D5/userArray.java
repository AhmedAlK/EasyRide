package D5;

import java.util.*;

public class userArray{

   private user[] uArray = new user[500];         //Array of 500 because this app won't go live
   private List<user> driverList = new ArrayList<user>();      //ArrayList for drivers because it's easier
   private List<user> passengerList = new ArrayList<user>();   //ditto ^^

   
   public void addUser(user newUser){
      int length = 0;
   
      length = uArray.length;                     //find spot to put new user in
      uArray[length] = newUser;                     //Put user in
      newUser.setID(length);                        //set ID
   }
   
   public user findUser(int id){					//probably useless
      return uArray[id];
   }
   
   public void removeUser(user rUser){				//remove user from userArray
      int id = rUser.getID();
      int counter = id;
      
      uArray[id] = null;                                 //remove user from Array
      while(uArray[counter] != null){                //Move all users after removed user down a slot
         uArray[counter] = uArray[counter + 1];
         uArray[counter+1].setID(counter);              //set new user's ID's
         counter++;
      }
   }
   
   public void addDriver(user newDriver){
      driverList.add(newDriver);                            //add drivers id to driver list
   }
   
   public void removeDriver(user exDriver){
      driverList.remove(exDriver);                         //remove Driver
   }

   public void addPassenger(user newPassenger){
      driverList.add(newPassenger);                            //add passengers id to passenger list
   }
   
   public void removePassenger(user exPassenger){
      driverList.remove(exPassenger);                          //remove passenger
   }
   
   public user[] getUsers(){											//gets the array of users
	   return uArray;
   }
   
   public user[] getDrivers(){											//returns a user array of all drivers
	   for(int i = 0; i < uArray.length; i++){
		   if(uArray[i].role() == false)								//if user == driver add to driverList
			   addDriver(uArray[i]);
	   }
	   user[] list =  driverList.toArray(new user[driverList.size()]);		//convert to user array
	   return list;
   }
   
   public user[] getPassengers(){										//returns a user array of all passengers
	   for(int i = 0; i < uArray.length; i++){					
		   if(uArray[i].role() == true)									//if user == passenger and to passengerList
			   addPassenger(uArray[i]);
	   }
	   user[] list =  passengerList.toArray(new user[passengerList.size()]);		//convert to user array
	   return list;
   }
}
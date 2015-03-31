package D5;

public class conversation{
	
	private user driver = new user();
	private user passenger = new user();
	private message head = null;
	
	public conversation(user driver, user passenger){						//initiates the driver and passenger users
		this.driver = driver;	
		this.passenger = passenger;
	}

	public void driverMessage(String contents){								//send message from P to D
		message toPassenger = new message(driver, passenger, contents);		//message
		message current = head;
		while(current.getNext() != null){									//Since driver can't start messages, we have no isHead check
			current = current.getNext();									//runs through messages until finds most recent
		}
		current.setNext(toPassenger);										//sets most recent message to have new message as next
		toPassenger.setPrev(current);										//sets new message to have most recent as previous
	}

	public void passengerMessage(String contents){							//send message from D to P
		message toDriver = new message(passenger, driver, contents);		//message
		message current = head;			
		if(head == null){													//Since passenger might have first message, checks if message
			head = toDriver;												//needs to be head
		}
		else{
			while(current.getNext() != null){								//same as driverMessage
				current = current.getNext();
			}
			current.setNext(toDriver);
			toDriver.setPrev(current);
		}
	}
	
	public message getConvo(){
		return head;									//returns head, might implement to return array or something
	}
	
	
}
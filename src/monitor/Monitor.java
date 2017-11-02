package monitor;

import java.lang.Thread;

public class Monitor {
	
	Bathroom bathroom;
	
	Monitor() {
		
	}
	
	//public synchronized void changeBathroomUsingNow(int value) {
	//	this.bathroom.changeUsingNow(value);
	//}
	
	public synchronized void humanOut() {
		this.bathroom.changeUsingNow(-1);
	}
}

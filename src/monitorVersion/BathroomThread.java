package monitorVersion;

import java.lang.Thread;

public class BathroomThread extends Thread {
	
	Bathroom bathroom;
	int type;
	
	BathroomThread(Bathroom bathroom, int type) {
		this.bathroom = bathroom;
		this.type = type;
	}
	
	@Override
	public void run() {
		if(type == 1) {
			// call nex human
			while(true) {
				this.bathroom.callNext();
				System.out.println("OKOK");
			}
		}
		else if(type == 2) {
			// change gender
			while(true) {
				try {
					Thread.sleep(10000);
					System.out.println("OKOK");
				}
				catch(Exception e) {
					e.getMessage();
				}
				this.bathroom.changeGendeTimer();
			}
		}
		else {
			
		}
	}
}

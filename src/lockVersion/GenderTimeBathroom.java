package lockVersion;

import java.lang.Thread;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GenderTimeBathroom extends Thread {
	private Bathroom bathroom;
	
	public GenderTimeBathroom(Bathroom bathroom) {
		this.bathroom = bathroom;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				TimeUnit.SECONDS.sleep(15);
				this.bathroom.setGenderTransiton(true);
				while(this.bathroom.isGenderTransiton() != false) {
					continue;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}

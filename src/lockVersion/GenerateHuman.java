package lockVersion;

import java.lang.Thread;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GenerateHuman extends Thread {

	private int timeInterval;
	private Bathroom bathroom;
	
	public GenerateHuman(int timeInterval, Bathroom bathroom) {
		this.timeInterval = timeInterval;
		this.bathroom = bathroom;
	}
	
	private Human newHuman() {
		Random r = new Random();
		int value = r.nextInt(10);
		if(value < 6) {
			return new Human(Gender.Female, 10, this.bathroom);
		}
		else {
			return new Human(Gender.Male, 10, this.bathroom);
		}
	}
	
	private void addHumanToBathroom(Human h) throws InterruptedException {
		if(h.getGender().equals(Gender.Male)) {
			this.bathroom.addHumanMale(h);
		}
		else {
			this.bathroom.addHumanFemale(h);
		}
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				// random generation of humans 
				System.out.println("GENERATE HUMAN");				
				TimeUnit.SECONDS.sleep(this.timeInterval);
				Human h = this.newHuman();
				this.addHumanToBathroom(h);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}

package monitor;

import java.util.Random;

public class Human {
	private char gender; // 'M' == Male or 'F' to female
	private int timeToFinish;

	private Monitor monitor;
	
	Human(Monitor monitor) {
		this.monitor = monitor;
	}
	
	public void setGender(char gender) {
		this.gender = gender;
	}

	char getGender() {
		return this.gender;
	}

	public void run() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(10000);
		try {
			Thread.sleep(randomInt);
		}
		catch(Exception e) {
			e.getMessage();
		}
		monitor.humanOut();
	}
}

package monitor;

import java.lang.Thread;
import java.util.Random;

public class Human extends Thread {
	/**
	*	'M' == Male or 'F' to female
	*/
	private char gender;
	/**
	* Max time to move out the bathroom. 
	*/
	private int timeToFinish;

	/**
	*
	*/
	private Monitor monitor;
	
	public Human(Monitor monitor, char gender, int timeToFinish) {
		this.monitor = monitor;
	}

	/**
	*
	*/
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	*
	*/
	char getGender() {
		return this.gender;
	}

	/**
	*
	*/
	@Override
	public void run() {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(timeToFinish);
		try {
			Thread.sleep(randomInt);
		}
		catch(Exception e) {
			e.getMessage();
		}
		monitor.humanOut();
	}
}

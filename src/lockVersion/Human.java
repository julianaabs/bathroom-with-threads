package lockVersion;

import java.util.Random;
import java.lang.Thread;
import java.util.concurrent.TimeUnit;

import lockVersion.Gender;

public class Human extends Thread {
	private int maxTimeOut;
	private Gender gender;
	private Bathroom bathroom;
	private boolean cameIn;
	
	public Human(Gender gender, int maxTimeOut, Bathroom bathroom) {
		this.gender = gender;
		this.maxTimeOut = maxTimeOut;
		this.bathroom = bathroom;
		this.cameIn = false;
	}
	
	@Override
	public void run() {
		try {
			while(!this.cameIn) {
				continue;
			}
			TimeUnit.SECONDS.sleep((new Random()).nextInt(this.maxTimeOut) + 1);
			this.bathroom.lessOne();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int getMaxTimeOut() {
		return maxTimeOut;
	}

	public void setMaxTimeOut(int maxTimeOut) {
		this.maxTimeOut = maxTimeOut;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Bathroom getBathroom() {
		return bathroom;
	}

	public void setBathroom(Bathroom bathroom) {
		this.bathroom = bathroom;
	}

	public boolean isCameIn() {
		return cameIn;
	}

	public void setCameIn(boolean cameIn) {
		this.cameIn = cameIn;
	}
	
	
}

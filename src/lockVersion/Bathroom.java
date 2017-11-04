package lockVersion;

import java.util.Random;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bathroom extends Thread {
	private int usingNow;
	private int capacity;
	private boolean genderTransiton;
	private Gender genderTime;
	private ArrayList<Human> maleQueue;
	private ArrayList<Human> femaleQueue;
	// locks
	private Lock lockLessOne;
	private Lock lockAddMale;
	private Lock lockAddFemale;
	// object that extends Thread and create humans to this bathroom
	private GenerateHuman generateHuman;
	private GenderTimeBathroom genderTimeBathroom;
	
	public Bathroom(int capacity) {
		try {
			this.usingNow = 0;
			this.capacity = capacity;
			this.genderTime = Gender.Female;
			this.maleQueue = new ArrayList<Human>();
			this.femaleQueue = new ArrayList<Human>();
			this.lockLessOne = new ReentrantLock();
			this.lockAddMale = new ReentrantLock();
			this.lockAddFemale = new ReentrantLock();
			// thread to generate humans to the queues
			this.generateHuman = new GenerateHuman(2, this);
			this.generateHuman.start();
			// after 15 seconds tell to bathroom wait all Humans out to call the other gender
			this.genderTimeBathroom = new GenderTimeBathroom(this);
			this.genderTimeBathroom.start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void lessOne() {
		this.lockLessOne.lock();
		this.usingNow -= 1;
		if(this.genderTime == Gender.Male) {
			System.out.println(">>> One person of male gender out the bathroom.");
		}
		else {
			System.out.println(">>> One person of female gender out the bathroom.");
		}
		if(this.genderTransiton == true && this.usingNow == 0) {
			this.genderTransiton = false;
			if(this.genderTime == Gender.Male) {
				this.genderTime = Gender.Female;
			}
			else {
				this.genderTime = Gender.Male;
			}
			// inform the gender transition
			if(this.genderTime == Gender.Male) {
				System.out.println(">>> Gender changed to male.");
			}
			else {
				System.out.println(">>> Gender changed to female.");
			}
		}
		this.lockLessOne.unlock();
	}
	
	public void addHumanMale(Human h) throws InterruptedException {
		this.lockAddMale.lock();
		this.maleQueue.add(h);
		h.start();
		this.lockAddMale.unlock();
	}
	
	public void addHumanFemale(Human h) throws InterruptedException {
		this.lockAddFemale.lock();
		this.femaleQueue.add(h);
		h.start();
		this.lockAddFemale.unlock();
	}
	
	@Override
	public void run() {
		while(true) {
			while(this.genderTransiton) {
				continue;
			}
			if(this.genderTime == Gender.Male && this.usingNow < this.capacity) {
				if(this.maleQueue.size() == 0) {
					continue;
				}
				System.out.println(">>> One person of male gender came in the bathroom.");
				Human h = this.maleQueue.get(0);
				this.maleQueue.remove(0);
				h.setCameIn(true);
			}
			else if(this.genderTime == Gender.Female && this.usingNow < this.capacity) {
				if(this.femaleQueue.size() == 0) {
					//continue;
				}
				System.out.println(">>> One person of female gender came in the bathroom.");
				Human h = this.femaleQueue.get(0);
				this.femaleQueue.remove(0);
				h.setCameIn(true);
			}
		}
	}

	public Gender getGenderTime() {
		return genderTime;
	}

	public void setGenderTime(Gender genderTime) {
		this.genderTime = genderTime;
	}

	public boolean isGenderTransiton() {
		return genderTransiton;
	}

	public void setGenderTransiton(boolean genderTransiton) {
		this.genderTransiton = genderTransiton;
	}
	
	
}

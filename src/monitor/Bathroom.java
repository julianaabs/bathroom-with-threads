package monitor;

import java.util.ArrayList;
import java.lang.Thread;
import monitor.Human;
import monitor.BathroomThread;

public class Bathroom {
	/**
	* fila com homens para entrar no banheiro.
	*/
	private ArrayList<Human> queueMale;
	/**
	* fila com mulheres para entrar no banheiro.
	*/
	private ArrayList<Human> queueFemale;
	/**
	*
	*/
	private int capacity;
	/**
	*
	*/
	private int usingNow;
	/**
	*
	*/
	private char genderTime;
	/**
	*
	*/
	private boolean GenderTransition;
	/**
	*
	*/
	BathroomThread callNextHuman;
	/**
	*
	*/
	BathroomThread changeGender;


	/**
	*
	*/
	public Bathroom(int capacity, char gender) {
		this.GenderTransition = false;
		this.usingNow = 0;
		this.genderTime = gender;
		this.capacity = capacity;
	    this.queueMale = new ArrayList<Human>(1000);
	    this.queueFemale = new ArrayList<Human>(1000);
	    
	    this.callNextHuman = new BathroomThread(this, 1);
	    this.changeGender = new BathroomThread(this, 2);
	    
	    this.callNextHuman.start();
	    this.changeGender.start();
	    
	}

	/**
	*
	*/
	public synchronized void callNext() {
		if(GenderTransition) {
			while(this.GenderTransition && this.usingNow != 0) {
				continue;
			}
			this.GenderTransition = false;
		}
		if(this.capacity > this.usingNow && this.genderTime == 'M') {
			if(this.queueMale.size() == 0) {
				return;
			}
			Human h = this.queueMale.get(0);
			this.queueMale.remove(0);
			h.start();
			this.usingNow++;
			System.out.println("Uma pessoa do genero " + h.getGender() + " saiu do banheiro");
		}
		if(this.capacity > this.usingNow && this.genderTime == 'F') {
			if(this.queueFemale.size() == 0) {
				return;
			}
			Human h = this.queueFemale.get(0);
			this.queueMale.remove(0);
			h.start();
			this.usingNow++;
			System.out.println("Uma pessoa do genero " + h.getGender() + " saiu do banheiro");
		}
	}

	/**
	*
	*/
	public synchronized void changeUsingNow(int value) {
		this.usingNow = this.usingNow + value;
	}

	/**
	*
	*/
	public void changeGendeTimer() {
	    if(this.genderTime == 'M') {
	      this.genderTime= 'F';
	      this.GenderTransition = true;
	    }
	    else {
	      this.genderTime= 'M';
	      this.GenderTransition = true;
	    }
	}
	
	public void addHuman(Human h) {
		if(h.getGender() == 'M') {
			this.queueMale.add(h);
		}
		else if(h.getGender() == 'F') {
			this.queueFemale.add(h);
		}
	}
	
	public char getGenderTime() {
		return this.genderTime;
	}
}

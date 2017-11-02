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
	
	private boolean GenderTransition;
	
	BathroomThread callNextHuman;
	BathroomThread changeGender;


	/**
	*
	*/
	Bathroom(int capacity, char gender) {
		this.GenderTransition = false;
		this.usingNow = 0;
		this.genderTime = gender;
		this.capacity = capacity;
	    this.queueMale = new ArrayList<Human>();
	    this.queueFemale = new ArrayList<Human>();
	    
	    this.callNextHuman = new BathroomThread(this, 1);
	    this.changeGender = new BathroomThread(this, 2);
	    
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
			Human h = this.queueMale.get(0);
			this.queueMale.remove(0);
			h.start();
			this.usingNow++;
		}
		if(this.capacity > this.usingNow && this.genderTime == 'F') {
			Human h = this.queueFemale.get(0);
			this.queueMale.remove(0);
			h.start();
			this.usingNow++;
		}
	}
	
	public synchronized void changeUsingNow(int value) {
		this.usingNow = this.usingNow + value;
	}

	/**
	*
	*/
	public synchronized void changeGendeTimer() {
	    if(this.genderTime == 'M') {
	      this.genderTime= 'F';
	      this.GenderTransition = true;
	    }
	    else {
	      this.genderTime= 'M';
	      this.GenderTransition = true;
	    }
	}
}

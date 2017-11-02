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
	
	BathroomThread callNextHuman;
	BathroomThread changeGender;


	/**
	*
	*/
	Bathroom(int capacity) {
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
		if(this.capacity > this.usingNow && this.genderTime == 'M') {
			// remove objeto da fila de homens.
		    // chama metodo "useBathroom" do objeto.
		    // incrementa em mais 1 o valor de "usingNow".
		}
		if(this.capacity > this.usingNow && this.genderTime == 'F') {
		    // remove objeto da fila de mulheres.
		    // chama metodo "useBathroom" do objeto.
			// incrementa em mais 1 o valor de "usingNow".
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
	    }
	    else {
	      this.genderTime= 'M';
	    }
	}
}

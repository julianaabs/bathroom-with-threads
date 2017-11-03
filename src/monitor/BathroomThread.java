package monitor;

/**
 * Classe com as threads referentes ao banheiro.
 * 
 * @author Nalbert Gabriel
 * @version 2.0
 * @since 11-02-2017
 * 
 * 
 */

import java.lang.Thread;

public class BathroomThread extends Thread {
	
	Bathroom bathroom;
	int type;
	
	/**
	 * 
	 * Construtor da classe
	 * 
	 * @param bathroom
	 * @param type
	 * 
	 */
	
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
			}
		}
		else if(type == 2) {
			// change gender
			while(true) {
				try {
					Thread.sleep(10000);
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

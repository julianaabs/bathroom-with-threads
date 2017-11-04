package monitor;

/**
 * Classe do monitor que irá administrar as threads.
 * 
 * @author Nalbert Gabriel
 */

import java.lang.Thread;
import java.util.Random;

import monitor.Human;

public class Monitor extends Thread {
	
	Bathroom bathroom;
	
	/**
	 *  Construtor vazio.
	 *  
	 * @param bathroom
	 */
	public Monitor(Bathroom bathroom) {
		
	}
	
	/**
	 *  Método que notifica quando alguém saiu do banheiro, indicando seu gênero e decrementando o número 
	 *  de pessoas que estão utilizando o banheiro.
	 * 
	 */
	
	public synchronized void humanOut() {
		this.bathroom.changeUsingNow(-1);
		System.out.println("Uma pessoa do genero " + this.bathroom.getGenderTime() + " saiu do banheiro");
	}
	
	/**
	 * Médoto para criar um humano randomicamente.
	 * 
	 */
	
	public void createHuman() {
		Human h;
		
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(10);
		if(randomInt < 6) {
			h = new Human(this, 'M', 10000);
		}
		else {
			h = new Human(this, 'F', 10000);
		}
		this.bathroom.addHuman(h);
		System.out.println("Pessoa do gênero " + h.getGender() + " entro na fila");
	}
	
	/**
	 * Método para rodar a thread.
	 * 
	 */
	
	@Override
	public void run() {
		while(true) {
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(10000);
			try {
				Thread.sleep(randomInt);
				this.createHuman();
			}
			catch(Exception e) {
				e.getMessage();
			}
		}
	}
}

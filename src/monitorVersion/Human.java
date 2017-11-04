package monitor;

/**
 * 
 * Classe para representar os humanos (homem ou mulher) que utilizarão o banheiro.
 * 
 * @author Juliana Barbosa, Nalbert Gabriel
 * @version 2.0
 * @since 11-02-2017
 * 
 */

import java.lang.Thread;
import java.util.Random;

public class Human extends Thread {
	/**
	*	Gênero 'M' para masculino e 'F' para feminino.
	*/
	private char gender;
	/**
	* 	Tempo máximo para sair do banheiro. 
	*/
	private int timeToFinish;

	/**
	*	Monitor.
	*/
	private Monitor monitor;
	
	/**
	 *  Construtor da classe.
	 * 
	 * @param monitor
	 * @param gender
	 * @param timeToFinish
	 */
	
	public Human(Monitor monitor, char gender, int timeToFinish) {
		this.monitor = monitor;
	}

	/**
	 * @param gender
	 */
	
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	 * @return this.gender
	 */
	char getGender() {
		return this.gender;
	}

	/**
	 * 
	 * Roda a thread.
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

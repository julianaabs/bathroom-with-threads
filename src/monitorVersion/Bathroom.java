package monitor;

/**
 *  Classe do banheiro que possui os atributos necessários para seu funcionamento.
 *  
 *  @author Juliana Barbosa, Nalbert Gabriel
 *  @version 2.0
 *  @since 11-02-2017
 * 
 */

import java.util.ArrayList;
import java.lang.Thread;
import monitor.Human;
import monitor.BathroomThread;

public class Bathroom {
	/**
	*	Fila com homens para entrar no banheiro.
	*/
	private ArrayList<Human> queueMale;
	/**
	*	Fila com mulheres para entrar no banheiro.
	*/
	private ArrayList<Human> queueFemale;
	/**
	*	Capacidade do banheiro.
	*/
	private int capacity;
	/**
	*	Quantidade de pessoas utilizando o banheiro.
	*/
	private int usingNow;
	/**
	*	Gênero da pessoa que entrou no banheiro por último.
	*/
	private char genderTime;
	/**
	*	Indica se o gênero das pessoas que estão ocupando o banheiro mudou.
	*/
	private boolean GenderTransition;
	/**
	*	Chama a próxima pessoa para entrar no banheiro.
	*/
	BathroomThread callNextHuman;
	/**
	*	
	*/
	BathroomThread changeGender;


	/**
	*	Construtor da classe.
	*
	*	@param capacity
	*	@param gender
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
	*	Chama a próxima pessoa para entrar no banheiro, analisando a capacidade total, o número de pessoas 
	* que estão no banheiro e o gênero que está ocupando o banheiro.
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
	*  Modifica o número de pessoas que estão usando o banheiro no momento.
	*  
	*  @param value
	*
	*/
	public synchronized void changeUsingNow(int value) {
		this.usingNow = this.usingNow + value;
	}

	/**
	*
	*	Modifica o gênero da pessoa que entrou por último no banheiro e, se for diferente do que estava
	* antes, modifica GenderTransition para indicar que houve mudança de gênero.
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
	
	/**
	 *  Adiciona uma pessoa à fila correspondente ao seu gênero.
	 *  
	 *  @param h
	 * 
	 */
	
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

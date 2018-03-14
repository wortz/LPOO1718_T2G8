package dkeep.logic;

import java.util.Random;

public class Guard  extends GameElement{

	private String guardMove[];
	private String guardMove_backwards[];
	private int guardIndex;
	private boolean asleep;
	private int sleep_counter;
	private boolean moving_front;
	private boolean aux_flag; //flag para se acaba de acordar no drunken e para se acaba de mudar de direcçao no suspicious

	public Guard(int xi, int yi) {
		super(xi, yi);
		String aux2[]= {"a","s","s","s","s","a","a","a","a","a","a","s","d","d","d","d","d",
				"d","d","w","w","w","w","w"};
		String aux3[]= {"d","w","w","w","w","d","d","d","d","d","d","w","a","a","a","a","a",
				"a","a","s","s","s","s","s"};
		this.guardMove=aux2;
		this.guardMove_backwards=aux3;
		this.guardIndex=0;
		this.moving_front=true;
		this.sleep_counter=0;
		this.asleep=false;	
		this.setAux_flag(false);

	}
	
	public void incrementIndex() {
		if((this.guardIndex)==(this.guardMove.length)-1)
			this.guardIndex=0;
		else
			this.guardIndex++;
	}
	public void decrementIndex() {
		if((this.guardIndex)==0)
			this.guardIndex=(this.guardMove.length)-1;
		else
			this.guardIndex--;
	}
	
	//metodo prox posiçao.
	public String getNextPosition() {
		if(this.moving_front)
			return guardMove[guardIndex];
		else
			return guardMove_backwards[guardIndex];
	}
	
	public boolean get_randAsleep() {
		return randomBool(4);
	}
	//true se muda
	public boolean get_randChangeDir() {
		return randomBool(3);
	}


	public boolean isMoving_front() {
		return moving_front;
	}
	
	public void setMoving_front(boolean b) {
		this.moving_front=b;
	}

	//usado para 
	public boolean isAsleep() {
		return asleep;
	}
	
	//se acordado random para dormir, senao getAsleep
	public boolean asleep_func() { 
		if (this.getAux_flag()) //se acabou de acordar
			return false;
		if (!asleep)
			this.setAsleep(this.get_randAsleep());
		return this.isAsleep();
	}

	public void setAsleep(boolean asleep) {
		this.asleep = asleep;
	}
	
	public void increment_sleepCounter() {
		if(this.sleep_counter<2) //nr de rondas que dorme (3)
			this.sleep_counter++;
		else {
			this.sleep_counter=0;
			this.setAsleep(false);
			this.setAux_flag(true);
			}
	}

	public boolean getAux_flag() {
		return aux_flag;
	}

	public void setAux_flag(boolean aux_flag) {
		this.aux_flag = aux_flag;
	}
	
	//returns random bool (true= 1/prob+1)
	public boolean randomBool (int probability) { 
		int aux;
		Random rand=new Random();
		aux=rand.nextInt(probability+1);
		if(aux<probability)
			return false;
		else return true;
	}


}

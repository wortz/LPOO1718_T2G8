package dkeep.logic;
import java.util.*;

public class Ogre extends GameElement {
	private String ogreMoves[];
	private int club[];
	private boolean stunned;
	private int stun_counter;
	public Ogre(int xi, int yi) {
		super(xi, yi);
		club=new int[2];
		String aux[]= {"a","w","s","d"};
		this.ogreMoves=aux;
		this.stunned=false;
		this.stun_counter=0;
	}
	
	
	
	public String getRandMove() {
		Random randomove=new Random();
		return(this.ogreMoves[randomove.nextInt(4)]);
	}

	public int[] getClub_coord() {
		return club;
	}

	public void setClub_coord(int club[]) {
		this.club[0]=club[0];
		this.club[1]=club[1];
	}



	public boolean isStunned() {
		return this.stunned;
	}



	public void setStunned(boolean stunned) {
		this.stunned = stunned;
	}		
}

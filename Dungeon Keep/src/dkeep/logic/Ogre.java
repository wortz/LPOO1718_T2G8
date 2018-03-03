package dkeep.logic;
import java.util.*;

public class Ogre extends GameElement {
	private String ogreMoves[];
	private int club[];
	public Ogre(int xi, int yi) {
		super(xi, yi);
		this.setClub_coord(new int[2]);
		String aux[]= {"a","w","s","d"};
		this.ogreMoves=aux;

	}
	
	public String getRandMove() {
		Random randomove=new Random();
		return(this.ogreMoves[randomove.nextInt(4)]);
	}

	public int[] getClub_coord() {
		return club;
	}

	public void setClub_coord(int club[]) {
		this.club = club;
	}
		
}

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
		club[0]=-1;
		club[1]=-1;
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

	public boolean stunHandler() {
		if(!this.stunned)
			return false;
		if(stun_counter>2) {
			this.stun_counter=0;
			this.stunned=false;
			return false;
		}
		else {
			this.stun_counter++;
			return true;
		}
	}

	public boolean isStunned() {
		return this.stunned;
	}



	public void setStunned(boolean stunned) {
		this.stunned = stunned;
	}		
	
	/////////////////////////////////////////////////////////////
	//\\\\\\\\\\\\\\\\\\\FUNCOES LVL 2/////////////////////////\\
	//////////////////////////////////////////////////////////
	
	public void moveOgre(Game g) {
		int aux[] = new int[2];
		String nextSimbol;
		String thisSimbol;
			aux[0] = this.getCoord()[0];
			aux[1] = this.getCoord()[1];
			if(this.stunHandler()) {
				return;
			}
			Game.moveHandler(this.getRandMove(), aux);
			while (this.invalidCoord(aux)) {
				aux[0] = this.getCoord()[0];
				aux[1] = this.getCoord()[1];
				Game.moveHandler(this.getRandMove(), aux);
			thisSimbol=g.getMap()[this.getCoord()[0]][this.getCoord()[1]];
			nextSimbol=g.getMap()[aux[0]][aux[1]];
			int a=this.ogreCol(i);
			/* if it is over the key */
			if (thisSimbol== "$"||thisSimbol== "%") {
				if (a!=-1) {
					this.setTableElem(this.getCoord(), "$");
				} else {
					this.setTableElem(this.getCoord(), "k");
				}
			} else {
				if (a==-1) {
					this.setTableElem(this.getCoord(), " ");
				}
				else if(ogre[a].isStunned())
					this.setTableElem(this.getCoord(),"8");
			}
			/* if it goes to the key */
			if (nextSimbol == "k" || nextSimbol == "$")
				this.setTableElem(aux, "$");
			else
				this.setTableElem(aux, "O");
			this.setCoord(aux);
		}
//	}
	

	public void swingClub() {
		int aux[]=new int[2];
		String nextSimbol;
		for(int i=0;i<this.ogre.length;i++) {
		aux[0] = this.getCoord()[0];
		aux[1] = this.getCoord()[1];
		//se strunned nao da swing
		if(this.this.isStunned())
			continue;
		Game.moveHandler(ogre[0].getRandMove(), aux);
		while(this.invalidClubCoord(aux)) {
			aux[0]=this.getCoord()[0];
			aux[1]=this.getCoord()[1];		
			Game.moveHandler(ogre[0].getRandMove(), aux);
		}
		nextSimbol=this.getTable()[aux[0]][aux[1]];
		if(nextSimbol=="k") 
			this.setTableElem(aux,"$");
		else
			this.setTableElem(aux,"*");	
		this.setClub_coord(aux);
		}
	}
	
	public int[][] getOgresCoord(){
		int aux[][]=new int[ogre.length][2];
		for(int i=0;i<ogre.length;i++) {
			if(this.this.isStunned()) 
				continue;
			aux[i][0]=this.this.getCoord()[0];
			aux[i][1]=this.this.getCoord()[1];
		}
		return aux;
	}
	
	
	//TODO:esta funcao no game?
	public int ogreCol(int i) {
		for (int j = 0; j < this.ogre.length; j++) {
			if (j == i)
				continue;
			else if (this.this.getCoord()[0]==this.ogre[j].getCoord()[0]&&this.this.getCoord()[1]==this.ogre[j].getCoord()[1])
				return j;
		}
		return -1;
	}
	
	public boolean invalidCoord(int aux[])
	{
		String tableSimbol=this.getTable()[aux[0]][aux[1]];
		return (tableSimbol=="X"||tableSimbol=="I"||tableSimbol=="S");
	}
	
	public boolean invalidClubCoord(int aux[])
	{
		String tableSimbol=this.getTable()[aux[0]][aux[1]];
		return (this.invalidCoord(aux)||tableSimbol=="O"||tableSimbol=="$"||tableSimbol=="*"||tableSimbol=="8");
	}
	
	public void deleteClub() {
		int aux[]= {-1,-1};
		for(int i=0;i<this.ogre.length;i++) {
		if(this.this.isStunned()) 
			continue;
		if(this.getTable()[this.getClub_coord()[0]][this.getClub_coord()[1]]=="$")
			this.setTableElem(this.getClub_coord(),"k");
		else
			this.setTableElem(this.getClub_coord()," ");
		this.setClub_coord(aux);
			}
		}
	
	public void stunOgre(int i) {
		
		this.setStunned(true);
		int currPos[]=this.this.getCoord();
		String currEle=this.getTable()[currPos[0]][currPos[1]];
		if(currEle=="k")
			this.setTableElem(currPos, "%");
		this.setTableElem(currPos, "8");
	}
}

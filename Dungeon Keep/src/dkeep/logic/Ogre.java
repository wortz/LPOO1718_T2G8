package dkeep.logic;
import java.util.*;

public class Ogre extends GameElement {
	private String ogreMoves[];
	private int club[];
	private boolean stunned;
	private int stun_counter;

	public Ogre(int xi, int yi) {
		super(xi, yi);
		club = new int[2];
		club[0] = -1;
		club[1] = -1;
		String aux[] = { "a", "w", "s", "d" };
		this.ogreMoves = aux;
		this.stunned = false;
		this.stun_counter = 0;
	}

	public String getRandMove() {
		Random randomove = new Random();
		return (this.ogreMoves[randomove.nextInt(4)]);
	}

	public int[] getClub_coord() {
		return club;
	}

	public void setClub_coord(int club[]) {
		this.club[0] = club[0];
		this.club[1] = club[1];
	}

	public boolean stunHandler() {
		if (!this.stunned)
			return false;
		if (stun_counter > 4) {
			this.stun_counter = 0;
			this.stunned = false;
			return false;
		} else {
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
	
	public void moveOgre(Game g, int i) {
		int aux[] = new int[2];
		aux[0] = this.getCoord()[0];
		aux[1] = this.getCoord()[1];
		if (this.stunHandler()) {
			return;
		}
		int a=0;
		Game.moveHandler(this.getRandMove(), aux);
		while (this.invalidCoord(g, aux)) {
			if(a>20)
				return;
			aux[0] = this.getCoord()[0];
			aux[1] = this.getCoord()[1];
			Game.moveHandler(this.getRandMove(), aux);
			a++;
		}
		moveIT(g, aux, i);
	}
	
	public void moveIT(Game g, int aux[], int i) {
		int coord[] = this.getCoord();
		int col = g.ogreCol(i);
		/* if it is over the key */
		if (coord[0] == g.getKeyCoord()[0] && coord[1] == g.getKeyCoord()[1]) {
			if (col != -1) {
				g.setElemTable(this.getCoord(), "$");
			} else {
				g.setElemTable(this.getCoord(), "k");
			}
		} else {
			if (col == -1) {
				g.setElemTable(this.getCoord(), " ");
			} else if (g.getStun(col))
				g.setElemTable(this.getCoord(), "8");
		}
		/* if it goes to the key */
		if (aux[0] == g.getKeyCoord()[0] && aux[1] == g.getKeyCoord()[1])
			g.setElemTable(aux, "$");
		else
			g.setElemTable(aux, "O");
		this.setCoord(aux);
	}
			
	

	public void swingClub(Game g) {
		int aux[]=new int[2];
		int i=0;
		aux[0] = this.getCoord()[0];
		aux[1] = this.getCoord()[1];
		Game.moveHandler(this.getRandMove(), aux);
		while(this.invalidClubCoord(g,aux)) {
			if(i>20)
				return;
			aux[0]=this.getCoord()[0];
			aux[1]=this.getCoord()[1];		
			Game.moveHandler(this.getRandMove(), aux);
			i++;
		}
		if(aux[0]==g.getKeyCoord()[0]&&aux[1]==g.getKeyCoord()[1]) 
			g.setElemTable(aux,"$");
		else
			g.setElemTable(aux,"*");	
		this.setClub_coord(aux);
	}
	
	
	public boolean invalidCoord(Game g, int aux[]) {
		String tableSimbol = g.getMap()[aux[0]][aux[1]];
		return (tableSimbol == "X" || tableSimbol == "I" || tableSimbol == "S" || tableSimbol==g.getHeroSimbol());
	}
	
	public boolean invalidClubCoord(Game g, int aux[]) {
		String tableSimbol = g.getMap()[aux[0]][aux[1]];
		return (this.invalidCoord(g, aux) || tableSimbol == "O" || tableSimbol == "$" || tableSimbol == "*"
				|| tableSimbol == "8");
	}
	
	public void deleteClub(Game g) {
		int aux[]= {-1,-1};
		if(this.club[0]==-1)
			return;
		if(this.getClub_coord()[0]==g.getKeyCoord()[0]&&this.getClub_coord()[1]==g.getKeyCoord()[1])
			g.setElemTable(this.getClub_coord(),"k");
		else
			g.setElemTable(this.getClub_coord()," ");
		this.setClub_coord(aux);
		}
	
	public void stunOgre(Game g, int i) {

		this.setStunned(true);
		int currPos[] = this.getCoord();
		int col = g.ogreCol(i);
		if (col == -1)
			g.setElemTable(currPos, "8");
	}
}

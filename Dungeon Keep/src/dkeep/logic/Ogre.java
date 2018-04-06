package dkeep.logic;
import java.util.*;
/**
 * Ogre.java - class that handles all the ogre logic .
 * @author Joao Fidalgo & Francisco Friande
 * @see GameElement
 */
public class Ogre extends GameElement {
	private String ogreMoves[];
	private int club[];
	private boolean stunned;
	private int stun_counter;

	/**
	 * Constructor of ogre object.
	 * @param xi-A variable of type Int. Position in row of ogre.
	 * @param yi-A variable of type Int. Position in col of ogre.
	 */
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

	/**
	 * gets a number from 0 (included) to 3 (included) and sends it to ogreMoves function.
	 * @return what ogresMoves returns.
	 */
	public String getRandMove() {
		Random randomove = new Random();
		return (this.ogreMoves[randomove.nextInt(4)]);
	}

	/**
	 * @return club - array that contains the coords of this ogre club
	 */
	public int[] getClub_coord() {
		return club;
	}

	/**
	 * Sets the coords of ogre's club.
	 * @param club-A variable of type Int[][].coords to set as coords of club.
	 */
	public void setClub_coord(int club[]) {
		this.club[0] = club[0];
		this.club[1] = club[1];
	}

	/**
	 * Handles the stun of the ogre, number of rounds.
	 * <p>If the number of rounds has reached the limit , or if it is not stunned returns false.
	 * @return true if it is stunned, false otherwise.
	 */
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

	/**
	 * @return stunned- if the ogre is stunned
	 */
	public boolean isStunned() {
		return this.stunned;
	}

	/**
	 * @param stunned-A variable of type Boolean.Boolean to set as this.stunned
	 */
	public void setStunned(boolean stunned) {
		this.stunned = stunned;
	}	
	
	/////////////////////////////////////////////////////////////
	//\\\\\\\\\\\\\\\\\\\FUNCOES LVL 2/////////////////////////\\
	//////////////////////////////////////////////////////////
	
	/**
	 * handles all the conditions and the movement of ogre.
	 * <p>Stays for 20 times in a loop that tries to move the ogre to a valid position.
	 * @param g-A variable of type Game.
	 * @param i-A variable of type Int.
	 */
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
	
	
	/**
	 * @param g - A variable of type Game.
	 * @param aux - A variable of type Int[].
	 * @param i -A variable of type Int.
	 * Handles the rest of move of ogre and club.
	 * <p>Checks all the contions in map.
	 */
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
			
	
	/**
	 * handles all the conditions and the movement of club.
	 * <p>Stays for 20 times in a loop that tries to move the club to a valid position.
	 * @param g-A variable of type Game.
	 */
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
	
	
	/**
	 * Checks if is a valid place to move ogre and club.
	 * @param g-A variable of type Game.
	 * @param aux-A variable of type Int[]. Array with coords.
	 * @return
	 */
	public boolean invalidCoord(Game g, int aux[]) {
		String tableSimbol = g.getMap()[aux[0]][aux[1]];
		return (tableSimbol == "X" || tableSimbol == "I" || tableSimbol == "S" || tableSimbol==g.getHeroSimbol());
	}
	
	/**
	 * Checks if is a valid place to move club with some conditions of invalidCoord.
	 * @param g-A variable of type Game.
	 * @param aux-A variable of type Int[]. Array with coords.
	 * @return
	 */
	public boolean invalidClubCoord(Game g, int aux[]) {
		String tableSimbol = g.getMap()[aux[0]][aux[1]];
		return (this.invalidCoord(g, aux) || tableSimbol == "O" || tableSimbol == "$" || tableSimbol == "*"
				|| tableSimbol == "8");
	}
	
	/**
	 * Delets club from the map.
	 * Sets its coords to -1,-1 to set it out of the map.
	 * @param g-A variable of type Game.
	 */
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
	
	/**
	 * Sets the ogre as stunned and changes the element in map.
	 * @param g-A variable of type Game.
	 * @param i-A variable of type Int. Index of the ogre in the game array of ogres.
	 * 
	 */
	public void stunOgre(Game g, int i) {

		this.setStunned(true);
		int currPos[] = this.getCoord();
		int col = g.ogreCol(i);
		if (col == -1)
			g.setElemTable(currPos, "8");
	}
}

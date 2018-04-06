package dkeep.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Game.java - class that handles all the game logic , where the levels are created
 * @author Joao Fidalgo & Francisco Friande
 *
 */
public class Game {

	private boolean win, lose;
	private int key[];
	private boolean key_catched;
	private float logic;
	private Hero hero;
	private Map map;
	private Guard guard;
	private Ogre ogres[];
	private boolean ogreKey;

	
	/**
	 * @param map A variable of type Map. 
	 * @param logic A variable of type Float. If game is on level1 logic ==1.1||1.2||1.3
	 * Class constructor.
	 */
	public Game(Map map,float logic) {
		this.map=map;
		this.logic=logic;
		this.searchGameElements(); 
		this.win =false;
		this.lose =false;
		this.key_catched=false;
		this.ogreKey=false;
	}
	
	
	/**
	 * searches for all the elements of game in the Map
	 */
	public void searchGameElements() {
		List<int[]> list = new ArrayList<int[]>();
		if (map.serchEle("H", list)) {
			this.hero = new Hero(list.get(0)[0], list.get(0)[1], "H");
			list.clear();
		}
		if (map.serchEle("A", list)) {
			this.hero = new Hero(list.get(0)[0], list.get(0)[1], "A");
			list.clear();
		}
		if (map.serchEle("G", list)) {
			this.guard = new Guard(list.get(0)[0], list.get(0)[1]);
			list.clear();
		}
		if (map.serchEle("O", list)||map.serchEle("$", list)) {
			this.ogres = new Ogre[list.size()];
			int ogre_counter = 0;
			for (int i=0;i<list.size();i++) {
				this.ogres[ogre_counter] = new Ogre(list.get(i)[0], list.get(i)[1]);
				ogre_counter++;
			}
			list.clear();
		}
		if(map.serchEle("$", list)||map.serchEle("k", list)) {
			this.key=new int[2];
			this.key=list.get(0);
			list.clear();
		}
		
	}
	
	/**
	 * @param mov A variable of type String.
	 * @param cord	A variable of type Int[], that is the coords of the object to move.
	 * Changes the cord for each mov.
	 */
	static public void moveHandler(String mov,int cord[]) {
		switch (mov) {
		case "w":
			cord[0]--;
			break;
		case "a":
			cord[1]--;
			break;
		case "s":
			cord[0]++; 
			break;
		case "d":
			cord[1]++;
			break;
				
		}
	}
	
	/**
	 * @param aux A variable of type Int[][].Is the coords of some object/s of game.
	 * @return i Index of the aux that is on a adjacent cell.-1 in case of not found.
	 * 
	 */
	public int onSide(int aux[][]) {
		int dx, dy;
		for(int i=0;i<aux.length;i++) { 
		dy = Math.abs(hero.getCoord()[0] - aux[i][0]);
		dx = Math.abs(hero.getCoord()[1] - aux[i][1]);
		if ((dy <= 1 && dx == 0) || (dy == 0 && dx <= 1)) {
			return i;
			}
		}
		return -1;
	}
	
	/**
	 * Prints Map in console.
	 */
	public void printTable() {
		this.map.printTable();
	}
	
	/**
	 * @param coord A variable of type Int[]. Represents the map coordinate to be changed.
	 * @param s String variable, which will replace the String in coord.
	 * Replaces a String in Map.
	 */
	public void setElemTable(int[] coord,String s) {
		map.setTableElem(coord, s);
	}

	/**
	 * @return True if the Game is in its Win state, false if not.
	 * Funcion to check if the game is won.
	 */
	public boolean isWin() {
		return win;
	}


	/**
	 * @return True if the Game is in its Lose state, false if not.
	 * Funcion to check if the game is lost.
	 */
	public boolean isLose() {
		return lose;
	}

	/**
	 * @param b Boolean that will determine Game's Win state.
	 * Sets Game to Win state.
	 */
	public void setWin(boolean b) {
		this.win=b;
	}

	/**
	 * @return String[][], a matrix of the game's current Map.
	 * Gets Map matrix of Strings.
	 */
	public String[][] getMap() {
		return this.map.getTable();
	}
	/**
	 * @return A variable of type float-the current game logic to be followed.
	 * Returns game's logic: 	<p>
	 * 1.1f if the current level is 1 and the Guard is Rookie.
	 * <p>
	 * 1.2f if the current level is 1 and the Guard is Drunken.
	 * <p>
	 * 1.3f if the current level is 1 and the Guard is Suspicious.
	 * <p>
	 * 2.0f if the current level is 2.
	 */
	public float getLogic() {
		return this.logic;
	}
	
	/**
	 * @param k Boolean variable to be applied in key-catched state.
	 * Sets the key-catched state to k.
	 */
	public void setKey(boolean k) {
		this.key_catched=k;
	}
	
	/**
	 * @return True if the game's key-catched state is true, false if not.
	 * Tells if key is catched or not.
	 */
	public boolean getCatched() {
		return this.key_catched;
	}
	
	/**
	 * @return Return type int[] of the key's coordinates.
	 * Function to recieve key's position.
	 * 
	 */
	public int[] getKeyCoord() {
		return this.key;
	}
	
	/**
	 * Checks and sets Game's loose state, covering all game possibilities and exceptions of losing.
	 */
	public void checkLose()
	{
		int aux[][] =new int[1][0];
		if(this.logic<2.0) {
			if (!this.guard.isAsleep()) {
				aux[0]=this.guard.getCoord();
					if(this.onSide(aux)!=-1)
						this.lose = true;
			}
		}
		if(this.logic>=2.0) {
			aux=this.getEnemysCoord();
			if(this.onSide(aux)!=-1)
				this.lose = true;
		}
	}
	

	/**
	 * @param mov String type parameter of the movement of the Hero.
	 * Calls for hero's movement function according to the mov String.
	 */
	public void mvHero(String mov) {
		this.hero.moveHero(mov,this);
		if(key_catched) {
			this.key[0]=-1;
			this.key[1]=-1;
		}
	}
	
	/**
	 * @return Returns the Hero current symbol.
	 * Gets Hero Symbol (A if hero is armed, H if not).
	 */
	public String getHeroSimbol() {
		return this.hero.getSimbol();
	}
	
	/**
	 * Handles guard's movement, according to the game logic(guard's personality)
	 */
	public void mvGuard() {
		if(this.logic==1.1f)
			guard.moveRookieGuard(this);
		if(this.logic==1.2f)
			guard.moveDrunkenGuard(this);
		if(this.logic==1.3f)
			guard.moveSuspiciousGuard(this);
	}
	
	/**
	 * Activates lever (level1) and turns all I's to S's
	 */
	public void leverOn()
	{
		String aux[][]= this.map.getTable();
		this.key_catched=true;
		for(int i=0;i<this.map.getTable().length;i++)
		{
			for(int j=0;j<this.map.getTable()[i].length;j++)
			{
				if(aux[i][j]=="I")
					aux[i][j]="S";
			}
		}
	}
	
	/**
	 * Moves all ogres in game and swings their clubs, also checks if the ogres get stunned.
	 */
	public void mvOgre() {
		this.ogreKey=false;
		for (int i = 0; i < this.ogres.length; i++) {
			ogres[i].moveOgre(this,i);
			if(key[0]==ogres[i].getCoord()[0]&&key[1]==ogres[i].getCoord()[1])
				this.ogreKey=true;
		}
		if(this.hero.getArmed())
			this.checkStun();
		for (int i = 0; i < this.ogres.length; i++) {
			if(ogres[i].isStunned())
				continue;
			ogres[i].swingClub(this);
		}
		
	} 
	
	/**
	 * Checks if the ogres gets stunned.
	 */
	public void checkStun() {
		int i;
		while((i=onSide(this.getOgresCoord()))!=-1)
			this.ogres[i].stunOgre(this, i);
	}
	
	/**
	 * @return An array of ogre's coordinates (also an array of integers).
	 * Function to get all ogres' positions.
	 */
	public int[][] getOgresCoord(){
		int aux[][]=new int[ogres.length][2];
		for(int i=0;i<ogres.length;i++) {
			if(this.ogres[i].isStunned()) 
				continue;
			aux[i][0]=this.ogres[i].getCoord()[0];
			aux[i][1]=this.ogres[i].getCoord()[1];
		}
		return aux;
	}
	
	
	/**
	 * @return Array of enemys' coordinates.
	 * Returns only clubs' coordinates if the hero is armed and both ogres' and clubs' coordinates if the Hero is unarmed.  
	 * 
	 */
	public int[][] getEnemysCoord(){
		int aux[][];
		if(this.hero.getArmed())		
			aux=new int[ogres.length][2];
		else
			aux=new int[ogres.length*2][2];
			int j=0;
			for(int i=0;i<aux.length;i++) {
			aux[i][0]=this.ogres[j].getClub_coord()[0];
			aux[i][1]=this.ogres[j].getClub_coord()[1];
			if(!this.hero.getArmed()) {
				aux[i+1][0]=this.ogres[j].getCoord()[0];
				aux[i+1][1]=this.ogres[j].getCoord()[1];
				i++;
				}
			j++;
		}
		return aux;
	}
	
	/**
	 * Functions's purpose is to erase all active ogre's clubs from map.
	 */
	public void delClub() {
		for(int i=0;i<ogres.length;i++) {
			if(ogres[i].isStunned()) 
				continue;
		ogres[i].deleteClub(this);
		}
	}
	
	
	/**
	 * @param i Integer type variable of the Ogre's index.
	 * @return True if the given Ogre is stunned, false if not.
	 * Gets the Stun state of the given Ogre (ogre of the index i).
	 */
	public boolean getStun(int i) {
		return this.ogres[i].isStunned();
	}

	/**
	 * @param i Integer type variable of the Ogre's index.
	 * @return Returns an Integer of the Ogre's index which is collided with Ogre of index i, if there is no collision returns -1.
	 * Checks if it has occurred a collision between Ogre of index i, and, if so, returns the index of the other Ogre. <p>
	 * If no collision, returns -1. <p>
	 * Collision means two Ogres are placed in the same position.
	 */
	public int ogreCol(int i) {
		for (int j = 0; j < this.ogres.length; j++) {
			if (j == i)
				continue;
			else {
				if (this.ogres[j].getCoord()[0] == this.ogres[i].getCoord()[0]
						&& this.ogres[j].getCoord()[1] == this.ogres[i].getCoord()[1])
					return j;
			}
		}
		return -1;
	}		
	
	/**
	 * @param i Index of the Ogre, which club is to be checked.
	 * @return A Integer[] variable of the Ogre's club coordinates.
	 * Gets a specific Ogre's club position.
	 */
	public int[] getClubPosition(int i) {
		return ogres[i].getClub_coord();
	}

	/**
	 * @return A Integer[] variable of the Hero's coordinates.
	 * Gets Hero position in the Map.
	 */
	public int[] getHeroPosition() {
		return this.hero.getCoord();
	}
	
	
	/**
	 * @return  An Integer of the current Game level.
	 * Gets the current level of the game.
	 */
	public int getCurrLevel()	{
		if (this.logic==2.0f)
			return 2;
		else return 1;
	}
	
	/**
	 * @return Boolean variable of the collision between Ogre and key- true if so, false if not.
	 * Tells if the Ogre is on top of the key or not.
	 */
	public boolean isOgreKey() {
		return this.ogreKey;
	}
}

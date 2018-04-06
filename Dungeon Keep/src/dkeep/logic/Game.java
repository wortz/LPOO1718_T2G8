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
	 * searches for all the elements of game in a specific Map
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
	 * @param cord	A variable of type Int[]. That is the coords of the object to move.
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
	 * 
	 */
	public void printTable() {
		this.map.printTable();
	}
	
	/**
	 * @param coord
	 * @param s
	 */
	public void setElemTable(int[] coord,String s) {
		map.setTableElem(coord, s);
	}

	/**
	 * @return
	 */
	public boolean isWin() {
		return win;
	}


	/**
	 * @return
	 */
	public boolean isLose() {
		return lose;
	}

	/**
	 * @param b
	 */
	public void setWin(boolean b) {
		this.win=b;
	}

	/**
	 * @return
	 */
	public String[][] getMap() {
		return this.map.getTable();
	}
	/**
	 * @return
	 */
	public float getLogic() {
		return this.logic;
	}
	
	/**
	 * @param k
	 */
	public void setKey(boolean k) {
		this.key_catched=k;
	}
	
	/**
	 * @return
	 */
	public boolean getCatched() {
		return this.key_catched;
	}
	
	/**
	 * @return
	 */
	public int[] getKeyCoord() {
		return this.key;
	}
	
	/**
	 * 
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
	 * @param mov
	 */
	public void mvHero(String mov) {
		this.hero.moveHero(mov,this);
		if(key_catched) {
			this.key[0]=-1;
			this.key[1]=-1;
		}
	}
	
	/**
	 * @return
	 */
	public String getHeroSimbol() {
		return this.hero.getSimbol();
	}
	
	/**
	 * 
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
	 * 
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
	 * 
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
	 * 
	 */
	public void checkStun() {
		int i;
		while((i=onSide(this.getOgresCoord()))!=-1)
			this.ogres[i].stunOgre(this, i);
	}
	
	/**
	 * @return
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
	 * @return
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
	 * 
	 */
	public void delClub() {
		for(int i=0;i<ogres.length;i++) {
			if(ogres[i].isStunned()) 
				continue;
		ogres[i].deleteClub(this);
		}
	}
	
	
	/**
	 * @param i
	 * @return
	 */
	public boolean getStun(int i) {
		return this.ogres[i].isStunned();
	}

	/**
	 * @param i
	 * @return
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
	 * @param i
	 * @return
	 */
	public int[] getClubPosition(int i) {
		return ogres[i].getClub_coord();
	}

	/**
	 * @return
	 */
	public int[] getHeroPosition() {
		return this.hero.getCoord();
	}
	
	
	/**
	 * @return
	 */
	public int getCurrLevel()	{
		if (this.logic==2.0f)
			return 2;
		else return 1;
	}
	
	/**
	 * @return
	 */
	public boolean isOgreKey() {
		return this.ogreKey;
	}
}

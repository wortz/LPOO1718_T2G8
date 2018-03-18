package dkeep.logic;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private boolean win, lose;
	private int key[];
	private boolean key_catched;
	private int logic;//=1 level1 logic =2 level2 logic
	private Hero hero;
	private Map map;
	private Guard guard;
	private Ogre ogres[];

	
	public Game(Map map,int logic) {
		this.map=map;
		this.logic=logic;
		this.searchGameElements();
		this.win =false;
		this.lose =false;
		this.key_catched=false;

	}
	
	// Functions for global game
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
		if (map.serchEle("O", list)|map.serchEle("$", list)) {
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
	
	/*Handles the move of hero or guard*/
	/*Static so it can be called anywhere*/

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
	//checks if any of the elements in aux are on side of hero
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
	
	public void printTable() {
		this.map.printTable();
	}
	
	public void setElemTable(int[] coord,String s) {
		map.setTableElem(coord, s);
	}

	public boolean isWin() {
		return win;
	}


	public boolean isLose() {
		return lose;
	}

	public void setWin(boolean b) {
		this.win=b;
	}

	public String[][] getMap() {
		return this.map.getTable();
	}
	public int getLogic() {
		return this.logic;
	}
	
	public void setKey(boolean k) {
		this.key_catched=k;
	}
	
	public boolean getCatched() {
		return this.key_catched;
	}
	
	public int[] getKeyCoord() {
		return this.key;
	}
	/*
	 * checks if the game is lost
	 * if the logic is 1 it checks the lose with the guard
	 * if it is 2 checks the loso with the clubs
	 */
	public void checkLose()
	{
		int aux[][] =new int[1][0];
		if(this.logic==1) {
			aux[0]=this.guard.getCoord();
		}
		if(this.logic==2) {
			aux=this.getEnemysCoord();
		}
		if(this.onSide(aux)!=-1)
			this.lose = true;
	}
	
	/*Hero's func*/
	public void mvHero(String mov) {
		this.hero.moveHero(mov,this);
	}
	
	public String getHeroSimbol() {
		return this.hero.getSimbol();
	}
	
	/*Guard/LEVEL1 func*/
	
	
	public void mvGuard() {
		guard.moveDrunkenGuard(this);
		if (!this.guard.isAsleep()) 
			this.checkLose();
	}
	
	public void leverOn()
	{
		String aux[][]= this.map.getTable();
		for(int i=0;i<this.map.getTable().length;i++)
		{
			for(int j=0;j<this.map.getTable()[i].length;j++)
			{
				if(aux[i][j]=="I")
					aux[i][j]="S";
			}
		}
	}
	
	/*OGRES/LEVEL2 func*/
	
	
	public void mvOgre() {
		for (int i = 0; i < this.ogres.length; i++) {
			ogres[i].moveOgre(this,i);
		}
		if(this.hero.getArmed())
			this.checkStun();
		for (int i = 0; i < this.ogres.length; i++) {
			if(ogres[i].isStunned())
				continue;
			ogres[i].swingClub(this);
		}
		this.checkLose();
	} 
	
	public void checkStun() {
		int i;
		while((i=onSide(this.getOgresCoord()))!=-1)
			this.ogres[i].stunOgre(this, i);
	}
	
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
	
	public void delClub() {
		for(int i=0;i<ogres.length;i++) {
			if(ogres[i].isStunned()) 
				continue;
		ogres[i].deleteClub(this);
		}
	}
	
	
	public boolean getStun(int i) {
		return this.ogres[i].isStunned();
	}

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

	public int[] getHeroPosition() {
		return this.hero.getCoord();
	}
	
}

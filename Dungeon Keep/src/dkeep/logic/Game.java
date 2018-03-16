package dkeep.logic;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private boolean win, lose;
	private boolean key_catched, lever_on;
	private Hero hero;
//	private Level1 l1;
//	private Level2 l2;
	private Map map;
	private Guard guard;
	private Ogre ogres[];

	
	public Game(Map map) {
		this.map=map;
		this.searchGameElements();
		this.win =false;
		this.lose =false;
		this.key_catched=false;
		this.lever_on=false;

	}
	
	
	public void leverOn()
	{
		this.lever_on=true;
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
	
	public void searchGameElements() {
		List<int[]> list = new ArrayList<int[]>();
		if(map.serchEle("H", list)) {
			this.hero=new Hero(list.get(0)[0],list.get(0)[1]);
			list.clear();
		}
		if(map.serchEle("A", list)) {
			this.hero=new Hero(list.get(0)[0],list.get(0)[1]);
			this.hero.setArmed(true);
			list.clear();
		}
		if(map.serchEle("G", list)) {
			this.guard=new Guard(list.get(0)[0],list.get(0)[1]);
			list.clear();
		}
		else {}
		if(map.serchEle("O", list)) {
			this.ogres=new Ogre[list.size()];
		 int ogre_counter=0;
			for(int[] i: list) {
				this.ogres[ogre_counter]=new Ogre(i[0],i[1]);
				ogre_counter++;
			}
			list.clear();
		}
	}
	public void level1Constructor(){ //nome errado
		String aux1[][]= {{"X","X","X","X","X","X","X","X","X","X"},
				{"X","H"," "," ","I"," ","X"," ","G","X"},
				{"X","X","X"," ","X","X","X"," "," ","X"},
				{"X"," ","I"," ","I"," ","X"," "," ","X"},
				{"X","X","X"," ","X","X","X"," "," ","X"},
				{"I"," "," "," "," "," "," "," "," ","X"},
				{"I"," "," "," "," "," "," "," "," ","X"},
				{"X","X","X"," ","X","X","X","X"," ","X"},
				{"X"," ","I"," ","I"," ","X","k"," ","X"},
				{"X","X","X","X","X","X","X","X","X","X"}};	
	
			this.map.setTable(aux1);	
			
				
			
	}
	
	public void mvHero(String mov) {
		this.hero.moveHero(mov,map);
		
	}
	
//	/*Function to deal with the hero's move*/
//	public void moveHero(String mov, Map l) {
//		int aux[]=new int[2];
//		aux[0]=hero.getCoord()[0];
//		aux[1]=hero.getCoord()[1];
//	    moveHandler(mov, aux);
//		if(l.getTable()[aux[0]][aux[1]]!="X"&&l.getTable()[aux[0]][aux[1]]!="I"&&l.getTable()[aux[0]][aux[1]]!="g"&&l.getTable()[aux[0]][aux[1]]!="8") {
//			//if the hero gets to the S , the winning condition
//			if(l.getTable()[aux[0]][aux[1]]=="S")
//			{
//				this.win=true;
//			}
//			/*if the hero gets to the lever,k*/
//			if(l.getTable()[aux[0]][aux[1]]=="k" && this.getLevel()==l1) {
//				l1.leverOn();
//			}
//			//if the hero gets to the lever,k
//			if(l.getTable()[aux[0]][aux[1]]=="k" && this.getLevel()==l2) {
//				this.key_catched=true;
//			}
//			
//			l.setTableElem(hero.getCoord()," ");
//			this.hero.setCoord(aux);
//			
//			if(this.key_catched)
//				l.setTableElem(hero.getCoord(),"K");
//			else
//				l.setTableElem(hero.getCoord(),this.hero.getSimbol());			
//			}
//		if(l.getTable()[aux[0]][aux[1]]=="I"&&this.key_catched)
//		{
//			int arr[]= {1,0};
//			l.setTableElem(arr,"S");			
//		}
//		this.checkStun();
//		this.checkLose(l);
//		
//	}
//	
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
	
	public void checkLose(Map l)
	{
		int aux[][]=l.checkLose_aux();
		if(this.onSide(aux)!=-1)
			this.lose = true;
	}
	
	public int onSide(int aux[][]) {
		int dx, dy;
		for(int i=0;i<aux.length;i++) { 
		dy = Math.abs(hero.getCoord()[0] - aux[i][0]);
		dx = Math.abs(hero.getCoord()[1] - aux[i][1]);
		if ((dy == 1 && dx == 0) || (dy == 0 && dx == 1)) {
			return i;
			}
		}
		return -1;
	}
	
	public void checkStun() {
		int i;
		while((i=onSide(l2.getOgresCoord()))!=-1)
			l2.stunOgre(i);
	}

	public void printTable() {
		this.getLevel().printTable();
	}

	public boolean isWin() {
		return win;
	}


	public boolean isLose() {
		return lose;
	}

	
	public Map getLevel() {
		if (l2==null)
			return l1;
		return l2;  
	}
	public void mvGuard() {
		guard.moveDrunkenGuard(this);//TODO: como pintar o mapa depois disto?
		if (!guard.getAsleep_guard())
			this.checkLose(l1);
	}
	public void mvOgre() {
		for (int i = 0; i < this.ogres.length; i++) {
		
			ogres[i].moveOgre(this);
			
		}
		l2.moveOgre();
		if(this.hero.getArmed())
		this.checkStun();
		l2.swingClub();
		this.checkLose(l2);
	}
	
	public void delClub() {
		ogre.deleteClub();
	}
	
	
	public void startLvl2() {
		this.l2 = new Level2();
		this.hero=new Hero(7,1);
		this.hero.setArmed(true);
		this.hero.setSimbol("A");
	}

	public void setWin(boolean b) {
		this.win=b;
	}


	public String[][] getMap() {
		return this.map.getTable();
	}
	
	public void setMap(String m[][] ) {
		this.map.setTable(m);
	}
	
}

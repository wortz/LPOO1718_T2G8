package dkeep.logic;

public class GameState {

	private boolean win, lose;
	private boolean key_catched;
	private Hero hero;
	private Level1 l1;
	private Level2 l2;
	
	public GameState() {
		this.win =false;
		this.lose =false;
		this.l1 = new Level1();
		this.hero=new Hero(1,1);

	}
	
	/*Function to deal with the hero's move*/
	public void moveHero(String mov, Level l) {
		int aux[]=new int[2];
		aux[0]=hero.getCoord()[0];
		aux[1]=hero.getCoord()[1];
	    moveHandler(mov, aux);
		if(l.getTable()[aux[0]][aux[1]]!="X"&&l.getTable()[aux[0]][aux[1]]!="I") {
			//if the hero gets to the S , the winning condition
			if(l.getTable()[aux[0]][aux[1]]=="S")
			{
				this.win=true;
			}
			/*if the hero gets to the lever,k*/
			if(l.getTable()[aux[0]][aux[1]]=="k") {
				l.leverOn();
			}
			//if the hero gets to the lever,k
			if(l.getTable()[aux[0]][aux[1]]=="k" && this.getLevel()==l2) {
				this.key_catched=true;
			}
			
			l.setTableElem(hero.getCoord()," ");
			this.hero.setCoord(aux);
			
			if(this.key_catched)
				l.setTableElem(hero.getCoord(),"K");
			else
				l.setTableElem(hero.getCoord(),"H");			
			}
		if(l.getTable()[aux[0]][aux[1]]=="I"&&this.key_catched)
		{
			int arr[]= {1,0};
			l.setTableElem(arr,"S");			
		}
		checkLose(l);
		
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
	
	public void checkLose(Level l)
	{
		
		int dx, dy;
		int aux[]=l.checkLose_aux();
		 
		dy = Math.abs(hero.getCoord()[0] - aux[0]);
		dx = Math.abs(hero.getCoord()[1] - aux[1]);

		if ((dy == 1 && dx == 0) || (dy == 0 && dx == 1))
			this.lose = true;

		if (aux.length==4) {
			dy = Math.abs(hero.getCoord()[0] - aux[2]);
			dx = Math.abs(hero.getCoord()[1] - aux[3]);
		
		if ((dy == 1 && dx == 0) || (dy == 0 && dx == 1))
				this.lose = true;
		}
	}

	public void printTable() {
		if(this.l2==null)
			l1.printTable();
		else l2.printTable();
	}

	public boolean isWin() {
		return win;
	}


	public boolean isLose() {
		return lose;
	}

	
	public Level getLevel() {
		if (l2==null)
			return l1;
		return l2;  
	}
	public void mvGuard() {
		l1.moveGuard();
		this.checkLose(l1);
	}
	public void mvOgre() {
		l2.moveOgre();
		this.checkLose(l2);
	}
	
	public void delClub() {
		l2.deleteClub();
	}
	
	
	public void startLvl2() {
		this.l2 = new Level2();
		this.hero=new Hero(7,1);
	}

	public void setWin(boolean b) {
		this.win=b;
	}
}

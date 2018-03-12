package dkeep.logic;

public class Level2 extends Level{
	private Ogre ogre[];
	public Level2() {
		int x=3;
		String aux1[][]= {{"X","X","X","X","X","X","X","X","X"},
				{"I"," "," "," ","O"," "," ","k","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," ","O","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X","A"," "," "," "," "," "," ","X"},
				{"X","X","X","X","X","X","X","X","X"}};		
		this.setTable(aux1);
		this.ogre=new Ogre[x];
		Ogre o1=new Ogre(1,4);
		Ogre o2=new Ogre(1,4);
		Ogre o3=new Ogre(3,7);
		this.ogre[0]=o1;
		this.ogre[1]=o2;
		this.ogre[2]=o3;
		
		
	}
	
	public void moveOgre() {
		int aux[] = new int[2];
		for (int i = 0; i < this.ogre.length; i++) {
			aux[0] = ogre[i].getCoord()[0];
			aux[1] = ogre[i].getCoord()[1];

			GameState.moveHandler(ogre[0].getRandMove(), aux);
			while (this.invalidCoord(aux)) {
				aux[0] = ogre[i].getCoord()[0];
				aux[1] = ogre[i].getCoord()[1];
				GameState.moveHandler(ogre[0].getRandMove(), aux);
			}
			/* if it is over the key */
			if (this.getTable()[ogre[i].getCoord()[0]][ogre[i].getCoord()[1]] == "$") {
				if (this.ogreCol(i)) {
					this.setTableElem(ogre[i].getCoord(), "$");
				} else {
					this.setTableElem(ogre[i].getCoord(), "k");
				}
			} else {
				if (!this.ogreCol(i)) {
					this.setTableElem(ogre[i].getCoord(), " ");
				}
			}
			/* if it goes to the key */
			if (this.getTable()[aux[0]][aux[1]] == "k" || this.getTable()[aux[0]][aux[1]] == "$")
				this.setTableElem(aux, "$");
			else
				this.setTableElem(aux, "O");
			ogre[i].setCoord(aux);
		}
		this.swingClub();
	}
	

	public void swingClub() {
		int aux[]=new int[2];
		for(int i=0;i<this.ogre.length;i++) {
		aux[0] = ogre[i].getCoord()[0];
		aux[1] = ogre[i].getCoord()[1];
		GameState.moveHandler(ogre[0].getRandMove(), aux);
		while(this.invalidClubCoord(aux)) {
			aux[0]=ogre[i].getCoord()[0];
			aux[1]=ogre[i].getCoord()[1];		
			GameState.moveHandler(ogre[0].getRandMove(), aux);
		}
		if(this.getTable()[aux[0]][aux[1]]=="k") 
			this.setTableElem(aux,"$");
		else
			this.setTableElem(aux,"*");	
		ogre[i].setClub_coord(aux);
		}
	}
	
	public int[][] getOgresCoord(){
		int aux[][]=new int[ogre.length][2];
		for(int i=0;i<ogre.length;i++) {
			aux[i]=this.ogre[i].getCoord();
		}
		return aux;
	}
	
	public boolean ogreCol(int i) {
		for (int j = 0; j < this.ogre.length; j++) {
			if (j == i)
				continue;
			else if (this.ogre[i].getCoord()[0]==this.ogre[j].getCoord()[0]&&this.ogre[i].getCoord()[1]==this.ogre[j].getCoord()[1])
				return true;
		}
		return false;
	}
	
	public boolean invalidCoord(int aux[])
	{
		return (this.getTable()[aux[0]][aux[1]]=="X"||this.getTable()[aux[0]][aux[1]]=="I"||this.getTable()[aux[0]][aux[1]]=="S");
	}
	
	public boolean invalidClubCoord(int aux[])
	{
		return (this.invalidCoord(aux)||this.getTable()[aux[0]][aux[1]]=="O");
	}
	
	public void deleteClub() {
		int aux[]= {10,10};
		for(int i=0;i<this.ogre.length;i++) {
		if(this.getTable()[ogre[i].getClub_coord()[0]][ogre[i].getClub_coord()[1]]=="$")
			this.setTableElem(ogre[i].getClub_coord(),"k");
		else
			this.setTableElem(ogre[i].getClub_coord()," ");
		ogre[i].setClub_coord(aux);
			}
		}
	
	public void stunOgre(int i) {
		ogre[i].setStunned(true);
	}

	public void leverOn() {}
	
	/*
	 * returns array size 4, first ogre coord[0 and 1], then club coor[2 and 3] 
	 * (non-Javadoc)
	 * @see dkeep.logic.Level#checkLose_aux()
	 */
	public int[][] checkLose_aux() {
		int aux[][]=new int[ogre.length][4];
		for(int i=0;i<this.ogre.length;i++) {
		aux[i][2]=this.ogre[i].getClub_coord()[0];
		aux[i][3]=this.ogre[i].getClub_coord()[1];
		}
		return aux;
	}
	
}

package dkeep.logic;

public class Level2 extends Level{
	private Ogre ogre;
	public Level2() {
		String aux1[][]= {{"X","X","X","X","X","X","X","X","X"},
				{"I"," "," "," ","O"," "," ","k","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X","H"," "," "," "," "," "," ","X"},
				{"X","X","X","X","X","X","X","X","X"}};		
		this.setTable(aux1);
		this.ogre=new Ogre(1,4);
	}
	
	public void moveOgre() {
		int aux[]=new int[2];
		aux[0]=ogre.getCoord()[0];
		aux[1]=ogre.getCoord()[1];

		GameState.moveHandler(ogre.getRandMove(), aux);
		while(this.invalidCoord(aux)) { 
			aux[0]=ogre.getCoord()[0];
			aux[1]=ogre.getCoord()[1];
			GameState.moveHandler(ogre.getRandMove(), aux);
		}
		/*if it is over the key*/
			if(this.getTable()[ogre.getCoord()[0]][ogre.getCoord()[1]]=="k") 
				this.setTableElem(ogre.getCoord(),"$");
			else
				this.setTableElem(ogre.getCoord()," ");		/*if it goes to the key*/
			if(this.getTable()[aux[0]][aux[1]]=="k") 
				this.setTableElem(aux,"$");
			else
				this.setTableElem(aux,"O");
			ogre.setCoord(aux);		
		this.swingClub();
	//	this.checkLose();
	}
	

	public void swingClub() {
		int aux[]=new int[2];
		aux[0]=ogre.getCoord()[0];
		aux[1]=ogre.getCoord()[1];	
		GameState.moveHandler(ogre.getRandMove(), aux);
		while(this.invalidCoord(aux)) {
			aux[0]=ogre.getCoord()[0];
			aux[1]=ogre.getCoord()[1];		
			GameState.moveHandler(ogre.getRandMove(), aux);
		}
		if(this.getTable()[aux[0]][aux[1]]=="k") 
			this.setTableElem(aux,"$");
		else
			this.setTableElem(aux,"*");	
		ogre.setClub_coord(aux);
	}
	
	public boolean invalidCoord(int aux[])
	{
		return (this.getTable()[aux[0]][aux[1]]=="X"||this.getTable()[aux[0]][aux[1]]=="I"||this.getTable()[aux[0]][aux[1]]=="S");
	}
	
	public void deleteClub() {
		if(this.getTable()[ogre.getClub_coord()[0]][ogre.getClub_coord()[1]]=="$")
			this.setTableElem(ogre.getClub_coord(),"k");
		else
			this.setTableElem(ogre.getClub_coord()," ");
		}

	public void leverOn() {}
	
	/*
	 * returns array size 4, first ogre coord[0 and 1], then club coor[2 and 3] 
	 * (non-Javadoc)
	 * @see dkeep.logic.Level#checkLose_aux()
	 */
	public int[] checkLose_aux() {
		int aux[]=new int[4];
		aux[0]=this.ogre.getCoord()[0];
		aux[1]=this.ogre.getCoord()[1];
		aux[2]=this.ogre.getClub_coord()[0];
		aux[3]=this.ogre.getClub_coord()[1];
		return aux;
	}
	
}

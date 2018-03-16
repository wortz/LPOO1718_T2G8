package dkeep.logic;

public class Level2 extends Map{
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
		this.setTable(aux1 );
		this.ogre=new Ogre[x];
		Ogre o1=new Ogre(1,4);
		Ogre o2=new Ogre(1,4);
		Ogre o3=new Ogre(3,7);
		this.ogre[0]=o1;
		this.ogre[1]=o2;
		this.ogre[2]=o3;
		
		
		
	}
	
//	public void moveOgre() {
//		int aux[] = new int[2];
//		String nextSimbol;
//		String thisSimbol;
//		for (int i = 0; i < this.ogre.length; i++) {
//			aux[0] = ogre[i].getCoord()[0];
//			aux[1] = ogre[i].getCoord()[1];
//			if(this.ogre[i].stunHandler()) {
//				continue;
//			}
//			Game.moveHandler(ogre[0].getRandMove(), aux);
//			while (this.invalidCoord(aux)) {
//				aux[0] = ogre[i].getCoord()[0];
//				aux[1] = ogre[i].getCoord()[1];
//				Game.moveHandler(ogre[0].getRandMove(), aux);
//			}
//			thisSimbol=this.getTable()[ogre[i].getCoord()[0]][ogre[i].getCoord()[1]];
//			nextSimbol=this.getTable()[aux[0]][aux[1]];
//			int a=this.ogreCol(i);
//			/* if it is over the key */
//			if (thisSimbol== "$"||thisSimbol== "%") {
//				if (a!=-1) {
//					this.setTableElem(ogre[i].getCoord(), "$");
//				} else {
//					this.setTableElem(ogre[i].getCoord(), "k");
//				}
//			} else {
//				if (a==-1) {
//					this.setTableElem(ogre[i].getCoord(), " ");
//				}
//				else if(ogre[a].isStunned())
//					this.setTableElem(ogre[i].getCoord(),"8");
//			}
//			/* if it goes to the key */
//			if (nextSimbol == "k" || nextSimbol == "$")
//				this.setTableElem(aux, "$");
//			else
//				this.setTableElem(aux, "O");
//			ogre[i].setCoord(aux);
//		}
//	}
//	
//
//	public void swingClub() {
//		int aux[]=new int[2];
//		String nextSimbol;
//		for(int i=0;i<this.ogre.length;i++) {
//		aux[0] = ogre[i].getCoord()[0];
//		aux[1] = ogre[i].getCoord()[1];
//		//se strunned nao da swing
//		if(this.ogre[i].isStunned())
//			continue;
//		Game.moveHandler(ogre[0].getRandMove(), aux);
//		while(this.invalidClubCoord(aux)) {
//			aux[0]=ogre[i].getCoord()[0];
//			aux[1]=ogre[i].getCoord()[1];		
//			Game.moveHandler(ogre[0].getRandMove(), aux);
//		}
//		nextSimbol=this.getTable()[aux[0]][aux[1]];
//		if(nextSimbol=="k") 
//			this.setTableElem(aux,"$");
//		else
//			this.setTableElem(aux,"*");	
//		ogre[i].setClub_coord(aux);
//		}
//	}
//	
//	public int[][] getOgresCoord(){
//		int aux[][]=new int[ogre.length][2];
//		for(int i=0;i<ogre.length;i++) {
//			if(this.ogre[i].isStunned()) 
//				continue;
//			aux[i][0]=this.ogre[i].getCoord()[0];
//			aux[i][1]=this.ogre[i].getCoord()[1];
//		}
//		return aux;
//	}
//	
//	public int ogreCol(int i) {
//		for (int j = 0; j < this.ogre.length; j++) {
//			if (j == i)
//				continue;
//			else if (this.ogre[i].getCoord()[0]==this.ogre[j].getCoord()[0]&&this.ogre[i].getCoord()[1]==this.ogre[j].getCoord()[1])
//				return j;
//		}
//		return -1;
//	}
//	
//	public boolean invalidCoord(int aux[])
//	{
//		String tableSimbol=this.getTable()[aux[0]][aux[1]];
//		return (tableSimbol=="X"||tableSimbol=="I"||tableSimbol=="S");
//	}
//	
//	public boolean invalidClubCoord(int aux[])
//	{
//		String tableSimbol=this.getTable()[aux[0]][aux[1]];
//		return (this.invalidCoord(aux)||tableSimbol=="O"||tableSimbol=="$"||tableSimbol=="*"||tableSimbol=="8");
//	}
//	
//	public void deleteClub() {
//		int aux[]= {-1,-1};
//		for(int i=0;i<this.ogre.length;i++) {
//		if(this.ogre[i].isStunned()) 
//			continue;
//		if(this.getTable()[ogre[i].getClub_coord()[0]][ogre[i].getClub_coord()[1]]=="$")
//			this.setTableElem(ogre[i].getClub_coord(),"k");
//		else
//			this.setTableElem(ogre[i].getClub_coord()," ");
//		ogre[i].setClub_coord(aux);
//			}
//		}
//	
//	public void stunOgre(int i) {
//		
//		ogre[i].setStunned(true);
//		int currPos[]=this.ogre[i].getCoord();
//		String currEle=this.getTable()[currPos[0]][currPos[1]];
//		if(currEle=="k")
//			this.setTableElem(currPos, "%");
//		this.setTableElem(currPos, "8");
//	}
//TODO: handle do stun counter 
//TODO: se tiver stunned nao stunnar
//TODO: Stun a dois ao mesmo tempo
//TODO: Stun em cima do k
//TODO: Ogre mexer para cima de um 8
	
	
	public int[][] checkLose_aux() {
		int aux[][]=new int[ogre.length][2];
		for(int i=0;i<this.ogre.length;i++) {
			if(this.ogre[i].isStunned())
				continue;
		aux[i][0]=this.ogre[i].getClub_coord()[0];
		aux[i][1]=this.ogre[i].getClub_coord()[1];
		}
		return aux;
	}
	
}

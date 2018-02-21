import java.util.*;


public class DK2 {
	private String table[][];
	private int hero[];
	private int ogre[];
	private boolean win,lose,key_catched;
	private String ogreMoves[];
	
	/*constructor to start the game*/
	public DK2()
	{
		this.hero=new int[2];
		this.ogre=new int[2];
		String aux1[][]= {{"X","X","X","X","X","X","X","X","X"},
				{"I"," "," "," ","O"," "," ","k","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X","H"," "," "," "," "," "," ","X"},
				{"X","X","X","X","X","X","X","X","X"}};	
		String aux2[]= {"a","w","s","d"};
		this.win=false;
		this.lose=false;
		this.key_catched=false;
		this.table = aux1;
		this.hero[0]=7;
		this.hero[1]=1;
		this.ogre[0]=1;
		this.ogre[1]=4;
		this.ogreMoves=aux2;
		
		
	}
	
	public boolean getWin() {
		return this.win;
	}
	
	public boolean getLose() {
		return this.lose;
	}
	
	public int getRandMove() {
		Random randomove=new Random();
		return(randomove.nextInt(4));
	}
	
	/*prints the table as it is in the moment*/
	public void printTable() {
		for(int i=0;i<this.table.length;i++)
		{
			for(int j=0;j<this.table[i].length;j++)
			{
				System.out.print(this.table[i][j]);
				System.out.print(' ');
			}
			System.out.println();
		}
	}
	/*Handles the move of hero or ogre*/
	public void moveHandler(String mov,int cord[]) {
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
	
	/*Function to deal with the hero's move*/
	public void moveHero(String mov) {
		int aux[]=new int[2];
		aux[0]=this.hero[0];
		aux[1]=this.hero[1];
		this.moveHandler(mov, aux);
		if(this.table[aux[0]][aux[1]]!="X"&&this.table[aux[0]][aux[1]]!="I") {
			//if the hero gets to the S , the winning condition
			if(this.table[aux[0]][aux[1]]=="S")
			{
				this.win=true;
			}
			//if the hero gets to the lever,k
			if(this.table[aux[0]][aux[1]]=="k") {
				this.key_catched=true;
			}
			this.table[hero[0]][hero[1]]=" ";
			this.hero=aux;
			if(this.key_catched)
				this.table[this.hero[0]][hero[1]]="K";
			else
				this.table[hero[0]][hero[1]]="H";
		}
		if(this.table[aux[0]][aux[1]]=="I"&&this.key_catched)
		{
			this.table[1][0]="S";
		}
		checkLose();
		
	}
	
	public void checkLose()
	{
		int dx,dy;
		dy=Math.abs(this.hero[0]-this.ogre[0]);
		dx=Math.abs(this.hero[1]-this.ogre[1]);
		
		if((dy==1&&dx==0)||(dy==0&&dx==1))
			this.lose=true;
	}
	
	
	public void swingClub(int ogre[]) {
		/*int aux[]=new int[2];
		aux[0]=this.ogre[0];
		aux[1]=this.ogre[1];*/		
		this.moveHandler(this.ogreMoves[this.getRandMove()], ogre);
		
	}
	
	public void moveOgre() {
		int aux[]=new int[2];
		aux[0]=this.ogre[0];
		aux[1]=this.ogre[1];

		this.moveHandler(this.ogreMoves[this.getRandMove()], aux);
		if(this.table[aux[0]][aux[1]]!="X"&&this.table[aux[0]][aux[1]]!="I"&&this.table[aux[0]][aux[1]]!="S") { 
			if(this.table[this.ogre[0]][this.ogre[1]]=="$")
				this.table[this.ogre[0]][this.ogre[1]]="k";
			else
				this.table[this.ogre[0]][this.ogre[1]]=" ";
			if(this.table[aux[0]][aux[1]]=="k") 
				this.table[aux[0]][aux[1]]="$";
			else
				this.table[aux[0]][aux[1]]="O";
			this.ogre=aux;		
		}
		this.checkLose();
	}

}

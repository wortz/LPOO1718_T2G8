import java.util.*;


public class DK2 {
	private String table[][];
	private int hero[];
	private int ogre[];
	private boolean win,lose;
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
		this.table = aux1;
		this.hero[0]=8;
		this.hero[1]=1;
		this.ogre[0]=1;
		this.ogre[1]=4;
		this.ogreMoves=aux2;
		
		
	}
	
	public int getRandMove() {
		Random randomove=new Random();
		return(randomove.nextInt(3));
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
				this.leverOn();
			}
			this.table[hero[0]][hero[1]]=" ";
			this.hero=aux;
			this.table[hero[0]][hero[1]]="H";
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
	
	/*Turns all the I to S when the lever is pressed*/
	public void leverOn()
	{
		for(int i=0;i<this.table.length;i++)
		{
			for(int j=0;j<this.table[i].length;j++)
			{
				if(this.table[i][j]=="I")
					this.table[i][j]="S";
			}
		}
	}
	
			
	public void moveOgre() {
		int aux[]=new int[2];
		aux[0]=this.ogre[0];
		aux[1]=this.ogre[1];

		this.moveHandler(this.ogreMoves[this.getRandMove()], aux);
		if(this.table[aux[0]][aux[1]]!="X"&&this.table[aux[0]][aux[1]]!="I") { 
			this.table[this.ogre[0]][this.ogre[1]]=" ";
			this.table[aux[0]][aux[1]]="O";
			this.ogre=aux;		
		}
	}
	
	public static void main(String[] args) {
		DK2 game=new DK2();
		Scanner input = new Scanner(System.in);
		game.printTable();
		while((!game.win)&&(!game.lose)) {
		String mov= input.next();
		game.moveHero(mov);
		game.moveOgre();
		game.printTable();
		}
		input.close();
		if(game.win)
			System.out.println("You win.");
		if(game.lose)
			System.out.println("You lose.");
	}

}

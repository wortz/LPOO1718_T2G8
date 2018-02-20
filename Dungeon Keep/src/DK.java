import java.util.*;


public class DK {
	private String table[][];
	private int hero[];
	private int guard[];
	private boolean win,lose;
	private String guardMove[];
	
	/*constructor to start the game*/
	public DK()
	{
		this.hero=new int[2];
		this.guard=new int[2];
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
		String aux2[]= {"a","s","s","s","s","s","a","a","a","a","a","a","s","d","d","d","d","d",
						"d","d","w","w","w","w","w"};
		this.win=false;
		this.lose=false;
		this.table = aux1;
		this.guardMove=aux2;
		this.hero[0]=1;
		this.hero[1]=1;
		this.guard[0]=1;
		this.guard[1]=8;
		
		
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
	
	/*Function to deal with the hero's move*/
	public void moveHero(String mov) {
		int aux[]=new int[2];
		aux[0]=this.hero[0];
		aux[1]=this.hero[1];
		switch (mov) {
		case "w":
			aux[0]--;
			break;
		case "a":
			aux[1]--;
			break;
		case "s":
			aux[0]++;
			break;
		case "d":
			aux[1]++;
			break;
				
		}
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
		/*if(this.table[this.hero[0]][this.hero[1]+1]!="X"&&this.table[this.hero[0]][this.hero[1]+1]!="I") {
			//if the hero gets to the S , the winning condition
			if(this.table[this.hero[0]][this.hero[1]+1]=="S")
			{
				this.win=true;
			}
			//if the hero gets to the lever,k
			if(this.table[this.hero[0]][this.hero[1]+1]=="k") {
				this.leverOn();
			}
			this.table[hero[0]][hero[1]]=" ";
			this.hero[1]++;
			this.table[hero[0]][hero[1]]="H";
		}*/
		checkLose();
		
	}
	
	public void checkLose()
	{
		int dx,dy;
		dy=Math.abs(this.hero[0]-this.guard[0]);
		dx=Math.abs(this.hero[1]-this.guard[1]);
		
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
	
	public void moveGuard() {
		
	}
	
	public static void main(String[] args) {
		DK game=new DK();
		Scanner input = new Scanner(System.in);
		game.printTable();
		while((!game.win)&&(!game.lose)) {
		String mov= input.next();
		game.moveHero(mov);
		game.printTable();
		}
		input.close();
		if(game.win)
			System.out.println("You win.");
		if(game.lose)
			System.out.println("You lose.");
	}

}

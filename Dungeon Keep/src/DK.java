import java.util.*;


public class DK {
	private String table[][];
	private int hero[];
	private int guard[];
	private boolean win,lose;
	public DK()
	{
		this.hero=new int[2];
		this.guard=new int[2];
		String aux[][]= {{"X","X","X","X","X","X","X","X","X","X"},
				{"X","H"," "," ","I"," ","X"," ","G","X"},
				{"X","X","X"," ","X","X","X"," "," ","X"},
				{"X"," ","I"," ","I"," ","X"," "," ","X"},
				{"X","X","X"," ","X","X","X"," "," ","X"},
				{"I"," "," "," "," "," "," "," "," ","X"},
				{"I"," "," "," "," "," "," "," "," ","X"},
				{"X","X","X"," ","X","X","X","X"," ","X"},
				{"X"," ","I"," ","I"," ","X","k"," ","X"},
				{"X","X","X","X","X","X","X","X","X","X"}};	
		this.win=false;
		this.lose=false;
		this.table = aux;
		this.hero[0]=1;
		this.hero[1]=1;
		this.guard[0]=1;
		this.guard[1]=8;
	}
	
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
	public void moveHero(String mov) {
		switch (mov) {
		case "w":
			if(this.table[this.hero[0]-1][this.hero[1]]!="X" && this.table[this.hero[0]-1][this.hero[1]]!="I") {
				if(this.table[this.hero[0]-1][this.hero[1]]=="S")
				{
					this.win=true;
					break;
				}
				if(this.table[this.hero[0]-1][this.hero[1]]=="k") {
					this.leverOn();
				}
				this.table[hero[0]][hero[1]]=" ";
				this.hero[0]--;
				this.table[hero[0]][hero[1]]="H";
			}
				;
			break;
		case "a":
			if(this.table[this.hero[0]][this.hero[1]-1]!="X" && this.table[this.hero[0]][this.hero[1]-1]!="I") {
				if(this.table[this.hero[0]][this.hero[1]-1]=="S")
				{
					this.win=true;
					break;
				}
				if(this.table[this.hero[0]][this.hero[1]-1]=="k") {
					this.leverOn();
				}
				this.table[hero[0]][hero[1]]=" ";
				this.hero[1]--;
				this.table[hero[0]][hero[1]]="H";
			}
			break;
		case "s":
			if(this.table[this.hero[0]+1][this.hero[1]]!="X" && this.table[this.hero[0]+1][this.hero[1]]!="I") {
				if(this.table[this.hero[0]+1][this.hero[1]]=="S")
				{
					this.win=true;
					break;
				}
				if(this.table[this.hero[0]+1][this.hero[1]]=="k") {
					this.leverOn();
				}
				this.table[hero[0]][hero[1]]=" ";
				this.hero[0]++;
				this.table[hero[0]][hero[1]]="H";
			}
			break;
		case "d":
			if(this.table[this.hero[0]][this.hero[1]+1]!="X"&&this.table[this.hero[0]][this.hero[1]+1]!="I") {
				if(this.table[this.hero[0]][this.hero[1]+1]=="S")
				{
					this.win=true;
					break;
				}
				if(this.table[this.hero[0]][this.hero[1]+1]=="k") {
					this.leverOn();
				}
				this.table[hero[0]][hero[1]]=" ";
				this.hero[1]++;
				this.table[hero[0]][hero[1]]="H";
			}
			break;
				
		}
	}
	
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
	
	public static void main(String[] args) {
		DK game=new DK();
		Scanner input = new Scanner(System.in);
		
		while(!game.win) {
		game.printTable();
		String mov= input.next();
		game.moveHero(mov);
		}
		input.close();
	}

}

package dkeep.logic;

public class Level1 extends Level{
	private Guard guard;
	private String guardMove[];
	private int guardIndex;
	public Level1() {
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
		String aux2[]= {"a","s","s","s","s","a","a","a","a","a","a","s","d","d","d","d","d",
				"d","d","w","w","w","w","w"};
		this.guardMove=aux2; 
		this.setTable(aux1);
		this.guard=new Guard(1,8);
		this.guardIndex=0;
	}
	
	public void leverOn()
	{
		String aux[][]= this.getTable();
		for(int i=0;i<this.getTable().length;i++)
		{
			for(int j=0;j<this.getTable()[i].length;j++)
			{
				if(aux[i][j]=="I")
					aux[i][j]="S";
			}
		}
		this.setTable(aux);
	}
	public void moveGuard() {
		String aux[][]= this.getTable();
		aux[this.guard.getCoord()[0]][this.guard.getCoord()[1]]=" ";
		GameState.moveHandler(this.guardMove[guardIndex], this.guard.getCoord());  //talvez por movehandler no elements
		aux[this.guard.getCoord()[0]][this.guard.getCoord()[1]]="G";
		if((this.guardIndex)==(this.guardMove.length)-1)
			this.guardIndex=0;
		else
			this.guardIndex++;
		this.setTable(aux);
		}
	

}
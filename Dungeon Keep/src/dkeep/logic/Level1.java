package dkeep.logic;


public class Level1 extends Level{
	private Guard guard;
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
		this.setTable(aux1);
		this.guard=new Guard(1,8);
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
	public void moveRookieGuard() {
		String aux[][]= this.getTable();
		aux[this.guard.getCoord()[0]][this.guard.getCoord()[1]]=" ";
		GameState.moveHandler(this.guard.getNextPosition(), this.guard.getCoord()); 
		aux[this.guard.getCoord()[0]][this.guard.getCoord()[1]]="G";
		this.guard.incrementIndex();
		this.setTable(aux);
	}

	public void moveDrunkenGuard() {
		String aux[][] = this.getTable();
		if (!this.guard.asleep_func() || this.guard.getAux_flag()) { // para o caso de dormir duas vezes seguidas (fodia o index e nao faz sentido)
			aux[this.guard.getCoord()[0]][this.guard.getCoord()[1]] = " ";
			GameState.moveHandler(this.guard.getNextPosition(), this.guard.getCoord());
			aux[this.guard.getCoord()[0]][this.guard.getCoord()[1]] = "G";
			nextGuardIndex();
			if (this.guard.getAux_flag())
				this.guard.setAux_flag(false);
		}

		else {
			aux[this.guard.getCoord()[0]][this.guard.getCoord()[1]] = "g";
			this.guard.increment_sleepCounter();
			if (!this.guard.isAsleep()) { // just woke up
				this.guard.setAux_flag(true); // just woke up flag(so he doesnt sleep 2 times or more in a row)
				if (this.guard.get_randChangeDir()) {
					this.guard.setMoving_front(!this.guard.isMoving_front());
					nextGuardIndex();
				}
			}
		}
		this.setTable(aux);
	}

	public void moveSuspiciousGuard() {
		String aux[][]= this.getTable();
			aux[this.guard.getCoord()[0]][this.guard.getCoord()[1]] = " ";
			GameState.moveHandler(this.guard.getNextPosition(), this.guard.getCoord());
			aux[this.guard.getCoord()[0]][this.guard.getCoord()[1]] = "G";
			if (this.guard.getAux_flag()) {  //se acabou de mudar de dir
				nextGuardIndex();
				this.guard.setAux_flag(false);
			}
			else {
				
			if (this.guard.get_randChangeDir()) {
				this.guard.setMoving_front(!this.guard.isMoving_front());
				this.guard.setAux_flag(true);
			}
			else {
				nextGuardIndex();
				
			}
			}
			
		this.setTable(aux);

		}
	
	
	public int[][] checkLose_aux() {
		int aux[][]=new int[1][2];
		aux[0]=this.guard.getCoord();
		return aux;
	}

	public void nextGuardIndex() {
		if (this.guard.isMoving_front())
			this.guard.incrementIndex();
		else
			this.guard.decrementIndex();		
	}
	

}
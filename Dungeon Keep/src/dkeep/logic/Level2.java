package dkeep.logic;

public class Level2{
	private int logic;
	private Game game;
	public Level2() {
		String table[][]= {{"X","X","X","X","X","X","X","X","X"},
				{"I"," "," "," ","O"," "," ","k","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X","A"," "," "," "," "," "," ","X"},
				{"X","X","X","X","X","X","X","X","X"}};		
		Map map=new Map(table);
		this.logic=2;
		this.game=new Game(map,logic);
	}
	
	public Game getGame() {
		return this.game;
	}
	
}

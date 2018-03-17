package dkeep.logic;


public class Level1{
	private int logic;
	private Game game;
	public Level1() {
		String table[][]= {{"X","X","X","X","X","X","X","X","X","X"},
			{"X","H"," "," ","I"," ","X"," ","G","X"},
			{"X","X","X"," ","X","X","X"," "," ","X"},
			{"X"," ","I"," ","I"," ","X"," "," ","X"},
			{"X","X","X"," ","X","X","X"," "," ","X"},
			{"I"," "," "," "," "," "," "," "," ","X"},
			{"I"," "," "," "," "," "," "," "," ","X"},
			{"X","X","X"," ","X","X","X","X"," ","X"},
			{"X"," ","I"," ","I"," ","X","k"," ","X"},
			{"X","X","X","X","X","X","X","X","X","X"}};
		Map map=new Map(table);
		this.logic=1;
		this.game=new Game(map,logic);
		
	}
	
	public Game getGame() {
		return this.game;
	}
	
}
package dkeep.logic;

/**
 * Level1.java - class that handles the creation of a game with the logic of level 1.
 * @author Joao Fidalgo & Francisco Friande
 *
 */
public class Level1{
	private Game game;
	/**
	 * Creates a new Game with the standard map and the given logic.
	 * @param logic - A variable of type Float.
	 */
	public Level1(float logic) {
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
		this.game=new Game(map,logic);
		
	}
	
	/**
	 * @return this.game - - A variable of type Game.
	 */
	public Game getGame() {
		return this.game;
	}
	
}
package dkeep.logic;

import java.util.Random;
/**
 * Level2.java - class that handles the creation of a game with the logic of level 2.
 * @author Joao Fidalgo & Francisco Friande
 *
 */
public class Level2{
	private int logic;
	private Game game;
	
	/**
	 * Creates a game with the given map.
	 * @param map - A variable of type Map.
	 */
	public Level2(Map map) {
		this.logic=2; 
		this.game=new Game(map,logic);
	}
	
	
	/**
	 * Creates a game with a given number of ogres.
	 * <p>Sets the position of the ogres in map randomly.
	 * @param ogres_number - A variable of type Int.
	 * @throws IllegalArgumentException - number of ogres must be from 1 to 5 , throws the exception if it is not.
	 */
	public Level2(int ogres_number) throws IllegalArgumentException{
		if(ogres_number<1||ogres_number>5)
			throw new IllegalArgumentException("Ogres number sould be a number between 1 and 5");
		
		String table[][]= {{"X","X","X","X","X","X","X","X","X"},
				{"I"," "," "," "," "," "," ","k","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X","A"," "," "," "," "," "," ","X"},
				{"X","X","X","X","X","X","X","X","X"}};		
		for(int i=0;i<ogres_number;i++) {
			int[] r=this.getRandomOgrePosition();
			while(table[r[0]][r[1]]!=" ") {
				r=this.getRandomOgrePosition();
			}
			table[r[0]][r[1]]="O";
		}
		Map map=new Map(table);
		this.logic=2;
		this.game=new Game(map,logic);
	}
	
	/**
	 * Generates a random ogres coords.
	 * @return r - - A variable of type Int[].Coords random generated.
	 */
	public int[] getRandomOgrePosition(){
		int row=0, col=0;
		
		Random rand = new Random();
		row= rand.nextInt(4)+1;
		col= rand.nextInt(6)+1;
		int[]r= {row,col};
		return r;
	}
	
	/**
	 * Gets the game created by this object.
	 * @return this.game- - A variable of type Game.
	 */
	public Game getGame() {
		return this.game;
	}
	
}

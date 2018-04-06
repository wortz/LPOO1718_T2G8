package dkeep.logic;

import java.util.Random;

public class Level2{
	private int logic;
	private Game game;
	
	//to be used in editor
	public Level2(Map map) {
		this.logic=2; 
		this.game=new Game(map,logic);
	}
	
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
	
	public int[] getRandomOgrePosition(){
		int row=0, col=0;
		
		Random rand = new Random();
		row= rand.nextInt(4)+1;
		col= rand.nextInt(6)+1;
		int[]r= {row,col};
		return r;
	}
	
	public Game getGame() {
		return this.game;
	}
	
}

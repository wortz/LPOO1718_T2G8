package dkeep.logic;

import java.util.Random;

public class Level2{
	private int logic;
	private Game game;
	public Level2(int ogres_number){
		String table[][]= {{"X","X","X","X","X","X","X","X","X"},
				{"I"," "," "," "," "," "," ","k","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X"," "," "," "," "," "," "," ","X"},
				{"X","A"," "," "," "," "," "," ","X"},
				{"X","X","X","X","X","X","X","X","X"}};		
		Map map=new Map(table);
		for(int i=0;i<ogres_number;i++) 
			map.setTableElem(this.getRandomOgrePosition(map), "O");
		this.logic=2;
		this.game=new Game(map,logic);
	}
	
	public int[] getRandomOgrePosition(Map m){
		int row=0, col=0;
		
		Random rand = new Random();
		while(m.getTable()[col][row]!=" ") {
			row= rand.nextInt(6)+1;
			col= rand.nextInt(6)+2;
		}
		int[]r= {row,col};
		return r;
	}
	
	public Game getGame() {
		return this.game;
	}
	
}

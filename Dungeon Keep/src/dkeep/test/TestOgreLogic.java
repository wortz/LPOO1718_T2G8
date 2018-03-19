package dkeep.test;
import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.*;

public class TestOgreLogic {
	String map[][]=  {{"X","X","X","X","X","X"},
			{"X","H"," "," ","O","X"}, 
			{"I"," "," "," "," ","X"},
			{"I"," "," "," "," ","X"},
			{"X"," "," ","k"," ","X"},
			{"X","X","X","X","X","X"}};	
	
	@Test
	public void testHeroKilledbyOgre() {
		Map m= new Map(map);
		Game game = new Game (m,2);
		assertFalse(game.isLose());
		game.mvHero("d");
		game.mvHero("d");
		game.checkLose();
		assertTrue(game.isLose());
	}
	
	
}

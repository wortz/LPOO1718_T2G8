package dkeep.test;
import static org.junit.Assert.*;

import dkeep.logic.Hero;
import dkeep.logic.Level1;
import dkeep.logic.GameState;

public class TestDKlogic {
	String map[][]=  {{"X","X","X","X","X","X"},
			{"X","H"," "," ","G","X"}, 
			{"I"," "," "," "," ","X"},
			{"I"," "," "," "," ","X"},
			{"X"," "," ","k"," ","X"},
			{"X","X","X","X","X","X"}};	
	
	@Test
	public void testMoveHeroIntoaFreeCell() {
		Level1 game = new Level1();
		game.setTable(map);
		int c[ ]= {4,1};
		game.setGuardCoord(c);
		assertEquals(new GameElement(1,1),game.)

}

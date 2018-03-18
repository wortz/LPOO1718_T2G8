package dkeep.test;
import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.*;

public class TestDungeonLevelLogic {
	String map[][]=  {{"X","X","X","X","X","X"},
			{"X","H"," "," ","G","X"}, 
			{"I"," "," "," "," ","X"},
			{"I"," "," "," "," ","X"},
			{"X"," "," ","k"," ","X"},
			{"X","X","X","X","X","X"}};	
	
	@Test
	public void testMoveHeroIntoaFreeCell() {
		Map m= new Map(map);
		Game game = new Game (m,1);
		int pos1[]= {1,1};
		int pos2[]= {2,1};
		assertArrayEquals(pos1,game.getHeroPosition());
		game.mvHero("s");
		assertArrayEquals(pos2,game.getHeroPosition());}


	@Test
	public void testMoveHeroIntoaWall() {
		Map m= new Map(map);
		Game game = new Game (m,1);
		int pos1[]= {1,1};
		assertArrayEquals(pos1,game.getHeroPosition());
		game.mvHero("w");
		assertArrayEquals(pos1,game.getHeroPosition());}

	@Test
	public void testHeroKilledbyGuard() {
		Map m= new Map(map);
		Game game = new Game (m,1);
		assertFalse(game.isLose());
		game.mvHero("d");
		game.mvHero("d");
		game.checkLose();
		assertTrue(game.isLose());
	}
	
	@Test
	public void testHeroTriestoLeaveClosedDoor() {
		Map m= new Map(map);
		Game game = new Game (m,1);
		int pos1[]= {2,1};
		game.mvHero("s");
		assertArrayEquals(pos1,game.getHeroPosition());
		game.mvHero("a");
		assertArrayEquals(pos1,game.getHeroPosition());
		assertFalse(game.isWin());
	}
	
	@Test
	public void testHeroOpensGates() {
		Map m= new Map(map);
		Game game = new Game (m,1);
		assertEquals("I",game.getMap()[2][0]);
		game.mvHero("s");
		game.mvHero("s");
		game.mvHero("s");
		game.mvHero("d");
		game.mvHero("d");
		assertEquals("S",game.getMap()[2][0]);
		///TODO aqui deviamos fazer um get do leverOn
	}
	
	@Test
	public void testHeroGetstoKeepLevel() {
		Map m= new Map(map);
		Game game = new Game (m,1);
		game.mvHero("s");
		game.mvHero("s");
		game.mvHero("s");
		game.mvHero("d");
		game.mvHero("d");
		game.mvHero("a");
		game.mvHero("a");
		game.mvHero("w");
		game.mvHero("a");
		assertTrue(game.isWin());	
	}
}

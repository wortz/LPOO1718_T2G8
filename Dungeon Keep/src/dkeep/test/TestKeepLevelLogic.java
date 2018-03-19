package dkeep.test;
import static org.junit.Assert.*;

import org.junit.Test;

import dkeep.logic.*;

public class TestKeepLevelLogic {
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
	
	
	@Test
	public void testHeroCatchesKey() {
		Map m= new Map(map);
		Game game = new Game (m,2);
		game.mvHero("s");
		game.mvHero("s");
		game.mvHero("s");
		game.mvHero("d");
		game.mvHero("d");
		assertEquals("K",game.getHeroSimbol());
	}
	
	@Test
	public void testHeroTriestoLeaveClosedDoor() {
		Map m= new Map(map);
		Game game = new Game (m,2);
		int pos1[]= {2,1};
		game.mvHero("s");
		assertArrayEquals(pos1,game.getHeroPosition());
		game.mvHero("a");
		assertArrayEquals(pos1,game.getHeroPosition());
		assertFalse(game.isWin());
	}
	
	@Test
	public void testHeroOpensDoorWithKey() {
		Map m= new Map(map);
		Game game = new Game (m,2);
		game.mvHero("s");
		game.mvHero("s");
		game.mvHero("s");
		game.mvHero("d");
		game.mvHero("d");
		game.mvHero("a");
		game.mvHero("a");
		game.mvHero("w");
		assertEquals("I",game.getMap()[3][0]);
		game.mvHero("a");
		assertEquals("S",game.getMap()[3][0]);
	}
	
	@Test
	public void testHeroWinsGame() {
		Map m= new Map(map);
		Game game = new Game (m,2);
		game.mvHero("s");
		game.mvHero("s");
		game.mvHero("s");
		game.mvHero("d");
		game.mvHero("d");
		game.mvHero("a");
		game.mvHero("a");
		game.mvHero("w");
		game.mvHero("a");
		game.mvHero("a");
		assertTrue(game.isWin());
	}
	
}

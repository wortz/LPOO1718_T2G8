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
	
	String map_armed[][]=  {{"X","X","X","X","X","X"},
			{"X","A"," "," ","O","X"}, 
			{"I"," "," "," "," ","X"},
			{"I"," "," "," "," ","X"},
			{"X"," "," ","k"," ","X"},
			{"X","X","X","X","X","X"}};	
	
	@Test(timeout=1000)
	public void testOgreMovesRandomly() {
		Map m = new Map(map);
		Game game = new Game(m, 2);
		boolean movesDown = false, movesLeft = false, movesUp = false, movesRight = false;
		int pos1[] = game.getOgresCoord()[0];
		while (!movesDown || !movesLeft || !movesUp || !movesRight) {
			game.mvOgre();
			game.delClub();
			if (game.getOgresCoord()[0][0] == (pos1[0]+1)) {
				movesDown = true;
			}
			else if (game.getOgresCoord()[0][1] == (pos1[1]-1)) {
				movesLeft = true;
			} else if (game.getOgresCoord()[0][0] == (pos1[0]-1)) {
				movesUp = true;
			} else if (game.getOgresCoord()[0][1] == (pos1[1]+1)) {
				movesRight = true;
			} else
				fail("Did not move in any direction");
			pos1 = game.getOgresCoord()[0];

		}
	}
	
	@Test(timeout=1000)
	public void testOgreSwingsClub() {
		Map m = new Map(map);
		Game game = new Game(m, 2);
		boolean swingsDown = false, swingsLeft = false, swingsUp = false, swingsRight = false;
		while (!swingsDown || !swingsLeft || !swingsUp || !swingsRight) {
			game.mvOgre();
			int pos1[] = game.getOgresCoord()[0];
			if (game.getClubPosition(0)[0] == (pos1[0]+1)) {
				swingsDown = true;
			} else if (game.getClubPosition(0)[1] == (pos1[1]-1)) {
				swingsLeft = true;
			} else if (game.getClubPosition(0)[0] == (pos1[0]-1)) {
				swingsUp = true;
			} else if (game.getClubPosition(0)[1] == (pos1[1]+1)) {
				swingsRight = true;
			} else
				fail("Did not move in any direction");
			game.delClub();
		}
	}
	
	@Test
	public void testOgreGetsStunned() {
		Map m = new Map(map_armed);
		Game game = new Game(m, 2);
		int[] pos=game.getOgresCoord()[0];
		assertFalse(game.getStun(0));
		assertEquals("O",game.getMap()[pos[0]][pos[1]]);
		game.mvHero("d");
		game.mvHero("d");
		game.checkStun();
		assertTrue(game.getStun(0));
		assertEquals("8",game.getMap()[pos[0]][pos[1]]);
		}
	
	@Test(timeout=1000)
	public void testOgreMovesRandomly_and_HeroDies() {
		Map m = new Map(map_armed);
		Game game = new Game(m, 2);
		while (!game.isLose()) {
			game.mvOgre();
			game.checkLose();
		
			game.printTable();
			game.delClub();
		}
		assertTrue(game.isLose());
	}
	
}

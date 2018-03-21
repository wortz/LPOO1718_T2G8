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
	
	@Test(timeout=1000)
	public void testOgreMovesRandomly() {
		Map m = new Map(map);
		Game game = new Game(m, 2);
		boolean movesDown = false, movesLeft = false, movesUp = false, movesRight = false;
		int pos1[] = game.getOgresCoord()[0];
		while (!movesDown || !movesLeft || !movesUp || !movesRight) {
			game.mvOgre();
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
	
	@Test
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
		}
	}
	
	/*@Test
	public void testOgreGetsStunned() {
		Map m = new Map(map);
		Game game = new Game(m, 2);
		boolean swingsDown = false, swingsLeft = false, swingsUp = false, swingsRight = false;
		while (!swingsDown && !swingsLeft && !swingsUp && !swingsRight) {
			game.mvOgre();
			int pos1[] = game.getOgresCoord()[0];
			if (game.getClubPosition(0)[0] == (pos1[0]++)) {
				swingsDown = true;
			} else if (game.getClubPosition(0)[1] == (pos1[1]--)) {
				swingsLeft = true;
			} else if (game.getClubPosition(0)[0] == (pos1[0]--)) {
				swingsUp = true;
			} else if (game.getClubPosition(0)[1] == (pos1[1]++)) {
				swingsRight = true;
			} else
				fail("Did not move in any direction");
		}
	}*/
	
	
	
}

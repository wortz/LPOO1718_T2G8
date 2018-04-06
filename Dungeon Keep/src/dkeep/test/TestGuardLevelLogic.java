package dkeep.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dkeep.logic.Game;
import dkeep.logic.*;
import dkeep.logic.Map;

public class TestGuardLevelLogic {
	private String aux2[] = { "a", "s", "s", "s", "s", "a", "a", "a", "a", "a", "a", "s", "d", "d", "d", "d", "d", "d",
			"d", "w", "w", "w", "w", "w" };
	
	public void StringHandler(int pos[],String mov) {
		switch (mov) {
		case "w":
			pos[0]--;
			break;
		case "a":
			pos[1]--;
			break;
		case "s":
			pos[0]++;
			break;
		case "d":
			pos[1]++;
			break;
		}
	}
	
	@Test
	public void testRookiePath() {
		Level1 l1=new Level1(1.1f);
		Game game = l1.getGame();
		int pos[]= {1,8};
		String guard="G";
		assertEquals(guard,game.getMap()[pos[0]][pos[1]]);
		for(String i : aux2) {
		game.mvGuard();
		StringHandler(pos,i);
		assertEquals(guard,game.getMap()[pos[0]][pos[1]]);
		}
	}
	
	/*@Test
	public void testDrunkeSleep() {
		Level1 l1=new Level1(1.2f);
		Game game = l1.getGame();
		
	}*/
	
	
}

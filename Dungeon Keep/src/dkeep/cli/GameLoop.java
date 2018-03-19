package dkeep.cli;
import java.util.*;
import dkeep.logic.*;

public class GameLoop {
	
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		String mov;
		Level1 l1=new Level1();
		Game game=l1.getGame();
		game.printTable();
		while ((!game.isWin()) && (!game.isLose())) {
			mov = input.next();
			game.mvHero(mov);
			game.mvGuard();
			game.checkLose();
			game.printTable();
		}
		if (game.isLose()) {
			System.out.println("You lose.");
			input.close();
			return;
		}
		else 
			game.setWin(false);
		System.out.println();
		Level2 l2=new Level2();
		game=l2.getGame();
		game.printTable();
		while((!game.isWin()) && (!game.isLose())) {
			mov= input.next();
			game.mvHero(mov);
			game.mvOgre();
			game.checkLose();
			game.printTable();
			System.out.println(game.getHeroSimbol());
			game.delClub();
			}
		if(game.isLose()) {
			System.out.println("You lose.");
			input.close();
			return;
			}
		System.out.println("You win.");
		
		
		input.close();
	}

}

package dkeep.cli;
import java.util.*;
import dkeep.logic.*;

public class GameLoop {
	
	
	public static void main(String[] args) {
		GameState game = new GameState();
		Scanner input = new Scanner(System.in);
		String mov;
		/*game.printTable();
		while ((!game.isWin()) && (!game.isLose())) {
			mov = input.next();
			game.moveHero(mov, game.getLevel());
			if(game.isLose()) {
				game.printTable();
				break;
			}
			game.mvGuard();
			game.printTable();
		}
		if (game.isLose()) {
			System.out.println("You lose.");
			input.close();
			return;
		}
		else 
			game.setWin(false);*/
		System.out.println();
		game.startLvl2();
		game.printTable();
		while((!game.isWin()) && (!game.isLose())) {
			mov= input.next();
			game.moveHero(mov,game.getLevel());
			if(game.isLose()) {
				game.printTable();
				break;
			}
			game.mvOgre();
			game.printTable();
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


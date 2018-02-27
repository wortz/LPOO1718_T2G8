package dkeep.cli;

import java.util.Scanner;

public class UserInterface {
	public void gameLoop()
	{
		
	}
	
	
	public static void main(String[] args) {
		Level1 game=new Level1();
		Scanner input = new Scanner(System.in);
		String mov;
		game.printTable();
		while((!game.win)&&(!game.lose)) {
		mov= input.next();
		game.moveHero(mov);
		game.moveGuard();
		game.printTable();
		}
		if(game.lose) {
			System.out.println("You lose.");
			input.close();
			return;
		}
		Level2 game2=new Level2();
		System.out.println();
		game2.printTable();
		while((!game2.getWin())&&(!game2.getLose())) {
			mov= input.next();
			game2.moveHero(mov);
			game2.moveOgre();
			game2.printTable();
			game2.deleteClub();
			}
		if(game.lose) {
			System.out.println("You lose.");
			input.close();
			return;
			}
		System.out.println("You win.");
		input.close();
	}
}

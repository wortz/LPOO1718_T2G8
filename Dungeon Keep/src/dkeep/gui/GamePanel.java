package dkeep.gui;

import java.awt.GridLayout;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import dkeep.logic.Game;
import dkeep.logic.Level1;
import dkeep.logic.Level2;
import dkeep.logic.Map;

public class GamePanel{
	private Game game;
	private MapGraphics map;
	private GraphicMode graphic;
    private Level2 l2;	
	
	public GamePanel(int ogresNr,float guardPers,GraphicMode graphic){
		startGameWindow( guardPers, graphic);
		l2 = new Level2(ogresNr);
	}
	
	//editor
	public GamePanel(float guardPers, Map map,GraphicMode graphic){
		startGameWindow( guardPers, graphic);
		l2=new Level2(map);
	}
	
	public void startGameWindow(float guardPers,GraphicMode graphic) {
		this.graphic=graphic;
		Level1 l1=new Level1(guardPers);
		game = l1.getGame();
		map=new MapGraphics(game.getMap(),game.getLogic()); 
	}
	
	public void gameLoop(String direction) { 
		if ((!game.isWin()) && (!game.isLose())) {
			game.mvHero(direction);
			game.checkLose();
			if (game.isLose()) {
				map.update(game.getMap(), game.isOgreKey()); 
				return;
			}
			if (game.getCurrLevel() == 1)
				game.mvGuard();
			else if (game.getCurrLevel() == 2)
				game.mvOgre();

			game.checkLose();
			map.update(game.getMap(), game.isOgreKey());
			if (game.getCurrLevel() == 2)
				game.delClub();
			
			if (game.isWin() && game.getCurrLevel() == 1) {
				this.game = l2.getGame();

				game.setWin(false);
				graphic.removeGame();
				map=new MapGraphics(game.getMap(),game.getLogic());
				
				graphic.setLayerAux(this);
				map.repaint();
				
			} else if (game.isWin() && game.getCurrLevel() == 2) {
				return;
			}
			if (game.isLose()) {
				System.out.println("loose");
				return;}
		}
	}
	
	public MapGraphics getMapGraphics() {
		return this.map;
	}
}

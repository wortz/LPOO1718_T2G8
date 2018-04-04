package dkeep.gui;

import java.awt.GridLayout;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import dkeep.logic.Game;
import dkeep.logic.Level1;
import dkeep.logic.Level2;

public class GamePanel{
	private Game game;
	private int ogresNr;
	private float guardPers;
	private MapGraphics map;
	private GraphicMode graphic;
	
	
	public GamePanel(int ogresNr,float guardPers,GraphicMode graphic){
		this.graphic=graphic;
		this.ogresNr=ogresNr;
		this.guardPers=guardPers;
		startGameWindow();
	}
	
	public void startGameWindow() {
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
			if (game.isLose())
				return;
			else if (game.isWin() && game.getCurrLevel() == 1) {
				Level2 l2 = new Level2(this.ogresNr);
				this.game = l2.getGame();
				game.setWin(false);
				
				map=new MapGraphics(game.getMap(),game.getLogic());
				
				graphic.setLayerAux(this);
				map.repaint();
				
			} else if (game.isWin() && game.getCurrLevel() == 2) {
				return;
			}
		}
	}
	
	public MapGraphics getMapGraphics() {
		return this.map;
	}
}

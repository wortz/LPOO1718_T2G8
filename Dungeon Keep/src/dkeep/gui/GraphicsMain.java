package dkeep.gui;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import dkeep.logic.Game;
import dkeep.logic.Level1;
import dkeep.logic.Level2;

public class GraphicsMain extends JFrame implements KeyListener{

	private GamePanel g;
	private JFrame frame;
	static Game game;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicsMain window = new GraphicsMain();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	GraphicsMain(){
		frame = new JFrame();
		frame.setBounds(100, 100, 597, 688);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Graphically enhanced DUNGEON KEEP");
		
		
		

		Level1 l1 = new Level1( 1.1f);
		game = l1.getGame();
		
		g = new GamePanel(game);
		g.setBounds(0, 0, 1000, 1000);
		g.loadImages();
		
		addKeyListener(this);

		update();
	}

	
	public void update() {
		g.update(game);
		frame.setVisible(true);
		frame.add(g);
	}

	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_A) {
			moveHandler( "a");
		}
		else if(k.getKeyCode() == KeyEvent.VK_W) {
			moveHandler( "w");
		}
		else if(k.getKeyCode() == KeyEvent.VK_D) {
			moveHandler("d");
		}
		else if(k.getKeyCode() == KeyEvent.VK_S) {
			moveHandler( "s");
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}
	
	public void moveHandler(String direction) {

		if ((!game.isWin()) && (!game.isLose())) {
			game.mvHero(direction);
			game.checkLose();
			if (game.isLose()) {
				update();
				return;
			}
			if (game.getCurrLevel() == 1)
				game.mvGuard();
			else if (game.getCurrLevel() == 2)
				game.mvOgre();
			game.checkLose();
			update();
			if (game.getCurrLevel() == 2)
				game.delClub();
			if (game.isLose()) {
 				return;
			} else if (game.isWin() && game.getCurrLevel() == 1) {
				Level2 l2 = new Level2(3);
				this.game = l2.getGame();
				game.setWin(false);
				update();
		}

			else if (game.isWin() && game.getCurrLevel() == 2) {
				return;
			}
		}
	};
}


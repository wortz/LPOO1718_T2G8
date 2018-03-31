package dkeep.gui;

import java.awt.Color;
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
	Game game;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicsMain window = new GraphicsMain();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	GraphicsMain(){
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Graphically enhanced DUNGEON KEEP");
		
		frame.addKeyListener(this);
		
		
		Level1 l1 = new Level1( 1.1f);
		game = l1.getGame();
		
		g = new GamePanel(game);
		g.setBounds(0, 0, 500, 500);
		g.loadImages(game);
		iniciate();
	}

	
	public void iniciate() {
		g.paint(game);
		frame.setVisible(true);
		frame.add(g);
	}
	
	public void update() {
		g.update(game);
		frame.add(g);
	}

	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_LEFT) {
			moveHandler( "a");
		}
		else if(k.getKeyCode() == KeyEvent.VK_UP) {
			moveHandler( "w");
		}
		else if(k.getKeyCode() == KeyEvent.VK_RIGHT) {
			moveHandler("d");
		}
		else if(k.getKeyCode() == KeyEvent.VK_DOWN) {
			moveHandler( "s");
		}
		
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
			else if (game.getCurrLevel() == 2) {
				game.mvOgre();
			}
			game.checkLose();
			update();
			if (game.getCurrLevel() == 2)
				game.delClub();
			if (game.isLose()) {
 				return;
			} else if (game.isWin() && game.getCurrLevel() == 1) {
				Level2 l2 = new Level2(1);
				this.game = l2.getGame();
				game.setWin(false);
		}

			else if (game.isWin() && game.getCurrLevel() == 2) {
				return;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}


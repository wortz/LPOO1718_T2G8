package dkeep.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import dkeep.logic.Game;
import dkeep.logic.Level1;
import dkeep.logic.Level2;

public class GraphicsMain implements KeyListener{

	private GamePanel gamePanel;
	private OptionsPanel optionsPanel;
	private JFrame frame;
	Game game;
	private int ogresNr;
	private float guardPers;
	private JLayeredPane pane;
	
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
//		frame.getContentPane().setLayout(null);
		frame.setTitle("Graphically enhanced DUNGEON KEEP");
		frame.addKeyListener(this);
		
		
		
		pane = new JLayeredPane();
		pane.setBounds(0, 0, 500, 500);
		frame.getContentPane().add(pane);
		pane.setLayout(null);
		
		/////////////////////////////////////////////////////
		this.optionsPanel= new OptionsPanel();
		optionsPanel.setBounds(0, 0, 500, 500);
		frame.add(optionsPanel);
		frame.setVisible(true);
		
		
		
		//////////////////////////////////////////////////////////
		/*Level1 l1 = new Level1( 1.1f);
		game = l1.getGame();
		
		
		iniciate(game);
		
		frame.pack();*/

	}

	
	public void iniciate(Game game) {
		gamePanel = new GamePanel(game);
		gamePanel.setBounds(0, 0, 500, 500);
		gamePanel.loadImages(game);
		update();
//		frame.pack();
		frame.setVisible(true);
	}
	
	
	public void update() {
		gamePanel.update(game);
		frame.add(gamePanel);
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
		frame.requestFocusInWindow();	
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
				gamePanel.repaint();
				iniciate(game);
				
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


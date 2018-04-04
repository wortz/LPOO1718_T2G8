package dkeep.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
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

	ComboGrid c;
	static GamePanel gamePanel;
	static OptionsPanel optionsPanel;
	static JFrame frame;
	Game game;
	
	static JLayeredPane pane;
	
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
//		frame.setLayout(new FlowLayout());
		
		frame.setTitle("Graphically enhanced DUNGEON KEEP");
		
		
		frame.addKeyListener(this);
		
		pane = new JLayeredPane();
		pane.setBounds(0, 0, 900, 900);
		frame.getContentPane().add(pane);
		
		pane.addComponentListener(new ComponentAdapter() {
      public void componentResized(ComponentEvent e) {
      	gamePanel.scaleAll();
      	gamePanel.update();
          super.componentResized(e);
      }
  });
		
//		frame.add(pane, FlowLayout.CENTER);
		pane.setLayout(null);
		
		//////////////////////TEMPORARY
		this.c=new ComboGrid(10,10);
		c.setBounds(0, 0, 800,800);
		pane.add(c);
		c.setVisible(true);
		frame.setVisible(true);
		///////////////
		
		
		/////////////////////////////////////////////////////
	/*	this.optionsPanel= new OptionsPanel();
		optionsPanel.setBounds(0, 0, 500, 500);
		pane.add(optionsPanel);
		optionsPanel.setVisible(true);
		frame.setVisible(true);*/
		
		
		
		

	}


	
	@Override
	public void keyPressed(KeyEvent k) {
//		if(gamePanel!=null) {
		if(k.getKeyCode() == KeyEvent.VK_LEFT) {
			gamePanel.gameLoop("a");
		}
		else if(k.getKeyCode() == KeyEvent.VK_UP) {
			gamePanel.gameLoop("w");
		}
		else if(k.getKeyCode() == KeyEvent.VK_RIGHT) {
			gamePanel.gameLoop("d");
		}
		else if(k.getKeyCode() == KeyEvent.VK_DOWN) {
			gamePanel.gameLoop("s");
		}
		frame.requestFocusInWindow();	
		System.out.println(pane.getWidth());
	}
//	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
//
//	public static void startKeyListener() {
//		frame.addKeyListener(this);
//
//	}
	
	
}


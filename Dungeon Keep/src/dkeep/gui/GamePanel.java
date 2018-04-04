package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import dkeep.logic.Game;
import dkeep.logic.Level1;
import dkeep.logic.Level2;

public class GamePanel extends JPanel {
	private Game game;
	private int ogresNr;
	private float guardPers;
	private ImageIcon wall;
	private ImageIcon guard;
	private ImageIcon hero;
	private ImageIcon key;
	private ImageIcon door;
	private ImageIcon ogre;
	private ImageIcon club;
	private ImageIcon stunned_ogre;
	private ImageIcon armed_hero;
	private ImageIcon open_door;
	private ImageIcon empty_space;
	private ImageIcon ogre_key;
	/*private ImageIcon club_key;
	private ImageIcon armed_hero_key;
	private ImageIcon hero_key;*/

	public GamePanel(int ogresNr,float guardPers) {
		super();
		this.setBounds(0, 0, 500, 500);
		this.ogresNr=ogresNr;
		Level1 l1 = new Level1(guardPers);
		game = l1.getGame();
		this.setLayout(new GridLayout(game.getMap().length,game.getMap()[0].length));
		this.loadImages();
		this.addComponentListener(new ComponentAdapter() {
		      public void componentResized(ComponentEvent e) {
		      	GraphicsMain.gamePanel.loadImages();
		      	GraphicsMain.gamePanel.update();
		          super.componentResized(e);
		      }
		  });
		update();
		
	}
	
	  public void paintComponent() {
		  
		  String el;

		  for(int i=0;i<game.getMap().length;i++)
			{
				for(int j=0;j<game.getMap()[i].length;j++)
				{
					el=game.getMap()[i][j];
					elementImage(el);
					
				}
			}
	  }

		public void loadImages() {

			wall = new ImageIcon(this.getClass().getResource("res/wall.png"));
			armed_hero= new ImageIcon(this.getClass().getResource("res/armed_hero.png"));
			guard = new ImageIcon(this.getClass().getResource("res/guard.png"));
			hero = new ImageIcon(this.getClass().getResource("res/hero.png"));
			key = new ImageIcon(this.getClass().getResource("res/key.png"));
			door = new ImageIcon(this.getClass().getResource("res/door.png"));
			ogre = new ImageIcon(this.getClass().getResource("res/ogre.png"));
			stunned_ogre = new ImageIcon(this.getClass().getResource("res/stunned_ogre.png"));
			club = new ImageIcon(this.getClass().getResource("res/club.png"));
			open_door = new ImageIcon(this.getClass().getResource("res/open_door.png"));
			empty_space = new ImageIcon(this.getClass().getResource("res/empty_space.png"));
			ogre_key=  new ImageIcon(this.getClass().getResource("res/ogre_key.png"));
		
			
			scaleAll();


		}

		public void scaleAll() {
			wall = scaleImage(wall);
			guard = scaleImage(guard);
			armed_hero=scaleImage(armed_hero);
			empty_space = scaleImage(empty_space);
			hero = scaleImage(hero);
			key = scaleImage(key);
			door = scaleImage(door);
			ogre = scaleImage(ogre);
			club = scaleImage(club);
			stunned_ogre= scaleImage(stunned_ogre);
			open_door = scaleImage(open_door);
		}
		
		private ImageIcon scaleImage(ImageIcon im) {

			Image img = im.getImage();
			Image newimg = img.getScaledInstance(this.getWidth() / game.getMap().length, this.getHeight() / game.getMap()[0].length, Image.SCALE_AREA_AVERAGING);
			System.out.println(this.getWidth()+ " " +this.getHeight());
			return new ImageIcon(newimg);
	}
		
		public void elementImage(String pos) {
			switch (pos) {
			case "X":
				this.add(new JLabel(wall));
				break;
			case "I":
				this.add(new JLabel(door));
				break;
			case "H":
				this.add(new JLabel(hero));
				break;
			case "A":
				this.add(new JLabel(armed_hero));
				break;
			case "S":
				this.add(new JLabel(open_door));
				break;
			case "G":
				this.add(new JLabel(guard));
				break;
			case "O":
				this.add(new JLabel(ogre));
				break;
			case "*":
				this.add(new JLabel(club));
				break;
			case "K":
				this.add(new JLabel(hero));
				break;
			case "k":
				this.add(new JLabel(key));
				break;
			case "8":
				this.add(new JLabel(stunned_ogre));
				break;
			case "g":
				this.add(new JLabel(stunned_ogre));
				break;
			case "$":
				this.add(new JLabel(ogre_key));
				break;
			case " ": 
				this.add(new JLabel(empty_space));
				break;
			default:
				break;
		}

	  
	  }
		public void update(){
			removeAll();
			repaint();
			this.paintComponent();
			revalidate();
			if(GraphicsMain.gamePanel!=null)
				GraphicsMain.gamePanel.setVisible(true);
	}
		
		
		public void gameLoop(String direction) {

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
					update();
					loadImages();
					repaint();
					this.setLayout(new GridLayout(game.getMap().length,game.getMap()[0].length));
					GraphicsMain.pane.setLayer(GraphicsMain.gamePanel, JLayeredPane.DEFAULT_LAYER.intValue());
					
			}

				else if (game.isWin() && game.getCurrLevel() == 2) {
					return;
				}
			}
		}
		
		
		
	
}
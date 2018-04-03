package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dkeep.logic.Game;

public class GamePanel extends JPanel {
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

	public GamePanel(Game game) {
		super();
		this.setLayout(new GridLayout(game.getMap().length,game.getMap()[0].length));
		this.addComponentListener(new ComponentAdapter() {
	        public void componentResized(ComponentEvent e) {
	        	scaleAll(game);
	        	update(game);
	            super.componentResized(e);
	        }
	    });
	}
	
	  public void paintComponent(Game game) {
		  
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

		public void loadImages(Game game) {

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
		
			
			scaleAll(game);


		}

		public void scaleAll(Game game) {
			wall = scaleImage(wall,game);
			guard = scaleImage(guard,game);
			armed_hero=scaleImage(armed_hero,game);
			empty_space = scaleImage(empty_space,game);
			hero = scaleImage(hero,game);
			key = scaleImage(key,game);
			door = scaleImage(door,game);
			ogre = scaleImage(ogre,game);
			club = scaleImage(club,game);
			stunned_ogre= scaleImage(stunned_ogre,game);
			open_door = scaleImage(open_door,game);
		}
		
		private ImageIcon scaleImage(ImageIcon im,Game game) {

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
		public void update(Game game){
			removeAll();
			repaint();
			this.paintComponent(game);
			revalidate();
	}
		
	
}
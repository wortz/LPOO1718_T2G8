package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
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
	private ImageIcon closed_door;
	private ImageIcon empty_space;

	public GamePanel(Game game) {
		super();
		this.setLayout(new GridLayout(game.getMap().length,game.getMap()[0].length));
	}
	
	  public void paint(Game game) {
		  
		  String el;
//		  
//		  super.paintComponent(g); // limpa fundo ... 
//		  g.setColor(Color.BLUE); 

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
			guard = new ImageIcon(this.getClass().getResource("res/guard.png"));
			hero = new ImageIcon(this.getClass().getResource("res/hero.png"));
			key = new ImageIcon(this.getClass().getResource("res/key.png"));
			door = new ImageIcon(this.getClass().getResource("res/door.png"));
			ogre = new ImageIcon(this.getClass().getResource("res/ogre.png"));
			stunned_ogre = new ImageIcon(this.getClass().getResource("res/stunned_ogre.png"));
			club = new ImageIcon(this.getClass().getResource("res/club.png"));
			closed_door = new ImageIcon(this.getClass().getResource("res/closed_door.png"));
			empty_space = new ImageIcon(this.getClass().getResource("res/empty_space.png"));
		
			wall = scaleImage(wall);
			guard = scaleImage(guard);
			empty_space = scaleImage(empty_space);
			hero = scaleImage(hero);
			key = scaleImage(key);
			door = scaleImage(door);
			ogre = scaleImage(ogre);
			club = scaleImage(club);
			stunned_ogre= scaleImage(stunned_ogre);
			closed_door = scaleImage(closed_door);

		}

		private ImageIcon scaleImage(ImageIcon im) {

			Image img = im.getImage();
			Image newimg = img.getScaledInstance(this.getWidth() / 20, this.getHeight() / 20, Image.SCALE_FAST);

			return new ImageIcon(newimg);
	}
		
		public void elementImage(String pos) {
			switch (pos) {
			case "X":
				this.add(new JLabel(wall));
				break;
			case "I":
				this.add(new JLabel(closed_door));
				break;
			case "H":
				this.add(new JLabel(hero));
				break;
			case "A":
				this.add(new JLabel(armed_hero));
				break;
			case "S":
				this.add(new JLabel(door));
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
				this.add(new JLabel(club));
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
			this.paint(game);
	}
}
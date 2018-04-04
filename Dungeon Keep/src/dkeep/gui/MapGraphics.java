package dkeep.gui;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dkeep.logic.Game;

public class MapGraphics extends JPanel{
	private String[][] map;
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
	private ImageIcon club_key;
	private ImageIcon armed_hero_key;
	private ImageIcon sleeping_guard;
	private float guardPers;
	
	MapGraphics(String[][] map, float guardPersonality){
		super();
		this.map=map;
		this.setBounds(0, 0, 500, 500);
		this.setLayout(new GridLayout(map.length, map[0].length));
		this.guardPers=guardPersonality;
		startMapWindow();
		this.addComponentListener(new ComponentAdapter() {
		      public void componentResized(ComponentEvent e) {
		    	  startMapWindow();
		        super.componentResized(e);
		      }
		  });
	}
	
	public void startMapWindow() {
		loadImages();
      	update(map, false);
	}
	
	public void paintMap(boolean isOgreKey) {
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[i].length; j++)
				elementImage(map[i][j], isOgreKey);
	}
	
	public void loadImages() {
		wall = new ImageIcon(this.getClass().getResource("res/wall.png"));
		armed_hero = new ImageIcon(this.getClass().getResource("res/armed_hero.png"));
		if(guardPers==1.1f)
			guard = new ImageIcon(this.getClass().getResource("res/rookie_guard.png"));
		else if(guardPers==1.2f)
			guard = new ImageIcon(this.getClass().getResource("res/drunken_guard.png"));
		else
			guard = new ImageIcon(this.getClass().getResource("res/suspicious_guard.png"));
		hero = new ImageIcon(this.getClass().getResource("res/hero.png"));
		key = new ImageIcon(this.getClass().getResource("res/key.png"));
		door = new ImageIcon(this.getClass().getResource("res/door_closed.png"));
		ogre = new ImageIcon(this.getClass().getResource("res/ogre.png"));
		stunned_ogre = new ImageIcon(this.getClass().getResource("res/stunned_ogre.png"));
		club = new ImageIcon(this.getClass().getResource("res/club.png"));
		open_door = new ImageIcon(this.getClass().getResource("res/door_open.png"));
		empty_space = new ImageIcon(this.getClass().getResource("res/empty_space.png"));
		ogre_key = new ImageIcon(this.getClass().getResource("res/ogre_key.png"));
		armed_hero_key= new ImageIcon(this.getClass().getResource("res/hero_key.png"));
		sleeping_guard= new ImageIcon(this.getClass().getResource("res/sleeping_guard.png"));
		club_key= new ImageIcon(this.getClass().getResource("res/club_key.png"));

		scaleAll();
	}
	
	public void scaleAll() {
		wall = scaleImage(wall);
		guard = scaleImage(guard);
		armed_hero = scaleImage(armed_hero);
		empty_space = scaleImage(empty_space);
		hero = scaleImage(hero);
		key = scaleImage(key);
		door = scaleImage(door);
		ogre = scaleImage(ogre);
		club = scaleImage(club);
		stunned_ogre = scaleImage(stunned_ogre);
		open_door = scaleImage(open_door);
		sleeping_guard= scaleImage(sleeping_guard);
		armed_hero_key= scaleImage(armed_hero_key);
		ogre_key= scaleImage(ogre_key);
		club_key= scaleImage(club_key);
	}
	
	public ImageIcon scaleImage(ImageIcon im) {
		Image img = im.getImage();
		Image newimg = img.getScaledInstance(this.getWidth() / map.length, this.getHeight() / map[0].length, Image.SCALE_AREA_AVERAGING);
		return new ImageIcon(newimg);
	}
	
	public void elementImage(String elem, boolean isOgreKey) {
		switch (elem) {
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
			this.add(new JLabel(armed_hero_key));
			break;
		case "k":
			this.add(new JLabel(key));
			break;
		case "8":
			this.add(new JLabel(stunned_ogre));
			break;
		case "g":
			this.add(new JLabel(sleeping_guard));
			break;
		case "$":
			if(isOgreKey)
				this.add(new JLabel(ogre_key));
			else 
				this.add(new JLabel(club_key));
			break;
		case " ":
			this.add(new JLabel(empty_space));
			break;
		default:
			break;
		}
	}
	
	public void update(String[][] map, boolean isOgreKey){
		this.map=map;
		removeAll();
		repaint();
		this.paintMap(isOgreKey);
		revalidate();
		}
	
	
}

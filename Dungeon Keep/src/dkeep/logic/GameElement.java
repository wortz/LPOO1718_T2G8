package dkeep.logic;

/**
 * GameElement.java - abstract class of every game element- Hero, Guard, Ogre.
 * @author Joao Fidalgo & Francisco Friande
 *
 */
public abstract class GameElement {
	
	private int coord[];
	
	/**
	 * @param xi Integer variable of the X-axis coordinate.
	 * @param yi Integer variable of the Y-axis coordinate.
	 * Class constructor.
	 */
	public GameElement(int xi, int yi) {
		this.coord=new int[2];
		this.coord[0]=xi;
		this.coord[1]=yi;
	}
	
	/**
	 * @return Integer[], the coordinates.
	 * Gets the element's position (coordinates).
	 */
	public int[] getCoord() {
		return coord;
	}
	
	/**
	 * @param coord Integer[] variable of the element's coordinates to be set.
	 * Sets element's coordinates to coord.
	 */
	public void setCoord(int coord[]) {
		this.coord[0] = coord[0];
		this.coord[1] = coord[1];
	}
	
	
}

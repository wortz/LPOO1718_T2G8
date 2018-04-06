package dkeep.logic;

import javax.swing.JPanel;
/**
 * Hero.java - class that handles all the ogre logic .
 * @author Joao Fidalgo & Francisco Friande
 * @see GameElement
 */
public class Hero extends GameElement {
	private boolean isArmed;
	private String heroSimbol;

	/**
	 * Constructor that creates a new hero.
	 * @param xi Integer variable of the X-axis coordinate.
	 * @param yi Integer variable of the Y-axis coordinate.
	 * @param simbol -A variable of type String.Simbol of the hero.
	 */
	public Hero(int xi, int yi,String simbol) {
		super(xi, yi);
		this.heroSimbol=simbol;
		if(simbol=="A")
			this.isArmed=true;
		else this.isArmed=false;
	}

	/**
	 * True if the hero is armed,false otherwise.
	 * @return this.isArmed - A variable of type Boolean.
	 */
	public boolean getArmed() {
		return this.isArmed;
	}

	/**
	 * Set's this.heroSimbol equal to s.
	 * @param s - -A variable of type String.
	 */
	public void setSimbol(String s) {
		this.heroSimbol = s;
	}

	/**
	 * Gets the hero simbol.
	 * @return this.heroSimbol -A variable of type String.
	 */
	public String getSimbol() {
		return this.heroSimbol;
	}

	/**
	 * Function to deal with the hero's move.
	 * <p>Checks all the valid/invalid contitions.
	 * @param mov -A variable of type String.
	 * @param g -A variable of type Game.
	 */
	public void moveHero(String mov, Game g) {
		int aux[] = new int[2];
		aux[0] = this.getCoord()[0];
		aux[1] = this.getCoord()[1];
		Game.moveHandler(mov, aux);
		String movSimbol = g.getMap()[aux[0]][aux[1]];
		if (movSimbol != "X" && movSimbol != "I" && movSimbol != "g" && movSimbol != "8") {
			// if the hero gets to the S , the winning condition
			if (movSimbol == "S") {
				g.setWin(true);
			}
			// if the hero gets to the lever,k
			if (movSimbol == "k" && g.getLogic() <2.0) {
				g.leverOn();
			}
			// if the hero gets to the lever,k
			if (movSimbol == "k" && g.getLogic() >= 2.0) {
				g.setKey(true);
				this.setSimbol("K");
			}

			g.setElemTable(this.getCoord(), " ");
			this.setCoord(aux);
			g.setElemTable(this.getCoord(), this.getSimbol());
			}
			if (movSimbol == "I" && g.getCatched()) {
				g.setElemTable(aux, "S");
			}
		}
}

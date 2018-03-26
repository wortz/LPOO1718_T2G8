package dkeep.logic;

public class Hero extends GameElement {
	private boolean isArmed;
	private String heroSimbol;

	public Hero(int xi, int yi,String simbol) {
		super(xi, yi);
		this.heroSimbol=simbol;
		if(simbol=="A")
			this.isArmed=true;
		else this.isArmed=false;
	}

	public boolean getArmed() {
		return this.isArmed;
	}

	public void setSimbol(String s) {
		this.heroSimbol = s;
	}

	public String getSimbol() {
		return this.heroSimbol;
	}

	/* Function to deal with the hero's move */
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
			/*
			 * this.checkStun(); this.checkLose(l);
			 */

		}
}

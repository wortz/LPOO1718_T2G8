package dkeep.logic;

public class GameElement {
	
	private int coord[];
	private int initialCoord[];
	
	public GameElement(int xi, int yi) {
		this.setCoord(new int[2]);
		this.initialCoord=new int[2];
		this.initialCoord[0]=xi;
		this.initialCoord[1]=yi;
		this.getCoord()[0]=this.initialCoord[0];
		this.getCoord()[1]=this.initialCoord[1];
	}
	
	public int[] getCoord() {
		return coord;
	}
	public void setCoord(int coord[]) {
		this.coord = coord;
	}
	
	
}

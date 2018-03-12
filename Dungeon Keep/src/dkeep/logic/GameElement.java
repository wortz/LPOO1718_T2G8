package dkeep.logic;

public class GameElement {
	
	private int coord[];
	
	public GameElement(int xi, int yi) {
		this.coord=new int[2];
		this.coord[0]=xi;
		this.coord[1]=yi;
	}
	
	public int[] getCoord() {
		return coord;
	}
	public void setCoord(int coord[]) {
		this.coord[0] = coord[0];
		this.coord[1] = coord[1];
	}
	
	
}

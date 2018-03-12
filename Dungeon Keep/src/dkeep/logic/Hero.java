package dkeep.logic;

public class Hero extends GameElement{
	private boolean isArmed;
	private String heroSimbol;
	public Hero(int xi, int yi) {
		super(xi, yi);
	}
	
	
	public boolean getArmed() {
		return this.isArmed;
	}
	
	public void setArmed(boolean a) {
		this.isArmed=a;
	}
	
	public void setSimbol(String s) {
		this.heroSimbol=s;
	}
	
	public String getSimbol() {
		return this.heroSimbol;
	}
	
}

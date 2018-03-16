package dkeep.logic;

public class Hero extends GameElement{
	private boolean isArmed;
	private String heroSimbol;
	public Hero(int xi, int yi) {
		super(xi, yi);
		this.isArmed=false;
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
	
	
	
	
	/*Function to deal with the hero's move*/
	public void moveHero(String mov, Map l) {
		int aux[]=new int[2];
		aux[0]=this.getCoord()[0];
		aux[1]=this.getCoord()[1];
	    Game.moveHandler(mov, aux);
		if(l.getTable()[aux[0]][aux[1]]!="X"&&l.getTable()[aux[0]][aux[1]]!="I"&&l.getTable()[aux[0]][aux[1]]!="g"&&l.getTable()[aux[0]][aux[1]]!="8") {
			//if the hero gets to the S , the winning condition
			if(l.getTable()[aux[0]][aux[1]]=="S")
			{
				this.win=true;
			}
			/*if the hero gets to the lever,k*/
			if(l.getTable()[aux[0]][aux[1]]=="k" && this.getLevel()==l1) {
				l1.leverOn();
			}
			//if the hero gets to the lever,k
			/*if(l.getTable()[aux[0]][aux[1]]=="k" && this.getLevel()==l2) {
				this.key_catched=true;
			}*/
			
			l.setTableElem(this.getCoord()," ");
			this.setCoord(aux);
			
			/*if(this.key_catched)
				l.setTableElem(hero.getCoord(),"K");
			else*/
				l.setTableElem(this.getCoord(),this.getSimbol());			
			//}
		/*if(l.getTable()[aux[0]][aux[1]]=="I"&&this.key_catched)
		{
			int arr[]= {1,0};
			l.setTableElem(arr,"S");			
		}*/
		this.checkStun();
		this.checkLose(l);
		
	}
	
	
}

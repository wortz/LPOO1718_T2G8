package dkeep.logic;

public class Guard  extends GameElement{

	private String guardMove[];
	private int guardIndex;
	public Guard(int xi, int yi) {
		super(xi, yi);
		String aux2[]= {"a","s","s","s","s","a","a","a","a","a","a","s","d","d","d","d","d",
				"d","d","w","w","w","w","w"};
		this.guardMove=aux2;
		this.guardIndex=0;
	}
	
	public void incrementIndex() {
		if((this.guardIndex)==(this.guardMove.length)-1)
			this.guardIndex=0;
		else
			this.guardIndex++;
	}
	
}

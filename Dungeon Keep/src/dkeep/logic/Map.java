package dkeep.logic;

import java.util.List;

/**
 * Map.java - class that handles the creation and alteration of map .
 * @author Joao Fidalgo & Francisco Friande
 *
 */
public class Map {
	private String table[][];

	// used in editor
	/**
	 * Constructor that creates a map (height x width) with the borders as walls(X)
	 * @param height - A variable of type Int.
	 * @param width - A variable of type Int.
	 */
	public Map(int height, int width) {
		String[][] aux = new String[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i == 0 || i == height-1 || j == 0 || j == width-1) 
					aux[i][j] = "X";
				else 
					aux[i][j] = " ";
			}
		}
		this.table = aux;
	}

	/**
	 * Constructor that creates a map with a given table.
	 * <p>Set's this.table equals to the param.
	 * @param table - A variable of type String[][].
	 */
	public Map(String[][] table) {
		this.table=table;
	}
	
	/**
	 * @return this.table
	 */
	public String[][] getTable() {
		return table;
	}
	/**
	 * Set's this.table equals to the param.
	 * @param table - A variable of type String[][].
	 */
	public void setTable(String table[][]) {
		this.table = table;
	}	
	/*changes a single element in the table*/
	/**
	 * Changes a element of the table with the coords coord to el.
	 * @param coord - A variable of type Int[].
	 * @param el- A variable of type String.
	 */
	public void setTableElem(int coord[], String el) {
			this.table[coord[0]][coord[1]] = el;
	}	
	
	/*prints the table as it is in the moment*/
	/**
	 * Print's the all table.
	 */
	public void printTable() {
		for(int i=0;i<this.table.length;i++)
		{
			for(int j=0;j<this.table[i].length;j++)
			{
				System.out.print(this.table[i][j]);
				System.out.print(' ');
			}
			System.out.println();
		}
		
	}
	/**
	 * Searches for all the occurrences of the element with simbol c.
	 * <p>Set's in the list all the coords that the element was found.
	 * @param c- A variable of type String.
	 * @param list- A variable of type List<int[]>.
	 * @return boolean- true if the element c found on the table, false if it is not.
	 */
	public boolean serchEle(String c, List<int[]> list) {
		boolean res=false;
		int n=list.size();
		int coord[];
		for(int i=0;i<this.table.length;i++)
			{
				for(int j=0;j<this.table[i].length;j++)
				{
					if(table[i][j]==c) {
						list.add(new int[2]);
						coord=list.get(n);
						coord[0]=i;
						coord[1]=j;
						n++; 
						res=true;
					}
				}
			}
		return res;
	}
}

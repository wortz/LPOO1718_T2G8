package dkeep.logic;

import java.util.List;

public class Map {
	private String table[][];
	
	public String[][] getTable() {
		return table;
	}
	public void setTable(String table[][]) {
		this.table = table;
	}	
	/*changes a single element in the table*/
	public void setTableElem(int coord[], String el) {
		this.table[coord[0]][coord[1]] = el;
	}	
	
	/*prints the table as it is in the moment*/
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
	public boolean serchEle(String c, List<int[]> list) {
		int coord[]=new int[2];
		boolean res=false;
		for(int i=0;i<this.table.length;i++)
			{
				for(int j=0;j<this.table[i].length;j++)
				{
					if(table[i][j]==c) {
						coord[0]=i;
						coord[1]=j;
						list.add(coord); 
						res=true;
					}
				}
			}
		return res;
	}


//	public abstract int[][] checkLose_aux();
	
}

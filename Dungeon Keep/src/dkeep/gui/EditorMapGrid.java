package dkeep.gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import dkeep.logic.Map;

public class EditorMapGrid {
	
	private MapGraphics graphics;
	private Map map;
	private int height,width;
	
	public EditorMapGrid(int height, int width){
		super();
		this.height=height;
		this.width=width;
		
		map= new Map(height,width);
		graphics= new MapGraphics(map.getTable(),0);
	
		graphics.setVisible(true);
		graphics.setSize(500, 500);
	}
	
	
	public void setTableElem(int coord[], String el) {
		this.map.setTableElem(coord, el);
		graphics.update(map.getTable(), false);		
	}	
	
	public boolean isPlayableMap() {
		List<int[]> list = new ArrayList<int[]>();
		if(map.serchEle("H",list)&&map.serchEle("K",list)&&map.serchEle("I",list)&&map.serchEle("O",list))
			return true;
		return false;
	}
	
	
	public MapGraphics getMapGraphics() {
		return 	this.graphics;
	}
}

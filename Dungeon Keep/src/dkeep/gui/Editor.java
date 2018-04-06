package dkeep.gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dkeep.logic.Map;

public class Editor {

	private MapGraphics graphics;
	private Map map;
	private String selected;

	public Editor(int height, int width) {
		map = new Map(height, width);
		graphics = new MapGraphics(map.getTable(), 0);
		graphics.setEditor(this);
		this.selected = "I";
		graphics.addMouseListener(graphics);

		graphics.setVisible(true);
		graphics.setSize(500, 500);
	}

	public void setTableElem(int coord[], String el) {
		this.map.setTableElem(coord, el);
		graphics.update(map.getTable(), false);
	}

	public boolean isPlayableMap() {
		List<int[]> list = new ArrayList<int[]>();
		if (map.serchEle("A", list) && map.serchEle("k", list) && map.serchEle("I", list) && map.serchEle("O", list))
			return true;
		JOptionPane.showMessageDialog(null, "<html>The following requirements must be fulfilled:<br/>-At least 1 Ogre.<br/>-1 Key.<br/>-1 Hero.<br/>-At least 1 Exit Door.</html>");
		return false;
	}

	public MapGraphics getMapGraphics() {
		return this.graphics;
	}

	public void changeMap(int x, int y) {
		if (selected == null) {
			return;
		}
		int row, col;
		row = y / graphics.getDvY();
		col = x / graphics.getDvX();
		// corners
		if ((row == 0 && col == 0) || (row == map.getTable().length - 1 && col == map.getTable().length - 1)
				|| (col == 0 && row == map.getTable().length - 1) || (col == map.getTable()[0].length - 1 && row == 0))
			return;
		// borders
		if (row == 0 || row == map.getTable().length - 1 || col == 0 || col == map.getTable()[0].length - 1) {
			if ((selected != "I") && (selected != "X"))
				return;

		}
		int[] coord = { row, col };
		if(selected!=map.getTable()[row][col])
			if (!checkEditMap())
				return;
		map.setTableElem(coord, selected);
		graphics.update(map.getTable(), false);
	}

	public void setSelected(String el) {
		selected = el;
	}

	// check if can alter map
	public boolean checkEditMap() {
		List<int[]> list = new ArrayList<int[]>();
		if (selected == "O")
			if (map.serchEle("O", list))
				if (list.size() >= 5) {
					JOptionPane.showMessageDialog(null, "Maximum Number of Ogres is only 5!");
					return false;
				}
		if (selected == "A")
			if (map.serchEle("A", list))
				if (list.size() >= 1) {
					JOptionPane.showMessageDialog(null, "There can only be 1 Hero!");
					return false;
				}
		if (selected == "k")
			if (map.serchEle("k", list))
				if (list.size() >= 1) {
					JOptionPane.showMessageDialog(null, "There can only be One Key!");
					return false;
				}
		return true;
	}
	
	public Map getMap() {
		return this.map;
	}

}

package dkeep.gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ComboGrid extends JPanel{
	
	private JComboBox boxes[][];
	private int height,width;
	
	public ComboGrid(int height, int width){
		super();
		this.height=height;
		this.width=width;
		boxes= new JComboBox[height][width];
		this.setLayout(new GridLayout(height, width));
		for (int i=0; i<height;i++) {
			for (int j=0; j<width;j++) {
				if((i==0) || (i==height-1) || (j==0) || (j==width-1)) {
					addComboBoxBorder(i,j);
				}
				else addComboBox(i,j);
			}
		}
	}
	
	private void addComboBox( int i, int j) {
		boxes[i][j] = new JComboBox();
		for (Component component : boxes[i][j].getComponents())
		{
		    if (component instanceof JButton) {
		    	boxes[i][j].remove(component);
		    }
		}
		boxes[i][j].setModel(new DefaultComboBoxModel(new String[] { "Empty Space", "Wall", "Ogre", "Key", "Hero", "Exit" }));
		boxes[i][j].setSelectedIndex(0);
		
		this.add(boxes[i][j]);
		
	}
	
	private void addComboBoxBorder(int i,int j) {
		boxes[i][j] = new JComboBox();
		boxes[i][j].setModel(new DefaultComboBoxModel(new String[] {"Wall", "Exit" }));
		boxes[i][j].setSelectedIndex(0);
		if((i==0 && j==width-1) || (j==0 && i==height-1) || (i==0 && j==0) || (i==height-1 && j==width-1)) 
			boxes[i][j].setEnabled(false);
		for (Component component : boxes[i][j].getComponents())
		{
		    if (component instanceof JButton) {
		    	boxes[i][j].remove(component);
		    }
		}
		this.add(boxes[i][j]);
	}

}

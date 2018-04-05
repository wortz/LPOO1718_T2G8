package dkeep.gui;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Editor extends JPanel implements MouseListener{
	int ogresNr;
	float guardPers;
	private EditorMapGrid grid;
	private JComboBox comboBox;
	int height, width;
	JTextField fldHeight,fldWidth; 
	
	
	public Editor() {
		super();
//		this.setLayout(new GridBagLayout());
		setPersonalityButton();
		setHeightField();
		setWidthField() ;
		setStartEditingBtn();
	}
	private void setPersonalityButton() {
		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(61, 104, 111, 43);
		this.add(lblGuardPersonality);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Rookie", "Drunken", "Suspicious" }));
		comboBox.setBounds(175, 109, 102, 33);
		comboBox.setSelectedIndex(0);
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				String personality;

				if (e.getSource() == comboBox) {
					personality = (String) comboBox.getSelectedItem();
					if ("Rookie".equals(personality))
						guardPers = 1.1f;
					else if ("Drunken".equals(personality))
						guardPers = 1.2f;
					else if ("Suspicious".equals(personality))
						guardPers = 1.3f;
					else
						;
				} 

			}
		});

		this.add(comboBox);
		 
	}

	private void setHeightField() {
		fldHeight = new JTextField();
		fldHeight.setBounds(175, 13, 91, 43);
		this.add(fldHeight);
		fldHeight.setText("6");
		fldHeight.setColumns(10);
	}

	private void setWidthField() {
		fldWidth = new JTextField();
		fldWidth.setBounds(175, 13, 91, 43);
		this.add(fldWidth);
		fldWidth.setText("6");
		fldWidth.setColumns(10);
	}
	
	private void setStartEditingBtn() {
		JButton startEditing = new JButton("Start Editing!");
		startEditing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if ((height = tryParseInt(fldHeight.getText())) == -1 ||(width = tryParseInt(fldWidth.getText())) == -1  )
					return;
				if (comboBox.getSelectedIndex() == 0)
					guardPers = 1.1f;
				
			//////////////C/////////////
				beginEditing();
			}
		});
		startEditing.setContentAreaFilled(false);
		startEditing.setBounds(358, 109, 209, 43);
		this.add(startEditing);
		
	}
	 
	
	private void beginEditing() {
		if(grid!=null) 
			this.remove(grid.getMapGraphics());
		this.grid=new EditorMapGrid(height, width);
		this.add(grid.getMapGraphics());
//		this.setBounds(0,0,700,700);
		this.revalidate();
		grid.getMapGraphics().setVisible(true);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	static int tryParseInt(String value) { //TODO:muito codigo repetido aqui
		int v;
		try {
			v = Integer.parseInt(value);
			if (v < 6 || v > 25)
				throw new IllegalArgumentException("Height and width must be between 6 and 25");
			return v;
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Height and width must be between 6 and 25");
			return -1;
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
		
}

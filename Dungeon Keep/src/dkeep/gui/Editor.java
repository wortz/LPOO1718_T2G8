package dkeep.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Editor extends JPanel{
	int ogresNr;
	float guardPers;
	private JComboBox comboBox;
	int height, width;
	JTextField fldHeight,fldWidth;
	
	
	public Editor() {
		super();
		setPersonalityButton();
		setHeightField();
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
	
	private void startEditingBtn() {
		JButton startEditing = new JButton("Start Editing!");
		startEditing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if ((height = tryParseInt(fldHeight.getText())) == -1 ||(width = tryParseInt(fldWidth.getText())) == -1  )
					return;
				if (comboBox.getSelectedIndex() == 0)
					guardPers = 1.1f;
				
			//////////////C/////////////

			}
		});
		startEditing.setContentAreaFilled(false);
		startEditing.setBounds(358, 109, 209, 43);
		this.add(startEditing);
		
	}
	
	
	private void startEditing() {
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	static int tryParseInt(String value) { //TODO:muito codigo repetido aqui
		int v;
		try {
			v = Integer.parseInt(value);
			if (v < 6 || v > 25)
				throw new IllegalArgumentException("Ogres number sould be a number between 1 and 5");
			return v;
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Ogres number ust be an integer between 1 and 5");
			return -1;
		}
	}
		
}

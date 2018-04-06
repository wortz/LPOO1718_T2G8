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

public class OptionsPanel extends JPanel {
	
	private GraphicMode graphic;
	private int ogresNr;
	private float guardPers;
	private JComboBox comboBox;
	private JTextField fldOgresNr;
	
	OptionsPanel(GraphicMode graphic) {
		super();
		this.graphic=graphic;
		TextOgresNr();
		BoxGuardPers();
		ButtonExitGame();
		ButtonStandardGame();
		ButtonCustomMap();
		
	}
	
	public void TextOgresNr() {
		JLabel lblOgresNumber = new JLabel("No of Ogres");
		lblOgresNumber.setBounds(61, 13, 188, 43);
		this.add(lblOgresNumber);

		fldOgresNr = new JTextField();
		fldOgresNr.setBounds(175, 13, 91, 43);
		this.add(fldOgresNr);
		fldOgresNr.setText("1");
		fldOgresNr.setColumns(10);
	}
	
	public void BoxGuardPers() {
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
	
	public void ButtonExitGame() {
		JButton btnExitGame = new JButton("Exit Game");
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}

		});
		btnExitGame.setContentAreaFilled(false);
		btnExitGame.setBounds(358, 551, 209, 43);
		this.add(btnExitGame);
	}
	
	public void ButtonStandardGame() {
		JButton btnNewGame = new JButton("Start Standard Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if ((ogresNr = tryParseInt(fldOgresNr.getText())) == -1)
					return;
				if (comboBox.getSelectedIndex() == 0)
					guardPers = 1.1f;
				
				graphic.getOptions().setVisible(false);
				graphic.createGamePanel(ogresNr, guardPers);
				

			}
		});
		btnNewGame.setContentAreaFilled(false);
		btnNewGame.setBounds(358, 109, 209, 43);
		this.add(btnNewGame);
	}
	
	public void ButtonCustomMap() {
		JButton btnEditGame = new JButton("Edit Keep Level");
		btnEditGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				graphic.getOptions().setVisible(false);
				graphic.createEditorPanel();
			}
		});
		btnEditGame.setContentAreaFilled(false);
		btnEditGame.setBounds(358, 109, 209, 43);
		this.add(btnEditGame);
	}
	
	public int tryParseInt(String value) {
		int v;
		try {
			v = Integer.parseInt(value);
			if (v < 1 || v > 5)
				throw new IllegalArgumentException("Ogres number sould be a number between 1 and 5");
			return v;
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Ogres number ust be an integer between 1 and 5");
			return -1;
		}
	}
	
}

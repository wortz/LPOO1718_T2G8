package dkeep.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dkeep.logic.Game;
import dkeep.logic.Level1;

public class OptionsPanel extends JPanel {
	int ogresNr;
	float guardPers;
	private JComboBox comboBox;
	private JTextField fldOgresNr;
	
	public OptionsPanel() {
		super();
		
		initialize();
	}
	
	private void initialize() {
		JLabel lblOgresNumber = new JLabel("No of Ogres");
		lblOgresNumber.setBounds(61, 13, 188, 43);
		this.add(lblOgresNumber);

		fldOgresNr = new JTextField();
		fldOgresNr.setBounds(175, 13, 91, 43);
		this.add(fldOgresNr);
		fldOgresNr.setText("1");
		fldOgresNr.setColumns(10);

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
		
		JButton btnExitGame = new JButton("Exit Game");
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}

		});
		btnExitGame.setContentAreaFilled(false);
		btnExitGame.setBounds(358, 551, 209, 43);
		this.add(btnExitGame);

	
	
	JButton btnNewGame = new JButton("Start New Game");
	btnNewGame.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {

			if ((ogresNr = tryParseInt(fldOgresNr.getText())) == -1)
				return;
			if (comboBox.getSelectedIndex() == 0)
				guardPers = 1.1f;
			
			GraphicsMain.gamePanel=new GamePanel(ogresNr,guardPers);
			
			GraphicsMain.optionsPanel.setVisible(false);
			GraphicsMain.pane.add(GraphicsMain.gamePanel);
			GraphicsMain.gamePanel.setVisible(true);
			GraphicsMain.frame.requestFocusInWindow();

		}
	});
	btnNewGame.setContentAreaFilled(false);
	btnNewGame.setBounds(358, 109, 209, 43);
	this.add(btnNewGame);
	
	
	JButton btnEditGame = new JButton("Edit Keep Level");
	btnEditGame.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			/*GraphicsMain.gamePanel=new GamePanel(ogresNr,guardPers);
			
			GraphicsMain.optionsPanel.setVisible(false);
			GraphicsMain.pane.add(GraphicsMain.gamePanel);
			GraphicsMain.gamePanel.setVisible(true);
			GraphicsMain.frame.requestFocusInWindow();*/

		}
	});
	btnEditGame.setContentAreaFilled(false);
	btnEditGame.setBounds(358, 109, 209, 43);
	this.add(btnEditGame);
	}
	
	public float getGuardPers() {
		return this.guardPers;
	}
	
	public int getOgresNr() {
		return this.ogresNr;
	}
	
	static int tryParseInt(String value) { //TODO:muito codigo repetido aqui
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
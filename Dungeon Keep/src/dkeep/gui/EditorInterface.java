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

public class EditorInterface extends JPanel {
	private int ogresNr;
	private float guardPers;
	private Editor editor;
	private JComboBox comboBox;
	private int height, width;
	private JTextField fldHeight,fldWidth; 
	private boolean buttonsCreated;
	private GraphicMode graphic;
	
	public EditorInterface(GraphicMode graphic) {
		super();
		this.graphic=graphic;
//		this.setLayout(new GridBagLayout());
		setPersonalityButton();
		setHeightField();
		setWidthField() ;
		setStartEditingBtn();
		buttonsCreated=false;
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
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setBounds(61, 13, 188, 43);
		this.add(lblHeight);
		
		fldHeight = new JTextField();
		fldHeight.setBounds(175, 13, 91, 43);
		this.add(fldHeight);
		fldHeight.setText("6");
		fldHeight.setColumns(10);
	}

	private void setWidthField() {
		JLabel lblHeight = new JLabel("Width");
		lblHeight.setBounds(61, 13, 188, 43);
		this.add(lblHeight);
		
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
				beginEditing();
			}
		});
		startEditing.setContentAreaFilled(false);
		startEditing.setBounds(358, 109, 209, 43);
		this.add(startEditing);
		
	}
	 
	private void beginEditing() {
		if(!buttonsCreated)
			setEditingButtons();
		if(editor!=null) 
			this.remove(editor.getMapGraphics());
		this.editor=new Editor(height, width);
		this.add(editor.getMapGraphics());
//		this.setBounds(0,0,700,700);
		this.revalidate();
		editor.getMapGraphics().setVisible(true);
		
	}

	public void setEditingButtons() {
		buttonsCreated=true;
		setOgreButton();
		setHeroButton();
		setKeyButton();
		setDoorButton();
		setWallButton();
		setEmptyCellButton();
		setPlayButton();
	}
	
	
	
	
	public void setOgreButton() {
		JButton ogreButton = new JButton("Ogre");
		ogreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				editor.setSelected("O");
			
			}
		});
		ogreButton.setContentAreaFilled(false);
		ogreButton.setBounds(358, 109, 209, 43);
		this.add(ogreButton);
	}
	
	public void setHeroButton() {
		JButton heroButton = new JButton("Hero");
		heroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editor.setSelected("A");
				
			}
		});
		heroButton.setContentAreaFilled(false);
		heroButton.setBounds(358, 109, 209, 43);
		this.add(heroButton);
	}
	
	public void setKeyButton() {
		JButton keyButton = new JButton("Key");
		keyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editor.setSelected("k");
			
			}
		});
		keyButton.setContentAreaFilled(false);
		keyButton.setBounds(358, 109, 209, 43);
		this.add(keyButton);
	}
	
	public void setDoorButton() {
		JButton doorButton = new JButton("Door");
		doorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editor.setSelected("I");
				
			}
		});
		doorButton.setContentAreaFilled(false);
		doorButton.setBounds(358, 109, 209, 43);
		this.add(doorButton);
	}
	
	public void setWallButton() {
		JButton wallButton = new JButton("Wall");
		wallButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editor.setSelected("X");
				
			}
		});
		wallButton.setContentAreaFilled(false);
		wallButton.setBounds(358, 109, 209, 43);
		this.add(wallButton);
	}
	
	public void setEmptyCellButton() {
		JButton emptyButton = new JButton("Empty Space");
		emptyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editor.setSelected(" ");
			
			}
		});
		emptyButton.setContentAreaFilled(false);
		emptyButton.setBounds(358, 109, 209, 43);
		this.add(emptyButton);
	}
	
	public void setPlayButton() {
		JButton playButton = new JButton("Play Game");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (editor.isPlayableMap()) {
					graphic.getEditor().setVisible(false);
					graphic.createCustomGamePanel(guardPers, editor.getMap(), graphic);
				}
			}
		});
		playButton.setContentAreaFilled(false);
		playButton.setBounds(358, 109, 209, 43);
		this.add(playButton);
	}
	
	
	static int tryParseInt(String value) { 
		int v;
		try {
			v = Integer.parseInt(value);
			if (v < 6 || v > 20)
				throw new IllegalArgumentException("Height and width must be between 6 and 20");
			return v;
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Height and width must be between 6 and 20");
			return -1;
		}
	}
		
}

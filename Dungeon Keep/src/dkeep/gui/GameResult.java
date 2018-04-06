package dkeep.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameResult extends JPanel {
	private ImageIcon img1;
	private ImageIcon img2;
	private ImageIcon img3;
	private GraphicMode g;
	private boolean winPanel;
	private GridBagConstraints gbc= new GridBagConstraints();
	GameResult(GraphicMode g,boolean winPanel){
		super();
		this.winPanel=winPanel;
		this.g=g;
		if (winPanel)
			this.setBackground(Color.GREEN);
		else
			this.setBackground(Color.RED);
		this.setLayout(new GridBagLayout());
		loadImages();
		gbc.anchor=gbc.EAST;
		this.add(new JLabel(img2),gbc);
		gbc.anchor=gbc.CENTER;
		this.add(new JLabel(img1),gbc);
		gbc.anchor=gbc.WEST;
		this.add(new JLabel(img3),gbc);
		gbc.anchor=gbc.CENTER;
        gbc.insets = new Insets(70, 0, 0, 10);
		setMenuButton();
		setExitButton();
		
		
	}
	
	public void loadImages() {
		if(winPanel) {
		img1 = new ImageIcon(this.getClass().getResource("res/win.png"));
		img3 = new ImageIcon(this.getClass().getResource("res/happyferb.png"));
		img2 = new ImageIcon(this.getClass().getResource("res/happyphin.png"));
		}else {
		img1 = new ImageIcon(this.getClass().getResource("res/lose.png"));
		img3 = new ImageIcon(this.getClass().getResource("res/sadferb.png"));
		img2 = new ImageIcon(this.getClass().getResource("res/sadphin.png"));
		}

	}

	public void setExitButton() {
		JButton btnExitGame = new JButton("Exit Game");
		btnExitGame.setFont(new Font("Arial", Font.BOLD, 25));
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}

		});
		btnExitGame.setContentAreaFilled(false);
		btnExitGame.setBounds(0, 0, 700, 150);
        gbc.insets = new Insets(0, 0, 0, 2);
		this.add(btnExitGame,gbc);
	}
	
	public void setMenuButton() {
		JButton btnExitGame = new JButton("Return to Menu");
		btnExitGame.setFont(new Font("Arial", Font.ROMAN_BASELINE, 25));
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				g.removeResult();
				g.getOptions().setVisible(true);

			}

		});
		btnExitGame.setContentAreaFilled(false);
		btnExitGame.setBounds(0, 0, 350, 150);
		gbc.gridx=1;
		this.add(btnExitGame,gbc);
	}
	
	
}

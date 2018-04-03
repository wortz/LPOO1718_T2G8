package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;

import javax.swing.JTextField;

import dkeep.cli.GameLoop;
import dkeep.logic.Game;
import dkeep.logic.Level1;
import dkeep.logic.Level2;

import javax.swing.JComboBox;
import java.awt.GridLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SwingWindow {

	private JFrame frame;
	private JTextField fldOgresNr;
	private JButton btnUp, btnLeft, btnRight, btnDown;
	private JLabel lblGameStatus;
	private float guardPers;
	private int ogresNr;
	private Game game;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingWindow window = new SwingWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SwingWindow() {
		 initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 597, 688);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("DUNGEON KEEP");

		JLabel lblOgresNumber = new JLabel("No of Ogres");
		lblOgresNumber.setBounds(61, 13, 188, 43);
		frame.getContentPane().add(lblOgresNumber);

		fldOgresNr = new JTextField();
		fldOgresNr.setBounds(175, 13, 91, 43);
		frame.getContentPane().add(fldOgresNr);
		fldOgresNr.setText("1");
		fldOgresNr.setColumns(10);

		JLabel lblGuardPersonality = new JLabel("Guard Personality");
		lblGuardPersonality.setBounds(61, 104, 111, 43);
		frame.getContentPane().add(lblGuardPersonality);

		JComboBox comboBox = new JComboBox();
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

		frame.getContentPane().add(comboBox);

		JButton btnExitGame = new JButton("Exit Game");
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				System.exit(0);
				
			}

		});
		btnExitGame.setContentAreaFilled(false);
		btnExitGame.setBounds(358, 551, 209, 43);
		frame.getContentPane().add(btnExitGame);

		JTextArea textGameDisplayer = new JTextArea();
		textGameDisplayer.setFont(new Font("Courier New", Font.PLAIN, 15));
		textGameDisplayer.setBounds(22, 211, 311, 342);
		frame.getContentPane().add(textGameDisplayer);

		btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				movebuttonsHandler(game, textGameDisplayer, "w");
				updateStatusLabel(game);
			}

		});
		btnUp.setEnabled(false);
		btnUp.setBounds(409, 207, 97, 25);
		frame.getContentPane().add(btnUp);

		btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				movebuttonsHandler(game, textGameDisplayer, "a");
				updateStatusLabel(game);
			}

		});
		btnLeft.setEnabled(false);
		btnLeft.setBounds(343, 265, 97, 25);
		frame.getContentPane().add(btnLeft);

		btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				movebuttonsHandler(game, textGameDisplayer, "d");
				updateStatusLabel(game);
			}

		});
		btnRight.setEnabled(false);
		btnRight.setBounds(470, 265, 97, 25);
		frame.getContentPane().add(btnRight);

		btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				movebuttonsHandler(game, textGameDisplayer, "s");
				updateStatusLabel(game);
			}

		});
		btnDown.setEnabled(false);
		btnDown.setBounds(409, 334, 97, 25);
		frame.getContentPane().add(btnDown);

		JButton btnNewGame = new JButton("Start New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if ((ogresNr = tryParseInt(fldOgresNr.getText())) == -1)
					return;
				if (comboBox.getSelectedIndex() == 0)
					guardPers = 1.1f;
				Level1 l1 = new Level1(guardPers);
				game = l1.getGame();
				printMap(game, textGameDisplayer);
				updateStatusLabel(game);
				setMovBtns(true);

			}
		});
		btnNewGame.setContentAreaFilled(false);
		btnNewGame.setBounds(358, 109, 209, 43);
		frame.getContentPane().add(btnNewGame);

		lblGameStatus = new JLabel("You can start a new game.");
		lblGameStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGameStatus.setVerticalAlignment(SwingConstants.TOP);
		lblGameStatus.setBounds(22, 564, 311, 64);
		frame.getContentPane().add(lblGameStatus);
	}

	public void movebuttonsHandler(Game game, JTextArea textGameDisplayer, String direction) {

		if ((!game.isWin()) && (!game.isLose())) {
			game.mvHero(direction);
			game.checkLose();
			if (game.isLose()) {
				printMap(game, textGameDisplayer);
				return;
			}
			if (game.getCurrLevel() == 1)
				game.mvGuard();
			else if (game.getCurrLevel() == 2)
				game.mvOgre();
			game.checkLose();
			printMap(game, textGameDisplayer);
			if (game.getCurrLevel() == 2)
				game.delClub();
			if (game.isLose()) {
				setMovBtns(false);
				return;
			} else if (game.isWin() && game.getCurrLevel() == 1) {
				Level2 l2 = new Level2(ogresNr);
				this.game = l2.getGame();
				game.setWin(false);
				printMap(this.game, textGameDisplayer);
			}

			else if (game.isWin() && game.getCurrLevel() == 2) {
				setMovBtns(false);
				return;
			}
		}
	}

	public void setMovBtns(boolean en_dis) {
		btnDown.setEnabled(en_dis);
		btnLeft.setEnabled(en_dis);
		btnRight.setEnabled(en_dis);
		btnUp.setEnabled(en_dis);
	}

	public void printMap(Game game, JTextArea textGameDisplayer) {
		textGameDisplayer.setText("\n");
		for (int i = 0; i < game.getMap().length; i++) {
			for (int j = 0; j < game.getMap()[i].length; j++) {
				textGameDisplayer.append(game.getMap()[i][j]);
				textGameDisplayer.append(" ");
			}
			textGameDisplayer.append("\n");
		}
	}

	public void updateStatusLabel(Game game) {
		if ((!game.isWin()) && (!game.isLose()) && game.getCurrLevel() == 1)
			if (game.getCatched())
				lblGameStatus.setText("<html>Great! Now go through the gates.</html>");
			else
				lblGameStatus.setText("<html>Get to the lever to open the gates. Be careful not to get caught!</html>");

		else if ((!game.isWin()) && (!game.isLose()) && game.getCurrLevel() == 2)
			if (game.getCatched())
				lblGameStatus.setText("<html>You're close now! Open the door and save yourself!</html>");
			else
				lblGameStatus.setText(
						"<html>Catch the key to open the door.<br/>Your weapon can stun the ogres, but beware of their clubs!!</html>");
		else if (game.isLose())
			lblGameStatus.setText("<html>You lose! Tough Luck...</html>");
		else if (game.isWin() && game.getCurrLevel() == 2)
			lblGameStatus.setText("<html>You WIN! Congratulations</html>");

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

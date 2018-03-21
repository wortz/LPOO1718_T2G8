package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridLayout;

public class swingDesign {

	private JFrame frame;
	private JTextField fldOgresNr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					swingDesign window = new swingDesign();
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
	public swingDesign() {
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
		
		JLabel lblOgresNumber = new JLabel("No of Ogres");
		lblOgresNumber.setBounds(61, 13, 188, 43);
		frame.getContentPane().add(lblOgresNumber);
		
		fldOgresNr = new JTextField();
		fldOgresNr.setBounds(175, 13, 91, 43);
		frame.getContentPane().add(fldOgresNr);
		fldOgresNr.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(75, 109, 56, 16);
		frame.getContentPane().add(lblNewLabel);
	}
}

package dkeep.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import dkeep.logic.Map;

public class GraphicMode implements KeyListener{


	private JFrame frame;
	private JLayeredPane pane;
	private GamePanel gameWindow;
	private OptionsPanel options;
	private EditorInterface editor;
	private GameResult result;
	
	GraphicMode(){
		createJframe();
		createLayeredPanel();
		createOptionsPanel();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicMode window = new GraphicMode();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void createJframe() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		frame.setTitle("Graphically enhanced DUNGEON KEEP");
		frame.addKeyListener(this);
		frame.setVisible(true);
		
	}
	
	public void createLayeredPanel() {
		pane = new JLayeredPane();
		pane.setBounds(0, 0, 900, 900);
		
		pane.setLayout(new BorderLayout());
		frame.getContentPane().add(pane,BorderLayout.CENTER);
	}
	
	public void createOptionsPanel() {
		this.options=new OptionsPanel(this);
		getOptions().setBounds(0, 0, 500, 500);
		pane.setLayer(this.options, JLayeredPane.DEFAULT_LAYER.intValue());
		pane.add(this.options);
		getOptions().setVisible(true);
	}
	
	public void removeResult() {
		result.setVisible(false);
		pane.remove(result);
		pane.revalidate();
}
	
	public void createGamePanel(int ogresNr,float guardPers) {
		this.gameWindow=new GamePanel(ogresNr,guardPers,this);
		pane.setLayer(gameWindow.getMapGraphics(), JLayeredPane.DEFAULT_LAYER.intValue());
		pane.add(gameWindow.getMapGraphics());
		gameWindow.getMapGraphics().setVisible(true);
		frame.requestFocusInWindow();
	}
	
	public void createCustomGamePanel(float guardPers, Map map,GraphicMode graphic) {
		this.gameWindow=new GamePanel(guardPers,map,this);
		pane.setLayer(gameWindow.getMapGraphics(), JLayeredPane.DEFAULT_LAYER.intValue());
		pane.add(gameWindow.getMapGraphics());
		gameWindow.getMapGraphics().setVisible(true);
		frame.requestFocusInWindow();
	}
	
	
	public void createEditorPanel() {
		this.editor=new EditorInterface(this);
		pane.setLayer(editor, JLayeredPane.DEFAULT_LAYER.intValue());
		pane.add(editor);
		editor.setVisible(true);
		frame.requestFocusInWindow();
	}
	
	public void createResultPanel(boolean winPanel) {
		pane.remove(gameWindow.getMapGraphics());
		result=new GameResult(this,winPanel);
		pane.setLayer(result, JLayeredPane.DEFAULT_LAYER.intValue());
		pane.add(result);
		result.setVisible(true);
		frame.requestFocusInWindow();
		frame.pack();
	}
	public void setLayerAux(GamePanel g) {
		gameWindow.getMapGraphics().setVisible(false);
		this.gameWindow=g;
		pane.setLayer(gameWindow.getMapGraphics(), JLayeredPane.DEFAULT_LAYER.intValue());
		pane.add(gameWindow.getMapGraphics());
		gameWindow.getMapGraphics().setVisible(true);
	}
	
	
	public void removeGame() {
		pane.remove(gameWindow.getMapGraphics());
	}
	
	
	
	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_LEFT) {
			gameWindow.gameLoop("a");
		}
		else if(k.getKeyCode() == KeyEvent.VK_UP) {
			gameWindow.gameLoop("w");
		}
		else if(k.getKeyCode() == KeyEvent.VK_RIGHT) {
			gameWindow.gameLoop("d");
		}
		else if(k.getKeyCode() == KeyEvent.VK_DOWN) {
			gameWindow.gameLoop("s");
		}
		frame.requestFocusInWindow();	
	}
	

	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public OptionsPanel getOptions() {
		return this.options;
	}
	
	public EditorInterface getEditor() {
		return this.editor;
	}
	
}

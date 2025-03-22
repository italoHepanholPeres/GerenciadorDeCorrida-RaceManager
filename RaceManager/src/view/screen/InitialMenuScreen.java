package view.screen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import model.Race;

public class InitialMenuScreen extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private Image wallpaper;
	private int screenHeight;
	private int screenWidth;
	
	public InitialMenuScreen() {
		JFrame thisScreen = this;
		this.setTitle("Menu");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		screenHeight = d.height;
		screenWidth = d.width;
		this.setSize(screenWidth, screenHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		wallpaper = tk.createImage("src/resource/wallpaper.jpeg");
		JPanel backPanel = new NewContentPanel();

		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(backPanel,BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu register = new JMenu("Criação");
		
		menuBar.add(register);
		
		ImageIcon originalIcon1 = new ImageIcon("src/resource/createRace.jpg");
		Image imageIcon1 = originalIcon1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		
		
		JMenuItem createRace = new JMenuItem("Criar corrida", new ImageIcon(imageIcon1));
		register.add(createRace);
		
		createRace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateRaceScreen(thisScreen);
			}
		});
			
		this.setVisible(true);
	}
	
	private class NewContentPanel extends JPanel{
		protected void paintComponent(final Graphics g) {
			super.paintComponent(g);
			g.drawImage(wallpaper, 0, 0, screenWidth,screenHeight-50,this);
		}
	}
	
}

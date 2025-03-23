package view.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Race;
import model.Runner;

public class InitialMenuScreen extends JFrame {

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
		this.setSize(screenWidth, screenHeight - 45);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		ImageIcon icon = new ImageIcon("src/resource/icon.png");
		this.setIconImage(icon.getImage());

		wallpaper = tk.createImage("src/resource/wallpaper.jpeg");
		JPanel backPanel = new NewContentPanel();

		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(backPanel, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		JMenu register = new JMenu("Criação");
		register.setFont(new Font("Arial", Font.BOLD, 18));

		JMenu history = new JMenu("Histórico");
		history.setFont(new Font("Arial", Font.BOLD, 18));

		menuBar.add(register);
		menuBar.add(history);

		ImageIcon originalIcon1 = new ImageIcon("src/resource/plus.jpg");
		Image imageIcon1 = originalIcon1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

		JMenuItem createRace = new JMenuItem("Criar corrida", new ImageIcon(imageIcon1));
		register.add(createRace);

		ImageIcon originalIcon2 = new ImageIcon("src/resource/history.png");
		Image imageIcon2 = originalIcon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

		JMenuItem lastRace1 = new JMenuItem("Ultima corrida", new ImageIcon(imageIcon2));
		history.add(lastRace1);

		createRace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateRaceScreen(thisScreen);
			}
		});

		lastRace1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Primeiro crie uma corrida!");
			}
		});

		JPanel statusBar = new JPanel();
		statusBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		statusBar.setBackground(Color.GRAY);

		JLabel statusLabel = new JLabel("Desenvolvido por: Ítalo Hespanhol Peres - Versão 1.0 - Copyright (C) 2025 Todos os direitos reservados");
		statusBar.add(statusLabel);
		c.add(statusBar, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	public InitialMenuScreen(Race race) {
		JFrame thisScreen = this;
		this.setTitle("Menu");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		screenHeight = d.height;
		screenWidth = d.width;
		this.setSize(screenWidth, screenHeight - 45);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		ImageIcon icon = new ImageIcon("src/resource/icon.png");
		this.setIconImage(icon.getImage());

		wallpaper = tk.createImage("src/resource/wallpaper.jpeg");
		JPanel backPanel = new NewContentPanel();

		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(backPanel, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		JMenu register = new JMenu("Criação");
		register.setFont(new Font("Arial", Font.BOLD, 18));

		JMenu history = new JMenu("Histórico");
		history.setFont(new Font("Arial", Font.BOLD, 18));

		menuBar.add(register);
		menuBar.add(history);

		ImageIcon originalIcon1 = new ImageIcon("src/resource/plus.jpg");
		Image imageIcon1 = originalIcon1.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

		JMenuItem createRace = new JMenuItem("Criar corrida", new ImageIcon(imageIcon1));
		register.add(createRace);

		ImageIcon originalIcon2 = new ImageIcon("src/resource/history.png");
		Image imageIcon2 = originalIcon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

		JMenuItem lastRace2 = new JMenuItem("Ultima corrida", new ImageIcon(imageIcon2));
		history.add(lastRace2);

		createRace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateRaceScreen(thisScreen);
			}
		});

		lastRace2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Object first = race.getRunnerFinishedByClassification(1);
				Object second = race.getRunnerFinishedByClassification(2);
				Object third = race.getRunnerFinishedByClassification(3);

				JOptionPane.showMessageDialog(null,
						"Ultima Corrida:\nnome:" + race.getName() + "\ncidade: " + race.getCity()
								+ "\nlocal de partida e chegada: " + race.getStartAndFinishLocal() + "\ndistância: "
								+ race.getDistance() + "\ndata e hora: " + race.getDate() + "\ntempo limite: "
								+ race.getLimitRaceTimeToString() + "\n1° lugar: "
								+ (first instanceof Runner
										? "Id: " + ((Runner) first).getId() + ", Nome: " + ((Runner) first).getName()
										: first)
								+ "\n2° lugar: "
								+ (second instanceof Runner
										? "Id: " + ((Runner) second).getId() + ", Nome: " + ((Runner) second).getName()
										: second)
								+ "\n3° lugar: "
								+ (third instanceof Runner
										? "Id: " + ((Runner) third).getId() + ", Nome: " + ((Runner) third).getName()
										: third));
			}
			// ternario: condicao? se verdadeiro : se falso;
		});
		
		JPanel statusBar = new JPanel();
		statusBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		statusBar.setBackground(Color.GRAY);

		JLabel statusLabel = new JLabel("Desenvolvido por: Ítalo Hespanhol Peres - Versão 1.0 - Copyright (C) 2025 Todos os direitos reservados");
		statusBar.add(statusLabel);
		c.add(statusBar, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}

	private class NewContentPanel extends JPanel {
		protected void paintComponent(final Graphics g) {
			super.paintComponent(g);
			g.drawImage(wallpaper, 0, 0, screenWidth, screenHeight - 50, this);
		}
	}

}

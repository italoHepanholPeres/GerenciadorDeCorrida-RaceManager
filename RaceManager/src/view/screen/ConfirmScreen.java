package view.screen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
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
import view.panel.RegisterRunnerPanel;

public class ConfirmScreen extends JFrame{
	
	private int screenHeight;
	private int screenWidth;
	
	public ConfirmScreen(final Race race) {
		this.setTitle("Registrar corredor");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		screenHeight = d.height;
		screenWidth = d.width;
		this.setSize(screenWidth-100, screenHeight-200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu register = new JMenu("Cadastro básico");
		
		menuBar.add(register);

		ImageIcon originalIcon2 = new ImageIcon("src/resource/registerRunners.png");
		Image imageIcon2 = originalIcon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		
		JMenuItem registerRunner = new JMenuItem("Registrar Corredores", new ImageIcon(imageIcon2));
		register.add(registerRunner);
		
		JPanel racePanel = new JPanel();
		racePanel.setPreferredSize(new Dimension(screenWidth,200));
		racePanel.setLayout(new GridLayout(6,2,1,1));
		
		JLabel name = new JLabel("Nome da corrida:");
		JLabel nameOutput = new JLabel(race.getName());
		racePanel.add(name);
		racePanel.add(nameOutput);
		
		JLabel city = new JLabel("cidade em que ocorrerá a corrida:");
		JLabel cityOutput = new JLabel(race.getCity());
		racePanel.add(city);
		racePanel.add(cityOutput);
		
		JLabel startLocal = new JLabel("Local de iníco da corrida:");
		JLabel startLocalOutput = new JLabel(race.getStartLocal());
		racePanel.add(startLocal);
		racePanel.add(startLocalOutput);
		
		JLabel distance = new JLabel("Distância da corrida:");
		JLabel distanceOutput = new JLabel(race.getDistance());
		racePanel.add(distance);
		racePanel.add(distanceOutput);
		
		JLabel date = new JLabel("Data da corrida:");
		JLabel dateOutput = new JLabel(race.getDate());
		racePanel.add(date);
		racePanel.add(dateOutput);
		
		JLabel timeLimit = new JLabel("Tempo limite da corrida:");
		JLabel timeLimitOutput = new JLabel(race.getLimitRaceTimeToString());
		racePanel.add(timeLimit);
		racePanel.add(timeLimitOutput);
		
		RegisterRunnerPanel runnerPanel = new RegisterRunnerPanel(race);
		JPanel bttnPanel = new JPanel();
		bttnPanel.setPreferredSize(new Dimension(screenWidth,50));
		bttnPanel.setLayout(new GridLayout(1,2,2,2));
		runnerPanel.add(bttnPanel, BorderLayout.SOUTH);
		
		JButton backBttn = new JButton("Apagar corrida");
		bttnPanel.add(backBttn, BorderLayout.SOUTH);
		
		backBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				new InitialMenuScreen();
			}
		});
		
		c.add(racePanel, BorderLayout.NORTH);
		c.add(runnerPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
}

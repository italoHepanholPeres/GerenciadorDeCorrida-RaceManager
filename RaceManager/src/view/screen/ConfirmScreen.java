package view.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.Race;
import view.panel.RegisterRunnerPanel;

public class ConfirmScreen extends JFrame {

	
	private static final long serialVersionUID = 1L;
	
	private int screenHeight;
	private int screenWidth;

	public ConfirmScreen(final Race race) {
		this.setTitle("Confirmar corrida");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		screenHeight = d.height;
		screenWidth = d.width;
		this.setSize(screenWidth, screenHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		ImageIcon icon = new ImageIcon("src/resource/icon.png");
		this.setIconImage(icon.getImage());

		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());

		ImageIcon originalIcon2 = new ImageIcon("src/resource/registerRunners.png");
		Image imageIcon2 = originalIcon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);

		JPanel racePanel = new JPanel();
		racePanel.setPreferredSize(new Dimension(screenWidth, 200));
		racePanel.setLayout(new GridLayout(6, 2, 1, 1));
		TitledBorder raceborder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Corrida",
				TitledBorder.LEFT, TitledBorder.TOP);

		raceborder.setTitleFont(new Font("Arial", Font.BOLD, 22));

		racePanel.setBorder(raceborder);

		JLabel name = new JLabel("Nome da corrida:");
		name.setFont(new Font("Arial", Font.BOLD, 18));
		JLabel nameOutput = new JLabel(race.getName());
		nameOutput.setFont(new Font("Arial", Font.BOLD, 18));
		racePanel.add(name);
		racePanel.add(nameOutput);

		JLabel city = new JLabel("cidade em que ocorrerá a corrida:");
		city.setFont(new Font("Arial", Font.BOLD, 18));
		JLabel cityOutput = new JLabel(race.getCity());
		cityOutput.setFont(new Font("Arial", Font.BOLD, 18));
		racePanel.add(city);
		racePanel.add(cityOutput);

		JLabel startFinishLocal = new JLabel("Local de iníco e fim da corrida:");
		startFinishLocal.setFont(new Font("Arial", Font.BOLD, 18));
		JLabel startfinisheLocalOutput = new JLabel(race.getStartAndFinishLocal());
		startfinisheLocalOutput.setFont(new Font("Arial", Font.BOLD, 18));
		racePanel.add(startFinishLocal);
		racePanel.add(startfinisheLocalOutput);

		JLabel distance = new JLabel("Distância da corrida:");
		distance.setFont(new Font("Arial", Font.BOLD, 18));
		JLabel distanceOutput = new JLabel(race.getDistance());
		distanceOutput.setFont(new Font("Arial", Font.BOLD, 18));
		racePanel.add(distance);
		racePanel.add(distanceOutput);

		JLabel date = new JLabel("Data da corrida:");
		date.setFont(new Font("Arial", Font.BOLD, 18));
		JLabel dateOutput = new JLabel(race.getDate());
		dateOutput.setFont(new Font("Arial", Font.BOLD, 18));
		racePanel.add(date);
		racePanel.add(dateOutput);

		JLabel timeLimit = new JLabel("Tempo limite da corrida:");
		timeLimit.setFont(new Font("Arial", Font.BOLD, 18));
		JLabel timeLimitOutput = new JLabel(race.getLimitRaceTimeToString());
		timeLimitOutput.setFont(new Font("Arial", Font.BOLD, 18));
		racePanel.add(timeLimit);
		racePanel.add(timeLimitOutput);

		RegisterRunnerPanel runnerPanel = new RegisterRunnerPanel(race);
		
		TitledBorder registerRunnerBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Registrar Corredores",
				TitledBorder.LEFT, TitledBorder.TOP);
		
		registerRunnerBorder.setTitleFont(new Font("Arial", Font.BOLD, 22));
		
		runnerPanel.setBorder(registerRunnerBorder);
		JPanel bttnPanel = new JPanel();
		bttnPanel.setPreferredSize(new Dimension(screenWidth, 50));
		bttnPanel.setLayout(new GridLayout(1, 2, 2, 2));
		runnerPanel.add(bttnPanel, BorderLayout.SOUTH);

		JButton backBttn = new JButton("Apagar corrida");
		backBttn.setFont(new Font("Arial", Font.BOLD, 18));
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

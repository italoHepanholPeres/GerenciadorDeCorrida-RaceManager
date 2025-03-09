package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Race;
import util.RunnersListPanel;

public class ConfirmRace extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public ConfirmRace(Race race) {
		this.setTitle("Confirmar Corrida");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel racePanel = new JPanel();
		racePanel.setLayout(new GridLayout(5,2,1,1));
		
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
		
		RunnersListPanel runnerList = new RunnersListPanel(race);
		
		
		c.add(racePanel, BorderLayout.NORTH);
		c.add(runnerList, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
}

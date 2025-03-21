package view.screen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import model.Race;
import model.Runner;
import view.panel.RunnersListPanel;

public class SimulateRaceScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private int screenHeight;
	private int screenWidth;

	public SimulateRaceScreen(Race race) {
		this.setTitle("Cronômetro");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		screenHeight = d.height;
		screenWidth = d.width;
		this.setSize(screenWidth, screenHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());

		JPanel stopWatchPanel = new JPanel();
		stopWatchPanel.setLayout(new GridLayout(2, 2, 2, 2));

		JLabel stopWatch = new JLabel("00:00:0000");
		stopWatch.setFont(new Font("Arial", Font.BOLD, 32));
		

		JLabel enterId = new JLabel("Informe o id de quem terminou a corrida:");
		JTextField idInput = new JTextField(10);
		JButton completeBttn = new JButton("Completar corrida");
		completeBttn.setPreferredSize(new Dimension(screenWidth, 50));
		completeBttn.setEnabled(false);
		stopWatchPanel.add(enterId);
		stopWatchPanel.add(stopWatch);
		stopWatchPanel.add(idInput);
		stopWatchPanel.add(completeBttn);
		

		JPanel listsPanel = new JPanel();
		listsPanel.setLayout(new FlowLayout());
		RunnersListPanel listOfRunning = new RunnersListPanel("Resultado");
		RunnersListPanel listOfFinished = new RunnersListPanel("Resultado");
		listsPanel.add(listOfRunning, BorderLayout.WEST);
		listsPanel.add(listOfFinished, BorderLayout.EAST);
		
		completeBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int passedId = Integer.parseInt(idInput.getText().trim());
					
					Runner runner = race.completeRace(passedId);

					listOfRunning.addRunner(runner.getId(), runner.getName(), runner.getBirthDate(), runner.getGender(),
							runner.getPhone(), runner.getElapsedTime());

				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Impossível converter para número", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} catch (NullPointerException e3) {
					JOptionPane.showMessageDialog(null, "Corredor inexistente ou já terminou a corrida", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPanel bttnPanel = new JPanel();
		bttnPanel.setLayout(new GridLayout(1, 2));

		JButton startRaceBttn = new JButton("Começar Corrida");
		startRaceBttn.setPreferredSize(new Dimension(screenWidth, 50));
		bttnPanel.add(startRaceBttn);
		JButton nextScreenbttn = new JButton("Exibir resultados");
		nextScreenbttn.setPreferredSize(new Dimension(screenWidth, 50));
		bttnPanel.add(nextScreenbttn);

		startRaceBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Race.startRace();
				timer = new Timer(10, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						long tempoDecorrido = System.currentTimeMillis() - Race.getStartTime();
						int minutos = (int) (tempoDecorrido / 60000) % 60;
						int segundos = (int) ((tempoDecorrido % 60000) / 1000);
						int milissegundos = (int) (tempoDecorrido % 1000);

						stopWatch.setText(String.format("%02d:%02d:%03d", minutos, segundos, milissegundos));
					}
				});

				timer.start();
				completeBttn.setEnabled(true);
				startRaceBttn.setEnabled(false);
			}
		});

		c.add(stopWatchPanel, BorderLayout.NORTH);
		c.add(listsPanel, BorderLayout.CENTER);
		c.add(bttnPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}
}

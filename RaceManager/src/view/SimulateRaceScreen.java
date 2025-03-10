package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
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
import util.RunnersListPanel;

public class SimulateRaceScreen extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Timer timer;

	public SimulateRaceScreen(Race race) {
		this.setTitle("Cronômetro");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel stopWatchPanel = new JPanel();
		stopWatchPanel.setLayout(new GridLayout(2,2,2,2));
		
		JLabel stopWatch = new JLabel("00:00:0000");
		stopWatch.setFont(new Font("Arial", Font.BOLD, 32));
		stopWatchPanel.add(stopWatch);
		
		JLabel enterId = new JLabel("Informe o id de quem terminou a corrida:");
		JTextField idInput = new JTextField(10);
		JButton completeBttn = new JButton("Completar corrida");
		completeBttn.setEnabled(false);
		stopWatchPanel.add(enterId);
		stopWatchPanel.add(idInput);
		stopWatchPanel.add(completeBttn);
						
		RunnersListPanel list = new RunnersListPanel("Tempo");
		
		completeBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int passedId = Integer.parseInt(idInput.getText().trim());
					
					race.completeRace(passedId);
					int id = race.getRunnerFinishedById(passedId).getId();
					String name = race.getRunnerFinishedById(id).getName();
					String birthDate = race.getRunnerFinishedById(id).getBirthDate();
					String gender = race.getRunnerFinishedById(id).getGender();
					String phone = race.getRunnerFinishedById(id).getPhone();
					
					list.addRunner(id, name, birthDate, gender, phone,race.getRunnerFinishedById(id).getElapsedTime());
					
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Impossível converter para número", "Erro", JOptionPane.ERROR_MESSAGE);
				}catch (NullPointerException e3) {
					JOptionPane.showMessageDialog(null, "Corredor inexistente", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JPanel bttnPanel = new JPanel();
		bttnPanel.setLayout(new GridLayout(1,2));
		
		
		JButton startRaceBttn = new JButton("Começar Corrida");
		bttnPanel.add(startRaceBttn);
		JButton nextScreenbttn = new JButton("Exibir resultados");
		bttnPanel.add(nextScreenbttn);
		
		startRaceBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Race.startRace();
					timer = new Timer(10, new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent e) {
			                long tempoDecorrido = System.currentTimeMillis() - Race.getStartTime()*1000;
			                int minutos = (int) (tempoDecorrido / 60000)% 60;
			                int segundos = (int) ((tempoDecorrido % 60000) / 1000);
			                int milissegundos = (int) (tempoDecorrido % 1000);

			                stopWatch.setText(String.format("%02d:%02d:%03d", minutos, segundos, milissegundos));
			            }
			        });

			        timer.start();
					completeBttn.setEnabled(true);
					startRaceBttn.setEnabled(false);
				} catch (NumberFormatException e2) {
				}
			}
		});
		
		
		
		c.add(stopWatchPanel, BorderLayout.NORTH);
		c.add(list, BorderLayout.CENTER);
		c.add(bttnPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}
}

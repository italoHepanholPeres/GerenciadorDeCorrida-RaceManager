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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import model.Race;
import model.Runner;
import view.panel.RegisterRunnerPanel;
import view.panel.RunnersListPanel;

public class SimulateRaceScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private int screenHeight;
	private int screenWidth;
	private long actualTime;
	

	public SimulateRaceScreen(Race race) {
		this.setTitle("Cronômetro");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		screenHeight = d.height;
		screenWidth = d.width;
		this.setSize(screenWidth, screenHeight - 45);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		ImageIcon icon = new ImageIcon("src/resource/icon.png");
		this.setIconImage(icon.getImage());
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		
		TitledBorder northBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Completar corrida",
				TitledBorder.LEFT, TitledBorder.TOP);
		northBorder.setTitleFont(new Font("Arial", Font.BOLD, 22));
		
		northPanel.setBorder(northBorder);
		JPanel completePanel = new JPanel();
		completePanel.setLayout(new GridLayout(1, 2, 2, 2));

		JLabel stopWatch = new JLabel("00:00:0000");
		stopWatch.setFont(new Font("Arial", Font.BOLD, 44));

		JLabel enterId = new JLabel("Informe o id de quem terminou a corrida:");
		enterId.setFont(new Font("Arial", Font.BOLD, 18));
		JTextField idInput = new JTextField(10);
		JButton completeBttn = new JButton("Completar corrida");
		completeBttn.setFont(new Font("Arial", Font.BOLD, 18));
		completeBttn.setPreferredSize(new Dimension(screenWidth, 50));
		completeBttn.setEnabled(false);
		
		completePanel.add(enterId);
		completePanel.add(idInput);
		completePanel.setPreferredSize(new Dimension(screenWidth, 50));
		
		JPanel completeBttnPanel = new JPanel();
		completeBttnPanel.add(completeBttn);
		
		northPanel.add(completePanel, BorderLayout.NORTH);
		northPanel.add(completeBttnPanel, BorderLayout.CENTER);
	
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		
		TitledBorder centerBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Cronômetro e listas",
				TitledBorder.LEFT, TitledBorder.TOP);
		centerBorder.setTitleFont(new Font("Arial", Font.BOLD, 22));
		
		centerPanel.setBorder(centerBorder);
		
		JPanel listsPanel = new JPanel();
		listsPanel.setPreferredSize(new Dimension(1000,1000));
		listsPanel.setLayout(new FlowLayout());
		RunnersListPanel listOfRunning = new RunnersListPanel();

		for (Runner runner : race.listRunnersrunning()) {
			listOfRunning.addRunner(runner);
		}

		listsPanel.add(listOfRunning, BorderLayout.WEST);

		RunnersListPanel listOfFinished = new RunnersListPanel("Resultado", "Classificação");
		listOfFinished.setPreferredSize(new Dimension(700,450));
		listsPanel.add(listOfFinished, BorderLayout.EAST);
		
		JPanel stopWatchPanel = new JPanel();
		stopWatchPanel.add(stopWatch);
		
		centerPanel.add(listsPanel, BorderLayout.CENTER);
		centerPanel.add(stopWatchPanel, BorderLayout.NORTH);
		
		JButton backBttn = new JButton("Voltar a tela principal");
		backBttn.setFont(new Font("Arial", Font.BOLD, 18));
		backBttn.setPreferredSize(new Dimension(screenWidth, 50));
		
		completeBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int passedId = Integer.parseInt(idInput.getText().trim());

					Runner runner = race.completeRace(passedId, actualTime);

					listOfRunning.removeRunner(passedId);
					listOfFinished.addRunnerFinished(runner);

				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Impossível converter para número", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} catch (NullPointerException e3) {
					JOptionPane.showMessageDialog(null, "Corredor inexistente ou já terminou a corrida", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
				
				if(race.listRunnersrunning().isEmpty()) {
					scheduler.shutdown();
					backBttn.setEnabled(true);
				}
				
			}
		});

		JPanel bttnPanel = new JPanel();
		bttnPanel.setLayout(new GridLayout(1, 2));

		JButton startRaceBttn = new JButton("Começar Corrida");
		startRaceBttn.setFont(new Font("Arial", Font.BOLD, 18));
		startRaceBttn.setPreferredSize(new Dimension(screenWidth, 50));
		
		bttnPanel.add(startRaceBttn);
		bttnPanel.add(backBttn);

		startRaceBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Race.startRace();
				long startTime = Race.getStartTime();
				long limitTime = Race.getLimitRaceTime();
				
				scheduler.scheduleAtFixedRate(() -> {
					long elapsedTime = System.currentTimeMillis() - startTime;
					actualTime = System.currentTimeMillis();
					int minutes = (int) (elapsedTime / 60000) % 60;
					int seconds = (int) ((elapsedTime % 60000) / 1000);
					int miliSeconds = (int) (elapsedTime % 1000);
					
					 SwingUtilities.invokeLater(() -> stopWatch.setText(String.format("%02d:%02d:%03d", minutes, seconds, miliSeconds)));
					
					if(elapsedTime >= limitTime) {
						scheduler.shutdown();
						backBttn.setEnabled(true);
						
						long finishTime = System.currentTimeMillis();
						
						for(Runner runner : race.listRunnersrunning()) {
							
							runner.finishRace(finishTime, "Desqualificado");
							
							listOfFinished.addRunnerFinished(runner);
						}
						
					}

				}, 0, 10, TimeUnit.MILLISECONDS);
				
				completeBttn.setEnabled(true);
				startRaceBttn.setEnabled(false);
				backBttn.setEnabled(false);
			}
		});
		
		backBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new InitialMenuScreen(race);
			}
		});
		

		c.add(northPanel, BorderLayout.NORTH);
		c.add(centerPanel, BorderLayout.CENTER);
		c.add(bttnPanel, BorderLayout.SOUTH);
		this.setVisible(true);
	}
}

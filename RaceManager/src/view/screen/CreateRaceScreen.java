package view.screen;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Race;
import util.DateValidator;

public class CreateRaceScreen extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private int screenHeight;
	private int screenWidth;
	
	public CreateRaceScreen(JFrame previousScreen) {
		this.setTitle("Criar Corrida");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		screenHeight = d.height;
		screenWidth = d.width;
		this.setSize(screenWidth-400, screenHeight-500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		ImageIcon icon = new ImageIcon("src/resource/icon.png");
		this.setIconImage(icon.getImage());

		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 2, 2, 2));
		c.add(panel);

		JLabel name = new JLabel("Informe o nome da corrida:");
		name.setToolTipText("Nome da corrida");
		name.setFont(new Font("Arial", Font.BOLD, 18));
		JTextField nameInput = new JTextField(30);
		nameInput.setToolTipText("EX:Rodrigo..");
		nameInput.setFont(new Font("Arial", Font.BOLD, 18));
		panel.add(name);
		panel.add(nameInput);

		JLabel city = new JLabel("Informe a cidade que ocorrerá:");
		city.setToolTipText("Cidade em que ocorrerá a corrida");
		city.setFont(new Font("Arial", Font.BOLD, 18));
		JTextField cityInput = new JTextField(30);
		cityInput.setToolTipText("EX:São Fidélis..");
		cityInput.setFont(new Font("Arial", Font.BOLD, 18));
		panel.add(city);
		panel.add(cityInput);

		JLabel startFinishLocal = new JLabel("Informe o local de início e fim da corrida:");
		startFinishLocal.setToolTipText("Local de início da corrida");
		startFinishLocal.setFont(new Font("Arial", Font.BOLD, 18));
		JTextField startFinisheLocalInput = new JTextField(30);
		startFinisheLocalInput.setToolTipText("EX:Praça/prefeitura..");
		startFinisheLocalInput.setFont(new Font("Arial", Font.BOLD, 18));
		panel.add(startFinishLocal);
		panel.add(startFinisheLocalInput);

		JLabel distance = new JLabel("Informe a distância total da corrida:");
		distance.setToolTipText("distância da corrida");
		distance.setFont(new Font("Arial", Font.BOLD, 18));
		JTextField distanceInput = new JTextField(30);
		distanceInput.setToolTipText("EX:5KM..");
		distanceInput.setFont(new Font("Arial", Font.BOLD, 18));
		panel.add(distance);
		panel.add(distanceInput);
		
		JLabel date = new JLabel("Informe a data e hora da corrida:");
		date.setToolTipText("Data da corrida");
		date.setFont(new Font("Arial", Font.BOLD, 18));
		JTextField dateInput = new JTextField(30);
		dateInput.setToolTipText("EX:12/12/2025 12:00");
		dateInput.setFont(new Font("Arial", Font.BOLD, 18));
		panel.add(date);
		panel.add(dateInput);
		
		JLabel limitTime = new JLabel("Informe o tempo limite para completar a corrida:");
		limitTime.setToolTipText("Tempo limite pra atravessar a chegada");
		limitTime.setFont(new Font("Arial", Font.BOLD, 18));
		JTextField limitTimeInput = new JTextField(30);
		limitTimeInput.setToolTipText("EX:02:00(duas horas de duração)..");
		limitTimeInput.setFont(new Font("Arial", Font.BOLD, 18));
		panel.add(limitTime);
		panel.add(limitTimeInput);


		JButton createBttn = new JButton("Criar Corrida");
		panel.add(createBttn);

		createBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//testes
				nameInput.setText("corrida tal");
				cityInput.setText("sao fidelis");
//				startFinisheLocalInput.setText("");
				distanceInput.setText("12KM");
				dateInput.setText("19/05/2025 13:00");
				
				String name = nameInput.getText().trim();
				String city = cityInput.getText().trim();
				String startFinishLocal = startFinisheLocalInput.getText().trim();
				String distance = distanceInput.getText().trim();
				String date = dateInput.getText().trim();
				String timeLimitStr = limitTimeInput.getText().trim();

				
				
				if(name.isEmpty() || city.isEmpty() || startFinishLocal.isEmpty() || distance.isEmpty() || date.isEmpty() || timeLimitStr.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Um ou mais campos estão vazios");
					return;
				}
				
				if (!timeLimitStr.matches("\\d{1,2}:\\d{2}")) {
		            JOptionPane.showMessageDialog(null, "Formato inválido para tempo limite. Use 'hh:mm'.");
		            return;
		        }
				
				String[] timeParts = timeLimitStr.split(":");
		        int hours = Integer.parseInt(timeParts[0]);
		        int minutes = Integer.parseInt(timeParts[1]);
		        
		        if (hours < 0 || hours > 24) {
		        	JOptionPane.showMessageDialog(null, "Horas inválidas! O limite de horas deve ser entre 0 e 24.");
	                return;
	            }
	            if (minutes < 0 || minutes > 59) {
	            	JOptionPane.showMessageDialog(null, "Minutos inválidos! O limite de minutos deve ser entre 0 e 59.");
	                return;
	            }
		        
		        long limitRaceTime = (hours * 60 * 60 * 1000) + (minutes * 60 * 1000);
				
				if(!DateValidator.validateDateToRace(date, Race.getFORMAT())) {
					JOptionPane.showMessageDialog(null, "A data não pode ja ter passado ou/e a data tem estar no formato dd/MM/yyyy HH:mm ");
					return;
				}
				
				if (!startFinishLocal.matches("^(?=.*[a-zA-Z])[^/]+/[^/]+$")) {
				    JOptionPane.showMessageDialog(null, "O local de início e fim deve ser informado no formato 'localInicial/localFinal', e não pode conter apenas números.");
				    return;
				}
				
					Race race = new Race(name,
							city,
							startFinishLocal,
							distance,
							date,
							limitRaceTime);
					previousScreen.dispose();
					dispose();
					new ConfirmScreen(race);
				}
		});
		
		this.setVisible(true);
	}
}

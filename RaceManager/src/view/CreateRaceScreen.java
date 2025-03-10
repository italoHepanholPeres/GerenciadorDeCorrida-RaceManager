package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;

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

	public CreateRaceScreen() {
		this.setTitle("Criar Corrida");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2, 2, 2));
		c.add(panel);

		JLabel name = new JLabel("Informe o nome da corrida:");
		name.setToolTipText("Nome da corrida");
		JTextField nameInput = new JTextField(30);
		panel.add(name);
		panel.add(nameInput);

		JLabel city = new JLabel("Informe a cidade que ocorrerá:");
		city.setToolTipText("Cidade em que ocorrerá a corrida");
		JTextField cityInput = new JTextField(30);
		panel.add(city);
		panel.add(cityInput);

		JLabel startLocal = new JLabel("Informe o local de início da corrida:");
		startLocal.setToolTipText("Local de início da corrida");
		JTextField startLocalInput = new JTextField(30);
		panel.add(startLocal);
		panel.add(startLocalInput);

		JLabel distance = new JLabel("Informe a distância total da corrida:");
		distance.setToolTipText("distância da corrida");
		JTextField distanceInput = new JTextField(30);
		distanceInput.setText("Ex:12KM");
		panel.add(distance);
		panel.add(distanceInput);
		
		JLabel date = new JLabel("Informe a data da corrida:");
		date.setToolTipText("Data da corrida");
		JTextField dateInput = new JTextField(30);
		panel.add(date);
		panel.add(dateInput);

		JButton createBttn = new JButton("Criar Corrida");
		panel.add(createBttn);

		createBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//testes
				nameInput.setText("corrida tal");
				cityInput.setText("sao fidelis");
				startLocalInput.setText("praça");
				distanceInput.setText("12KM");
				dateInput.setText("19/05/2025 13:00");
				
				String name = nameInput.getText().trim();
				String city = cityInput.getText().trim();
				String startLocal = startLocalInput.getText().trim();
				String distance = distanceInput.getText().trim();
				String date = dateInput.getText().trim();
				
				if(name.isEmpty() || city.isEmpty() || startLocal.isEmpty() || distance.isEmpty() || date.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Um ou mais campos estão vazios");
					return;
				}
				
				if(!DateValidator.validateDateToRace(date, Race.getFORMAT())) {
					JOptionPane.showMessageDialog(null, "A data não pode ja ter passado ou/e a data tem estar no formato dd/MM/yyyy HH:mm ");
					return;
				}
				
					Race race = new Race(name,
							city,
							startLocal,
							distance,
							date);
					dispose();
					new RegisterRunnerScreen(race);
				}
		});
		
		this.setVisible(true);
	}
}

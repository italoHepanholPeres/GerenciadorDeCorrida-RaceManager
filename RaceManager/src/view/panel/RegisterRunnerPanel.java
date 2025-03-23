package view.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import model.Race;
import model.Runner;
import util.DateValidator;
import view.screen.SimulateRaceScreen;

public class RegisterRunnerPanel extends JPanel {

	private int screenWidth;

	private static final long serialVersionUID = 1L;

	public RegisterRunnerPanel(Race race) {
		this.setLayout(new BorderLayout());
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();

		screenWidth = d.width;

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(7, 2, 0, 0));

		JLabel name = new JLabel("Informe o nome do corredor:");
		name.setFont(new Font("Arial", Font.BOLD, 18));
		JTextField nameInput = new JTextField(20);
		nameInput.setFont(new Font("Arial", Font.BOLD, 18));
		inputPanel.add(name);
		inputPanel.add(nameInput);

		JLabel birthDate = new JLabel("Informe a data de nascimento do corredor:");
		birthDate.setFont(new Font("Arial", Font.BOLD, 18));
		JTextField birthDateInput = new JTextField(20);
		birthDateInput.setFont(new Font("Arial", Font.BOLD, 18));
		inputPanel.add(birthDate);
		inputPanel.add(birthDateInput);

		JLabel gender = new JLabel("Informe o genero do corredor:");
		gender.setFont(new Font("Arial", Font.BOLD, 18));
		JTextField genderInput = new JTextField(20);
		genderInput.setFont(new Font("Arial", Font.BOLD, 18));
		inputPanel.add(gender);
		inputPanel.add(genderInput);

		JLabel phone = new JLabel("Informe o telefone do corredor:");
		phone.setFont(new Font("Arial", Font.BOLD, 18));
		JTextField phoneInput = new JTextField(20);
		phoneInput.setFont(new Font("Arial", Font.BOLD, 18));
		inputPanel.add(phone);
		inputPanel.add(phoneInput);

		JButton addBttn = new JButton("Adicionar corredor");
		addBttn.setFont(new Font("Arial", Font.BOLD, 18));
		addBttn.setPreferredSize(new Dimension(150, 30));
		inputPanel.add(addBttn);

		JLabel remove = new JLabel("Informe um id para remover o corredor");
		remove.setFont(new Font("Arial", Font.BOLD, 18));
		JTextField idToRemove = new JTextField(10);
		idToRemove.setFont(new Font("Arial", Font.BOLD, 18));
		JButton removeBttn = new JButton("Remover corredor");
		removeBttn.setFont(new Font("Arial", Font.BOLD, 18));
		removeBttn.setPreferredSize(new Dimension(150, 30));

		inputPanel.add(remove);
		inputPanel.add(removeBttn);
		inputPanel.add(idToRemove);

		RunnersListPanel outputPanel = new RunnersListPanel();

		addBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//				nameInput.setText("teste");
//				birthDateInput.setText("01/01/2000");
//				genderInput.setText("dabajda");
//				phoneInput.setText("1234567890");

				String name = nameInput.getText().trim();
				String birthDate = birthDateInput.getText().trim();
				String gender = genderInput.getText().trim();
				String phone = phoneInput.getText().trim();

				if (name.isEmpty() || birthDate.isEmpty() || gender.isEmpty() || phone.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!", "Erro",
							JOptionPane.ERROR_MESSAGE);
					outputPanel.revalidate();
					outputPanel.repaint();
					RegisterRunnerPanel.this.revalidate();
					RegisterRunnerPanel.this.repaint();

					return;
				}

				if (!phone.matches("\\d{8,11}")) {
					JOptionPane.showMessageDialog(null, "O telefone deve conter apenas números (8 a 11 dígitos)!",
							"Erro", JOptionPane.ERROR_MESSAGE);
					outputPanel.revalidate();
					outputPanel.repaint();
					RegisterRunnerPanel.this.revalidate();
					RegisterRunnerPanel.this.repaint();

					return;
				}

				if (!DateValidator.validateBirthDate(birthDate, Runner.getFORMAT())) {
					JOptionPane.showMessageDialog(null,
							"A data já tem que ter passado ou estar no formato dd/MM/yyyy HH:mm");
					outputPanel.revalidate();
					outputPanel.repaint();
					RegisterRunnerPanel.this.revalidate();
					RegisterRunnerPanel.this.repaint();

					return;

				}

				try {
					Runner runner = race.addRunner(name, birthDate, gender, phone);
					outputPanel.addRunner(runner);
					
					nameInput.setText("");
					birthDateInput.setText("");
					genderInput.setText("");
					phoneInput.setText("");
					
					outputPanel.revalidate();
					outputPanel.repaint();
					RegisterRunnerPanel.this.revalidate();
					RegisterRunnerPanel.this.repaint();

				} catch (DateTimeParseException ex) {
					JOptionPane.showMessageDialog(null, "Formato inválido! Use o formato dd/MM/yyyy (ex: 25/12/2000)",
							"Erro", JOptionPane.ERROR_MESSAGE);
					outputPanel.revalidate();
					outputPanel.repaint();
					RegisterRunnerPanel.this.revalidate();
					RegisterRunnerPanel.this.repaint();

				}
			}
		});

		removeBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int passedId = Integer.parseInt(idToRemove.getText().trim());
					outputPanel.revalidate();
					outputPanel.repaint();
					RegisterRunnerPanel.this.revalidate();
					RegisterRunnerPanel.this.repaint();

					// teste
//                    if(passedId == 100) {
//                    	removeBttn.setVisible(false);
//                    	addBttn.setVisible(false);
//                    }

					race.removeRunner(passedId);
					outputPanel.removeRunner(passedId);
					outputPanel.revalidate();
					outputPanel.repaint();
					RegisterRunnerPanel.this.revalidate();
					RegisterRunnerPanel.this.repaint();

				} catch (NumberFormatException ex2) {
					JOptionPane.showMessageDialog(null, "Impossível converter para número", "Erro",
							JOptionPane.ERROR_MESSAGE);
					outputPanel.revalidate();
					outputPanel.repaint();
					RegisterRunnerPanel.this.revalidate();
					RegisterRunnerPanel.this.repaint();
				}
			}
		});

		JButton confirmBttn = new JButton("Confirmar Corrida");
		confirmBttn.setPreferredSize(new Dimension(screenWidth, 50));
		confirmBttn.setFont(new Font("Arial", Font.BOLD, 18));
		outputPanel.add(confirmBttn, BorderLayout.SOUTH);

		confirmBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!race.listRunnersrunning().isEmpty()) {
					SwingUtilities.getWindowAncestor(RegisterRunnerPanel.this).dispose();
					new SimulateRaceScreen(race);
				} else {
					JOptionPane.showMessageDialog(null, "Não é possícel começar uma corrida sem corredores!");
				}
			}
		});

		this.add(outputPanel);
		this.add(inputPanel, BorderLayout.NORTH);
	}
}

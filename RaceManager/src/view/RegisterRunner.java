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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Race;
import model.Runner;
import util.DateValidator;
import util.RunnersListPanel;

public class RegisterRunner extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public RegisterRunner(Race race) {
		this.setTitle("Cadastrar Corredor");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(7, 2, 2, 2));

		JLabel name = new JLabel("Informe o nome do corredor:");
		JTextField nameInput = new JTextField(20);
		inputPanel.add(name);
		inputPanel.add(nameInput);

		JLabel birthDate = new JLabel("Informe a data de nascimento do corredor:");
		JTextField birthDateInput = new JTextField(20);
		inputPanel.add(birthDate);
		inputPanel.add(birthDateInput);

		JLabel gender = new JLabel("Informe o genero do corredor:");
		JTextField genderInput = new JTextField(20);
		inputPanel.add(gender);
		inputPanel.add(genderInput);

		JLabel phone = new JLabel("Informe o telefone do corredor:");
		JTextField phoneInput = new JTextField(20);
		inputPanel.add(phone);
		inputPanel.add(phoneInput);

		JButton addBttn = new JButton("Adicionar corredor");
		inputPanel.add(addBttn);
		
		JLabel remove = new JLabel("Informe um id para remover o corredor");
		JTextField idToRemove = new JTextField(10);
		JButton removeBttn = new JButton("Remover corredor");
		inputPanel.add(remove);
		inputPanel.add(removeBttn);
		inputPanel.add(idToRemove);
		
		RunnersListPanel outputPanel = new RunnersListPanel();
		
		addBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//teste
				nameInput.setText("teste");
				birthDateInput.setText("01/01/2000");
				genderInput.setText("dabajda");
				phoneInput.setText("1234567890");
				
				String name = nameInput.getText().trim();
			    String birthDate = birthDateInput.getText().trim();
			    String gender = genderInput.getText().trim();
			    String phone = phoneInput.getText().trim();

			    if (name.isEmpty() || birthDate.isEmpty() || gender.isEmpty() || phone.isEmpty()) {
			        JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
			        return;
			    }

			    if (!phone.matches("\\d{8,11}")) { 
			        JOptionPane.showMessageDialog(null, "O telefone deve conter apenas números (8 a 11 dígitos)!", "Erro", JOptionPane.ERROR_MESSAGE);
			        return;
			    }		
			    
			    if(!DateValidator.validateBirthDate(birthDate, Runner.getFORMAT())) {
			    	JOptionPane.showMessageDialog(null, "A data já tem que ter passado ou/e a data tem estar no formato dd/MM/yyyy HH:mm");
			    	return;
			    }
			    
				try {
					Runner runner = 
							race.addRunner(
							name,
							birthDate,
							gender, 
							phone);
					
					outputPanel.addRunner(runner.getId(),
							name,
							birthDate,
							gender, 
							phone);
					
				} catch (DateTimeParseException ex) {
					 JOptionPane.showMessageDialog(null, "Formato inválido! Use o formato dd/MM/yyyy (ex: 25/12/2000)", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		removeBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int passedId = Integer.parseInt(idToRemove.getText().trim());
					race.removeRunner(passedId);
					 
		            outputPanel.removeRunner(passedId);
		            
				}catch (NumberFormatException ex2) {
					JOptionPane.showMessageDialog(null, "Impossível converter para número", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
				
		JButton confirmBttn = new JButton("Confirmar Corrida");
		outputPanel.add(confirmBttn, BorderLayout.SOUTH);
		
		confirmBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ConfirmRace(race);
			}
		});
		
		c.add(outputPanel);
		c.add(inputPanel, BorderLayout.NORTH);
		
		this.setVisible(true);
	}

}

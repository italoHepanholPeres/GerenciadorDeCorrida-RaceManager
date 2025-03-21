package view.panel;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Race;
import model.Runner;

public class RunnersListPanel extends JPanel{
	 
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;

	    public RunnersListPanel() {
	        setLayout(new BorderLayout());
	        
	        model = new DefaultTableModel(new Object[] { "ID", "Nome", "Data de Nascimento", "Gênero", "Telefone" }, 0) {
	            private static final long serialVersionUID = 1L;
	            //não deixa editar os campos
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
	        };
	        
	        table = new JTable(model);
	        
	      //não deixa mexer na tabela
	        table.getTableHeader().setReorderingAllowed(false);
	        table.getTableHeader().setResizingAllowed(false);

	        JScrollPane scrollPane = new JScrollPane(table);
	        add(scrollPane, BorderLayout.CENTER);
	    }
	    
	    public RunnersListPanel(String field) {
	        setLayout(new BorderLayout());
	        
	        model = new DefaultTableModel(new Object[] { "ID", "Nome", "Data de Nascimento", "Gênero", "Telefone", field }, 0) {
	            private static final long serialVersionUID = 1L;
	            //não deixa editar os campos
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
	        };
	        //não deixa mexer na tabela
	        table = new JTable(model);
	        table.getTableHeader().setReorderingAllowed(false);
	        table.getTableHeader().setResizingAllowed(false);

	        JScrollPane scrollPane = new JScrollPane(table);
	        add(scrollPane, BorderLayout.CENTER);
	    }
	    
	    public RunnersListPanel(Race race) {
	        setLayout(new BorderLayout());
	        
	        model = new DefaultTableModel(new Object[] { "ID", "Nome", "Data de Nascimento", "Gênero", "Telefone" }, 0) {
	            private static final long serialVersionUID = 1L;
	            //não deixa editar os campos
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
	        };
	        //não deixa mexer na tabela
	        table = new JTable(model);
	        table.getTableHeader().setReorderingAllowed(false);
	        table.getTableHeader().setResizingAllowed(false);

	        JScrollPane scrollPane = new JScrollPane(table);
	        add(scrollPane, BorderLayout.CENTER);
	        
	        for(Runner runner : race.listRunners()) {
	        	model.addRow(new Object[] { runner.getId(), runner.getName(), runner.getBirthDate(), runner.getGender(), runner.getPhone()});
	        }
	    }

	    public void addRunner(int id, String name, String birthDate, String gender, String phone) {
	        model.addRow(new Object[]{ id, name, birthDate, gender, phone });
	    }
	    
	    public void addRunner(int id, String name, String birthDate, String gender, String phone, String field) {
	        model.addRow(new Object[]{ id, name, birthDate, gender, phone, field});
	    }

	    public void removeRunner(int id) {
	        int rowIndex = -1;
	        for (int i = 0; i < model.getRowCount(); i++) {
	            if ((int) model.getValueAt(i, 0) == id) {
	                rowIndex = i;
	                break;
	            }
	        }
	        if (rowIndex != -1) {
	            model.removeRow(rowIndex);
	        } else {
	        	JOptionPane.showMessageDialog(this, "ID não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
	        	this.revalidate();
	        	this.repaint();
	        }
	    }

	    public DefaultTableModel getModel() {
	        return model;
	    }
}

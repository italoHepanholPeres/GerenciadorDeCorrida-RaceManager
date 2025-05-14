package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import modelo.Race;

//classe DMLivro � subclasse de DMGeral
public class DMRace extends DMGeral {
	String codigo = null;
	
	private Race race;

	// implementa��o da opera��o abstrata incluir herdada de DMGeral
	public void incluir(Object obj) {
		Race race = (Race) obj;
		this.race = (Race) obj;
		try {
			Statement statement = getConnection().createStatement();

			// montagem da String SQL de inclus�o na tabela
			String incluirSQL = "INSERT INTO race ("
					+ "idrace,name,city,startAndFinishedLocal,distance,date,limitRaceTime" + ") VALUES ('"
					+ race.getId() + "', '" + race.getName() + "', '" + race.getCity() + "', '"
					+ race.getStartAndFinishLocal() + "', '" + race.getDistance() + "', '" + race.getDate() + "', '"
					+ race.getLimitRaceTimeToString() + "')";
			System.out.println("Enviando c�digo SQL: " + getConnection().nativeSQL(incluirSQL) + "\n");
			int result = statement.executeUpdate(incluirSQL);
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "Corrida cadastrada corretamente!", "Mensagem de Informação",
						JOptionPane.INFORMATION_MESSAGE);
				this.race.setSucesso(0);
			} else {
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar corrida!", "Mensagem de Erro",
						JOptionPane.ERROR_MESSAGE);
				race.setId("");
				race.setName("");
				race.setCity("");
				race.setStartAndFinishLocal("");
				race.setDistance("");
				race.setDate("");
				race.setLimitRaceTime(0);
				this.race.setSucesso(-1);
			}
			statement.close();
		} catch (SQLException e) {
			System.out.println("Problemas com o SQL de inclusão de Corrida!");
			this.race.setSucesso(-1);
		}
	}

	// implementa��o da opera��o abstrata consultar herdada de DMGeral
	public Object consultar(Object obj) {
		Race race = (Race) obj;
		try {
			Statement statement = getConnection().createStatement();

			// montagem da String SQL de consulta na tabela
			String consultarSQL = "SELECT * FROM race WHERE (idrace = '" + race.getId() + "')";
			System.out.println("Enviando código SQL: " + getConnection().nativeSQL(consultarSQL));

			ResultSet result = statement.executeQuery(consultarSQL);
			if (result.next()) {
				System.out.println("Corrida existente!");
				System.out.println("Corrida:");
				System.out.println("Id: " + result.getString("idrace"));
				System.out.println("Nome: " + result.getString("name"));
				System.out.println("Cidade: " + result.getString("city"));
				System.out.println("Local de começo e fim: " + result.getString("startAndFinishedLocal"));
				System.out.println("Distância: " + result.getString("distance"));
				System.out.println("Data: " + result.getString("date"));
				System.out.println("Tempo limite: " + result.getString("limitRaceTime"));
				JOptionPane.showMessageDialog(null, "Mude o identificador");
				result.close();
			} else {
				System.out.println("Corrida nâo encontrado!\n");
				race = null;
			}
			statement.close();
		} catch (SQLException e) {
			System.out.println("Problemas com o SQL de consulta de Corrida!");
		}
		return race;
	}
}
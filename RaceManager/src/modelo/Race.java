package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import persistencia.DMRace;

public class Race {
	private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
			.withResolverStyle(ResolverStyle.STRICT);

	private Map<Integer, Runner> runnersRunning = new HashMap<>();
	private Map<Integer, Runner> runnersFinished = new HashMap<>();
	private int nextId = 1;
	private int nextClassification = 1;

	private String id;
	private String name;
	private String city;
	private String StartAndFinishLocal;
	private String distance;
	private LocalDateTime date;
	private static long startTime;
	private static long limitRaceTime;
	
	private int sucesso;
	
	private DMRace dmrace;

	public Race(String id, String name, String city, String StartAndFinishLocal, String distance, String date,
			long limitRaceTime) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.StartAndFinishLocal = StartAndFinishLocal;
		this.distance = distance;
		this.date = LocalDateTime.parse(date, FORMAT);
		Race.limitRaceTime = limitRaceTime;

		dmrace = new DMRace();
		dmrace.conectaDataBase("racemanagerdb", "root", "root");// Fazer a Conexao com o BD
		System.out.println("Conexão feita à tabela racemanagerdb com sucesso!");
		incluir(this);
	}

	public static DateTimeFormatter getFORMAT() {
		return FORMAT;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStartAndFinishLocal() {
		return StartAndFinishLocal;
	}

	public void setStartAndFinishLocal(String StartAndFinishLocal) {
		this.StartAndFinishLocal = StartAndFinishLocal;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getDate() {
		return date.format(FORMAT);
	}

	public void setDate(String date) {
		this.date = LocalDateTime.parse(date, FORMAT);
	}

	public String getLimitRaceTimeToString() {
		// long limitRaceTime = (hours * 60 * 60 * 1000) + (minutes * 60 * 1000);
		long hours = limitRaceTime / (60 * 60 * 1000);
		long remainingMinutesInMillis = limitRaceTime % (60 * 60 * 1000);
		long minutes = remainingMinutesInMillis / (60 * 1000);

		return String.format("%02d:%02d", hours, minutes);
	}

	public static long getLimitRaceTime() {
		return limitRaceTime;
	}

	public void setLimitRaceTime(long limitRaceTime) {
		Race.limitRaceTime = limitRaceTime;
	}

	public Runner addRunner(String name, String dateNascimento, String genero, String telefone) {
		Runner runner = new Runner(nextId, name, dateNascimento, genero, telefone);
		runnersRunning.put(nextId, runner);
		nextId++;
		return runner;
	}

	public Runner getRunnerRunning(int id) {
		return runnersRunning.get(id);
	}

	public void removeRunner(int id) {
		runnersRunning.remove(id);
	}

	public List<Runner> listRunnersrunning() {
		return new ArrayList<Runner>(runnersRunning.values());
	}

	public Runner getRunnerFinishedById(int id) {
		return runnersFinished.get(id);
	}

	public Object getRunnerFinishedByClassification(int classification) {

		for (Runner runner : runnersFinished.values()) {

			if (runner.getClassification().equals(String.valueOf(classification))) {
				return runner;
			}
		}
		return "Não houve corredor classificado como " + classification + "!";
	}

	public static long getStartTime() {
		return startTime;
	}

	public static void startRace() {
		startTime = System.currentTimeMillis();
	}

	public Runner completeRace(int id, long actualTime) {
		Runner runner = runnersRunning.get(id);
		runner.finishRace(actualTime, nextClassification);
		nextClassification++;

		runnersFinished.put(runner.getId(), runner);
		runnersRunning.remove(runner.getId());
		return runnersFinished.get(runner.getId());
	}

	@Override
	public String toString() {
		return "Corrida [corredoresCorrendo=" + runnersRunning + ", corredoresFinalizados=" + runnersFinished
				+ ", proximoId=" + nextId + ", nome=" + name + ", cidade=" + city + ", Local de início="
				+ StartAndFinishLocal + ", percurso=" + distance + ", data=" + date + "]";
	}

	public void incluir(Race race) {
		if (dmrace.consultar(this) != null) {
			JOptionPane.showMessageDialog(null,
					"Cadastro de Corrida não realizado!\n Já existe um livro com este código!", "Mensagem de Erro",
					JOptionPane.ERROR_MESSAGE);
			System.out.println("Cadastro de livro não realizado!\n Já existe um livro com este código!!");
			this.sucesso = -1;
		} else {
			dmrace.incluir(this);
		}
	}

	public int getSucesso() {
		return sucesso;
	}

	public void setSucesso(int sucesso) {
		this.sucesso = sucesso;
	}
	
	

}

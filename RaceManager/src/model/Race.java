package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Race {
	private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
			.withResolverStyle(ResolverStyle.STRICT);
	private Map<Integer, Runner> runnersRunning = new HashMap<>();
	private Map<Integer, Runner> runnersFinished = new HashMap<>();
	private int nextId = 1;

	private String name;
	private String city;
	private String startLocal;
	private String distance;
	private LocalDateTime date;
	private static long startTime;
	private static long limitRaceTime;

	public Race(String name, String city, String startLocal, String distance, String date, long limitRaceTime) {
		this.name = name;
		this.city = city;
		this.startLocal = startLocal;
		this.distance = distance;
		this.date = LocalDateTime.parse(date, FORMAT);
		Race.limitRaceTime = limitRaceTime;
	}

	public static DateTimeFormatter getFORMAT() {
		return FORMAT;
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

	public String getStartLocal() {
		return startLocal;
	}

	public void setStartLocal(String startLocal) {
		this.startLocal = startLocal;
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

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getLimitRaceTimeToString() {
		//long limitRaceTime = (hours * 60 * 60 * 1000) + (minutes * 60 * 1000);
		long hours = limitRaceTime / (60 * 60 * 1000);
        long remainingMinutesInMillis = limitRaceTime % (60 * 60 * 1000);
        long minutes = remainingMinutesInMillis / (60 * 1000); //
		
		return String.format("%02d:%02d", hours, minutes);
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

	public List<Runner> listRunners() {
		return new ArrayList<Runner>(runnersRunning.values());
	}

	public Runner getRunnerFinishedById(int id) {
		return runnersFinished.get(id);
	}

	public static long getStartTime() {
		return startTime;
	}

	public static void startRace() {
		startTime = System.currentTimeMillis();
	}

	public void finishRace() {

	}

	public Runner completeRace(int id) {
		long tempoAtual = System.currentTimeMillis();
		Runner runner = runnersRunning.get(id);
		runner.finishRace(tempoAtual);

		runnersFinished.put(runner.getId(), runner);
		runnersRunning.remove(runner.getId());
		return runnersFinished.get(runner.getId());
	}

	@Override
	public String toString() {
		return "Corrida [corredoresCorrendo=" + runnersRunning + ", corredoresFinalizados=" + runnersFinished
				+ ", proximoId=" + nextId + ", nome=" + name + ", cidade=" + city + ", Local de início=" + startLocal
				+ ", percurso=" + distance + ", data=" + date + "]";
	}

}

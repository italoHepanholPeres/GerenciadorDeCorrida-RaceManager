package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Runner {

	private int id;
	private String name;
	private LocalDate birthDate;
	private String gender;
	private String phone;
	private long finishedTime;
	private String classification;
	
	private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/uuuu")
			.withResolverStyle(ResolverStyle.STRICT);

	public Runner() {
	}
	
	public Runner(int id, String name, String birthDate, String gender, String phone) {
		this.id = id;
		this.name = name;
		this.birthDate = LocalDate.parse(birthDate, FORMAT);
		this.gender = gender;
		this.phone = phone;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate.format(FORMAT);
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = LocalDate.parse(birthDate, FORMAT);
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public static DateTimeFormatter getFORMAT() {
		return FORMAT;
	}

	public long getFinishedTime() {
		return finishedTime;
	}

	public void finishRace(long tempoAtual, int obtainedClassification) {
		this.finishedTime = tempoAtual;
		this.classification = String.valueOf(obtainedClassification);
	}
	
	public void finishRace(long tempoAtual, String obtainedClassification) {
		this.finishedTime = tempoAtual;
		this.classification = obtainedClassification;
	}
	
	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getElapsedTime() {
	    long elapsedMillis = this.finishedTime - Race.getStartTime();

	    long minutes = (elapsedMillis / 60000) % 60;
	    long seconds = (elapsedMillis / 1000) % 60;
	    long milliseconds = elapsedMillis % 1000;

	    return String.format("%02d:%02d:%03d", minutes, seconds, milliseconds);
	}

	@Override
	public String toString() {
		return "Corredor [id=" + id + ", nome=" + name + ", dataNascimento=" + birthDate + ", genero=" + gender
				+ ", telefone=" + phone + ", tempo final=" + finishedTime + "]";
	}
}

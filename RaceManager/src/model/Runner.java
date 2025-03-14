package model;

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

	private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/uuuu")
			.withResolverStyle(ResolverStyle.STRICT);

	public Runner() {
	}
	
	public Runner(Runner runner) {
		this.id = runner.getId();
		this.name = runner.getName();
		this.birthDate = LocalDate.parse(runner.getBirthDate(), FORMAT);
		this.gender = runner.getGender();
		this.phone = runner.getPhone();
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

	public void setFinishedTime(long finishedTime) {
		this.finishedTime = finishedTime/1000;
	}

	public void finishRace(long tempoAtual) {
		this.finishedTime = tempoAtual;
	}

	public long getElapsedTime() {
		return this.finishedTime - Race.getStartTime();
	}

	@Override
	public String toString() {
		return "Corredor [id=" + id + ", nome=" + name + ", dataNascimento=" + birthDate + ", genero=" + gender
				+ ", telefone=" + phone + ", tempo final=" + finishedTime + "]";
	}
	
	
}

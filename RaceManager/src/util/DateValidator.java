package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

public class DateValidator {
	public static boolean validateDateToRace(String date, DateTimeFormatter format) {
		LocalDate now = LocalDate.now();
		try {
			LocalDate testDate = LocalDate.parse(date,format);
		
			if(testDate.isBefore(now)) {
				return false;
			}else if(testDate.isEqual(now)){
				return false;
			}else {
				return true;
			}
		}catch (DateTimeParseException e) {
			return false;
		}	
	}
	
	public static boolean validateBirthDate(String dateBirth, DateTimeFormatter format) {
		LocalDate now = LocalDate.now();
		
		try {
			LocalDate testDate = LocalDate.parse(dateBirth,format);
			
			if(testDate.isAfter(now)) {
				return false;
			}else if(testDate.isEqual(now)){
				return false;
			}else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
			
	}
	
	
}

package blockchain.entrepreneur.cuisine.model;
import java.time.LocalDate;


public class Date {
	private int year;
	private int month;
	private int day;
	
	public Date() {
		LocalDate todaysDate = LocalDate.now();
		this.year = todaysDate.getYear();
		this.month = todaysDate.getMonthValue();
		this.day = todaysDate.getDayOfMonth();
	}
	
	public int compareTo(Date date) {
		if (this.year > date.year) return 1;
		if (this.year < date.year) return -1;
		else {
			if (this.month > date.month) return 1;
			if (this.month < date.month) return -1;
			else {
				if (this.year > date.year) return 1;
				if (this.year < date.year) return -1;
				if (this.year == date.year) return 0;
			}
		}
		return 0;		
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	
}

package com.tg.java.model;

public class Account {
	
	String id;
	String firstName;
	String lastName;
	String email;
	String gender;
	double salary;
	boolean recruiter;
	String jobTitle;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public boolean isRecruiter() {
		return recruiter;
	}
	public void setRecruiter(boolean recruiter) {
		this.recruiter = recruiter;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", gender=" + gender + ", salary=" + salary + ", recruiter=" + recruiter + ", jobTitle=" + jobTitle
				+ "]";
	}
	
	public static Account build(String sample) {
		String[] arr = sample.split("\\^");
		Account account = new Account();
		account.setId(arr[0]);
		account.setSalary(Double.parseDouble(arr[1]));
		account.setEmail(arr[2]);
		account.setRecruiter(Boolean.parseBoolean(arr[3]));
		account.setFirstName(arr[4]);
		account.setLastName(arr[5]);
		account.setGender(arr[6]);
		account.setJobTitle(arr[7]);
		return account;
	}
}

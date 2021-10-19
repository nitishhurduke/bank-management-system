package com.braindata.bankmanagement.model;

public class Account {
	
	private String accNo;
	private String fname;
	private String lname;
	private String mobNo;
	private String adharno;
	private String gender;
	private String age;
	private double balance;

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public void setAdharNo(String adharno) {
		this.adharno = adharno;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccNo() {
		return accNo;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public String getMobNo() {
		return mobNo;
	}

	public String getAdharNo() {
		return adharno;
	}

	public String getGender() {
		return gender;
	}

	public String getAge() {
		return age;
	}

	public double getBalance() {
		return balance;
	}
}
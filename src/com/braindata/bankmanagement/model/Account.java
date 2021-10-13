package com.braindata.bankmanagement.model;

public class Account {
	private int accNo;
	private String name;
	private String mobNo;
	private String adharno;
	private String gender;
	private int age;
	private double balance;

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setAge(int age) {
		this.age = age;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccNo() {
		return accNo;
	}

	public String getName() {
		return name;
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

	public int getAge() {
		return age;
	}

	public double getBalance() {
		return balance;
	}
}
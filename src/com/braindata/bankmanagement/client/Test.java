package com.braindata.bankmanagement.client;


import com.braindata.bankmanagement.service.Rbi;
import com.braindata.bankmanagement.serviceImpl.Sbi;

public class Test {
	public static void main(String args[]) {

		Rbi bank = new Sbi();
		bank.initializeDataBase();
		bank.mainMenu();
	}

}
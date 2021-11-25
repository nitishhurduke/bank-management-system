package com.braindata.bankmanagement.client;


import com.braindata.bankmanagement.service.Rbi;
import com.braindata.bankmanagement.serviceImpl.Bank;

public class Application {
	public static void main(String args[]) {

		Rbi bank = new Bank();
		bank.initializeDataBase();
		bank.mainMenu();
	}

}
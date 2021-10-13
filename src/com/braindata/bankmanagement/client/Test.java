package com.braindata.bankmanagement.client;

import com.braindata.bankmanagement.service.Rbi;
import com.braindata.bankmanagement.serviceImpl.Sbi;
import java.util.Scanner;

public class Test {

	public static void main(String args[]) {
		Rbi bank = new Sbi();
		Scanner sc = new Scanner(System.in);
		boolean flag = false;

//		System.out.println("Lets start with creating your account...");
//		bank.createAccount();

		while (true) {
			System.out.println("Choose from Following operations..");
			System.out.println("\n 1.Create Account");
			System.out.println(" 2.Display Account Details");
			System.out.println(" 3.Check Balance");
			System.out.println(" 4.Deposit Money");
			System.out.println(" 5.Withdraw Money");
			System.out.println(" 0.Exit");

			int input = sc.nextInt();

			switch (input) {
			case 1:
				flag = true;
				bank.createAccount();
				break;

			case 2:
				if (flag) {
					bank.displayAllDetails();
					break;
				} else {
					System.out.println("No Account Found..");
					System.out.println("Create an Account First to Use this Operation");
					break;
				}

			case 3:

				if (flag) {
					System.out.println("	*BALANCE CHECK*");
					bank.checkBalance();
					break;
				} else {
					System.out.println("No Account Found..");
					System.out.println("Create an Account First to Use this Operation");
					break;
				}

			case 4:
				if (flag) {
					System.out.println("	*DEPOSIT MONEY*");
					bank.depositMoney();
					break;
				} else {
					System.out.println("No Account Found..");
					System.out.println("Create an Account First to Use this Operation");
					break;
				}

			case 5:
				if (flag) {
					System.out.println("	*WITHDRAW MONEY*");
					bank.withdrawal();
					break;
				} else {
					System.out.println("No Account Found..");
					System.out.println("Create an Account First to Use this Operation");
					break;
				}

			case 0:
				System.out.println("Thank You for banking with us!!");
				System.exit(0);
				break;

			}

		}
	}

}
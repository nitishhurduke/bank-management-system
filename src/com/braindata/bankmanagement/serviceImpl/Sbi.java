package com.braindata.bankmanagement.serviceImpl;

import com.braindata.bankmanagement.service.Rbi;
import com.braindata.bankmanagement.model.Account;
import java.util.Scanner;
import java.util.Random;//To Generate Account Number

public class Sbi implements Rbi {

	Scanner sc = new Scanner(System.in);
	Account ac = new Account();
	Random random = new Random();

	public void createAccount() {

		System.out.print("Enter Account Name :  ");
		String name = sc.next();
		ac.setName(name);

		System.out.print("Enter Mobile Number : ");
		String mob = sc.next();
		ac.setMobNo(mob);

		System.out.print("Enter Adhar Number : ");
		String adhar = sc.next();
		ac.setAdharNo(adhar);

		System.out.print("Enter Gender : ");
		String gender = sc.next();
		ac.setGender(gender);

		/* Code to set and check if Age is Equal or Greater than 18 */
		setAge();

		/* Code to get and Check if Initial Deposit is Greater than or equal 1000 rs. */
		initialDeposit();

		System.out.println("Congratulations!! Account successfully created.....");

		/* Code to Generate Account Number */
		generateAccNo();

		System.out.println(
				"Your Account Number is : " + ac.getAccNo() + " Please use this Account Number for Baning Features");
		System.out.println("-------------------------------------------");

	}

	public void displayAllDetails() {
		System.out.println("	*ACCOUNT DETAILS*");
		System.out.println("Account Number       : " + ac.getAccNo());
		System.out.println("Account Holders Name : " + ac.getName());
		System.out.println("Mobile Number        : " + ac.getMobNo());
		System.out.println("Adhar Number         : " + ac.getAdharNo());
		System.out.println("Gender               : " + ac.getGender());
		System.out.println("Age                  : " + ac.getAge() + " years");
		System.out.println("Current balance      : " + ac.getBalance());

		System.out.println("-------------------------------------------");
	}

	public void depositMoney() {
		System.out.print("Enter Account Number: ");
		int input = sc.nextInt();
		int accNo = ac.getAccNo();

		if (accNo == input) {
			System.out.print("Enter Ammount to Deposit: ");
			double deposit = sc.nextDouble();
			/*
			 * Code to Make sure Deposit Amount is Greater than 500
			 */
			if (deposit >= 500) {

				double balance = ac.getBalance();

				balance = balance + deposit;

				ac.setBalance(balance);

				System.out.println("Ammount " + deposit + " deposited in your account successfully....");
				System.out.println("-------------------------------------------");
			} else {
				System.out.println("Failed Deposit...Can NOT deposit Amount less than 500 rs.");
				depositMoney();
			}
		} else {
			System.out.println("Inorrect Account Number...Try Again..");
			depositMoney();
		}
	}

	public void withdrawal() {

		System.out.print("Enter Account Number: ");
		int input = sc.nextInt();
		int accNo = ac.getAccNo();

		/*
		 * Condition to Verify Account Number
		 */
		if (accNo == input) {
			double balance = ac.getBalance();

			/*
			 * Condition to Check if Current Balance is Already Greater than Minimum Balance
			 */
			if (balance > 1000) {
				System.out.print("Enter Ammount to Withdraw: ");
				double withdraw = sc.nextDouble();

				if (withdraw >= 500) {
					/*
					 * Condition to Check Withdrawal amount is Less than Current Balance
					 */
					if (balance >= withdraw) {
						double newBalance = balance - withdraw;

						/*
						 * Condition to ensure New Balance after withdrawal must be Greater than Minimum
						 * Balance
						 */
						if (newBalance >= 1000) {
							ac.setBalance(newBalance);
							System.out.println("Ammount " + withdraw + " withdrawn Successfully....");
							System.out.println("-------------------------------------------");
						} else {
							System.out.println("\nFailed to Withdraw...you can NOT withdraw '" + withdraw
									+ "' from your current balance '" + balance + " rs.' ");
							System.out.println("You must Maintain MINIMUM BALANCE '1000 rs.'");
							System.out.println("-------------------------------------------");
						}

					} else {
						System.out.println("Insufficent Balance in your account...Try Again");
						System.out.println("Your Current Balance : " + balance);
						withdrawal();
					}
				} else {
					System.out.println("Please Enter Amount Greater Than 500 rs.");
					withdrawal();
					
				}
			} else {
				System.out.println("Your Current Balance : " + balance + " rs.");
				System.out.println("Minimum Balance to be maintained : 1000 rs.");
				System.out.println("Failed to withdraw...Your current balance is already at MINIMUM BALANCE aproved ");
				System.out.println("-------------------------------------------");
			}
		} else {
			System.out.println("Inorrect Account Number...");

			withdrawal();
		}

	}

	public void checkBalance() {

		System.out.print("Enter Account Number: ");
		int input = sc.nextInt();
		int accNo = ac.getAccNo();
		/*
		 * Condition to Verify Account Number
		 */
		if (accNo == input) {
			System.out.println("Account Number: " + ac.getAccNo());
			System.out.println("Balance Ammount in your account : " + ac.getBalance());
			System.out.println("-------------------------------------------");
		} else {
			System.out.println("Enter Correct Account Number");
			checkBalance();
		}

	}

	public void initialDeposit() {

		System.out.println("To start banking you must deposit at least 1000 rs. ");
		System.out.print("Enter amount to deposit initialy: ");
		double iniDep = sc.nextDouble();
		if (iniDep < 1000) {
			System.out.println("Please try again... ");
			initialDeposit();
		} else {
			ac.setBalance(iniDep);
		}

	}

	public void setAge() {
		System.out.print("Enter Age:  ");
		int age = sc.nextInt();
		if (age >= 18) {
			ac.setAge(age);
		} else {
			System.out.println(
					"You are not Eligible to Create a Bank Account(Eligibility age to create a bank account is 18 or more)");
			System.exit(0);
		}
	}

	public void generateAccNo() {
		int accNo = random.nextInt(9999);
		ac.setAccNo(accNo);

	}

}
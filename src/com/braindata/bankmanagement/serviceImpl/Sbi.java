package com.braindata.bankmanagement.serviceImpl;

import com.braindata.bankmanagement.service.Rbi;
import com.braindata.bankmanagement.model.Account;
import java.util.Scanner;

public class Sbi implements Rbi {

	Scanner sc = new Scanner(System.in);
	Account ac = new Account(); //Instance of Account Class ==> Holds information of Customer.
	CreateAccount ca = new CreateAccount();//Instance of CreateAccount Class ==> Holds all methods to Set Customer Information.
	boolean accountCreated = false; //To check whether account is created or not before use other banking operations.

	public void mainMenu() {

		System.out.println("-------------------------------------------");
		System.out.println("Choose from Following operations..");
		System.out.println("\n 1.Create Account");
		System.out.println(" 2.Display Account Details");
		System.out.println(" 3.Check Balance");
		System.out.println(" 4.Deposit Money");
		System.out.println(" 5.Withdraw Money");
		System.out.println(" 0.Exit");

		String input = sc.next();

		switch (input) {
		case "1":
			if (accountCreated == false) {
				createAccount();
				accountCreated = true;
			} else {
				System.out.println("Account Already created...Multiple Accounts Feature Comming Soon...");
				System.out.println("-------------------------------------------");
				subMenu();
			}
			break;

		case "2":
			if (accountCreated) {
				System.out.println("-------------------------------------------");
				displayAllDetails();
				break;
			} else {
				System.out.println("No Account Found..");
				System.out.println("Create an Account First to Use this Operation");
				mainMenu();
				break;
			}

		case "3":

			if (accountCreated) {
				System.out.println("-------------------------------------------");

				checkBalance();
				break;
			} else {
				System.out.println("No Account Found..");
				System.out.println("Create an Account First to Use this Operation");
				mainMenu();
				break;
			}

		case "4":
			if (accountCreated) {
				System.out.println("-------------------------------------------");
				System.out.println("	*DEPOSIT MONEY*");
				compareAccNo();
				depositMoney();
				break;
			} else {
				System.out.println("No Account Found..");
				System.out.println("Create an Account First to Use this Operation");
				mainMenu();
				break;
			}

		case "5":
			if (accountCreated) {
				System.out.println("-------------------------------------------");
				System.out.println("	*WITHDRAW MONEY*");
				compareAccNo();
				withdrawal();
				break;
			} else {
				System.out.println("No Account Found..");
				System.out.println("Create an Account First to Use this Operation");
				mainMenu();
				break;
			}

		case "0":
			System.out.println("Thank You for banking with us!!");
			System.exit(0);
			break;

		default:
			System.out.println("! Invalid Entry...");
			mainMenu();

		}

	}

	public void subMenu() {

		System.out.println("Enter '5' for Main Menu Or '0' for Exit");
		String input = sc.next();
		switch (input) {
		case "5":
			mainMenu();
			break;
		case "0":
			System.out.println("Thank You for banking with us!!");
			System.exit(0);
		default:
			System.out.println("! Invalid Entry...");
			subMenu();
			break;
		}
	}

	public void createAccount() {
		System.out.println("-------------------------------------------");
		System.out.println("  *REGISTRATION*");
		/* Code to set Name */
		ac.setFname(ca.setFname());
		ac.setLname(ca.setLname());

		/* Code to set and check if Age is Equal or Greater than 18 */
		ac.setAge(ca.setAge());

		/* Code to set Mobile Number */
		ac.setMobNo(ca.setMobNo());

		/* Code to set Aadhaar Number */
		ac.setAdharNo(ca.setAdhar());

		/* Code to set Gender */
		ac.setGender(ca.setGender());

		/* Code to get and Check if Initial Deposit is Greater than or equal 1000 rs. */
		ac.setBalance(ca.initialDeposit());

		System.out.println("\nCongratulations!! Account successfully created.....");

		/* Code to Generate Account Number */
		ac.setAccNo(ca.generateAccNo());

		System.out.println("\nYOUR ACCOUNT NUMBER IS : " + ac.getAccNo()
				+ " Please use your Account Number to experience the best of our Banking Features");
		System.out.println("-------------------------------------------");
		subMenu();
	}


	public void displayAllDetails() {
		System.out.println("	*ACCOUNT DETAILS*");
		System.out.println("Customer Account Number: " + ac.getAccNo());
		System.out.println("Customer Name          : " + ac.getFname() + " " + ac.getLname());
		System.out.println("Mobile Number          : " + ac.getMobNo());
		System.out.println("Aadhaar Number         : " + ac.getAdharNo());
		System.out.println("Gender                 : " + ac.getGender());
		System.out.println("Age                    : " + ac.getAge() + " years");
		System.out.println("Current balance        : " + ac.getBalance());

		System.out.println("-------------------------------------------");
		subMenu();
	}

	public void depositMoney() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		double deposit = 0;
		System.out.print("Enter Amount to Deposit: ");
		try {
			deposit = sc.nextDouble(); // Risky Code
		}
		/*
		 * Code to Make sure Deposit Amount is Greater than 500
		 */
		catch (Exception e) {
			System.out.println("! Invalid Entry");
		} finally {

			if (deposit != 0) {

				if (deposit >= 500) {

					double balance = ac.getBalance();

					balance = balance + deposit;

					ac.setBalance(balance);

					System.out.println(
							"Congratulations...Amount " + deposit + " deposited in your account successfully....");
					System.out.println("-------------------------------------------");
				} else {
					System.out.println("Failed Deposit...Can NOT deposit Amount less than 500 rs.");
				}

				subMenu();

			} else {
				depositMoney();
			}

		}

	}

	public void withdrawal() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		double balance = ac.getBalance();
		double withdraw = 0;
		boolean ok = false;

		if (balance > 1000) {
			ok = true;
		} else {
			withdrawal();
		}

		try {
			/*
			 * Condition to Check if Current Balance is Already Greater than Minimum Balance
			 */
			System.out.print("Enter Amount to Withdraw: ");
			withdraw = sc.nextDouble();

		} catch (Exception e) {
			System.out.println("! Invalid Entry...Try Again...");
		} finally {
			if (withdraw != 0) {

				if (ok) {
					/*
					 * Condition to check Amount is multiple of 100
					 */
					if (withdraw % 100 == 0) {

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
								System.out.println("Amount " + withdraw + " withdrawn Successfully....");
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
							System.out.println("-------------------------------------------");

						}
					} else {
						System.out.println("Please Enter Amount multiple of 100 ");
						withdrawal();

					}
				} else {
					System.out.println(
							"Failed to Withdraw...Your current balance is already at MINIMUM BALANCE aproved ");
					System.out.println("*Your Current Balance : " + balance + " rs.");
					System.out.println("*Minimum Balance to be maintained : 1000 rs.");
					System.out.println("-------------------------------------------");
				}
				subMenu();
			} else {
				withdrawal();
			}
		}
	}

	public void checkBalance() {

		/*
		 * Condition to Verify Account Number
		 */
		compareAccNo();

		System.out.println("-------------------------------------------");
		System.out.println("	*BALANCE CHECK*");
		System.out.println("Account Number: " + ac.getAccNo());
		System.out.println("Balance Amount in your account : " + ac.getBalance());
		System.out.println("-------------------------------------------");

		subMenu();
	}

	public void compareAccNo() {
		Scanner in = new Scanner(System.in);
		String inAccNo = null;
		System.out.print("Enter Account Number : ");
		try {
			inAccNo = in.next();
		} catch (Exception e) {
			System.out.println("! Invalid Entry...Try Again...");
		} finally {
			if (inAccNo != null) {
				String accNo = ac.getAccNo();
				if (accNo.equals(inAccNo)) {
					System.out.println("Account number matches..");
				} else {
					System.out.println("Invalid Account Number...Try Again");
					compareAccNo();
				}
			} else {
				compareAccNo();
			}
		}
	}

}
package com.braindata.bankmanagement.serviceImpl;

import com.braindata.bankmanagement.service.Rbi;
import com.braindata.bankmanagement.model.Account;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;//To Generate Account Number

public class Sbi implements Rbi {

	Scanner sc = new Scanner(System.in);
	Account ac = new Account();
	Random random = new Random();
	short attempt = 3;
	boolean flag = false;
	
	public void mainMenu() {
		System.out.println("-------------------------------------------");
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
			System.out.println("-------------------------------------------");
			System.out.println("  *REGISTRATION*");
			createAccount();
			break;

		case 2:
			if (flag) {
				System.out.println("-------------------------------------------");
				displayAllDetails();
				break;
			} else {
				System.out.println("No Account Found..");
				System.out.println("Create an Account First to Use this Operation");
				mainMenu();
				break;
			}

		case 3:

			if (flag) {
				System.out.println("-------------------------------------------");
				System.out.println("	*BALANCE CHECK*");
				checkBalance();
				break;
			} else {
				System.out.println("No Account Found..");
				System.out.println("Create an Account First to Use this Operation");
				mainMenu();
				break;
			}

		case 4:
			if (flag) {
				System.out.println("-------------------------------------------");
				System.out.println("	*DEPOSIT MONEY*");
				depositMoney();
				break;
			} else {
				System.out.println("No Account Found..");
				System.out.println("Create an Account First to Use this Operation");
				mainMenu();
				break;
			}

		case 5:
			if (flag) {
				System.out.println("-------------------------------------------");
				System.out.println("	*WITHDRAW MONEY*");
				withdrawal();
				break;
			} else {
				System.out.println("No Account Found..");
				System.out.println("Create an Account First to Use this Operation");
				mainMenu();
				break;
			}

		case 0:
			System.out.println("Thank You for banking with us!!");
			System.exit(0);
			break;

		}
	}
	public void subMenu() {
		System.out.println("Enter '5' for Main Menu Or '0' for Exit");
		byte input = sc.nextByte();
		switch (input) {
		case 5:
			mainMenu();
			break;
		case 0:
			System.out.println("Thank You for banking with us!!");
			System.exit(0);
		default:
			System.out.println("Enter Valid Input!!");
			subMenu();
			break;
		}
		
	}
	
	
	public void createAccount() {

		System.out.print("\nEnter Account Name :  ");
		String name = sc.next();
		ac.setName(name);
		
		/* Code to set and check if Age is Equal or Greater than 18 */
		setAge();

		System.out.print("Enter Mobile Number : ");
		String mob = sc.next();
		ac.setMobNo(mob);

		System.out.print("Enter Adhar Number : ");
		String adhar = sc.next();
		ac.setAdharNo(adhar);

		System.out.print("Enter Gender : ");
		String gender = sc.next();
		ac.setGender(gender);

		

		/* Code to get and Check if Initial Deposit is Greater than or equal 1000 rs. */
		initialDeposit();

		System.out.println("Congratulations!! Account successfully created.....");

		/* Code to Generate Account Number */
		generateAccNo();

		System.out.println(
				"\nYOUR ACCOUNT NUMBER IS : " + ac.getAccNo() + " Please use this Account Number for BanKing Features");
		System.out.println("-------------------------------------------");
		subMenu();
	}

	public void setAge() {
	    Scanner ag = new Scanner(System.in);
		int age = 0;
		System.out.print("Enter Age: ");
		try {
			age = ag.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid Age !! Try again... ");
		} finally {
			if (age != 0) {
				if (age >= 18) {
					ac.setAge(age);
				
				} else {
				
					System.out.println(
							"You are not Eligible to Create a Bank Account(Eligibility age to create a bank account is 18 or more)");
					System.exit(0);
				}
			} else {
				setAge();
			}
			
		}
	}
	
	public void setMobNo() {
		
	}
	public void generateAccNo() {
		int min = 1000;
		int max = 9999;
		int accNo1 = (int) (Math.random() * (max - min + 1) + min);
		/* Or */
		int accNo = random.nextInt(9999);
		ac.setAccNo(accNo1);
	
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
		System.out.println("Withdrawal attempts left: " + attempt);
		System.out.println("-------------------------------------------");
		subMenu();
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

				System.out.println("Congratulations...Ammount " + deposit + " deposited in your account successfully....");
				System.out.println("-------------------------------------------");
			} else {
				System.out.println("Failed Deposit...Can NOT deposit Amount less than 500 rs.");
				depositMoney();
			}
		} else {
			System.out.println("Inorrect Account Number...Try Again..");
			depositMoney();
		}
		subMenu();
	}

	public void withdrawal() {

		if (attempt > 0) {
			System.out.println("Withdrawal attempt " + (4 - attempt) + " out of 3");
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
								System.out.println("Ammount " + withdraw + " withdrawn Successfully....");
								System.out.println("-------------------------------------------");
								attempt--;
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
						System.out.println("Please Enter Amount multiple of 100 ");
						withdrawal();

					}
				} else {
					System.out.println("Your Current Balance : " + balance + " rs.");
					System.out.println("Minimum Balance to be maintained : 1000 rs.");
					System.out.println(
							"Failed to withdraw...Your current balance is already at MINIMUM BALANCE aproved ");
					System.out.println("-------------------------------------------");
				}
			} else {
				System.out.println("Inorrect Account Number...");

				withdrawal();
			}

		} else {
			System.out.println("You have Exhausted withdrawal attempt limit of 3 for the day");
			System.out.println("Please visit after 24 hrs....");
			System.out.println("-------------------------------------------");
		}
		subMenu();
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
		subMenu();
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

}
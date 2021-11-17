package com.braindata.bankmanagement.serviceImpl;

import com.braindata.bankmanagement.service.Rbi;
import com.braindata.bankmanagement.model.Account;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class Sbi implements Rbi {

	Scanner sc = new Scanner(System.in);
	Map<String, Account> accMap = new HashMap<>();

	// Instance of Account Class ==> Holds information of Customer.

	boolean accountCreated = false; // To check whether account is created or not before use other banking
									// operations.

	public void mainMenu() {

		System.out.println("-------------------------------------------");
		System.out.println(" ----* MAIN MENU *----");
		System.out.println("Choose from Following operations..");
		System.out.println("\n 1.Create Account");
		System.out.println(" 2.Display Account Details");
		System.out.println(" 3.Check Balance");
		System.out.println(" 4.Deposit Money");
		System.out.println(" 5.Withdraw Money");
		System.out.println(" 6.Transfer Money");
		System.out.println(" 0.Exit");

		String input = sc.next();

		switch (input) {
		case "1":
			accountCreated = true;
			createAccount();
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
				System.out.println("----* DEPOSIT MONEY *----");
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
				System.out.println("----* WITHDRAW MONEY *----");
				withdrawal();
				break;
			} else {
				System.out.println("No Account Found..");
				System.out.println("Create an Account First to Use this Operation");
				mainMenu();
				break;
			}
		case "6":
			if (accountCreated) {
				System.out.println("-------------------------------------------");
				System.out.println("----* TRANSFER MONEY *----");
				transfer();
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
		System.out.println("----* REGISTRATION *----");
		Account ac = new Account();
		CreateAccount ca = new CreateAccount();// Instance of CreateAccount Class ==> Holds all methods to Set Customer
		// Information.

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
		accMap.put(ac.getAccNo(), ac);

		/* Passbook Building part */
		initializePassbook(ac);

		subMenu();
	}

	public void displayAllDetails() {

		Account ac = compareAccNo();
		System.out.println("----* ACCOUNT DETAILS *----");
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
		Account ac = compareAccNo();
		System.out.println("Account of " + ac.getFname() + " " + ac.getLname() + "(" + ac.getAccNo() + ")");
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
					boolean check = true;
					updatePassbook(ac,deposit,check);

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
		Account ac = compareAccNo();
		System.out.println("Account of " + ac.getFname() + " " + ac.getLname() + "(" + ac.getAccNo() + ")");
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
								boolean check = false;
//								updatePassbokWith(ac, withdraw);
								updatePassbook(ac, newBalance, check);

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

		Account ac = compareAccNo();
		System.out.println("----* BALANCE CHECK *----");
		System.out.println("Account Number: " + ac.getAccNo());
		System.out.println("Balance Amount in your account : " + ac.getBalance());
		System.out.println("-------------------------------------------");

		subMenu();
	}

	public void transfer() {
		System.out.print("From(Your)      --> ");
		Account ac = compareAccNo();
		System.out.println("Your Account : " + ac.getFname() + " " + ac.getLname() + "(" + ac.getAccNo() + ")");
		System.out.print("To(Benificiary) --> ");
		Account accBenificiary = compareAccNo();
		System.out.println("Benificiary Account : " + accBenificiary.getFname() + " " + accBenificiary.getLname() + "("
				+ accBenificiary.getAccNo() + ")");
		System.out.println("Confirm by entering '5' to continue or press '0' to change accounts");
		boolean confirm = false;
		String ch = sc.next();
		switch (ch) {
		case "5":
			confirm = true;
			break;
		case "0":
			transfer();
			break;
		default:
			System.out.println("Invalid Input..Try Again...");
			transfer();
		}

		boolean diffaccnts = false;
		if (ac != accBenificiary) {
			diffaccnts = true;
		} else {
			System.out.println("Choose Different Accounts");
			transfer();
		}
		Scanner sc = new Scanner(System.in);
		double balance = ac.getBalance();
		double transfer = 0;
		boolean sufficientBalance = false;

		if (balance > 1000) {
			sufficientBalance = true;
		} else {
			transfer();
		}

		try {
			/*
			 * Condition to Check if Current Balance is Already Greater than Minimum Balance
			 */
			System.out.print("Enter Amount to Transfer(" + ac.getFname() + "-->" + accBenificiary.getFname() + "): ");
			transfer = sc.nextDouble();

		} catch (Exception e) {
			System.out.println("! Invalid Entry...Try Again...");
		} finally {
			if (confirm) {
				if (transfer != 0) {
					if (diffaccnts) {
						if (sufficientBalance) {

							/*
							 * Condition to Check Transfer amount is Less than Current Balance
							 */
							if (balance >= transfer) {
								double newBalance = balance - transfer;

								/*
								 * Condition to ensure New Balance after transfer must be Greater than Minimum
								 * Balance
								 */
								if (newBalance >= 1000) {
									ac.setBalance(newBalance);
									accBenificiary.setBalance(accBenificiary.getBalance() + transfer);

									updatePassbok(ac, accBenificiary, transfer);

									System.out.println("Amount " + transfer + " transferred Successfully....");
									System.out.println("-------------------------------------------");
								} else {
									System.out.println("\nFailed to Transfer...you can NOT transfer '" + transfer
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
							System.out.println(
									"Failed to Transfer...Your current balance is already at MINIMUM BALANCE aproved ");
							System.out.println("*Your Current Balance : " + balance + " rs.");
							System.out.println("*Minimum Balance to be maintained : 1000 rs.");
							System.out.println("-------------------------------------------");
						}
						subMenu();
					} else {

						subMenu();
					}
				} else {
					transfer();
				}
			} else {
				transfer();
			}
		}

	}

	public void initializePassbook(Account ac) {
		/* Date and Time part */
		String instance = getDateAndTime();
		String fullname = ac.getFname() + " " + ac.getLname();

		File passbook = new File(
				"E:\\CJC Workspace\\Class Java workspace\\BankManagementSystem\\src\\Accounts\\" + fullname);
		// File passbook = new File(fullname);

		try {
			passbook.createNewFile();
			FileWriter fw = new FileWriter(passbook);
			fw.write("		***** PASSBOOK *****");
			fw.write("\n");
			fw.write("\n");
			fw.write("    ----* Account Details *----");
			fw.write("\n");
			fw.write(" * Account Number      : " + ac.getAccNo());
			fw.write("\n");
			fw.write(" * Account Holders Name: " + ac.getFname() + " " + ac.getLname());
			fw.write("\n");
			fw.write(" * Age                 : " + ac.getAge());
			fw.write("\n");
			fw.write(" * Gender              : " + ac.getGender());
			fw.write("\n");
			fw.write(" * Mobile Number       : " + ac.getMobNo());
			fw.write("\n");
			fw.write(" * Aadhaar Number      : " + ac.getAdharNo());
			fw.write("\n");
			fw.write("\n");
			fw.write("---------------------------------------");
			fw.write("\n");
			fw.write("    ----* Transactions Details *----");
			fw.write("\n");
			fw.write(instance + "        +" + ac.getBalance() + "(Cr)");
			fw.write("\n");
			fw.write("                           bal(" + ac.getBalance() + ")");
			fw.write("\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			System.out.println("File Not written due to some problem");

		}
	}

	public void updatePassbook(Account ac, double amount,boolean check) {
		String instance = getDateAndTime();
		String fullname = ac.getFname() + " " + ac.getLname();
		try {
			FileWriter fw = new FileWriter(
					"E:\\CJC Workspace\\Class Java workspace\\BankManagementSystem\\src\\Accounts\\" + fullname, true);
			// FileWriter fw = new FileWriter(fullname,true);
			fw.append("\n");
			if(check) {
			fw.append(instance + "        +" + amount + "(Cr)");
			}else {
			fw.append(instance + "         -" + amount + "(D)");	
			}
			fw.append("\n");
			fw.append("                           bal(" + ac.getBalance() + ")");
			fw.append("\n");
			fw.flush();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePassbok(Account ac, Account accBenificiary, double transfer) {
		String instance = getDateAndTime();
		String afullname = ac.getFname() + " " + ac.getLname();
		String bfullname = accBenificiary.getFname() + " " + accBenificiary.getLname();

		try {
			FileWriter fw = new FileWriter(
					"E:\\CJC Workspace\\Class Java workspace\\BankManagementSystem\\src\\Accounts\\" + afullname, true);
//			FileWriter fw = new FileWriter( afullname,true);
			fw.append("\n");
			fw.append(instance + "         -" + transfer + "(D)");
			fw.append("\n");
			fw.append("Transferred to " + bfullname + "(" + accBenificiary.getAccNo() + ")");
			fw.append("\n");
			fw.append("                           bal(" + ac.getBalance() + ")");
			fw.append("\n");
			fw.flush();
			fw.close();

			FileWriter fw1 = new FileWriter(
					"E:\\CJC Workspace\\Class Java workspace\\BankManagementSystem\\src\\Accounts\\"
							+ bfullname,
					true);
//			FileWriter fw1 = new FileWriter(bfullname, true);
			fw1.append("\n");
			fw1.append(instance + "        +" + transfer + "(Cr)");
			fw1.append("\n");
			fw1.append("Transferred from " + afullname + "(" + ac.getAccNo() + ")");
			fw1.append("\n");
			fw1.append("                           bal(" + accBenificiary.getBalance() + ")");
			fw1.append("\n");
			fw1.flush();
			fw1.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Account compareAccNo() {

		String inAccNo = null;
		boolean match = false;
		Account ac = null;
		while (!match) {
			System.out.print("Enter Account Number : ");
			inAccNo = sc.next();
			Set<String> keys = accMap.keySet();
			for (String key : keys) {
				ac = accMap.get(key);
				if (inAccNo.equals(ac.getAccNo())) {
					match = true;
					return ac;
				}
			}
			if (!match) {
				System.out.println("No Account Found with this number,Please Try Again...");
			}
		}
		return ac;

	}

	public String getDateAndTime() {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String instance = date.format(format);
		return instance;
	}
}
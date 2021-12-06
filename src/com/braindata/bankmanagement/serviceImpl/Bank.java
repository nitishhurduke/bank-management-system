package com.braindata.bankmanagement.serviceImpl;

import com.braindata.bankmanagement.service.Rbi;
import com.braindata.bankmanagement.Database.Dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Bank implements Rbi {

	Scanner sc = new Scanner(System.in);
//	public Map<String, Account> accMap = new HashMap<>();
	public int totalAccounts = 0;

	// Instance of Account Class ==> Holds information of Customer.

	public void mainMenu() {

		System.out.println(" ----* MAIN MENU *----");
		System.out.println("Choose from Following operations..");
		System.out.println("\n 1.Create Account");
		System.out.println(" 2.Display Account Details");
		System.out.println(" 3.Check Balance");
		System.out.println(" 4.Deposit Money");
		System.out.println(" 5.Withdraw Money");
		System.out.println(" 6.Transfer Money");
		System.out.println(" 7.Delete Account");
		System.out.println(" 0.Exit");

		String input = sc.next();

		switch (input) {
		case "1":
			System.out.println("------------------------");
			System.out.println("----* REGISTRATION *----");
			createAccount();
			break;

		case "2":
			if (totalAccounts > 0) {
				System.out.println("---------------------------");
				displayAllDetails();
				break;
			} else {
				System.out.println("No Account Found..");
				System.out.println("Create an Account First to Use this Operation");
				mainMenu();
				break;
			}

		case "3":

			if (totalAccounts > 0) {
				System.out.println("-------------------------");
				checkBalance();
				break;
			} else {
				System.out.println("No Account Found..");
				System.out.println("Create an Account First to Use this Operation");
				mainMenu();
				break;
			}

		case "4":
			if (totalAccounts > 0) {
				System.out.println("-------------------------");
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
			if (totalAccounts > 0) {
				System.out.println("--------------------------");
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
			if (totalAccounts > 1) {
				System.out.println("--------------------------");
				System.out.println("----* TRANSFER MONEY *----");
				transfer();
				break;
			} else {
				System.out.println("Atleast TWO Accounts needed to use this operation..");
				mainMenu();
				break;
			}
		case "7":
			if (totalAccounts > 0) {
				System.out.println("--------------------------");
				System.out.println("----* DELETE ACCOUNT *----");
				deleteAccount();
				break;
			} else {
				System.out.println("No Account Found to Delete..");
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

		CreateAccount ca = new CreateAccount();// Instance of CreateAccount Class ==> Holds all methods to Set Customer
		// Information.

		/* Code to set Name */
		String fname = ca.setFname();
		String lname = ca.setLname();
		String fullname = fname + " " + lname;

		/* Code to set and check if Age is Equal or Greater than 18 */
		String age = ca.setAge();

		/* Code to set Mobile Number */
		String mob = ca.setMobNo();

		/* Code to set Aadhaar Number */
		String adhar = ca.setAdhar();

		/* Code to set Gender */
		String gender = ca.setGender();

		/* Code to get and Check if Initial Deposit is Greater than or equal 1000 rs. */
		Double balance = ca.initialDeposit();

		/* Code to Generate Account Number */
		String acNo = ca.generateAccNo();

		/* Getting Date And Time instance */
		String instance = getDateAndTime();

		/* pushing Account details into Customers data Table in Database */
		pushAccountIntoDatabase(acNo, fname, lname, mob, adhar, gender, age, balance);
		recordTransaction(acNo, balance, true, instance, balance);

		/*
		 * Building Passbook and Initializing Transactions' Table of Customer in
		 * Database
		 */
		initializePassbook(acNo, fullname, mob, adhar, gender, age, balance, instance);

		totalAccounts++;

		System.out.println("\nCongratulations!! Account successfully created.....");
		System.out.println("\nYOUR ACCOUNT NUMBER IS : " + acNo
				+ " Please use your Account Number to experience the best of our Banking Features");
		System.out.println("-------------------------------------------");

		subMenu();
	}

	public void displayAllDetails() {

		try {
			ResultSet rs = getAccountData();
			rs.next();
			System.out.println("----* ACCOUNT DETAILS *----");
			System.out.println("Customer Account Number: " + rs.getString("accNo"));
			System.out.println("Customer Name          : " + rs.getString("fname") + " " + rs.getString("lname"));
			System.out.println("Mobile Number          : " + rs.getString("mobNO"));
			System.out.println("Aadhaar Number         : " + rs.getString("adharno"));
			System.out.println("Gender                 : " + rs.getString("gender"));
			System.out.println("Age                    : " + rs.getString("age") + " years");
			System.out.println("Current balance        : " + rs.getDouble("balance"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("-------------------------------------------");
		subMenu();
	}

	public void depositMoney() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String fullname = null;
		double balance = 0;
		String acNo = null;
		try {
			ResultSet rs = getAccountData();
			rs.next();
			fullname = rs.getString("fname") + " " + rs.getString("lname");
			balance = rs.getDouble("balance");
			acNo = rs.getString("accNo");

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("Account of " + fullname + "(" + acNo + ")");
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
					balance = balance + deposit;
					String instance = getDateAndTime();
					boolean check = true;

					recordTransaction(acNo, balance, check, instance, deposit);
					updateDatabase(acNo, balance);
					updatePassbook(fullname, balance, deposit, check, instance);

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

		ResultSet rs = getAccountData();
		String fullname = null;
		String acNo = null;
		double balance = 0;

		try {
			rs.next();
			fullname = rs.getString("fname") + " " + rs.getString("lname");
			acNo = rs.getString("accNo");
			balance = rs.getDouble("balance");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		System.out.println("Account of " + fullname + "(" + acNo + ")");
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
								String instance = getDateAndTime();
								boolean check = false;

								recordTransaction(acNo, newBalance, check, instance, withdraw);
								updatePassbook(fullname, newBalance, withdraw, check, instance);
								updateDatabase(acNo, newBalance);

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

		ResultSet rs = getAccountData();
		String acNo = null;
		double balance = 0;
		try {
			rs.next();
			acNo = rs.getString(1);
			balance = rs.getDouble(8);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("----* BALANCE CHECK *----");
		System.out.println("Account Number: " + acNo);
		System.out.println("Balance Amount in your account : " + balance);
		System.out.println("-------------------------------------------");

		subMenu();
	}

	public void transfer() {

//		--------------------- Getting User Account 
		System.out.print("From(Your)      --> ");
		ResultSet rsAc = getAccountData();
		String acNo = null;
		String fullname = null;
		double balanceAc = 0;
		try {
			rsAc.next();
			acNo = rsAc.getString("accNo");
			fullname = rsAc.getString("fname") + " " + rsAc.getString("lname");
			balanceAc = rsAc.getDouble("balance");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("Your Account : " + fullname + "(" + acNo + ")");

//		----------------------- Getting Beneficiary Account
		System.out.print("To(Benificiary) --> ");
		ResultSet rsBen = getAccountData();
		String acNoBen = null;
		String fullnameBen = null;
		double balanceBen = 0;
		try {
			rsBen.next();
			acNoBen = rsBen.getString("accNo");
			fullnameBen = rsBen.getString("fname") + " " + rsBen.getString("lname");
			balanceBen = rsBen.getDouble("balance");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("Your Account : " + fullnameBen + "(" + acNoBen + ")");

//		----------------------- Confirming Transfer
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
		if (acNo != acNoBen) {
			diffaccnts = true;
		} else {
			System.out.println("Choose Different Accounts");
			transfer();
		}
		Scanner sc = new Scanner(System.in);
		double transfer = 0;
		boolean sufficientBalance = false;

		if (balanceAc > 1000) {
			sufficientBalance = true;
		} else {
			transfer();
		}

		try {
			/*
			 * Condition to Check if Current Balance is Already Greater than Minimum Balance
			 */
			System.out.print("Enter Amount to Transfer(" + fullname + "-->" + fullnameBen + "): ");
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
							if (balanceAc >= transfer) {
								double newBalanceAc = balanceAc - transfer;

								/*
								 * Condition to ensure New Balance after transfer must be Greater than Minimum
								 * Balance
								 */
								if (newBalanceAc >= 1000) {
									double newBalanceBen = balanceBen + transfer;
									String instance = getDateAndTime();

									/* Database & passBook update */
									recordTransaction(acNo, newBalanceAc, false, instance, transfer);
									recordTransaction(acNoBen, newBalanceBen, true, instance, transfer);
									updatePassbok(acNo, acNoBen, fullname, fullnameBen, newBalanceAc, newBalanceBen,
											transfer, instance);
									updateDatabase(acNoBen, newBalanceBen);
									updateDatabase(acNo, newBalanceAc);

									System.out.println("Amount " + transfer + " transferred Successfully....");
									System.out.println("-------------------------------------------");
								} else {
									System.out.println("\nFailed to Transfer...you can NOT transfer '" + transfer
											+ "' from your current balance '" + balanceAc + " rs.' ");
									System.out.println("You must Maintain MINIMUM BALANCE '1000 rs.'");
									System.out.println("-------------------------------------------");
								}

							} else {
								System.out.println("Insufficent Balance in your account...Try Again");
								System.out.println("Your Current Balance : " + balanceAc);
								System.out.println("-------------------------------------------");

							}

						} else {
							System.out.println(
									"Failed to Transfer...Your current balance is already at MINIMUM BALANCE aproved ");
							System.out.println("*Your Current Balance : " + balanceAc + " rs.");
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

	public void deleteAccount() {

		ResultSet rs = getAccountData();
		String fullname = null;
		String acNo = null;
		try {
			rs.next();
			fullname = rs.getString("fname") + " " + rs.getString("lname");
			acNo = rs.getString("accno");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		System.out.println("Account Of : " + fullname + "(" + acNo + ")");
		System.out.println(
				"WARNING: This action is Non-reversible,Once deleted, All the data associated with this account will be removed Permenantly");
		System.out.println("Confirm by entering '1' to Continue or press '5' to Change Account or '0' to Main Menu");
		boolean confirm = false;
		String ch = sc.next();
		switch (ch) {
		case "1":
			confirm = true;
			break;
		case "5":
			deleteAccount();
			break;
		case "0":
			mainMenu();
			break;
		default:
			System.out.println("Invalid Input..Try Again...");
			deleteAccount();
		}
		if (confirm) {
			totalAccounts--;
			try {
				CallableStatement cs = Dao.getCon().prepareCall("{call deleteAccount(?) }");
				cs.setString(1, acNo);
				cs.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("Account Deleted SuccessFully...");
		}
		subMenu();
	}

	public void pushAccountIntoDatabase(String acNo, String fname, String lname, String mob, String adhar,
			String gender, String age, double balance) {
		/* Insert into Using Prepared Statement */
		try {
			Connection con = Dao.getCon();
			String query = "INSERT INTO customer_data VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, acNo);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.setString(4, mob);
			ps.setString(5, adhar);
			ps.setString(6, gender);
			ps.setString(7, age);
			ps.setDouble(8, balance);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateDatabase(String acNo, Double balance) {

		String query = "update customer_data set balance = " + balance + " where accNo = " + acNo;
		Dao.execute(query);
	}

	public void initializeDataBase() {

		try {

			CallableStatement cs = Dao.getCon().prepareCall("{call getAllData()}");
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				totalAccounts++;
			}
		} catch (SQLException e) {
			System.out.println("Problem while retrieving data");
		}
	}

	public void initializePassbook(String acNo, String fullname, String mob, String adhar, String gender, String age,
			double balance, String instance) {
		/* Date and Time part */

		File passbook = new File(
				"E:\\CJC Workspace\\Class Java workspace\\BankManagementSystem\\src\\Accounts\\" + fullname + ".txt");
//		 File passbook = new File("\\user.dir\\Accounts\\"+fullname+".txt");

		try {
			passbook.createNewFile();
			FileWriter fw = new FileWriter(passbook);
			fw.write("\n");
			fw.write("          ***** PASSBOOK *****");
			fw.write("\n");
			fw.write("\n");
			fw.write("      ----* Account Details *----");
			fw.write("\n");
			fw.write("\n");
			fw.write(" * Account Number      : " + acNo);
			fw.write("\n");
			fw.write(" * Account Holders Name: " + fullname);
			fw.write("\n");
			fw.write(" * Age                 : " + age);
			fw.write("\n");
			fw.write(" * Gender              : " + gender);
			fw.write("\n");
			fw.write(" * Mobile Number       : " + mob);
			fw.write("\n");
			fw.write(" * Aadhaar Number      : " + adhar);
			fw.write("\n");
			fw.write("\n");
			fw.write("---------------------------------------");
			fw.write("\n");
			fw.write("\n");
			fw.write("    ----* Transactions Details *----");
			fw.write("\n");
			fw.write("\n");
			fw.write(instance + "        +" + balance + "(Cr)");
			fw.write("\n");
			fw.write("                           bal(" + balance + ")");
			fw.write("\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			System.out.println("File Not written due to some problem");

		}
	}

	public void recordTransaction(String acNo, Double balance, boolean credit, String instance, double amount) {
		// Insert into transactions (accNo,instance, txnType, amount,
		// balance) values (45685475854,'1994-08-28 12:10:50','Debit',5000,50000);

		String type = null;
		if (credit) {
			type = "Credit";
		} else {
			type = "Debit";
		}
		String query = "Insert Into transactions(accNo,txnTime, txnType, amount, balance) values ('" + acNo + "','"
				+ instance + "','" + type + "'," + amount + "," + balance + ")";
		Dao.execute(query);
	}

	public void updatePassbook(String fullname, Double balance, double amount, boolean check, String instance) {

		try {
			FileWriter fw = new FileWriter(
					"E:\\CJC Workspace\\Class Java workspace\\BankManagementSystem\\src\\Accounts\\" + fullname
							+ ".txt",
					true);
//			 FileWriter fw = new FileWriter("\\user.dir\\Accounts\\"+fullname+".txt",true);
			fw.append("\n");
			if (check) { // Deposit Transaction
				fw.append(instance + "        +" + amount + "(Cr)");
			} else {// Withdraw Transaction
				fw.append(instance + "         -" + amount + "(D)");
			}
			fw.append("\n");
			fw.append("                           bal(" + balance + ")");
			fw.append("\n"); 
			fw.flush();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updatePassbok(String acNo, String acNoBen, String afullname, String bfullname, double newBalAc,
			double newBalBen, double transfer, String instance) {


		try {
			FileWriter fw = new FileWriter(
					"E:\\CJC Workspace\\Class Java workspace\\BankManagementSystem\\src\\Accounts\\" + afullname
							+ ".txt",
					true);
//			FileWriter fw = new FileWriter( "\\user.dir\\Accounts\\"+afullname+".txt",true);
			fw.append("\n");
			fw.append(instance + "         -" + transfer + "(D)");
			fw.append("\n");
			fw.append("Transferred to " + bfullname + "(" + acNoBen + ")");
			fw.append("\n");
			fw.append("                           bal(" + newBalAc + ")");
			fw.append("\n");
			fw.flush();
			fw.close();

			FileWriter fw1 = new FileWriter(
					"E:\\CJC Workspace\\Class Java workspace\\BankManagementSystem\\src\\Accounts\\" + bfullname
							+ ".txt",
					true);
//			FileWriter fw1 = new FileWriter("\\user.dir\\Accounts\\"+bfullname+".txt", true);
			fw1.append("\n");
			fw1.append(instance + "        +" + transfer + "(Cr)");
			fw1.append("\n");
			fw1.append("Transferred from " + afullname + "(" + acNo + ")");
			fw1.append("\n");
			fw1.append("                           bal(" + newBalBen + ")");
			fw1.append("\n");
			fw1.flush();
			fw1.close();

		} catch (IOException e) {
			System.out.println("Problem while writting file");
		}

	}

	public String accountNumber() {

		String inAccNo = null;
		boolean match = false;
		String acNo = null;
		System.out.print("Enter Account Number : ");
		while (!match) {

			inAccNo = sc.next();

			try {
				CallableStatement cs = Dao.getCon().prepareCall("{call getAccNo(?,?)}");
				cs.setString(1, inAccNo);
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.execute();
				acNo = cs.getString("ac");
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (acNo != null) {
				match = true;
			}
			if (!match) {
				System.out.println("No Account Found with this number,Please Try Again...");
				System.out.print("Enter Correct Account Number: ");
			}
		}

		return acNo;
	}

	public ResultSet getAccountData() {

		String acNo = accountNumber();
		ResultSet rs = null;
		try {
			CallableStatement cs = Dao.getCon().prepareCall("{call getCustomerData(?)}");
			cs.setString(1, acNo);
			rs = cs.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public String getDateAndTime() {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String instance = date.format(format);
		return instance;
	}
}
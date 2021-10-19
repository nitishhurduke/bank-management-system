package com.braindata.bankmanagement.serviceImpl;

import com.braindata.bankmanagement.service.Rbi;
import com.braindata.bankmanagement.model.Account;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Random;//To Generate Account Number

public class Sbi implements Rbi {

	Scanner sc = new Scanner(System.in);
	Account ac = new Account();
	CreateAccount ca = new CreateAccount();
	Random random = new Random();
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

		String input = sc.next();

		switch (input) {
		case "1":
			if (flag == false) {
				flag = true;
				System.out.println("-------------------------------------------");
				System.out.println("  *REGISTRATION*");
				createAccount();
			} else {
				System.out.println("Account Already created...Multiple Accounts Feature Comming Soon...");
				System.out.println("-------------------------------------------");
				subMenu();
			}
			break;

		case "2":
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

		case "3":

			if (flag) {
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
			if (flag) {
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
			if (flag) {
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

		/* Code to set Name */
		ac.setFname(ca.setFname());
		ac.setLname(ca.setLname());

		/* Code to set and check if Age is Equal or Greater than 18 */
		ac.setAge(ca.setAge());

		/* Code to set Mobile Number */
		ac.setMobNo(ca.setMobNo());

		/* Code to set Adhar Number */
		ac.setAdharNo(ca.setAdhar());

		/* Code to set Gender */
		ac.setGender(ca.setGender());

		/* Code to get and Check if Initial Deposit is Greater than or equal 1000 rs. */
		ac.setBalance(ca.initialDeposit());

		System.out.println("\nCongratulations!! Account successfully created.....");

		/* Code to Generate Account Number */
		ac.setAccNo(ca.generateAccNo());

		System.out.println("\nYOUR ACCOUNT NUMBER IS : " + ac.getAccNo()
				+ " Please use your Account Number to experience best of our Banking Features");
		System.out.println("-------------------------------------------");
		subMenu();
	}

//	public void setFname() {
//		System.out.print("Enter First Name : ");
//		String fname = sc.next();
//		if (Pattern.matches("[A-Z]{1}[a-z]{1,}", fname)) {
//			ac.setFname(fname);
//		} else {
//			System.out.println("! Invalid First Name..Try Again...");
//			setFname();
//		}
//
//	}
//
//	public void setLname() {
////		Scanner na = new Scanner(System.in);
//		System.out.print("Enter Last Name : ");
//		String lname = sc.next();
//		if (Pattern.matches("[A-Z]{1}[a-z]{1,}", lname)) {
//			ac.setLname(lname);
//		} else {
//			System.out.println("! Invalid Last Name..Try Again...");
//			setLname();
//		}
//
//	}
//
//	public void setMobNo() {
//
//		/* Using regex api */
//
//		System.out.print("Enter 10 digit Mobile Number : ");
//		String mobile = sc.next();
//
//		if (Pattern.matches("[7-9]{1}[0-9]{9}", mobile)) {
//			ac.setMobNo(mobile);
//		} else {
//			System.out.println("Invalid Mobile Number Try Again...");
//			setMobNo();
//		}
//
//		/* Using Exception Handling */
//		// long mobile = 0;
//		// Scanner mob = new Scanner(System.in);
//		// System.out.print("Enter 10 digit Mobile Number : ");
//		// try {
//		// mobile = mob.nextLong();
//		// } catch (Exception e) {
//		// System.out.println("Invalid Mobile no... Try again...");
//		// } finally {
//		// if (mobile != 0) {
//		// if (mobile >= 000000000 && mobile <= 9999999999l) {
//		// ac.setMobNo(mobile);
//		// } else {
//		// System.out.println("Mobile Number MUST be in 10 digits...Try Again...");
//		// setMobNo();
//		// }
//		//
//		// } else {
//		// setMobNo();
//		// }
//		//
//		// }
//	}
//
//	public void setAge() {
//
//		/* Using regex api */
//
//		System.out.print("Enter Age : ");
//		String age = sc.next();
//
//		if (Pattern.matches("[1-9]{1,3}", age)) {
//			ac.setAge(age);
//		} else {
//			System.out.println("Invalid Age Try Again...");
//			setAge();
//		}
//
////		Scanner ag = new Scanner(System.in);
////		int age = 0;
////		System.out.print("Enter Age: ");
////		try {
////			age = ag.nextInt();
////		} catch (Exception e) {
////			System.out.println("Invalid Age !! Try again... ");
////		} finally {
////			if (age != 0) {
////				if (age >= 18) {
////					ac.setAge(age);
////
////				} else {
////
////					System.out.println(
////							"You are not Eligible to Create a Bank Account(Eligibility age to create a bank account is 18 or more)");
////					System.exit(0);
////				}
////			} else {
////				setAge();
////			}
////
////		}
//	}
//
//	public void setAdhar() {
//		/* Using regex api */
//
//		System.out.print("Enter 12 digit Aadhaar Number : ");
//		String adhar = sc.next();
//
//		if (Pattern.matches("[1-9]{1}[0-9]{11}", adhar)) {
//			ac.setAdharNo(adhar);
//		} else {
//			System.out.println("Invalid Aadhaar Number Try Again...");
//			setAdhar();
//		}
//
////		long adhar = 0;
////		Scanner ad = new Scanner(System.in);
////		System.out.print("Enter 12 digit Aadhaar Number : ");
////		try {
////			adhar = ad.nextLong();
////		} catch (Exception e) {
////			System.out.println("Invalid Aadhaar no... Try again...");
////		} finally {
////			if (adhar != 0) {
////				if (adhar >= 00000000000l && adhar <= 999999999999l) {
////					ac.setAdharNo(adhar);
////				} else {
////					System.out.println("Aadhaar Number MUST be in 12 digits...Try Again...");
////					setAdhar();
////				}
////
////			} else {
////				setAdhar();
////			}
////
////		}
//
//	}
//
//	public void setGender() {
//
//		Scanner gen = new Scanner(System.in);
//		byte input = 117;
//
//		System.out.println("Select Gender from below -");
//		System.out.println("  1.Male");
//		System.out.println("  2.Female");
//		System.out.println("  3.Other");
//
//		try {
//			input = gen.nextByte();
//		} catch (Exception e) {
//			System.out.println("! Invalid Entry...");
//		} finally {
//			if (input != 117) {
//				switch (input) {
//				case 1:
//					ac.setGender("Male");
//					System.out.println("Selected Gender : " + ac.getGender());
//					break;
//				case 2:
//					ac.setGender("Female");
//					System.out.println("Selected Gender : " + ac.getGender());
//					break;
//				case 3:
//					ac.setGender("Other");
//					System.out.println("Selected Gender : " + ac.getGender());
//					break;
//				default:
//					System.out.println("! Choose from given option Only..");
//					setGender();
//					break;
//				}
//			} else {
//				setGender();
//			}
//		}
//
//	}
//
//	public void initialDeposit() {
//		Scanner dep = new Scanner(System.in);
//		double iniDep = 0;
//		System.out.print("Enter amount to deposit initially: ");
//		try {
//			iniDep = dep.nextDouble();
//		} catch (Exception e) {
//			System.out.println("! Invalid Entry...");
//		} finally {
//			if (iniDep != 0) {
//				if (iniDep < 1000) {
//					System.out.println(" !!! To start banking you MUST deposit at least 1000 rs. ");
//					System.out.println("Please try again... ");
//					initialDeposit();
//				} else {
//					ac.setBalance(iniDep);
//				}
//			} else {
//				initialDeposit();
//			}
//		}
//
//	}
//
//	public void generateAccNo() {
//		int min = 1000;
//		int max = 9999;
//		int accNo1 = (int) (Math.random() * (max - min + 1) + min);
//		String acc = Integer.toString(accNo1);
//		/* Or */
////		int accNo = random.nextInt(9999);
//
//		ac.setAccNo(acc);

//}

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
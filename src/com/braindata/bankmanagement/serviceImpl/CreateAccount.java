package com.braindata.bankmanagement.serviceImpl;

import java.util.Scanner;
import java.util.regex.Pattern;

import com.braindata.bankmanagement.model.Account;

public class CreateAccount {

	Scanner sc = new Scanner(System.in);

	public String setFname() {

		String fname = null;
		System.out.print("Enter First Name : ");

		boolean match = true;

		while (match) {
			fname = sc.next();
			if (Pattern.matches("[A-Z]{1}[a-z]{1,}", fname)) {
				match = false;
			} else {
				System.out.println("Invalid First Name(ONLY First letter MUST Capital) Try Again..");
				System.out.print("Enter a valid First Name: ");
			}

		}

		return fname;
	}

	public String setLname() {

		String lname = null;
		System.out.print("Enter Last Name : ");

		boolean match = true;

		while (match) {
			lname = sc.next();
			if (Pattern.matches("[A-Z]{1}[a-z]{1,}", lname)) {
				match = false;
			} else {
				System.out.println("Invalid Last Name(ONLY First letter MUST Capital) Try Again..");
				System.out.print("Enter a valid Last Name: ");
			}

		}
		return lname;
	}

	public String setMobNo() {

		/* Using regex api */

		String mobile = null;
		System.out.print("Enter 10 digit Mobile Number : ");
		boolean match = true;
		while (match) {
			mobile = sc.next();
			if (Pattern.matches("[7-9]{1}[0-9]{9}", mobile)) {
				match = false;
			} else {
				System.out.println(
						"Invalid Mobile Number(Mobile number MUST be of 10 digits and ONLY starting with 7 or 8 or 9) Try Again...");
				System.out.print("Enter a valid Mobile Number : ");
			}

		}
		return mobile;
	}

	/* Using Exception Handling */
	// long mobile = 0;
	// Scanner mob = new Scanner(System.in);
	// System.out.print("Enter 10 digit Mobile Number : ");
	// try {
	// mobile = mob.nextLong();
	// } catch (Exception e) {
	// System.out.println("Invalid Mobile no... Try again...");
	// } finally {
	// if (mobile != 0) {
	// if (mobile >= 000000000 && mobile <= 9999999999l) {
	// ac.setMobNo(mobile);
	// } else {
	// System.out.println("Mobile Number MUST be in 10 digits...Try Again...");
	// setMobNo();
	// }
	//
	// } else {
	// setMobNo();
	// }
	//
	// }

	public String setAge() {
		/* Using regex api */

		String age = null;
		boolean match = true;
		System.out.print("Enter Age : ");
		while (match) {
			age = sc.next();
			if (Pattern.matches("[1-9]{1,3}", age)) {
				match = false;
			} else {
				System.out.println("Invalid Age(Age must be Max. 3 digits) Try Again...");
				System.out.print("Enter a valid Age Number : ");
			}
		}
		return age;
	}
//		Scanner ag = new Scanner(System.in);
//		int age = 0;
//		System.out.print("Enter Age: ");
//		try {
//			age = ag.nextInt();
//		} catch (Exception e) {
//			System.out.println("Invalid Age !! Try again... ");
//		} finally {
//			if (age != 0) {
//				if (age >= 18) {
//					ac.setAge(age);
//
//				} else {
//
//					System.out.println(
//							"You are not Eligible to Create a Bank Account(Eligibility age to create a bank account is 18 or more)");
//					System.exit(0);
//				}
//			} else {
//				setAge();
//			}
//
//		}

	public String setAdhar() {
		/* Using regex api */
		String adhar = null;
		System.out.print("Enter 12 digit Aadhaar Number : ");

		boolean match = true;

		while (match) {
			adhar = sc.next();
			if (Pattern.matches("[1-9]{1}[0-9]{11}", adhar)) {
				match = false;
			} else {
				System.out.println("Invalid Aadhaar Number(Aadhaar number MUST be of 12 digits) Try Again...");
				System.out.print("Enter a valid Aadhaar Number : ");
			}
		}
		return adhar;
	}

//		long adhar = 0;
//		Scanner ad = new Scanner(System.in);
//		System.out.print("Enter 12 digit Aadhaar Number : ");
//		try {
//			adhar = ad.nextLong();
//		} catch (Exception e) {
//			System.out.println("Invalid Aadhaar no... Try again...");
//		} finally {
//			if (adhar != 0) {
//				if (adhar >= 00000000000l && adhar <= 999999999999l) {
//					ac.setAdharNo(adhar);
//				} else {
//					System.out.println("Aadhaar Number MUST be in 12 digits...Try Again...");
//					setAdhar();
//				}
//
//			} else {
//				setAdhar();
//			}
//
//		}

	public String setGender() {

		String gender = null;
		boolean match = true;
		System.out.println("Select Gender from below -");

		while (match) {
			System.out.println("  1.Male");
			System.out.println("  2.Female");
			System.out.println("  3.Other");

			String input = sc.next();

			switch (input) {
			case "1":
				gender = "Male";
				match = false;
				break;
			case "2":
				gender = "Female";
				match = false;
				break;
			case "3":
				gender = "Other";
				match = false;
				break;
			default:
				System.out.println("Choose from given option Only..");
				System.out.println("Select Correct Gender from below - ");
				break;
			}
		}
		return gender;
	}

	public double initialDeposit() {
		double iniDep = 0;
		boolean match = true;
		
		while (match) {
			System.out.print("Enter amount to deposit initially: ");
			String input = sc.next();
			if (Pattern.matches("[0-9]{1,}", input)) {
				iniDep = Double.parseDouble(input);
				if (iniDep < 1000) {
					System.out.println(" !!! To start banking you MUST deposit at least 1000 rs. ");
					System.out.println("Please try again... ");
				} else {
					match = false;
				}
			} else {
				System.out.println("Invalid Entry... Try again...");
			}

		}
		return iniDep;
	}

//		Scanner dep = new Scanner(System.in);
//		double iniDep = 0;
//		double input = 0;
//		System.out.print("Enter amount to deposit initially: ");
//		try {
//			input = dep.nextDouble();
//		} catch (Exception e) {
//			System.out.println("! Invalid Entry...");
//		} finally {
//			if (input != 0) {
//				if (input < 1000) {
//					System.out.println(" !!! To start banking you MUST deposit at least 1000 rs. ");
//					System.out.println("Please try again... ");
//					initialDeposit();
//				} else {
//					iniDep = input;
//				}
//			} else {
//				initialDeposit();
//			}
//			return iniDep;
//		}

	public String generateAccNo() {
		int min = 1000;
		int max = 9999;
		int accNo1 = (int) (Math.random() * (max - min + 1) + min);
		String acc = Integer.toString(accNo1);
		/* Or */
//		int accNo = random.nextInt(9999);

		return acc;

	}

}

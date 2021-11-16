package com.braindata.bankmanagement.serviceImpl;

import java.util.Scanner;
import java.util.regex.Pattern;


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

	

	public String setAge() {
		/* Using regex api */

		String age = null;
		boolean match = true;
		System.out.print("Enter Age : ");
		while (match) {
			age = sc.next();
			if (Pattern.matches("[0-9]{1,3}", age)) {
				match = false;
			} else {
				System.out.println("Invalid Age(Age must be Max. 3 digits) Try Again...");
				System.out.print("Enter a valid Age Number : ");
			}
		}
		return age;
	}


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


	public String generateAccNo() {
		long min = 10000000000l;
		long max = 99999999999l;
		long accNo1 = (long) (Math.random() * (max - min + 1) + min);
		String acc = Long.toString(accNo1);

		return acc;

	}

}

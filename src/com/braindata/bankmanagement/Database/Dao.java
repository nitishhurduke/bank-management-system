package com.braindata.bankmanagement.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
	static Connection con;
	static Statement st;
	
	public static Statement connection() {
			
		try {
			st = getCon().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			System.out.println("Problem occured while establishing connection");
		return st;
	}

	public static void execute(String query) {
		
		try {
			st = getCon().createStatement();
			st.execute(query);
		} catch (SQLException e) {
			System.out.println("Problem while excecuting Query");
		}
	}

	public static ResultSet executeQuery(String query) {
		ResultSet rs = null;
		
		try {
			st = getCon().createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Problem while executing data retieving query");
		}
		return rs;
	}
	public static Connection getCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagement", "root", "root");
		}catch (Exception e) {
			e.printStackTrace();
		}
			 return con;
	}

}

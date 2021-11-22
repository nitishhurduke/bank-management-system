package com.braindata.bankmanagement.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {

	public static Statement connection() {
		Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagement", "root", "root");
			st = con.createStatement();
		} catch (Exception e) {
			System.out.println("Problem occured while establishing connection");
		}
		return st;
	}

	public static void execute(String query) {
		Statement st = connection();
		try {
			st.execute(query);
		} catch (SQLException e) {
			System.out.println("Problem while excecuting Query");
		}
	}

	public static ResultSet executeQuery(String query) {
		ResultSet rs = null;
		Statement st = connection();
		try {
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Problem while executing data retieving query");
		}
		return rs;
	}

}

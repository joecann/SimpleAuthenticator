package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
	
	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","JoeCoventry01");
		return con;
		
	}
	
	public static String getPassword(String email) {
		StringBuffer pw = new StringBuffer();
		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			String query = "SELECT * FROM test.database WHERE email = '" + email + "'";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				pw.append(rs.getString("hash"));
			}
			//ResultSet to return full DB
			return pw.toString();
		} 
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}		
		
		return null;
	}
	
	public static String getSalt(String email) {
		StringBuffer pw = new StringBuffer();
		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			String query = "SELECT * FROM test.database WHERE email = '" + email + "'";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				pw.append(rs.getString("salt"));
			}
			//ResultSet to return full DB
			return pw.toString();
		} 
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}		
		
		return null;
	}
	
	public static boolean checkEmail(String email) {
		try {
			Connection con = getConnection();
			Statement st = con.createStatement();
			String query = "SELECT email FROM test.database";
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				if(rs.getString("email").equals(email)) return true;
			}
		} 
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}		
		
		return false;
	}
	
}

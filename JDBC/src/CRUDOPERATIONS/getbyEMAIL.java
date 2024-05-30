package CRUDOPERATIONS;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class getbyEMAIL {
	
	private static final String Driver ="com.mysql.cj.jdbc.Driver";
	
	private static final String username ="root";
	
	private static final String password ="root";
	
	private static Connection conn;
	
	private static PreparedStatement psmt;
	
	public static void main(String[] args) {
		
		Scanner scr = new Scanner(System.in);
		
		try {
			
			Class.forName(Driver);
			
			System.out.println("Enter database name");
			
		    String url ="jdbc:mysql://localhost:3306/" +scr.next();
			
			 conn = DriverManager.getConnection(url,username,password );
			 
			 System.out.println("Enter table name");
			 
			 String sql ="select * from "+ scr.next()+" where email = ?" ;
			
		    psmt = conn.prepareStatement(sql);
		    
		    System.out.println("Enter email");
		    
		    psmt.setString(1,scr.next());
		    
		    ResultSet rs = psmt.executeQuery();
		    
		    while(rs.next()) {
		    	
		    	System.out.println("id:" +rs.getInt("id"));
		    	
		    	System.out.println("name:" +rs.getString("name"));
		    	
		    	System.out.println("email:" +rs.getString("email"));
		    }
			
			
			conn.close();
			
			psmt.close();
			
			scr.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}


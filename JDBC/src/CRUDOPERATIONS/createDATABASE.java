package CRUDOPERATIONS;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.util.Scanner;

public class createDATABASE {
	
	private static final String Driver ="com.mysql.cj.jdbc.Driver";
	
	private static final String url ="jdbc:mysql://localhost:3306/";
	
	private static final String username ="root";
	
	private static final String password ="root";
	
	private static Connection conn;
	
	private static PreparedStatement psmt;
	
	public static void main(String[] args) {
		
		Scanner scr = new Scanner(System.in);
		
		try {
			
			Class.forName(Driver);
			
			 conn = DriverManager.getConnection(url,username,password );
			 
			 System.out.println("Enter database name");
			 
			String sql ="create database "+ scr.next();
			
		    psmt = conn.prepareStatement(sql);
		    
			int i = psmt.executeUpdate();
			
			if(i>0) {
				
				System.out.println("Created Database Succesfully");
			}
			else {
				
				System.out.println("Error");
				
				}
			
			conn.close();
			
			psmt.close();
			
			scr.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}

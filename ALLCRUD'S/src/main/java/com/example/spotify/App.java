package com.example.spotify;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class App 
{
	
	
private static final String Driver ="com.mysql.cj.jdbc.Driver";
	
	private static final String username ="root";
	
	private static final String password ="root";
	
	private static Connection conn;
	
	private static PreparedStatement psmt;
	
    public static void main( String[] args )
    {
       Scanner scr =new Scanner(System.in);
        
       int i;
       
       do {
    	   
    	  display();
    	  
    	  System.out.println("Enter your choice");
    	  
    	  i= Integer.parseInt(scr.next());
    	  
    	  switch (i) {
    	  case 1:
			CreateDatabase();
			break;
    	  case 2:
  			DropDatabase();
  			break;
    	  case 3:
  			CreateTable();
  			break;
    	  case 4:
  			DropTable();
  			break;
    	  case 5:
  			InsertData();
  			break;
    	  case 6:
  			UpdateData();
  			break;
    	  case 7:
  			DeleteData();
  			break;
    	  case 8:
  			Getallrecords();
  			break;
    	  case 9:
  			RecordbyEmail();
  			break;
    	  case 10:
  			System.exit(0);
  			break;
  	    	default:
  	    		System.out.println("Invalid Operation");
			break;
		}
    	  
    	  
    	   
		
	} while (i>0);
       
    }

	private static void RecordbyEmail() {

        Scanner scr = new Scanner(System.in);
		
		try {
			
			Class.forName(Driver);
			
			System.out.println("Enter database name");
			
			String url = "jdbc:mysql://localhost:3306/"+scr.next();
			
			conn = DriverManager.getConnection(url, username, password);
			
			String sql = "select * from "+ scr.next()+ "where email = ?";
			
			psmt = conn.prepareStatement(sql);
			
			ResultSet rs = psmt.executeQuery();
			
			System.out.println("Enter email");
			
			psmt.setString(1, scr.next());
			
			 while(rs.next()) {
				 
				 System.out.println("id:"+ scr.nextInt());
				 System.out.println("name:"+ scr.next());
				 System.out.println("email:"+ scr.next());
			 }
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	}
		
	

	private static void Getallrecords() {
		
          Scanner scr = new Scanner(System.in);
		
		try {
			
			Class.forName(Driver);
			
			System.out.println("Enter database name");
			
			String url = "jdbc:mysql://localhost:3306/"+scr.next();
			
			conn = DriverManager.getConnection(url, username, password);
			
			System.out.println("Enter table name");
			
			String sql = "select * from "+ scr.next();
			
			psmt = conn.prepareStatement(sql);
			
			ResultSet rs = psmt.executeQuery();
			
			while (rs.next()) {
				System.out.println("id:"+ rs.getInt("id")+ 
						" name"+rs.getString("name")+
						" email"+rs.getString("email"));
				} 
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	}

	private static void DeleteData() {
		
		
		Scanner scr = new Scanner(System.in);
		
		try {
			
			Class.forName(Driver);
			
			System.out.println("Enter database name");
			
			String url = "jdbc:mysql://localhost:3306/"+scr.next();
			
			conn = DriverManager.getConnection(url, username, password);
			
			String sql = "delete from "+ scr.next();
			
			psmt = conn.prepareStatement(sql);
			
			int i = psmt.executeUpdate();
			
			if(i==0) {
				
				System.out.println("Dropped table successfully");
			}else {
				
				System.out.println("Error");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	private static void UpdateData() {
		 
		Scanner scr = new Scanner(System.in);
		
		System.out.println("Enter database name");
		
		String url = "jdbc:mysql://localhost:3306/"+ scr.next();
		
		try {
			
			Class.forName(Driver);
			
			conn = DriverManager.getConnection(url, username, password);
			
			System.out.println("Enter table name");
			
			String sql = "update " +scr.next() + " set name = ?, email = ? where id = ?";
			
			psmt = conn.prepareStatement(sql);
			
			System.out.println("Enter name:");
			
			psmt.setString(1, scr.next());
			
			System.out.println("Enter email:");
			
			psmt.setString(2, scr.next());
			
			System.out.println("Enter id:");
			
			psmt.setInt(3, scr.nextInt());
			
			int i = psmt.executeUpdate();
			
			if(i>0) {
				
				System.out.println("Updated succesfully");
			}
			else {
				
				System.out.println("Error");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	private static void InsertData() {
		
       Scanner scr = new Scanner(System.in);
       
       System.out.println("Enter database name");
		
		String url = "jdbc:mysql://localhost:3306/"+ scr.next();


		
		try {
			
			Class.forName(Driver);
			
			 conn = DriverManager.getConnection(url,username,password );
			 
			 System.out.println("Enter table name");
			 
			 String sql ="insert into "+ scr.next() + " (id,name,email) values (?,?,?)";
			
		    psmt = conn.prepareStatement(sql);
		    
		    System.out.println("Enter id");
		    
		    psmt.setInt(1,scr.nextInt());
		    
		    System.out.println("Enter name");
		    
		    psmt.setString(2, scr.next());
		    
		    System.out.println("Enter email");
		    
		    psmt.setString(3, scr.next());	
		    
		    
			int i = psmt.executeUpdate();
			
			if(i>0) {
				
				System.out.println("Inserted data Succesfully");
			}
			else {
				
				System.out.println("Error");
				
				}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
		
	

	private static void DropTable() {
		
Scanner scr = new Scanner(System.in);
		
		System.out.println("Enter database name");
		
		String url = "jdbc:mysql://localhost:3306/"+ scr.next();
		
		try {
			
			Class.forName(Driver);
			
			conn = DriverManager.getConnection(url, username, password);
			
			System.out.println("Enter table name");
			
			String sql = "drop table "+ scr.next() ;
			
			psmt = conn.prepareStatement(sql);
			
			 int i = psmt.executeUpdate();
			
			 if(i==0) {
				 
				 System.out.println("Dropped table successfully");
			 }else {
				 
				 System.out.println("Error");
			 }
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	private static void CreateTable() {
		
		Scanner scr = new Scanner(System.in);
		
		System.out.println("Enter database name");
		
		String url = "jdbc:mysql://localhost:3306/"+ scr.next();
		
		try {
			
			Class.forName(Driver);
			
			conn = DriverManager.getConnection(url, username, password);
			
			System.out.println("Enter table name");
			
			String sql ="create table "+ scr.next() + "(id int,name varchar(20),email varchar(30))";
			
			
			psmt = conn.prepareStatement(sql);
			
			 int i = psmt.executeUpdate();
			
			 if(i==0) {
				 
				 System.out.println("Created table successfully");
			 }else {
				 
				 System.out.println("Error");
			 }
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	private static void DropDatabase() {
		Scanner scr = new Scanner(System.in);
        
        String url ="jdbc:mysql://localhost:3306/";
		
		try {
			
			Class.forName(Driver);
			
			 conn = DriverManager.getConnection(url,username,password );
			 
			 System.out.println("Enter database name");
			 
			String sql ="drop database "+ scr.next();
			
		    psmt = conn.prepareStatement(sql);
		    
			int i = psmt.executeUpdate();
			
			if(i==0) {
				
				System.out.println("Dropped Database Succesfully");
			}
			else {
				
				System.out.println("Error");
				
				}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}

	private static void CreateDatabase() {
		
        Scanner scr = new Scanner(System.in);
        
        String url ="jdbc:mysql://localhost:3306/";
		
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
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}


		
	

	private static void display() {
		
		System.out.println("****************************");
		System.out.println("\t1. Create Database");
		System.out.println("\t2. Drop Database");
		System.out.println("\t3. Create Table");
		System.out.println("\t4. Drop Table");
		System.out.println("\t5. Insert Data");
		System.out.println("\t6. Update Data");
		System.out.println("\t7. Delete Data");
		System.out.println("\t8. Get all records");
		System.out.println("\t9. Record by Email");
		System.out.println("\t10. Exit");
		System.out.println("****************************");
		
	}
}

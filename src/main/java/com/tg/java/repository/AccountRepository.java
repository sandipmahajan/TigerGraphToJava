package com.tg.java.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Repository;

import com.tg.java.config.ConfigParameters;
import com.tg.java.config.Driver;
import com.tg.java.model.Account;

@Repository
public class AccountRepository {
	
	public List<Account> getAccountByName(String FirstName) {
		
		List<Account> list = new ArrayList<Account>();	
		
		String url = ConfigParameters.getTigerGraphURL(); 
		Properties properties = ConfigParameters.getProperties();
		
		try{
	    	Driver driver = new Driver();
	    	
	    	 try (Connection con = driver.connect(url, properties)) {	    		 
	    		 String query = "get vertex(Account) params(filter=?)";
	    	        System.out.println("Running \"get vertex(Account) params(filter=?)\"...");
	    	        try (java.sql.PreparedStatement pstmt = con.prepareStatement(query)) {
	    	          pstmt.setString(1, "FirstName=\""+FirstName+"\"");
	    	          System.out.println("Query : "+"FirstName=\""+FirstName+"\"");
	    	          try (java.sql.ResultSet rs = pstmt.executeQuery()) {
	    	            do {
	    	            	String account = null;
		    	              java.sql.ResultSetMetaData metaData = rs.getMetaData();	    	              
		    	              while (rs.next()) {
		    	               account = String.valueOf(rs.getObject(1));
		    	                for (int i = 2; i <= metaData.getColumnCount(); ++i) {
		    	                  Object obj = rs.getObject(i);
		    	                  account =account + "^" + String.valueOf(obj);
		    	                }
		    	                list.add(Account.build(account));
		    	                System.out.println(account);
		    	              }
		    	              System.out.println(account);
	    	            } while (!rs.isLast());
	    	          }
	    	        } catch (SQLException e) {
	    	          System.out.println( "Failed to createStatement: " + e);
	    	        }
	    		 
	    	 }catch (SQLException e) {
	             System.out.println( "Failed to getConnection: " + e);
	         }	    	
	    	
	    }catch (SQLException e) {
	        System.out.println( "Failed to getConnection: " + e);
	    }		
		return list;		
	}
	
	public List<Account> getAllAccount() {
		
		List<Account> list = new ArrayList<Account>();
		
		String url = ConfigParameters.getTigerGraphURL(); 
		Properties properties = ConfigParameters.getProperties();
		
		try{
	    	Driver driver = new Driver();
	    	
	    	 try (Connection con = driver.connect(url, properties)) {	    		 
	    		 String query = "get vertex(Account)";
	    	        System.out.println("Running \"get vertex(Account)\"...");
	    	        try (java.sql.PreparedStatement pstmt = con.prepareStatement(query)) {
	    	          try (java.sql.ResultSet rs = pstmt.executeQuery()) {
	    	            do {
	    	            	String account = null;
	    	              java.sql.ResultSetMetaData metaData = rs.getMetaData();	    	              
	    	              while (rs.next()) {
	    	               account = String.valueOf(rs.getObject(1));
	    	                for (int i = 2; i <= metaData.getColumnCount(); ++i) {
	    	                  Object obj = rs.getObject(i);
	    	                  account =account + "^" + String.valueOf(obj);
	    	                }
	    	                list.add(Account.build(account));
	    	                System.out.println(account);
	    	              }
	    	              System.out.println(account);
	    	            } while (!rs.isLast());
	    	          }
	    	        } catch (SQLException e) {
	    	          System.out.println( "Failed to createStatement: " + e);
	    	        }
	    		 
	    	 }catch (SQLException e) {
	             System.out.println( "Failed to getConnection: " + e);
	         }	    	
	    	
	    }catch (SQLException e) {
	        System.out.println( "Failed to getConnection: " + e);
	    }		
		return list;		
	}
}

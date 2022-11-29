package com.tg.java.config;

import java.util.Properties;

public class ConfigParameters {
	
	public static String getTigerGraphURL() {
		//Ip where TigerGraph is running
	    String ipAddr = "127.0.0.1";
	    // port of GraphStudio
	    Integer port = 14240;
	    
	    StringBuilder sb = new StringBuilder();
	    sb.append("jdbc:tg:http://").append(ipAddr).append(":").append(port);	    
	   
	    return sb.toString();
	}
	
	public static Properties getProperties() {
		Properties properties = new Properties();
		
	    properties.put("username", "tigergraph");
	    properties.put("password", "tigergraph");
	    properties.put("graph", "LinkedUp");
	   
	    //Using SSL then
		/*
		 * properties.put("trustStore", "/tmp/trust.jks");
		 * properties.put("trustStorePassword", "password");
		 * properties.put("trustStoreType", "JKS");
		 * 
		 * properties.put("keyStore", "/tmp/identity.jks");
		 * properties.put("keyStorePassword", "password");
		 * properties.put("keyStoreType", "JKS");
		 * 
		 * StringBuilder sb = new StringBuilder();
		 * sb.append("jdbc:tg:https://").append(ipAddr).append(":").append(port);
		 */
	    
	    return properties;
	}

}

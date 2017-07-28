package com.example.DataAccess.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection getConnection() throws ClassNotFoundException {
		
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SchemaDesign","postgres","bondstone");
		} catch (SQLException ex) {
			ex.getStackTrace();
		}			
		
			
		return connection;		


	}}

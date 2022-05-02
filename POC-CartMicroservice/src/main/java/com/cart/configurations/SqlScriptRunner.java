package com.cart.configurations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqlScriptRunner {

	
	@Value("${spring.datasource.url}")
	private String dbUrl;
	
	@Value("${spring.datasource.username}")
	private String dbUsername;
	
	@Value("${spring.datasource.password}")
	private String dbPassword;
	
	Logger log = LoggerFactory.getLogger(SqlScriptRunner.class);
	
	@Bean
	public void createDbTable() {
		try (
			Connection conn = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);   
			Statement stmt = conn.createStatement();
		    ) {
				String cartTable = "CREATE TABLE IF NOT EXISTS poc.carts ("
						+ "cartId BIGINT AUTO_INCREMENT PRIMARY KEY,"
						+ "customer VARCHAR(50) NOT NULL"
						+ ")";
				String toLog = "Creating table ... \n"+cartTable; //For debugging
				stmt.executeUpdate(cartTable);
				
				String cartItemsTable = "CREATE TABLE IF NOT EXISTS poc.cartitem ("
						+ "itemId BIGINT AUTO_INCREMENT PRIMARY KEY,"
						+ "cartId BIGINT,"
						+ "quantity BIGINT NOT NULL,"
						+ "subTotal DOUBLE PRECISION NOT NULL,"
						+ "productId BIGINT,"
						+ "FOREIGN KEY (`productId`) REFERENCES `product` (`productId`),"
						+ "FOREIGN KEY (`cartId`) REFERENCES `carts` (`cartId`)"
						+ ")";
				
				String toLog1 = "Creating table ... \n"+cartItemsTable; //For debugging
				
				log.info(toLog);
				log.info(toLog1);
		        stmt.executeUpdate(cartItemsTable);
		      } 
		catch(SQLException ex) {
		         ex.printStackTrace();
		} 
	}
	
}

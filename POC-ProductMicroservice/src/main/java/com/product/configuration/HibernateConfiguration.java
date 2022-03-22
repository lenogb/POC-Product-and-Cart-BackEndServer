package com.product.configuration;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {
	
	
	@Value("com.mysql.cj.jdbc.Driver")
	private String driver;
 
	@Value("${spring.datasource.password}")
	private String password;
 
	@Value("${spring.datasource.url}")
	private String url;
 
	@Value("${spring.datasource.username}")
	private String username;
 
	@Value("org.hibernate.dialect.MySQL5InnoDBDialect")
	private String dialect;
 
	@Value("true")
	private String showsql;
 
	@Value("update")
	private String ddlauto;
 
	@Value("com.product")
	private String scanpackages;
 
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
 
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(scanpackages);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", dialect);
		hibernateProperties.put("hibernate.show_sql", showsql);
		hibernateProperties.put("hibernate.hbm2ddl.auto", ddlauto);
		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}
 
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
}
 



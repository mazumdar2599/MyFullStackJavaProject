package com.app.MyBackEndProject.Config;


import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration

@EnableTransactionManagement

@ComponentScan(basePackages={"com.app.MyBackEndProject.Modal"})

public class HibernateConfig 
{
private String DATABASE_URL = "jdbc:mysql://localhost:3306/JadavpurBackEnd?useSSL=false";
private String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
private String DATABASE_DIALECT = "org.hibernate.dialect.MySQLDialect";
private String DATABASE_USERNAME = "root";
private String DATABASE_PASSWORD = "mypassword";

@Bean("dataSource")
public DataSource getDataSource() {
	BasicDataSource dataSource = new BasicDataSource();

	// Providing the database connection information
	dataSource.setDriverClassName(DATABASE_DRIVER);
	dataSource.setUrl(DATABASE_URL);
	dataSource.setUsername(DATABASE_USERNAME);
	dataSource.setPassword(DATABASE_PASSWORD);

	return dataSource;

}

private Properties getHibernateProperties() {

	Properties properties = new Properties();

	properties.put("hibernate.dialect", DATABASE_DIALECT);
	properties.put("hibernate.show_sql", "true");
	properties.put("hibernate.format_sql", "true");

	properties.put("hibernate.hbm2ddl.auto", "update");

	return properties;
}

@Bean
public SessionFactory getSessionFactory(DataSource dataSource) {

	LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);

	builder.addProperties(getHibernateProperties());
	builder.scanPackages("com.app.MyBackEndProject.Modal");

	return builder.buildSessionFactory();

}

@Bean
public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
	HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
	return transactionManager;
}

}

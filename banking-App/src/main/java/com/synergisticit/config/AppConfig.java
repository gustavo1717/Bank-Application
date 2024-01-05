package com.synergisticit.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.Address;
import com.synergisticit.domain.Branch;
import com.synergisticit.domain.Customer;
import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;

@Configuration
@PropertySource(value="classpath:db.properties")
public class AppConfig {
	@Autowired Environment env;
	
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("url"));
		dataSource.setDriverClassName(env.getProperty("driver"));
		dataSource.setUsername(env.getProperty("myusername"));
		dataSource.setPassword(env.getProperty("mypassword"));
		
		return dataSource;
	}
	
	@Bean (name ="entityManagerFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setAnnotatedClasses(User.class);
		sessionFactory.setPackagesToScan("com.synergisticit.domain");
		sessionFactory.setHibernateProperties(hibernateProerties());
		
		return sessionFactory;
	}
	
	Properties hibernateProerties() {
		Properties jpaProerties = new Properties();
		jpaProerties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		jpaProerties.setProperty("hibernate.show_SQL", "true");
		jpaProerties.setProperty("hibernate.hbm2ddl.auto", "update");
		return jpaProerties;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		return bCryptPasswordEncoder;
	}

}

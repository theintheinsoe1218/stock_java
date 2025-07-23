package com.tts.stock;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:application.properties" })
public class DatabaseConfig {

	@Autowired
	private Environment env;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		//dataSource.setMinIdle(4);
		//dataSource.setMaxActive(6);
		// dataSource.setMaxTotal(8);
		// dataSource.setMinEvictableIdleTimeMillis(0);
		// dataSource.setTimeBetweenEvictionRunsMillis(0);
		// dataSource.setNumTestsPerEvictionRun(-1);

		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.tts.stock.domain" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	private Properties hibernateProperties() {
		Properties additionalProperties = new Properties();
		additionalProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		additionalProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		additionalProperties.put("hibernate.connection.charSet", "UTF-8");
		additionalProperties.put("hibernate.connection.characterEncoding", "UTF-8");
		additionalProperties.put("hibernate.connection.useUnicode", true);
		// additionalProperties.put("hibernate.cache.use_query_cache",
		// env.getProperty("hibernate.cache.use_query_cache"));
		// additionalProperties.put("hibernate.cache.use_second_level_cache",
		// env.getProperty("hibernate.cache.use_second_level_cache"));
		// additionalProperties.put("net.sf.ehcache.configurationResourceName",env.getProperty("net.sf.ehcache.configurationResourceName"));
		// additionalProperties.put("hibernate.cache.region.factory_class",
		// env.getProperty("hibernate.cache.region.factory_class"));
		// additionalProperties.put("cache.provider_class",env.getProperty("cache.provider_class"));

		additionalProperties.put("hibernate.connection.autocommit", env.getProperty("hibernate.connection.autocommit"));
		return additionalProperties;
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

}

/**
 * 
 */
package com.athene.data;

import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.athene.data.jpa.repository.support.JpaRepositoryFactoryBean;

/**
 * @author zhaochf
 *
 */
@Configuration
@PropertySource("classpath:/jdbc.properties")
@EnableJpaRepositories(repositoryFactoryBeanClass=JpaRepositoryFactoryBean.class)
public class ApplicationConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);

	@Autowired
	private Environment environment;

	@Bean(initMethod = "init", destroyMethod = "close")
	public DataSource dataSource() {

		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDefaultReadOnly(false);
		dataSource.setDefaultAutoCommit(false);
		dataSource.setUrl(environment.getProperty("jdbc.url"));
		dataSource.setUsername(environment.getProperty("jdbc.username"));
		dataSource.setPassword(environment.getProperty("jdbc.password"));
		dataSource.setInitialSize(environment.getProperty("jdbc.initialSize", Integer.class));
		dataSource.setMinIdle(environment.getProperty("jdbc.minIdle", Integer.class));
		dataSource.setMaxActive(environment.getProperty("jdbc.maxActive", Integer.class));
		dataSource.setMaxWait(environment.getProperty("jdbc.maxWait", Integer.class));
		dataSource.setTimeBetweenEvictionRunsMillis(
				environment.getProperty("jdbc.timeBetweenEvictionRunsMillis", Integer.class));
		dataSource.setMinEvictableIdleTimeMillis(
				environment.getProperty("jdbc.minEvictableIdleTimeMillis", Integer.class));
		try {
			dataSource.addFilters(environment.getProperty("jdbc.filters"));
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.MYSQL);
		vendorAdapter.setGenerateDdl(false);
		vendorAdapter.setShowSql(true);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.athene.data");
		factory.setDataSource(dataSource());
		return factory;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}

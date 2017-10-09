/**
 * 
 */
package com.athene.sybxjr;

import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.ExtensionJpaRepositoryFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StringUtils;

import com.alibaba.druid.pool.DruidDataSource;
import com.athene.sybxjr.security.MessageProcessor;

/**
 * @author zhaochf
 *
 */
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(repositoryFactoryBeanClass = ExtensionJpaRepositoryFactoryBean.class)
@ComponentScan
@PropertySource({"classpath:/application.properties", "classpath:/jdbc.properties"})
public class ApplicationConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

	@Autowired
	private ApplicationConfigurations applicationConfigurations;
	
	@Autowired
	private JdbcConfigurations jdbcConfigurations;
	
	@Bean(initMethod = "init", destroyMethod = "close")
	public DataSource dataSource() {

		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDefaultReadOnly(false);
		dataSource.setDefaultAutoCommit(false);
		dataSource.setDriverClassName(jdbcConfigurations.getDriverClassName());
		dataSource.setUrl(jdbcConfigurations.getUrl());
		dataSource.setUsername(jdbcConfigurations.getUsername());
		dataSource.setPassword(jdbcConfigurations.getPassword());
		dataSource.setInitialSize(jdbcConfigurations.getInitialSize());
		dataSource.setMinIdle(jdbcConfigurations.getMinIdle());
		dataSource.setMaxActive(jdbcConfigurations.getMaxActive());
		dataSource.setMaxWait(jdbcConfigurations.getMaxWait());
		dataSource.setTimeBetweenEvictionRunsMillis(jdbcConfigurations.getTimeBetweenEvictionRunsMillis());
		dataSource.setMinEvictableIdleTimeMillis(jdbcConfigurations.getMinEvictableIdleTimeMillis());
		dataSource.setValidationQuery(jdbcConfigurations.getValidationQuery());
		dataSource.setTestWhileIdle(jdbcConfigurations.isTestWhileIdle());
		
		try {
			if (!StringUtils.isEmpty(jdbcConfigurations.getFilters())) {
				dataSource.setFilters(jdbcConfigurations.getFilters());
			}
			if (!StringUtils.isEmpty(jdbcConfigurations.getConnectionProperties())) {
				dataSource.setConnectionProperties(jdbcConfigurations.getConnectionProperties());
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}

		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.ORACLE);
		vendorAdapter.setGenerateDdl(false);
		vendorAdapter.setShowSql(true);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.athene");
		factory.setDataSource(dataSource());
		return factory;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
	@Bean
	public MessageProcessor messageProcessor() {
		return new MessageProcessor(applicationConfigurations.getSecretKey());
	}
	
	@Component
	public static final class ApplicationConfigurations {
		
		@Value("${application.secretKey}")
		private String secretKey;

		/**
		 * @return the secretKey
		 */
		public String getSecretKey() {
			return secretKey;
		}
		
		
	}
	
	@Component
	public static final class JdbcConfigurations {
		
		@Value("${jdbc.driverClassName}")
		private String driverClassName;
		
		@Value("${jdbc.url}")
		private String url;
		
		@Value("${jdbc.username}")
		private String username;
		
		@Value("${jdbc.password}")
		private String password;
		
		@Value("${jdbc.initialSize}")
		private int initialSize;
		
		@Value("${jdbc.minIdle}")
		private int minIdle;
		
		@Value("${jdbc.maxActive}")
		private int maxActive;
		
		@Value("${jdbc.maxWait}")
		private int maxWait;
		
		@Value("${jdbc.timeBetweenEvictionRunsMillis}")
		private int timeBetweenEvictionRunsMillis;
		
		@Value("${jdbc.minEvictableIdleTimeMillis}")
		private int minEvictableIdleTimeMillis;
		
		@Value("${jdbc.validationQuery}")
		private String validationQuery;
		
		@Value("${jdbc.testWhileIdle}")
		private boolean testWhileIdle;
		
		@Value("${jdbc.filters}")
		private String filters;
		
		@Value("${jdbc.connectionProperties}")
		private String connectionProperties;
		
		public String getDriverClassName() {
			return driverClassName;
		}

		/**
		 * @return the url
		 */
		public String getUrl() {
			return url;
		}

		/**
		 * @return the username
		 */
		public String getUsername() {
			return username;
		}

		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * @return the initialSize
		 */
		public int getInitialSize() {
			return initialSize;
		}

		/**
		 * @return the minIdle
		 */
		public int getMinIdle() {
			return minIdle;
		}

		/**
		 * @return the maxActive
		 */
		public int getMaxActive() {
			return maxActive;
		}

		/**
		 * @return the maxWait
		 */
		public int getMaxWait() {
			return maxWait;
		}

		/**
		 * @return the timeBetweenEvictionRunsMillis
		 */
		public int getTimeBetweenEvictionRunsMillis() {
			return timeBetweenEvictionRunsMillis;
		}

		/**
		 * @return the minEvictableIdleTimeMillis
		 */
		public int getMinEvictableIdleTimeMillis() {
			return minEvictableIdleTimeMillis;
		}

		/**
		 * @return the validationQuery
		 */
		public String getValidationQuery() {
			return validationQuery;
		}

		/**
		 * @param validationQuery the validationQuery to set
		 */
		public void setValidationQuery(String validationQuery) {
			this.validationQuery = validationQuery;
		}

		/**
		 * @return the testWhileIdle
		 */
		public boolean isTestWhileIdle() {
			return testWhileIdle;
		}

		/**
		 * @param testWhileIdle the testWhileIdle to set
		 */
		public void setTestWhileIdle(boolean testWhileIdle) {
			this.testWhileIdle = testWhileIdle;
		}

		/**
		 * @return the filters
		 */
		public String getFilters() {
			LOGGER.debug("The druid filters is: " + this.filters);
			return filters;
		}

		/**
		 * @return the connectionProperties
		 */
		public String getConnectionProperties() {
			LOGGER.debug("The druid connection properties is: " + this.connectionProperties);
			return connectionProperties;
		}

		/**
		 * @param connectionProperties the connectionProperties to set
		 */
		public void setConnectionProperties(String connectionProperties) {
			this.connectionProperties = connectionProperties;
		}
		
	}
}

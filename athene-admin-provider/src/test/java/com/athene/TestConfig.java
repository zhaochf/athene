/**
 * 
 */
package com.athene;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.ExtensionJpaRepositoryFactoryBean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.Assert;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author zhaochf
 *
 */
@Configuration
@EnableRedisRepositories
@EnableJpaRepositories(repositoryFactoryBeanClass = ExtensionJpaRepositoryFactoryBean.class)
@EnableCaching
@ComponentScan(basePackages="com.athene")
@PropertySource({"classpath:/jdbc.properties", "classpath:/redis.properties"})
public class TestConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestConfig.class);

	@Autowired
	private JdbcConfigurations jdbcConfigurations;
	
	@Autowired
	private RedisConfigurations redisConfigurations;

	@Bean(initMethod = "init", destroyMethod = "close")
	public DataSource dataSource() {

		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDefaultReadOnly(false);
		dataSource.setDefaultAutoCommit(false);
		dataSource.setUrl(jdbcConfigurations.getUrl());
		dataSource.setUsername(jdbcConfigurations.getUsername());
		dataSource.setPassword(jdbcConfigurations.getPassword());
		dataSource.setInitialSize(jdbcConfigurations.getInitialSize());
		dataSource.setMinIdle(jdbcConfigurations.getMinIdle());
		dataSource.setMaxActive(jdbcConfigurations.getMaxActive());
		dataSource.setMaxWait(jdbcConfigurations.getMaxWait());
		dataSource.setTimeBetweenEvictionRunsMillis(jdbcConfigurations.getTimeBetweenEvictionRunsMillis());
		dataSource.setMinEvictableIdleTimeMillis(jdbcConfigurations.getMinEvictableIdleTimeMillis());
		try {
			dataSource.addFilters(jdbcConfigurations.getFilters());
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}

		return dataSource;
	}
	
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		
		return new JedisConnectionFactory(new RedisClusterConfiguration(redisConfigurations.getClusterNodes()));
	}
	
	@Bean
	public RedisTemplate<?, ?> redisTemplate() {
		RedisTemplate<?, ?> redisTemplate = new RedisTemplate<byte[], byte[]>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		return redisTemplate;
	}
	
	@Bean
	public CacheManager redisCacheManager() {
		
		return new RedisCacheManager(redisTemplate());
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.MYSQL);
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
	
	@Component
	public static final class JdbcConfigurations {
		
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
		
		@Value("${jdbc.filters}")
		private String filters;

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
		 * @return the filters
		 */
		public String getFilters() {
			return filters;
		}
		
	}
	
	@Component
	public static final class RedisConfigurations {
		
		private static final List<String> clusterNodes = Collections.synchronizedList(new ArrayList<>());

		/**
		 * 
		 */
		public RedisConfigurations(@Value("${redis.cluster.nodes}") String nodes) {
			Assert.notNull(nodes);
			clusterNodes.addAll(Arrays.asList(nodes.split(",")));
		}

		/**
		 * @return the clusternodes
		 */
		public List<String> getClusterNodes() {
			return clusterNodes;
		}
	}
}

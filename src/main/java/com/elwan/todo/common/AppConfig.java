package com.elwan.todo.common;


import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import com.elwan.todo.dao.redis.RedisIndexesDAO;
import com.elwan.todo.model.Todo;
import com.elwan.todo.model.User;
import com.elwan.todo.service.TodoService;
import com.elwan.todo.service.UserService;
import com.elwan.todo.service.impl.TodoServiceImpl;
import com.elwan.todo.service.impl.UserServiceImpl;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
@EnableTransactionManagement
@ComponentScan("com.elwan.todo")
public class AppConfig {
	
	@Bean
	public UserService userService() {
		UserServiceImpl s = new UserServiceImpl();
		s.setLogger(UserServiceImpl.class);
		return s;
	}
	
	@Bean
	public TodoService todoService() {
		TodoServiceImpl s = new TodoServiceImpl();
		s.setLogger(TodoServiceImpl.class);
		return s;
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl(String.format("jdbc:mysql://%s:%s/%s", 
				ConfigManager.getInstance().get(AppConstant.Keys.MYSQL_IP),
				ConfigManager.getInstance().get(AppConstant.Keys.MYSQL_PORT),
				ConfigManager.getInstance().get(AppConstant.Keys.MYSQL_SERVICE)
				));
		ds.setUsername(ConfigManager.getInstance().get(AppConstant.Keys.MYSQL_USER));
		ds.setPassword(ConfigManager.getInstance().get(AppConstant.Keys.MYSQL_PWD));
		return ds;
	}
	
	@Bean
	public AbstractPlatformTransactionManager transactionManager() {
		int dbType = ConfigManager.getInstance().getInt(AppConstant.Keys.DB_TYPE);
		if(dbType == AppConstant.DBType.HIBERNATE)
			return new HibernateTransactionManager(sessionFactory().getObject());
		else
			return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean
	public RedisIndexesDAO redisIndexesDAO() {
		return new RedisIndexesDAO();
	}

	@Bean
	public JedisPool jedisPool() {
		return new JedisPool(new JedisPoolConfig(), 
				ConfigManager.getInstance().get(AppConstant.Keys.REDIS_IP), 
				ConfigManager.getInstance().getInt(AppConstant.Keys.REDIS_PORT), 
				Protocol.DEFAULT_TIMEOUT, 
				ConfigManager.getInstance().get(AppConstant.Keys.REDIS_PWD));
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(dataSource());
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.setProperty("hibernate.current_session_context_class", "thread");
		props.setProperty("hibernate.show_sql", "true");
		
		props.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
		props.setProperty("hibernate.cache.use_second_level_cache", "true");
		props.setProperty("hibernate.cache.use_query_cache", "true");
		
		props.setProperty("net.sf.ehcache.configurationResourceName", "/myehcache.xml");
		lsfb.setHibernateProperties(props);
		
		lsfb.setAnnotatedClasses(User.class, Todo.class);
		
		return lsfb;
	}
}

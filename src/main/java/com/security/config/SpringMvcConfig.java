package com.security.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * To Configure Spring MVC 
 * @author Pranav
 *
 */

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.security"}) 
@PropertySource(value = { "classpath:application.properties" })
@EnableTransactionManagement
public class SpringMvcConfig  extends WebMvcConfigurerAdapter{

	@Autowired
	private Environment env;
	
	
	/**
	 * To Configure View Resolver 
	 * @return
	 */
	
	@Bean
	public ViewResolver resolver()
	{
		InternalResourceViewResolver url = new InternalResourceViewResolver();
		url.setViewClass(JstlView.class);
		url.setPrefix("/WEB-INF/views/");
		url.setSuffix(".jsp");
		return url;
	}

	/**
	 * To Configure MVC Resources
	 */
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	/**
	 * To configure Session Factory
	 * @return
	 */
	
	@Bean
    public LocalSessionFactoryBean getSessionFactory()
    {
		LocalSessionFactoryBean session = new LocalSessionFactoryBean();
        session.setDataSource(getDataSource());
        session.setHibernateProperties(getHibernateProperties());        
        session.setPackagesToScan(new String[]{env.getProperty("hibernate.pacakgeToScan")});
        return session;
    }
	
	/**
	 * To Configure Data Source
	 * @return
	 */
	
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource(); 
	    dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
	    dataSource.setUrl(env.getProperty("jdbc.url"));
	    dataSource.setUsername(env.getProperty("jdbc.username"));
	    dataSource.setPassword("root");
	 
	    return dataSource;
	}
	
	/**
	 * To Configure Transaction Manager
	 * @param sessionFactory
	 * @return
	 */
	
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory)
    {
        HibernateTransactionManager htm = new HibernateTransactionManager();
        htm.setSessionFactory(sessionFactory);
        return htm;
    }
	
	
	 @Bean
	 public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
	      return new PersistenceExceptionTranslationPostProcessor();
	 }
	
	/**
	 * To Set Hibernate Properties
	 * @return
	 */
	 
	@Bean
    public Properties getHibernateProperties()
    {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl"));
        properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        
        return properties;
    }
	
}

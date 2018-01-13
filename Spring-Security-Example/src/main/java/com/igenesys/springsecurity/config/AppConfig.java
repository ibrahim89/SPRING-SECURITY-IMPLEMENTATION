package com.igenesys.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@PropertySource({"classpath:db.properties"})
@ComponentScan({ "com.igenesys.springsecurity.*" })
@Import({ SecurityConfig.class })
public class AppConfig {
	 @Autowired
	 private Environment env;
	 
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName(this.env.getProperty("db.driver"));
	    driverManagerDataSource.setUrl(this.env.getProperty("db.url"));
	    driverManagerDataSource.setUsername(this.env.getProperty("db.user"));
	    driverManagerDataSource.setPassword(this.env.getProperty("db.password"));
	    return driverManagerDataSource;
	}
	 @Bean
	    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
	        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	        jdbcTemplate.setResultsMapCaseInsensitive(true);
	        return jdbcTemplate;
	    }
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
}
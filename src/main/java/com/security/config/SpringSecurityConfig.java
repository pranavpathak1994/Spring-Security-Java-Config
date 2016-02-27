package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.security.service.UserDetail;

/**
 * To Configure Spring Security
 * @author Pranav
 *
 */

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig  extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetail userDetail;
	
	/**
	 * Configuration of Spring Security
	 */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
    	http
    		.authorizeRequests().antMatchers("/403","/registration.html","/register.do","/","/authLogin**","/authLogout").permitAll()
    		.antMatchers("/home/**").access("hasRole('Admin')")
    		.anyRequest().authenticated()
    		.and()
    		.formLogin()
	    		.loginPage("/")
	    		.defaultSuccessUrl("/home/welcome.html")
	    		.loginProcessingUrl("/authLogin").usernameParameter("username").passwordParameter("password")
	    		.failureUrl("/?error=worng");
    	
    	http
    		.csrf().disable();
    	
    	
    	
    	http
    		.exceptionHandling().accessDeniedPage("/403");
    	
    	http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/authLogout", "GET")).logoutSuccessUrl("/");
    		
		
    }
	
	/**
	 * To Ignore Spring MVC Resources
	 */
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
	        .antMatchers("/resources/**");
	}
	/**
	 * Authenticate User with Credential
	 * @param auth
	 * @throws Exception
	 */
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		/*Below code is for use spring security without datasoruce*/
		
		auth
            .inMemoryAuthentication().passwordEncoder(passwordEncoder())
                .withUser("abc@gmail.com").password("abc").roles("Admin");
		
		/*Below code is for use spring security with datasoruce*/
		
		/*auth
			.userDetailsService(userDetail).passwordEncoder(passwordEncoder());*/
    }
	
	/**
	 * Password Encoder 
	 * @return
	 */
	
	@Bean
	public PlaintextPasswordEncoder passwordEncoder(){
		return new PlaintextPasswordEncoder();
	}

}

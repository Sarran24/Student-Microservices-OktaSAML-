package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.filter.JwtAuthFilter;
import com.security.service.UserInfoUserDetailsService;





@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MySecurityConfig {
	
	@Autowired
	private JwtAuthFilter authFilter;
	
	//authentication
	@Bean
    public UserDetailsService userDetailsService() {
//    	UserDetails admin = User.withUsername("Fahyien")
//    			.password(encoder.encode("password"))
//    			.roles("ADMIN")
//    			.build();
//    	
//    	UserDetails user = User.withUsername("Hermann")
//    			.password(encoder.encode("password"))
//    			.roles("USER")
//    			.build();
//    	
//    	return new InMemoryUserDetailsManager(admin,user);
		
		return new UserInfoUserDetailsService();
    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	
		return http.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/product/save","/product/saveUser","/product/authenticate").permitAll()
		.and()
		.authorizeHttpRequests().requestMatchers("/product/**").authenticated()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(authenticationProvider())
		.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
		.build();
	}
	
	
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    	authenticationProvider.setUserDetailsService(userDetailsService());
    	authenticationProvider.setPasswordEncoder(passwordEncoder());
    	return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

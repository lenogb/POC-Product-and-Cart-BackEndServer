//package com.product.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import com.product.enums.UsersRoles;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
//
//	@Bean
//	public PasswordEncoder passEncoder() {
//		return new BCryptPasswordEncoder(10);
//	}
//	
//
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		String productUri = "/product/**";
//		http
//			.csrf().disable()
//			.authorizeRequests()
//				.antMatchers("/cartRelatedOperations/**").permitAll()
//				.antMatchers(HttpMethod.GET,productUri).permitAll()
//				.antMatchers(HttpMethod.POST,productUri).hasRole(UsersRoles.ADMIN.name())
//				.antMatchers(HttpMethod.PUT,productUri).hasRole(UsersRoles.ADMIN.name())
//				.anyRequest().authenticated()
//				.and()
//				.httpBasic();
//	}
//	
//
//	@Bean
//	public UserDetailsService userDetailsService(PasswordEncoder passEncoder) {
//		UserDetails customer1 = User.builder()
//				.username("customer1")
//				.password(passEncoder.encode("password"))
//				.roles(UsersRoles.CUSTOMER.name())
//				.build();
//			
//		UserDetails customer2 = User.builder()
//				.username("customer2")
//				.password(passEncoder.encode("password"))
//				.roles(UsersRoles.CUSTOMER.name())
//				.build();
//			
//		UserDetails customer3 = User.builder()
//				.username("customer3")
//				.password(passEncoder.encode("password"))
//				.roles(UsersRoles.CUSTOMER.name())
//				.build();
//		
//		UserDetails admin = User.builder()
//			.username("admin")
//			.password(passEncoder.encode("password"))
//			.roles(UsersRoles.ADMIN.name())	
//			.build();
//		
//		return new InMemoryUserDetailsManager(customer1,customer2,customer3,admin);
//	}
//	
//
//	
//}

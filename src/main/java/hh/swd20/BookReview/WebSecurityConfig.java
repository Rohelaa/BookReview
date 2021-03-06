package hh.swd20.BookReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		// h2-console toimimaan Spring-securityn lisäyksen jälkeen
		.antMatchers("/h2-console/**", "/booksRest").permitAll()
		.anyRequest().authenticated()
		.and().csrf().ignoringAntMatchers("/h2-console/**")
		.and().headers().frameOptions().sameOrigin()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll();
	}
}

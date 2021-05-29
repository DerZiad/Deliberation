package com.ziad.security.authentification.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ziad.enums.MonRole;
import com.ziad.security.authentification.models.UserPrincipalDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private UserPrincipalDetailsService userPrincipalDetailsService;

	public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService) {
		this.userPrincipalDetailsService = userPrincipalDetailsService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint() {
		}).and().authenticationProvider(authenticationProvider()).authorizeRequests().antMatchers("/").permitAll()
				.antMatchers("/login").permitAll().antMatchers("/signup").permitAll().antMatchers("/logout")
				.authenticated() // Doît être authentifier pour se deconnecter

				// Gestion des espaces du système
				.antMatchers(MonRole.ROLEADMIN.getEspace()).hasRole(MonRole.ROLEADMIN.getRole())
				.antMatchers(MonRole.ROLEETUDIANT.getEspace()).hasRole(MonRole.ROLEETUDIANT.getRole())
				.antMatchers(MonRole.ROLEPROFESSEUR.getEspace())
				.hasAnyRole(MonRole.ROLERESPONSABLEFILIERE.getRole(), MonRole.ROLERESPONSABLEMODULE.getRole(),
						MonRole.ROLEPROFESSEUR.getRole())
				.antMatchers("/delib")
				.hasAnyRole(MonRole.ROLEADMIN.getRole(), MonRole.ROLERESPONSABLEMODULE.getRole(),
						MonRole.ROLERESPONSABLEFILIERE.getRole())
				.antMatchers("/delib/deliberationmodule")
				.hasAnyRole(MonRole.ROLEADMIN.getRole(), MonRole.ROLERESPONSABLEMODULE.getRole(),
						MonRole.ROLERESPONSABLEFILIERE.getRole())
				.antMatchers("/delib/deliberationsemestre")
				.hasAnyRole(MonRole.ROLEADMIN.getRole(), MonRole.ROLERESPONSABLEFILIERE.getRole())
				.antMatchers("/delib/deliberationetape")
				.hasAnyRole(MonRole.ROLEADMIN.getRole(), MonRole.ROLERESPONSABLEFILIERE.getRole())

				.anyRequest().permitAll().and().formLogin().loginProcessingUrl("/login").permitAll()

				.loginPage("/login").permitAll().successHandler(mySimpleUrlAuthenticationHandler())
				.failureUrl("/login?error=true").usernameParameter("username").passwordParameter("password").and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and()
				.rememberMe().tokenValiditySeconds(30000).key("mySecret!")
				.userDetailsService(userPrincipalDetailsService).rememberMeParameter("checkRememberMe");
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

		return daoAuthenticationProvider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	MySimpleUrlAuthenticationHandler mySimpleUrlAuthenticationHandler() {
		return new MySimpleUrlAuthenticationHandler();
	}
}

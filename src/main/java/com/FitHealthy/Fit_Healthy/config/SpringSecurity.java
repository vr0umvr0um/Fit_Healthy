package com.FitHealthy.Fit_Healthy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Configuration de la sécurité de l'application. Définit les paramètres de sécurité,
 * l'authentification, la gestion des rôles et les pages accessibles.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurity {

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Crée et retourne un encodeur de mot de passe utilisant l'algorithme BCrypt.
	 * 
	 * @return un instance de BCryptPasswordEncoder
	 */
	@Bean
	public static PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	/**
	 * Configure les règles de sécurité HTTP de l'application, y compris la gestion des accès,
	 * de la connexion, de la déconnexion et de la gestion des exceptions d'authentification.
	 * 
	 * @param http l'objet HttpSecurity pour configurer les règles de sécurité
	 * @return l'objet SecurityFilterChain configuré
	 * @throws Exception en cas d'erreur de configuration
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests((authorize) ->
		authorize
		.requestMatchers("/").permitAll()
		.requestMatchers("/index").permitAll()
		.requestMatchers("/login").permitAll()
		.requestMatchers("/register").permitAll()
		.requestMatchers("/register/save").permitAll()
		.requestMatchers("/admin-users").hasAuthority("ADMIN")
		.requestMatchers("/admin").hasAuthority("ADMIN")
		.requestMatchers("/admin-activities").hasAuthority("ADMIN")
		.anyRequest().authenticated()
				)
		.formLogin(
				form -> form
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/activities")
				.permitAll()
				)
		.logout(
				logout -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.permitAll()
				)
		.exceptionHandling()
		.authenticationEntryPoint((request, response, authException) -> {
			if (request.getUserPrincipal() != null) {
				response.sendRedirect("/activities");
			} else {
				response.sendRedirect("/login");
			}
		});
		return http.build();
	}

	/**
	 * Configure l'authentification en utilisant un service de détails utilisateur personnalisé
	 * et un encodeur de mot de passe.
	 * 
	 * @param auth l'objet AuthenticationManagerBuilder pour configurer l'authentification
	 * @throws Exception en cas d'erreur de configuration
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}
}
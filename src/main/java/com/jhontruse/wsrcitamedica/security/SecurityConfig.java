package com.jhontruse.wsrcitamedica.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jhontruse.wsrcitamedica.exception.type.JwtAuthenticationEntryPoint;
import com.jhontruse.wsrcitamedica.exception.type.RestAccessDeniedHandler;

import lombok.RequiredArgsConstructor;

// JWT - AUTH
@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Importante para anotaciones @PreAuthorize
@RequiredArgsConstructor
public class SecurityConfig {

  private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final RestAccessDeniedHandler restAccessDeniedHandler;
  private final UserDetailsService jwtUserDetailsService;
  private final JwtRequestFilter jwtRequestFilter;

  // --- AuthenticationManager (de la configuración autogenerada) ---
  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration cfg)
      throws Exception {
    return cfg.getAuthenticationManager();
  }

  @Bean
  public static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public DaoAuthenticationProvider authProvider(UserDetailsService uds,
      PasswordEncoder encoder) {
    log.info("********************************");
    log.info("********************************");
    log.info("SecurityConfig - DaoAuthenticationProvider");
    log.info("********************************");
    log.info("********************************");
    log.info("uds: {}", uds);
    log.info("encoder: {}", encoder);
    DaoAuthenticationProvider p = new DaoAuthenticationProvider();
    p.setUserDetailsService(uds);
    p.setPasswordEncoder(encoder);
    p.setPreAuthenticationChecks(new AccountStatusUserDetailsChecker()); // usatus flags
    log.info("p: {}", p);
    return p;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // Desde Spring Boot 3.1+
    http
        .csrf(AbstractHttpConfigurer::disable)
        .exceptionHandling(eh -> eh
            .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 401 JSON
            .accessDeniedHandler(restAccessDeniedHandler) // 403 JSON
        )
        .authorizeHttpRequests(req -> req
            .requestMatchers("/login").permitAll()
            .requestMatchers("/mail/**").permitAll()
            .requestMatchers("/auth/**").permitAll()
            .requestMatchers("/swagger-ui/**").permitAll()
            .requestMatchers("/v3/api-docs/**").permitAll()
            .requestMatchers("/swagger-ui.html").permitAll()
            .requestMatchers("/swagger-resources/**").permitAll()
            .requestMatchers("/webjars/**").permitAll()
            .anyRequest().authenticated())
        .formLogin(AbstractHttpConfigurer::disable);
    ;

    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

}

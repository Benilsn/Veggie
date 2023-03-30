package br.com.veggierecipes.veggierecipes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

                AuthenticationManagerBuilder authenticationManagerBuilder = http
                                .getSharedObject(AuthenticationManagerBuilder.class);
                // authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(encoder());
                authenticationManagerBuilder.inMemoryAuthentication().withUser("user")
                                .password(passwordEncoder().encode("123"))
                                .roles("USER");
                AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
                return authenticationManager;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                // .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                                // .requestMatchers("/admin/**").hasRole("ADMIN")
                                // .requestMatchers("/**").hasRole("USER"))
                                .authorizeHttpRequests().anyRequest().permitAll().and()
                                .authenticationManager(authenticationManager(http))
                                .formLogin()
                                .loginPage("/login").and()
                                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                                .permitAll()
                                                .logoutSuccessUrl("/login"))
                                .csrf().disable().headers().frameOptions().disable();

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}

package br.com.veggierecipes.veggierecipes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.veggierecipes.veggierecipes.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        private UserService userService;

        @Autowired
        private PasswordEncoder encoder;

        @Bean
        public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

                AuthenticationManagerBuilder authenticationManagerBuilder = http
                                .getSharedObject(AuthenticationManagerBuilder.class);
                authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(encoder);
                AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
                return authenticationManager;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                                .requestMatchers("/", "/login", "/register", "/recipes/**").permitAll())
                                .authorizeHttpRequests().anyRequest().permitAll().and()
                                .authenticationManager(authenticationManager(http))
                                .formLogin()
                                .loginPage("/login").usernameParameter("email").defaultSuccessUrl("/").and()
                                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                                .permitAll()
                                                .logoutSuccessUrl("/login?logout"))
                                .csrf().disable().headers().frameOptions().disable();

                return http.build();
        }

}

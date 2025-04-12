package self.dsa.core.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Apply security to /dsas-core/**
            .securityMatcher("/dsas-core/**")
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/dsas-core/h2-console/**").permitAll() // Public H2 console
                .requestMatchers("/dsas-core/login").permitAll()          // Public login page
                .requestMatchers("/dsas-core/logout").permitAll()        // Public logout
                .requestMatchers("/dsas-core/api/**").authenticated()    // APIs require Basic Auth
                .requestMatchers("/dsas-core/**").authenticated()        // Other /myApp paths require form login
            )
            // Enable HTTP Basic Authentication for APIs
            .httpBasic(httpBasic -> httpBasic.realmName("dsasCoreApis"))
            // Enable form login for /dsas-core/**
            .formLogin(form -> form
                .loginPage("/dsas-core/login")
                .loginProcessingUrl("/dsas-core/login")
                .defaultSuccessUrl("/dsas-core/home") // Redirect after login
                .permitAll()
            )
            // Configure logout
            .logout(logout -> logout
                .logoutUrl("/dsas-core/logout")
                .logoutSuccessUrl("/dsas-core/login?logout")
                .permitAll()
            )
            // Disable CSRF for APIs and H2 console
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/dsas-core/api/**")
                .ignoringRequestMatchers("/dsas-core/h2-console/**")
            )
            // Allow frames for H2 console
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin())
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("user")
                       .password("{noop}password") // For testing; use BCrypt in production
                       .roles("USER")
                       .build();
        return new InMemoryUserDetailsManager(user);
    }
}
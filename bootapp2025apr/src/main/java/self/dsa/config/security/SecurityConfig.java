package self.dsa.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.config.http.SessionCreationPolicy;

// Security configuration
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Configure CSRF token request handler
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
                
        http
            // Enable CSRF with CookieCsrfTokenRepository
            .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            // Enable Basic Authentication
            .httpBasic(httpBasic -> httpBasic.realmName("MyApp"))
            // Configure stateless session management
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Authorize requests
            .authorizeHttpRequests(auth -> auth
                // Allow authenticated access to all API endpoints
                .requestMatchers("/api/**").authenticated()
                // Permit all other requests (e.g., public endpoints, if any)
                .anyRequest().permitAll()
            );
        return http.build();
    }
}
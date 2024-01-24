package goral.psychotherapistoffice.config.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CustomSecurityConfig {
    private static final String ADMIN_ROLE = "ADMIN";

    private static final String PATIENT_ROLE = "PATIENT";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/termin/**").hasAnyRole(PATIENT_ROLE, ADMIN_ROLE)
                .requestMatchers("/admin/**").hasAnyRole(ADMIN_ROLE)

                .anyRequest().permitAll()
        )
                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(
                "/img/**",
                "/scripts/**",
                "/styles/**"
        );
    }
}

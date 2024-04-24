package goral.psychotherapistoffice.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class CustomSecurityConfig {

    private static final String ADMIN_ROLE = "ADMIN";
    private static final String USER_ROLE = "USER";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/admin/**").hasAnyRole(ADMIN_ROLE)
                        .requestMatchers("/termin/**", "/user/**").hasAnyRole(ADMIN_ROLE, USER_ROLE)
                        .requestMatchers("/secured").hasAnyRole(ADMIN_ROLE, USER_ROLE)
                        .requestMatchers("/register", "/confirmation","/forgot-password/**","/reset-password/**").permitAll()
                        .anyRequest().permitAll()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout/**", HttpMethod.GET.name()))
                        .logoutSuccessUrl("/login?logout").permitAll()
                );
        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().requestMatchers(
                "/images/**",
                "/scripts/**",
                "/styles/**",
                "/h2-console/**",
                "/reset-password/**"
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Autowired
//    private CustomUserDetailsService userDetailsService;

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
//        auth.setUserDetailsService(userDetailsService);
//        auth.setPasswordEncoder(passwordEncoder());
//        return auth;
//    }
//
//    public AuthenticationManager authenticationManager(
//            AuthenticationConfiguration authenticationConfiguration) throws Exception{
//        return authenticationConfiguration.getAuthenticationManager();
//    }

}

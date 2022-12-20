package com.block7.block7crudvalidation.security;

import com.block7.block7crudvalidation.security.authorities.Role;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtConfig jwtConfig;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter usernameAuthenticationFilter = new CustomAuthenticationFilter(
                authenticationManagerBuilder.getOrBuild(),
                this.jwtConfig.getKey(),
                this.jwtConfig.getExpirationDays()
        );
        usernameAuthenticationFilter.setFilterProcessesUrl("/login");

        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2 console
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .addFilter(usernameAuthenticationFilter)
            .addFilterAfter(
                    new CustomAuthorizationFilter(this.jwtConfig.getKey()),
                    CustomAuthenticationFilter.class
            )
            .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/person").permitAll()
                .antMatchers(HttpMethod.GET, "**").permitAll()
            .and()
                .authorizeRequests()
                .anyRequest()
                .hasAuthority(Role.ROLE_ADMIN.getAuthority());

        return http.build();
    }
}

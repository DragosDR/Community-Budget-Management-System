package com.example.Budget.Management.Application.securityConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails residentialUser = User.withUsername("user")
                .password(passwordEncoder.encode("userOfResidenceY"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("adminOfResidenceY"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(residentialUser, admin);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/homepage/welcome").permitAll()
                        .requestMatchers("/api/homepage/RulesT&C").permitAll()
//                                          Official requestMatchers
                        .requestMatchers(HttpMethod.GET,"/api/homepage/**").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.POST).hasRole("USER")

                        //testing-for-Admin
//                        .requestMatchers(HttpMethod.POST,"/addResidentUser").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST,"/addAdmin").hasRole("ADMIN")
//                                    //testing-for-User
//                        .requestMatchers(HttpMethod.POST,"/addLockedFunds").hasRole("USER")
//                        .requestMatchers(HttpMethod.POST,"/unlockFunds").hasRole("USER")

                        .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                        .anyRequest().authenticated())

                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults());


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}

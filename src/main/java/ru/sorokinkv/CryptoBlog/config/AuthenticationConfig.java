package ru.sorokinkv.CryptoBlog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.sorokinkv.CryptoBlog.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity(debug = true)
public class AuthenticationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
//    CustomAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(customUserDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//Disabled CSRF protection
                .authorizeRequests()
                .antMatchers("/**",
                        "/index.html",
                        "/blog",
                        "/api/message/**",
                        "/api/registration",
                        "/api/registrationConfirm/**",
                        "/css/**",
                        "/js/**",
                        "/webjars/**").permitAll()
                .antMatchers("/api/admin/**").access("hasRole('ADMIN')")
                .antMatchers("/api/user/**").access("hasRole('USER') or hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .loginPage("/login")
                .loginProcessingUrl("/authenticateTheUser")
                .successHandler(customAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll()
                .and().exceptionHandling().accessDeniedPage("/api/accessDenied");
//                .accessDeniedHandler(accessDeniedHandler);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(customUserDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

}
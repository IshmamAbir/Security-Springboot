package com.example.security.configuration;

import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /*auth
                .inMemoryAuthentication()
                .withUser("ishmam").password(passwordEncoder().encode("i12345")).roles("admin")
                .and()
                .withUser("tasbir").password(passwordEncoder().encode("t12345")).roles("teacher")
                .and()
                .withUser("hasnat").password(passwordEncoder().encode("h12345")).roles("student")
                .and()
                .withUser("super").password(passwordEncoder().encode("12345")).roles("teacher","student","admin");*/

        auth.userDetailsService(userService).passwordEncoder(passwordCustomEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/student/**").hasAuthority("Student")
                .antMatchers("/teacher/**").hasAuthority("Teacher")
                .antMatchers("/admin/**").hasAuthority("Admin")

                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/login-process")

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")

                /*.and()
                .exceptionHandling()
                .accessDeniedPage("/denied")*/;
    }

    @Bean
    public PasswordEncoder passwordCustomEncoder(){
        return new BCryptPasswordEncoder();
    }
}

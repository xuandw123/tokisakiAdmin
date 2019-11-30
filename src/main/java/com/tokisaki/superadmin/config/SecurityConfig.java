package com.tokisaki.superadmin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.tokisaki.superadmin.security.jwt.JwtSecurityConfigurer;
import com.tokisaki.superadmin.security.jwt.JwtTokenProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/csrf").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/swagger**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/vehicles/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/vehicles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/vehicles/**").permitAll()

                .antMatchers("/api/auth/signin**").permitAll()
                .antMatchers("/api/auth/qqlogin**").permitAll()
                .antMatchers("/api/auth/qqloginCallback**").permitAll()
                .antMatchers("/api/auth/qqblind**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .apply(new JwtSecurityConfigurer(jwtTokenProvider));
        //@formatter:on
    }


}


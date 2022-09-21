package com.gft.cybercoffee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.gft.cybercoffee.service.CyberCoffeeUserDetailsService;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
// @Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CyberCoffeeUserDetailsService cyberCoffeeUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/receita/**", "/unidademedida/**","/ingrediente/**","usuario/**", "elemento_receita/**" ).access("hasAuthority('ADMIN')")
                .antMatchers("/**","/login", "/register")//TODO tudo que for permitido ao usuario
                .permitAll() 
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
                .csrf().disable().cors();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(cyberCoffeeUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());

    }

    @Bean
    public static BCryptPasswordEncoder senha () {
        return new BCryptPasswordEncoder();
    }

    public static String verifyAuthority() {
        var logado = SecurityContextHolder.getContext().getAuthentication();
        if(logado.getPrincipal().equals("anonymousUser")){
            return "USER";        
        }

        if(logado.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return "ADMIN";
        }else {
            return "USER";
        }        

    }
}

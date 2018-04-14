package ru.kpfu.itis.csport.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import ru.kpfu.itis.csport.security.AuthProviderImpl;

@Configuration
@EnableWebSecurity
@ComponentScan("ru.kpfu.itis.csport.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProviderImpl authProvider;
    private final AccessDeniedHandler accessDeniedHandler;

    @Autowired
    public SecurityConfig(AuthProviderImpl authProvider, AccessDeniedHandler accessDeniedHandler) {
        this.authProvider = authProvider;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/registration").anonymous()
                .antMatchers("/profile").hasAnyRole("MANAGER", "USER")
        ;


        http.csrf().disable()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/process")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/profile", true)
                .failureUrl("/login?error=true")
                .and()
                .logout().logoutSuccessUrl("/login")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

}
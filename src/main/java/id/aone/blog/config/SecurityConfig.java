package id.aone.blog.config;


import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(username -> User.withDefaultPasswordEncoder()
                        .username("yukenz")
                        .password("123")
                        .roles("USER")
                        .build())
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                /* CSRF disable on /post/** */
                .csrf(csrf -> csrf
                        .ignoringAntMatchers("/post/**")
                )
                /* Disable Auth on /post/** */
                /* Disable Auth on /static/** */
                .authorizeRequests(req -> req
                        .antMatchers("/post/**")
                        .permitAll()
                        .antMatchers("/static/**")
                        .permitAll()
                )
                /* AnyRequest Need Auth */
                .authorizeRequests(req -> req
                        .anyRequest()
                        .authenticated()
                )
                /* Enable Login And Logout */
                .formLogin(SecurityConfigurerAdapter::and)
                .logout(SecurityConfigurerAdapter::and);

    }

}

package com.app.atlasultimate.security;

import com.app.atlasultimate.model.Rol;
import com.app.atlasultimate.service.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImp usuarioServiceImp;



    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(usuarioServiceImp);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/usuario/inicio/**").hasRole(Rol.ADMINISTRADOR.toString())
                .antMatchers("/hotel/habitacion/").hasRole(Rol.ADMINISTRADOR.toString())
                .antMatchers("/hotel/nuevo").hasRole(Rol.ADMINISTRADOR.toString())
                .antMatchers("/hotel/editar").hasRole(Rol.ADMINISTRADOR.toString())
                .antMatchers("/hotel/nuevo").hasRole(Rol.ADMINISTRADOR.toString())
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/registro").permitAll()
                .antMatchers("/hotel/**").permitAll()
                .and()
                .formLogin().loginPage("/login.html")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout().invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .permitAll();

    }



}

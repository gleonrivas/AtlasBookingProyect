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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static String encodeValue(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
    }

    public static String decode(String value) throws UnsupportedEncodingException {
        return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
    }


    @Autowired
    private UsuarioServiceImp usuarioServiceImp;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
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
                //.inMemoryAuthentication()
                //.withUser("usuario").password(passwordEncoder().encode("password")).roles(Rol.administrador.toString())
                //.and()
                //.withUser("administrador").password(passwordEncoder().encode("admin")).roles(Rol.usuario.toString())

    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .sessionManagement()
                .enableSessionUrlRewriting(true)
                .and()
                .authorizeRequests()
                .antMatchers("/usuario/inicio/**").hasAuthority(Rol.administrador.toString())
                .antMatchers("/hotel/habitacion/").hasAuthority(Rol.administrador.toString())
                .antMatchers("/hotel/nuevo").hasAuthority(Rol.administrador.toString())
                .antMatchers("/hotel/editar").hasAuthority(Rol.administrador.toString())
                .antMatchers("/hotel/nuevo").hasAuthority(Rol.administrador.toString())
                .antMatchers("reserva/datos/**").hasAuthority(Rol.usuario.toString())
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/registro").permitAll()
                .antMatchers("/hotel/**").permitAll()
                .and()
                .formLogin().loginPage("/login")
                .permitAll()
                .failureUrl("/login?error=true")
                .permitAll()
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout().invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .permitAll();
    }



}

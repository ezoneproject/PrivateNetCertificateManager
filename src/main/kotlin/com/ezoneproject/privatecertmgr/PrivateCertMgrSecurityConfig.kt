package com.ezoneproject.privatecertmgr

import mu.KotlinLogging
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint

@Configuration
@EnableWebSecurity
class PrivateCertMgrSecurityConfig : WebSecurityConfigurerAdapter(false) {
    private val log = KotlinLogging.logger {}

    override fun configure(http: HttpSecurity) {
        http
            //.csrf().disable().headers().frameOptions().disable().and()
            .authorizeRequests()
            .antMatchers("/health-check", "/error", "/login", "/unauthorized/**").permitAll()
            .anyRequest().authenticated()
            .and().exceptionHandling()
            .authenticationEntryPoint(LoginUrlAuthenticationEntryPoint("/login"))
            //.and().securityContext()
            //.and().servletApi()
            //.antMatchers("/api/**").hasRole(Role.USER.name())
            //.and().rememberMe()
            //.and().formLogin().loginPage("/login").successForwardUrl("/")
            .and().logout()
            .and().headers()
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/webjars/**", "/css/**", "/script/**", "/image/**", "/fonts/**")
    }
}

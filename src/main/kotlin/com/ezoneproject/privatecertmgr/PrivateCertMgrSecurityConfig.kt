package com.ezoneproject.privatecertmgr

import com.ezoneproject.privatecertmgr.provider.CertMgrAuthenticationProvider
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class PrivateCertMgrSecurityConfig : WebSecurityConfigurerAdapter(false) {
    private val log = KotlinLogging.logger {}

    @Autowired
    lateinit var authProvider: CertMgrAuthenticationProvider

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(http: HttpSecurity) {
        http
            //.csrf().disable().headers().frameOptions().disable().and()
            .authorizeRequests()
            .antMatchers("/health-check", "/error", "/login", "/unauthorized/**").permitAll()
            .anyRequest().authenticated()
            .and().exceptionHandling()
            //.authenticationEntryPoint(LoginUrlAuthenticationEntryPoint("/login"))
            .and().securityContext()
            //.and().servletApi()
            //.antMatchers("/api/**").hasRole(Role.USER.name())
            //.and().rememberMe()
            //.and().formLogin().loginPage("/login").successForwardUrl("/")
            .and().formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
            .and().logout()
            .and().headers()
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/webjars/**", "/css/**", "/script/**", "/image/**", "/fonts/**")
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        //super.configure(auth)
        //  AuthenticationProvider 또는, AbstractUserDetailsAuthenticationProvider  구현한 클래스를 넣어준다.
        auth.authenticationProvider(authProvider)
        //auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
}

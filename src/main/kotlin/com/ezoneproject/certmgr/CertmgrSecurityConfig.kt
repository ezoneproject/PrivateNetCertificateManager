/*
 * Copyright 2020 ezoneproject.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ezoneproject.certmgr

import mu.KotlinLogging
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint

@Configuration
@EnableWebSecurity
class CertmgrSecurityConfig : WebSecurityConfigurerAdapter(false) {
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

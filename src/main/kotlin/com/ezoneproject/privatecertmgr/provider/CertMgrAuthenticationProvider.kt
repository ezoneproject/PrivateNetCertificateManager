package com.ezoneproject.privatecertmgr.provider

import com.ezoneproject.privatecertmgr.service.CertMgrUserDetailsService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class CertMgrAuthenticationProvider : AbstractUserDetailsAuthenticationProvider() {
    // https://jeong-pro.tistory.com/205
    // AuthenticationProvider or AbstractUserDetailsAuthenticationProvider

    private val log = KotlinLogging.logger {}

    @Autowired
    lateinit var certMgrUserDetailsService: CertMgrUserDetailsService

    override fun additionalAuthenticationChecks(userDetails: UserDetails?, authentication: UsernamePasswordAuthenticationToken?) {
        // password check -> AuthenticationException
        // authentication -> 입력
        // authentication.principal -> ID, auth.credentials -> pw

        log.debug { "UserDetails => ${userDetails?.username}/${userDetails?.password}" }
    }

    override fun retrieveUser(username: String?, authentication: UsernamePasswordAuthenticationToken?): UserDetails {
        log.debug { "Get user (isAuthenticated: ${authentication?.isAuthenticated})" }
        return certMgrUserDetailsService.loadUserByUsername(username)
    }
}

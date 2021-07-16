package com.ezoneproject.privatecertmgr.controller

import mu.KotlinLogging
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/login")
class LoginController {
    private val log = KotlinLogging.logger {}

    @GetMapping
    fun printForm(): String {
        log.info { "GET login start .........." }
        return "login"
    }

    /*
    @PostMapping
    fun login(model: Model, @RequestParam map: Map<String, String>): String {
        log.info { "POST Start login .......... ${map}" }

        // new UsernamePasswordAuthenticationToken("username", "password")
        val result: Authentication = UsernamePasswordAuthenticationToken(
            map["username"], "N/A",
            AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"))
        SecurityContextHolder.getContext().authentication = result

        return "redirect:/home"
    }
     */
}

package com.ezoneproject.certmgr.controller

import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest

@Controller
class HomeController {
    private val log = KotlinLogging.logger {}

    /**
     * 인덱스 페이지
     */
    @GetMapping("/2222")
    fun index(model: Model): String {
        // 템플릿 엔진
        model.addAttribute("greeting", "yeah")

        //println("US =>" + messageSource.getMessage("login", null, Locale.US) + "<")
        //println("Test2 =>>" + messageSource.getMessage("login", arrayOf("saelobi"), Locale.US) + "<<=")

        return "index"
    }

    /**
     * 어플리케이션 상태 체크 페이지
     * @return 정상일 경우 "OK"
     */
    @GetMapping("/health-check")
    @ResponseBody
    fun healthCheck(): String {
        return "OK"
    }

    @PostMapping("/login")
    fun login(model: Model, request: HttpServletRequest) {
        log.info { "Start login .........." }

    }

    @GetMapping("/login")
    fun loginpage(model: Model, request: HttpServletRequest): String {
        log.info { "Start login ..........11 ${request.getHeader("referer")}" }

        return "login"
    }

}

package com.ezoneproject.privatecertmgr.controller

import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HomeController {
    private val log = KotlinLogging.logger {}

    /**
     * 인덱스 페이지
     */
    @GetMapping("/", "/home")
    fun home(model: Model): String {
        log.info { "GET Start home .........." }

        // 템플릿 엔진
        model.addAttribute("greeting", "yeah")

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
}

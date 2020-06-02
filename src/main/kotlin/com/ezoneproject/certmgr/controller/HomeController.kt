package com.ezoneproject.certmgr.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HomeController {
    /**
     * 인덱스 페이지
     */
    /*
    @GetMapping("/")
    fun index(model: Model): String {
        // 템플릿 엔진
        model.addAttribute("greeting", "yeah")

        //println("US =>" + messageSource.getMessage("login", null, Locale.US) + "<")
        //println("Test2 =>>" + messageSource.getMessage("login", arrayOf("saelobi"), Locale.US) + "<<=")

        return "/index.html"
    }
     */

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
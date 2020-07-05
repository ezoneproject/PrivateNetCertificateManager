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

package com.ezoneproject.certmgr.controller

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

}

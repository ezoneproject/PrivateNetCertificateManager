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

package com.ezoneproject.certmgr.controller;

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
        log.info { "GET Start login .........." }

        return "login"
    }

    @PostMapping
    fun login(model: Model, @RequestParam map: Map<String, String>): String {
        log.info { "POST Start login .......... ${map}" }

        val result: Authentication = UsernamePasswordAuthenticationToken(
                map["username"], "N/A",
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"))
        SecurityContextHolder.getContext().authentication = result

        return "redirect:/home"
    }


}

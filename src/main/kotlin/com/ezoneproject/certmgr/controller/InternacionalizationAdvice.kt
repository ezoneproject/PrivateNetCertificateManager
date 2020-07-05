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

import com.samskivert.mustache.Mustache
import com.samskivert.mustache.Template.Fragment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute
import java.io.Writer
import java.util.*

@ControllerAdvice
class InternacionalizationAdvice {

    @Autowired
    lateinit var messageSource: MessageSource

    // {{#i18n}}title{{/i18n}
    @ModelAttribute("i18n")
    fun i18n(locale: Locale?): Mustache.Lambda? {
        return Mustache.Lambda { frag: Fragment, out: Writer ->
            val body = frag.execute()
            val message = messageSource!!.getMessage(body, null, locale!!)
            out.write(message)
        }
    }
}
package com.ezoneproject.privatecertmgr

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class PrivateCertMgrSecurity : WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        //registry.addViewController("/login").setViewName("login")
        //registry.addViewController("/a").setViewName("index")
    }
}

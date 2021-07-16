package com.ezoneproject.privatecertmgr

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "cert-mgr")
data class CertMgrConfig (
    var opensslPath: String = "",
    var dataDir: String = "",
    var logDir: String = ""
)

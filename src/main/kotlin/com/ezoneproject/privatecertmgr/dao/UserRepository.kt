package com.ezoneproject.privatecertmgr.dao

import com.ezoneproject.privatecertmgr.CertMgrConfig
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.io.File
import java.util.*


@Component
class UserRepository {

    @Autowired
    lateinit var certMgrConfig: CertMgrConfig

    fun findByUsername(username: String?): Optional<UserDetails> {
        if (username == null)
            return Optional.empty()

        val userInfo = loadUsersJson().find { user -> user.username.equals(username) } ?: return Optional.empty()

        /**
         * 계정 만료: true: 만료됨
        var accountExpired: Boolean = false,
         */
        /**
         * 계정 잠금: true: 잠김
        var accountLocked: Boolean = false,
         */
        /**
         * 비밀번호 만료: false: 만료
        var credentialsExpired: Boolean = false,
         */

        //var encoder: PasswordEncoder? = PasswordEncoderFactories.createDelegatingPasswordEncoder()

        val userDetails = User
            .withUsername(userInfo.username)
            .password(userInfo.password)
            .disabled(!userInfo.enabled)
            .accountExpired(false)  // 계정 만료
            .accountLocked(userInfo.passwordErrorCount >= 5)
            .credentialsExpired(false)  // 비밀번호 만료됨
            .authorities(userInfo.roleName)
            //.passwordEncoder {  }
            .build()

        return Optional.of(userDetails)
    }

    fun loadUsersJson(): MutableList<UserInfo> {
        val jsonConverter = jacksonObjectMapper()
        val dataFile = File(certMgrConfig.dataDir + File.pathSeparator + "users.json");

        if (!dataFile.exists())
            return createAdminUser()

        return jsonConverter.readValue<ArrayList<UserInfo>>(dataFile)
    }

    fun saveUsersJson(users: MutableList<UserInfo>) {
        val jsonConverter = jacksonObjectMapper()
        val dataFile = File(certMgrConfig.dataDir + File.pathSeparator + "users.json");

        jsonConverter.writeValue(dataFile, users)
    }

    fun createAdminUser(): MutableList<UserInfo> {
        val user = UserInfo()
        user.username = "admin"
        user.password = ""
        user.enabled = true
        user.passwordChangedDate = "19000101"
        user.passwordErrorCount = 0
        user.roleName = "ROLE_USER"

        return mutableListOf<UserInfo>(user)
    }
}

data class UserInfo (
    /**
     * 사용자명
     */
    var username: String = "",
    /**
     * 패스워드
     */
    var password: String = "",
    /**
     * 활성 여부: true: 활성
     */
    var enabled: Boolean = false,
    /**
     * 비밀번호 변경일자(yyyymmdd)
     */
    var passwordChangedDate: String = "",
    /**
     * 비밀번호 오류횟수
     */
    var passwordErrorCount: Int = 0,
    /**
     * Role
     */
    var roleName: String = ""
)

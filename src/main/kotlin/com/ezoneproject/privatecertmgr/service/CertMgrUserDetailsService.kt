package com.ezoneproject.privatecertmgr.service

import com.ezoneproject.privatecertmgr.dao.UserInfo
import com.ezoneproject.privatecertmgr.dao.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class CertMgrUserDetailsService : UserDetailsService {

    @Autowired
    lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("Username ${username} not found.") }
    }

    fun getUserInfo(username: String): Optional<UserInfo> {
        val userInfo = userRepository.loadUsersJson().find { user -> user.username.equals(username) } ?: return Optional.empty()
        return Optional.of(userInfo)
    }

    fun updateUserInfo(userInfo: UserInfo) {
        val usersList = userRepository.loadUsersJson()
        val findUserInfo = userRepository.loadUsersJson().find { user -> user.username.equals(userInfo.username) }

        if (findUserInfo == null) {
            // add user
        }

    }
}

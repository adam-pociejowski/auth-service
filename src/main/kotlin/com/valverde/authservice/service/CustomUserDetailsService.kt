package com.valverde.authservice.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        return User("valverde", "\$2a\$10\$Eg7NYLVadcZpi76wQZ.bZe.yddo0v1I8TrpIX6iXh9VO0Hp/BtdbO", HashSet())
    }
}
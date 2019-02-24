package com.valverde.authservice.service

import com.valverde.authservice.entity.User
import com.valverde.authservice.model.OAuthUserDetails
import com.valverde.authservice.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomUserDetailsService(val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val userOptional: Optional<User> = userRepository.findByUsername(username)
        if (userOptional.isPresent) {
            val user = userOptional.get()
            val authorities = java.util.HashSet(user.roles.map { role -> SimpleGrantedAuthority(role.name) })
            return OAuthUserDetails(user.username, user.passwordHash, authorities, user.email, user.insertDate)
        } else {
            throw RuntimeException("No user with username: $username found")
        }
    }
}
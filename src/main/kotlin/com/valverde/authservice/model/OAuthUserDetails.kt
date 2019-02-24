package com.valverde.authservice.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

data class OAuthUserDetails(private val username: String?,
                            private val password: String?,
                            private val authorities: java.util.HashSet<SimpleGrantedAuthority>,
                            private val email: String?,
                            private val creationDate: LocalDateTime? = null) : UserDetails {

    override fun getUsername(): String? = username

    override fun getPassword(): String? = password

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = ArrayList()

    override fun isEnabled(): Boolean = true
}
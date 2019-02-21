package com.valverde.authservice.configuration

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class OAuthUserDetails(private val username: String = "",
                            private val password: String = "",
                            private val email: String = "",
                            private val name: String? = null,
                            private val surname: String? = null) : UserDetails {

    override fun getUsername(): String = username

    override fun getPassword(): String = password

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = ArrayList()

    override fun isEnabled(): Boolean = true
}
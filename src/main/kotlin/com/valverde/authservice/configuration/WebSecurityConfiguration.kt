package com.valverde.authservice.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Bean
    public override fun userDetailsService(): UserDetailsService {
        return InMemoryUserDetailsManager(
                User.withDefaultPasswordEncoder()
                        .username("valverde")
                        .password("valverde")
                        .roles("USER")
                        .build())
    }
}
package com.valverde.authservice.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@Configuration
@RestController
@EnableResourceServer
class ResourceServerConfiguration : ResourceServerConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.antMatcher("/user")
                .authorizeRequests()
                .anyRequest()
                .authenticated()
    }

    @RequestMapping("/user")
    fun user(principal: Principal, auth: Authentication): Principal {
        return principal
    }
}
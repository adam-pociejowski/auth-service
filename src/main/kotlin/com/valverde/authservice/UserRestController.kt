package com.valverde.authservice

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class UserRestController {

    @RequestMapping("/user", "/me")
    fun user(principal: Principal, auth: Authentication): Principal {
        return principal
    }
}
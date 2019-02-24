package com.valverde.authservice.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import java.math.BigInteger
import java.security.KeyFactory
import java.security.KeyPair
import java.security.spec.RSAPrivateKeySpec
import java.security.spec.RSAPublicKeySpec

@Configuration
class KeyPairConfiguration(val env: Environment) {

    @Bean
    fun keyPair(): KeyPair {
        try {
            val privateExponent = env["jwt.private.exponent"]
            val modulus = env["jwt.modulus"]
            val exponent = env["jwt.exponent"]
            val publicSpec = RSAPublicKeySpec(BigInteger(modulus), BigInteger(exponent))
            val privateSpec = RSAPrivateKeySpec(BigInteger(modulus), BigInteger(privateExponent))
            val factory = KeyFactory.getInstance("RSA")
            return KeyPair(factory.generatePublic(publicSpec), factory.generatePrivate(privateSpec))
        } catch (e: Exception) {
            throw IllegalArgumentException(e)
        }
    }
}
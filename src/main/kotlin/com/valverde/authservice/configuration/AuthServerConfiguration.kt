package com.valverde.authservice.configuration

import com.valverde.authservice.client.service.CustomClientsDetailsService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import java.security.KeyPair

@Configuration
@EnableAuthorizationServer
class AuthServerConfiguration(
        authenticationConfiguration: AuthenticationConfiguration,
        val customClientsDetailsService: CustomClientsDetailsService,
        private val keyPair: KeyPair,
        @param:Value("\${security.oauth2.authorizationserver.jwt.enabled:true}") private val jwtEnabled: Boolean) : AuthorizationServerConfigurerAdapter() {

    private val authenticationManager: AuthenticationManager = authenticationConfiguration.authenticationManager

    @Throws(Exception::class)
    override fun configure(oauthServer: AuthorizationServerSecurityConfigurer?) {
        oauthServer!!
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
    }

    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients!!.withClientDetails(customClientsDetailsService)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints!!
                .authenticationManager(this.authenticationManager)
                .tokenStore(tokenStore())
        if (this.jwtEnabled) {
            endpoints.accessTokenConverter(accessTokenConverter())
        }
    }

    @Bean
    fun tokenStore(): TokenStore {
        return if (this.jwtEnabled) {
            JwtTokenStore(accessTokenConverter())
        } else {
            InMemoryTokenStore()
        }
    }

    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter {
        val converter = JwtAccessTokenConverter()
        converter.setKeyPair(this.keyPair)
        val accessTokenConverter = DefaultAccessTokenConverter()
        converter.accessTokenConverter = accessTokenConverter
        return converter
    }
}

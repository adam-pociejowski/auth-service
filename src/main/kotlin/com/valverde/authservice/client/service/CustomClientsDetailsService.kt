package com.valverde.authservice.client.service

import com.valverde.authservice.client.entity.Client
import com.valverde.authservice.client.repository.ClientRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.client.BaseClientDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomClientsDetailsService(val clientRepository: ClientRepository) : ClientDetailsService {

    @Transactional
    override fun loadClientByClientId(clientId: String?): ClientDetails {
        val clientOptional = clientRepository.findByName(clientId)
        if (clientOptional.isPresent) {
            val client: Client = clientOptional.get()
            val details = BaseClientDetails()
            details.clientId = clientId
            details.clientSecret = "{noop}"+client.secretHash
            details.setAuthorizedGrantTypes(client.grantTypes.map { grantType -> grantType.name })
            details.setResourceIds(client.resources.map { resource -> resource.name })
            details.setScope(client.scopes.map { scope -> scope.name })
            details.authorities = client.roles.map { role -> SimpleGrantedAuthority(role.name) }
            return details
        } else {
            throw RuntimeException("No client with clientId: $clientId found")
        }
    }
}
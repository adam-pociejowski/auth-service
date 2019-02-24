package com.valverde.authservice.client.repository

import com.valverde.authservice.client.entity.Client
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ClientRepository: JpaRepository<Client, Long> {
    fun findByName(name: String?): Optional<Client>
}
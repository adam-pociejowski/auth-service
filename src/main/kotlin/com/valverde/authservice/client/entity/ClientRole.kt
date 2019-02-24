package com.valverde.authservice.client.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "client_role")
data class ClientRole(
        @Id
        @GeneratedValue
        val id: Long? = null,
        val name: String? = ""
)
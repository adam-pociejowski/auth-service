package com.valverde.authservice.client.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "resource")
data class Resource(
        @Id
        @GeneratedValue
        val id: Long? = null,
        val name: String? = ""
)
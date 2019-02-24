package com.valverde.authservice.client.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "scope")
data class Scope(
        @Id
        @GeneratedValue
        val id: Long? = null,
        val name: String? = ""
)
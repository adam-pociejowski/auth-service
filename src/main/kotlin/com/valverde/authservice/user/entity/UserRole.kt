package com.valverde.authservice.user.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user_role")
data class UserRole(
        @Id
        @GeneratedValue
        val id: Long? = null,
        val name: String? = ""
)
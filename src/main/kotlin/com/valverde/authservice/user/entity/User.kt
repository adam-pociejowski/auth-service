package com.valverde.authservice.user.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue
    val id: Long? = null,
    val username: String? = "",
    val passwordHash: String? = "",
    val email: String? = "",
    val insertDate: LocalDateTime? = null) {
        @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        @JoinTable(name = "users2role",
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")])
        lateinit var roles: List<UserRole>
}


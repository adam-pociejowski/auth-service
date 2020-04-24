package com.valverde.authservice.client.entity

import javax.persistence.*

@Entity
@Table(name = "client")
data class Client(
        @Id
        @GeneratedValue
        val id: Long? = null,
        val name: String? = "",
        val secretHash: String? = "",
        val accessTokenValiditySeconds: Int? = null,
        val refreshTokenValiditySeconds: Int? = null
) {
    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(name = "client2scope",
            joinColumns = [JoinColumn(name = "client_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "scope_id", referencedColumnName = "id")])
    lateinit var scopes: List<Scope>

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "client2resource",
            joinColumns = [JoinColumn(name = "client_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "resource_id", referencedColumnName = "id")])
    lateinit var resources: List<Resource>

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "client2role",
            joinColumns = [JoinColumn(name = "client_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")])
    lateinit var roles: List<ClientRole>

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "client2auth_grant_type",
            joinColumns = [JoinColumn(name = "client_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "auth_grant_type", referencedColumnName = "id")])
    lateinit var grantTypes: List<AuthorizationGrantType>
}

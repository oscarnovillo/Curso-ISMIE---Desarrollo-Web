package org.example.backend.data.modelo

import jakarta.persistence.*

@Entity
@Table(name = "tokens")
class TokenEntity(
    @Column(unique = true)
    var token: String = "",

    @Enumerated(EnumType.STRING)
    var tokenType: TokenType = TokenType.BEARER,

    var revoked: Boolean = false,

    var expired: Boolean = false,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserEntity? = null,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    )

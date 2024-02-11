package org.example.backend.data.modelo

import jakarta.persistence.*

@Entity
@Table(name = "roles")
class RolEntity (
    @Column(name = "rol", nullable = false)
    var rol: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long = 0,
)

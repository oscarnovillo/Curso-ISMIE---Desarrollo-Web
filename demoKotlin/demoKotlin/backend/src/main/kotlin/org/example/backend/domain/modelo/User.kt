package org.example.backend.domain.modelo

import org.example.backend.data.modelo.RolEntity

data class User(
    val id: Long,
    val name: String,
    val password: String,
    val roles: Set<RolEntity>,)

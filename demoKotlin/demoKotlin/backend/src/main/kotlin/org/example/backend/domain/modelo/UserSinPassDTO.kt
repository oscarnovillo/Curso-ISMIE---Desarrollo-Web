package org.example.backend.domain.modelo

import org.example.backend.data.modelo.RolEntity

data class UserSinPassDTO (
    val id: Long,
    val name: String,
    val roles: Set<RolEntity>,)

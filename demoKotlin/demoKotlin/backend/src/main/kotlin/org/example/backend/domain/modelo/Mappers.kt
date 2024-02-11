package org.example.backend.domain.modelo

fun User.toUserSinPassDTO() = UserSinPassDTO(this.id, this.name, this.roles)

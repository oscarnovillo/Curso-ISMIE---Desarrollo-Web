package org.example.backend.data.modelo

import org.example.backend.domain.modelo.Actor
import org.example.backend.domain.modelo.Pelicula
import org.example.backend.domain.modelo.User
import org.example.backend.domain.modelo.UserSinPassDTO
import java.util.stream.Collectors

fun User.toUserEntity() = UserEntity(name = this.name,password = this.password, id = this.id)
fun UserEntity.toUser() = User(this.id,this.name, this.password,this.roles)
fun UserEntity.toUserSinPassDTO() = UserSinPassDTO(this.id,this.name, this.roles)


fun Pelicula.toPeliculaEntity() = PeliculaEntity(id = this.id, titulo = this.titulo)
fun PeliculaEntity.toPelicula() = Pelicula(this.id, this.titulo)
fun Actor.toActorEntity() = ActorEntity(id = this.id, nombre = this.nombre)
fun ActorEntity.toActor() = Actor(this.id, this.nombre)

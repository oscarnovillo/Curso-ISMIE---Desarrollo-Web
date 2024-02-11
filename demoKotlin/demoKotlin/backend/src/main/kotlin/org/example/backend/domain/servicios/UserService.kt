package org.example.backend.domain.servicios

import org.example.backend.data.modelo.UserEntity
import org.example.backend.data.modelo.toUser
import org.example.backend.data.modelo.toUserEntity
import org.example.backend.data.modelo.toUserSinPassDTO
import org.example.backend.data.repositories.UserRepository
import org.example.backend.domain.modelo.User
import org.example.backend.domain.modelo.UserSinPassDTO
import org.springframework.stereotype.Service


@Service
class UserService(
    val userRepository: UserRepository,
) {

    fun createUser(user : User) : User {
        user.toUserEntity().let {
            //validaciones
            it.name.let{
                if (it == "") {
                    throw RuntimeException("El nombre no puede ser vacio")
                }
            }

            return userRepository.save(it).toUser()
        }
    }

    fun getUsers(): List<User> =
        userRepository.findAllWithRoles().map { it.toUser() }


    fun getUsersDto(): List<UserSinPassDTO> =

        userRepository.findAllWithRoles().map { it.toUserSinPassDTO() }




}

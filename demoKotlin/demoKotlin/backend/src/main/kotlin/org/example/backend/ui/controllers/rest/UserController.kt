package org.example.backend.ui.controllers.rest

import jakarta.annotation.security.RolesAllowed
import jakarta.servlet.http.HttpSession
import org.example.backend.domain.modelo.User
import org.example.backend.domain.modelo.UserSinPassDTO
import org.example.backend.domain.servicios.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,

) {

    @GetMapping
    @RolesAllowed("ADMIN")
    fun getUsers(): List<User> = userService.getUsers()

    @GetMapping("/dto")
    @RolesAllowed("USER")
    fun getUsersDto(): List<UserSinPassDTO> =
        userService.getUsersDto()


    @PostMapping
    fun postUsers(@RequestBody user: User, session: HttpSession): ResponseEntity<User> {
        val userSaved = userService.createUser(user)
        return ResponseEntity(userSaved, HttpStatus.CREATED)
    }
}

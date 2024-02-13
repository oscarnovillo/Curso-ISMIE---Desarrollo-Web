package com.example.routing

import com.example.dao.ExposedUser
import com.example.dao.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import joseluisgs.dev.config.AppConfig
import org.jetbrains.exposed.sql.Database
import org.koin.ktor.ext.inject


fun Application.userRoutes(){
    val appConfig = AppConfig()
    val database = Database.connect(
        url = appConfig.applicationConfiguration.property("database.url").getString(),
        user = appConfig.applicationConfiguration.property("database.user").getString(),
        driver = appConfig.applicationConfiguration.property("database.driver").getString(),
        password = appConfig.applicationConfiguration.property("database.password").getString()
    )


    val userRepository : UserRepository by inject()

    routing {
        // Create user
        post("/users") {
            val user = call.receive<ExposedUser>()
            val id = userRepository.create(user)
            call.respond(HttpStatusCode.Created, id)
        }
        get("/users") {
            val usuarios = userRepository.readAll()
            call.respond(HttpStatusCode.OK, usuarios ?: emptyList())
        }
        // Read user
        get("/users/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val user = userRepository.read(id)
            if (user != null) {
                call.respond(HttpStatusCode.OK, user)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        // Update user
        put("/users/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val user = call.receive<ExposedUser>()
            userRepository.update(id, user)
            call.respond(HttpStatusCode.OK)
        }
        // Delete user
        delete("/users/{id}") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            userRepository.delete(id)
            call.respond(HttpStatusCode.OK)
        }
    }



}

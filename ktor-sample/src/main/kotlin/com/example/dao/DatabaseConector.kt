package com.example.dao

import com.example.service.UserService
import joseluisgs.dev.config.AppConfig
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.annotation.Singleton

@Singleton
class DatabaseConector(
    private val appConfig : AppConfig
) {


    fun init() {
        val database = Database.connect(
            url = appConfig.applicationConfiguration.property("database.url").getString(),
            user = appConfig.applicationConfiguration.property("database.user").getString(),
            driver = appConfig.applicationConfiguration.property("database.driver").getString(),
            password = appConfig.applicationConfiguration.property("database.password").getString()
        )
        transaction(database) {
            SchemaUtils.create(UserService.Users)
        }
    }



    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

}

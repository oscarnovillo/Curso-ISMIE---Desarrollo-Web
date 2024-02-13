package com.example.dao

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.koin.core.annotation.Factory

@Factory
class UserRepository(private val database: DatabaseConector) {
    object Users : Table() {
        val id = integer("id").autoIncrement()
        val name = varchar("name", length = 50)
        val age = integer("age")

        override val primaryKey = PrimaryKey(id)
    }



    suspend fun create(user: ExposedUser): Int = database.dbQuery {
        Users.insert {
            it[name] = user.name
            it[age] = user.age
        }[Users.id]
    }

    suspend fun read(id: Int): ExposedUser? {
        return database.dbQuery {
            Users.select { Users.id eq id }
                .map { ExposedUser(it[Users.name], it[Users.age]) }
                .singleOrNull()
        }
    }

    suspend fun readAll(): List<ExposedUser>? {
        return database.dbQuery {
            Users.selectAll()
                .map { ExposedUser(it[Users.name], it[Users.age]) }

        }
    }

    suspend fun update(id: Int, user: ExposedUser) {
        database.dbQuery {
            Users.update({ Users.id eq id }) {
                it[name] = user.name
                it[age] = user.age
            }
        }
    }

    suspend fun delete(id: Int) {
        database.dbQuery {
            Users.deleteWhere { Users.id.eq(id) }
        }
    }
}

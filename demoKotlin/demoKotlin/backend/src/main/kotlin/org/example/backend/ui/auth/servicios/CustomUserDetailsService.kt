package org.example.backend.ui.auth.servicios

import org.example.backend.data.modelo.RolEntity
import org.example.backend.data.modelo.UserEntity
import org.example.backend.data.repositories.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class CustomUserDetailsService(
  private val userRepository: UserRepository
) : UserDetailsService {

  override fun loadUserByUsername(username: String): UserDetails =
    userRepository.findByName(username)
      ?.mapToUserDetails()
      ?: throw UsernameNotFoundException("Not found!")

  private fun UserEntity.mapToUserDetails(): UserDetails =

    User.builder()
      .username(this.name)
      .password(this.password)
      .roles(this.roles.map{it.rol}.joinToString(","))
      .build()
}

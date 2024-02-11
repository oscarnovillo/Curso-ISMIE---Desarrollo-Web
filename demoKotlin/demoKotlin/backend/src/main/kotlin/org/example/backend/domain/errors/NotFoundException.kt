package org.example.backend.domain.errors

class NotFoundException(override val message: String = "Not found") : RuntimeException(){

}

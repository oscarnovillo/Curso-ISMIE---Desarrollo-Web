package org.example.backend.ui.errors

import org.example.backend.domain.errors.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorHandling {

    @ExceptionHandler(NotFoundException::class)
    fun handleException(e: NotFoundException): ResponseEntity<ApiError> {
        val apiError = ApiError(e.message)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError)
    }
}

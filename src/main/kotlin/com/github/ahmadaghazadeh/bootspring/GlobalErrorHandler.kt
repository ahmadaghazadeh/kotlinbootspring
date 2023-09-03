package com.github.ahmadaghazadeh.bootspring

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@Component
@ControllerAdvice
class GlobalErrorHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
       val errors= ex.bindingResult.allErrors
            .map {
                error-> error.defaultMessage!!
            }.sorted()

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(errors.joinToString(", "){it})
    }
}
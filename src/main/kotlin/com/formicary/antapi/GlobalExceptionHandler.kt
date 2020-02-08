package com.formicary.antapi

import com.formicary.antapi.exception.UserNotFoundException
import com.formicary.antapi.exception.ValidateException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@ControllerAdvice
@RestController
class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUserNotFoundException(ex: RuntimeException) = getMessage(ex)

    @ExceptionHandler(ValidateException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidateException(ex: RuntimeException) = getMessage(ex)

    @ExceptionHandler(EmptyResultDataAccessException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleEmptyResultDataAccessException(ex: RuntimeException) = getMessage(ex)

    private fun getMessage(ex: RuntimeException) = ex.message


}
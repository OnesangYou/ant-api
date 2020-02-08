package com.formicary.antapi.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
data class User (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

        @get: NotBlank
    val nickName: String = "",

        @get: NotBlank
    val authId: String = "",

        @get: NotBlank
    val authType: String = "",

        @get: NotBlank
    val passWord: String = ""
)
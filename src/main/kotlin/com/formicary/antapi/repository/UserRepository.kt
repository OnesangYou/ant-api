package com.formicary.antapi.repository

import com.formicary.antapi.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findUserByAuthTypeAndAuthId(authType: String = "email", authId: String): User
}
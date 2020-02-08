package com.formicary.antapi.util

import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordHashGenerator : PasswordEncoder {
    override fun encode(rawPassword: CharSequence): String {

        return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(12))
    }

    override fun matches(rawPassword: CharSequence, encodedPassword: String): Boolean {
        return BCrypt.checkpw(rawPassword.toString(), encodedPassword)
    }
}
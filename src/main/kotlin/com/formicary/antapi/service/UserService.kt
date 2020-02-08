package com.formicary.antapi.service

import com.formicary.antapi.exception.ValidateException
import com.formicary.antapi.model.User
import com.formicary.antapi.model.UserInfo
import com.formicary.antapi.model.UserResponseInfo
import com.formicary.antapi.repository.UserRepository
import com.formicary.antapi.util.PasswordHashGenerator
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val jwtService: JwtService, private val passwordHashGenerator: PasswordHashGenerator) {

    private fun getUserResponseInfo(user: User): UserResponseInfo {
        val responseInfo = UserInfo(user.authType, user.authId, user.nickName)
        val token = jwtService.createToken(responseInfo)

        return UserResponseInfo(token, responseInfo)
    }

    fun loginByEmail(email: String, password: String): UserResponseInfo {
        val user = userRepository.findUserByAuthTypeAndAuthId(authId = email)
        if (!passwordHashGenerator.matches(password, user.passWord)) {
            throw ValidateException("패스워드가 일치하지 않음")
        }
        return getUserResponseInfo(user)
    }

    fun joinByEmail(email: String, password: String, nickName: String): UserResponseInfo {
        val encodePassword = passwordHashGenerator.encode(password)
        val user = User(authType = "email", authId = email, passWord = encodePassword, nickName = nickName)
        val newUser = userRepository.save(user)
        return getUserResponseInfo(newUser)
    }

    fun loginBySocial(authType: String, authId: String): UserResponseInfo {
        try{
            val user = userRepository.findUserByAuthTypeAndAuthId(authType = authType, authId = authId)
            return getUserResponseInfo(user)
        } catch (ex: EmptyResultDataAccessException) { // DB에 없는 경우
            return UserResponseInfo(message = "FirstUser, Do Join")
        }
    }

    fun joinBySocial(authType: String, authId: String, nickName: String): UserResponseInfo {
        val user = User(authType = authType, authId = authId, nickName = nickName)
        val newUser = userRepository.save(user)
        return getUserResponseInfo(newUser)
    }

}

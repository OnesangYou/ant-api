package com.formicary.antapi.controller

import com.formicary.antapi.model.EmailJoinRequestInfo
import com.formicary.antapi.model.EmailLoginRequestInfo
import com.formicary.antapi.model.SocialJoinRequestInfo
import com.formicary.antapi.model.SocialLoginRequestInfo
import com.formicary.antapi.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid



@RestController
@RequestMapping("/api/v1/users")
class UserController(private val userService: UserService) {

    @PostMapping("/email/login")
    fun loginByEmail(@Valid @RequestBody info: EmailLoginRequestInfo) =
            userService.loginByEmail(email = info.email, password = info.password)

    @PostMapping("/email")
    fun joinByEmail(@Valid @RequestBody info: EmailJoinRequestInfo) =
            userService.joinByEmail(email = info.email, password = info.password, nickName = info.nickName)

    @PostMapping("/social/login")
    fun loginBySocial(@Valid @RequestBody info: SocialLoginRequestInfo) =
            userService.loginBySocial(info.authType, info.authId)

    @PostMapping("/social")
    fun joinBySocial(@Valid @RequestBody info: SocialJoinRequestInfo) =
            userService.joinBySocial(info.authType, info.authId, info.nickName)

}


package com.formicary.antapi.model

import io.swagger.annotations.ApiModelProperty

data class UserResponseInfo(
        val token: String? = null,
        val userInfo: UserInfo? = null,
        @ApiModelProperty(notes = "최초 로그인 시도인 경우, 'FirstUser, Do Join' 값")
        val message: String? = null
)
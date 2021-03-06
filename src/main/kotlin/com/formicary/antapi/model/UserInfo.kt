package com.formicary.antapi.model

data class UserInfo(val authType: String, val authId: String, val nickName: String)

enum class AuthType{
    email, google, facebook
}
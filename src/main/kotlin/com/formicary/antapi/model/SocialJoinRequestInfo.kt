package com.formicary.antapi.model

import io.swagger.annotations.ApiModelProperty

data class SocialJoinRequestInfo(
        @ApiModelProperty(example = "google")
        val authType: String,
        @ApiModelProperty(example = "20391")
        val authId: String,
        @ApiModelProperty(example = "lukas")
        val nickName: String
)
package com.formicary.antapi.model

import io.swagger.annotations.ApiModelProperty

data class SocialLoginRequestInfo(
        @ApiModelProperty(example = "google")
        val authType: String,
        @ApiModelProperty(example = "20391")
        val authId: String
)
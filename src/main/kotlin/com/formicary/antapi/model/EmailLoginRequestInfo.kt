package com.formicary.antapi.model

import io.swagger.annotations.ApiModelProperty

data class EmailLoginRequestInfo(
        @ApiModelProperty(example = "kbj7353@naver.com")
        val email: String,
        @ApiModelProperty(example = "123456")
        val password: String
)


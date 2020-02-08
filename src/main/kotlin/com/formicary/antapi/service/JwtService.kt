package com.formicary.antapi.service

import com.formicary.antapi.exception.UnauthorizedException
import com.formicary.antapi.model.UserInfo
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*


@Service
class JwtService {

    private val SALT = "luvookSecret"

    private fun <T> createToken(key:String, data:T):String {
        val jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("regDate", System.currentTimeMillis())
                .setSubject("ant-api")
                .claim(key, data)
                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .compact()
        return jwt
    }

    fun createToken(info: UserInfo): String {
        return createToken("emailUser", info)
    }

    private fun generateKey():ByteArray? {
        val key: ByteArray?
        key = SALT.toByteArray(charset("UTF-8"))
        return key
    }

    fun isUsable(jwt:String):Boolean {
        try
        {
            val claims = Jwts.parser()
                    .setSigningKey(this.generateKey())
                    .parseClaimsJws(jwt)
            return true

        }
        catch (e:Exception) {

            throw UnauthorizedException("토큰이 유효하지 않습니다")

        }

    }

    fun get(key:String):Map<String, Any> {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        val jwt = request.getHeader("Authorization")
        var claims:Jws<Claims>? = null
        try
        {
            claims = Jwts.parser()
                    .setSigningKey(SALT.toByteArray(charset("UTF-8")))
                    .parseClaimsJws(jwt)
        }
        catch (e:Exception) {
            throw UnauthorizedException("토큰이 유효하지 않습니다")
        }

        val value = claims!!.body.get(key) as LinkedHashMap<String, Any>
        return value
    }
}
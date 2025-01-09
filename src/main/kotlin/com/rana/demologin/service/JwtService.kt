package com.rana.demologin.service

import com.rana.demologin.model.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService(
    @Value("\${jwt.secret}")
    private val secretKey: String,
    
    @Value("\${jwt.expiration}")
    private val jwtExpiration: Long
) {
    fun generateToken(user: User): String {
        return Jwts.builder()
            .setSubject(user.username)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtExpiration))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact()
    }
    
    private fun getSigningKey() = Keys.hmacShaKeyFor(secretKey.toByteArray())
} 
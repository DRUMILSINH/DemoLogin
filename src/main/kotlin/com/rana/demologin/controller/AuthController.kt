package com.rana.demologin.controller

import com.rana.demologin.dto.AuthResponse
import com.rana.demologin.dto.LoginRequest
import com.rana.demologin.dto.RegisterRequest
import com.rana.demologin.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<AuthResponse> {
        val token = authService.login(request)
        return ResponseEntity.ok(AuthResponse(token))
    }
    
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<Unit> {
        authService.register(request)
        return ResponseEntity.ok().build()
    }
} 
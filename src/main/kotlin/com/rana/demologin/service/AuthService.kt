package com.rana.demologin.service

import com.rana.demologin.dto.LoginRequest
import com.rana.demologin.dto.RegisterRequest
import com.rana.demologin.model.User
import com.rana.demologin.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService
) {
    fun login(request: LoginRequest): String {
        val user = userRepository.findByUsername(request.username)
            ?: throw RuntimeException("User not found")
            
        if (!passwordEncoder.matches(request.password, user.password)) {
            throw RuntimeException("Invalid password")
        }
        
        return jwtService.generateToken(user)
    }
    
    fun register(request: RegisterRequest) {
        if (userRepository.existsByUsername(request.username)) {
            throw RuntimeException("Username already exists")
        }
        
        if (userRepository.existsByEmail(request.email)) {
            throw RuntimeException("Email already exists")
        }
        
        val user = User(
            username = request.username,
            password = passwordEncoder.encode(request.password),
            email = request.email,
            fullName = request.fullName
        )
        
        userRepository.save(user)
    }
} 
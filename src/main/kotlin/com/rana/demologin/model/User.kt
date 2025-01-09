package com.rana.demologin.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(unique = true)
    val username: String = "",
    
    val password: String = "",
    val email: String = "",
    val fullName: String = ""
) 
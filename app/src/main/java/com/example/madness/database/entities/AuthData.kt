package com.example.vaccination.database.entities

data class AuthData(
    var authId: Int,
    var userId: Int,
    var mail: String? = null,
    var password: String? = null,
)

package com.example.vaccination.database.dao

import com.example.vaccination.database.entities.AuthData

interface AuthDAO {
    fun insertAuth(auth: AuthData): Boolean
    fun deleteAuth(userId: Int): Boolean
}
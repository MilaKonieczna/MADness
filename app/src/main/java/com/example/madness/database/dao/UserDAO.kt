package com.example.vaccination.database.dao

import com.example.vaccination.database.entities.UserData

interface UserDAO {
      fun insertUser(user: UserData): Boolean
      fun deleteUser(userId: Int): Boolean
      fun getUserId(username: String): Int
}
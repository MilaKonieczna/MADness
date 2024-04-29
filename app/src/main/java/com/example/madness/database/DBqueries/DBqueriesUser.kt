package com.example.vaccination.database.DBqueries

import com.example.vaccination.database.dao.UserDAO
import com.example.vaccination.database.entities.UserData
import com.mysql.jdbc.Connection

class DBqueriesUser(private val connection: Connection) : UserDAO {
    override fun insertUser(user: UserData): Boolean {
        val query = "CALL insertUser(?, ?, ?)"
        val preparedStatement = connection.prepareCall(query)
        preparedStatement.setInt(1, user.userId)
        preparedStatement.setString(2, user.username)
        preparedStatement.setString(3, user.name)

        val result = !preparedStatement.execute()
        preparedStatement.close()

        return result
    }

    override fun deleteUser(userId: Int): Boolean {
        val query = "CALL deleteAuth(?)"
        val preparedStatement = connection.prepareCall(query)
        preparedStatement.setInt(1, userId)

        return preparedStatement.executeUpdate() > 0
    }

    override fun getUserId(username: String): Int {
        val query = "CALL getUserIdByUsername(?)"
        connection.prepareCall(query).use { preparedStatement ->
            preparedStatement.setString(1, username)
            val resultSet = preparedStatement.executeQuery()
            if (resultSet.next()) {
                return resultSet.getInt("user_id")
            } else {
                throw NoSuchElementException("User not found with username: $username")
            }
    }}

}
package com.example.vaccination.database.DBqueries

import com.example.vaccination.database.dao.AuthDAO
import com.example.vaccination.database.entities.AuthData
import com.mysql.jdbc.Connection
import java.sql.ResultSet

class DBqueriesAuth(private val connection: Connection) : AuthDAO {
    override fun insertAuth(auth: AuthData): Boolean {
        val query = "CALL insertAuth(?, ?, ?, ?)"
        val preparedStatement = connection.prepareCall(query)
        preparedStatement.setInt(1, auth.authId)
        preparedStatement.setInt(2, auth.userId)
        preparedStatement.setString(3, auth.mail)
        preparedStatement.setString(4, auth.password)

        val result = !preparedStatement.execute()
        preparedStatement.close()

        return result
    }

    override fun deleteAuth(userId: Int): Boolean {
        val query = "CALL deleteAuth(?)"
        val preparedStatement = connection.prepareCall(query)
        preparedStatement.setInt(1, userId)

        return preparedStatement.executeUpdate() > 0
    }

    private fun mapResultSetToAuthData(resultSet: ResultSet): AuthData {
        val authId = resultSet.getInt("auth_id")
        val userId = resultSet.getInt("user_id")
        val mail = resultSet.getString("mail")
        val password = resultSet.getString("password")

        return AuthData(authId, userId, mail, password)
    }
}
package com.example.vaccination.database.DBqueries

import com.example.vaccination.database.dao.VaxAddInfoDAO
import com.example.vaccination.database.entities.VaxAddInfoData
import com.mysql.jdbc.Connection

class DBqueriesVaxAddInfo(private val connection: Connection): VaxAddInfoDAO {
    override fun insertVaxInfo(vaxAddInfo: VaxAddInfoData): Boolean {
        val query = "CALL insertVaxAddInfo(?, ?, ?, ?)"
        val preparedStatement = connection.prepareCall(query)
        preparedStatement.setInt(1, vaxAddInfo.vaxAddInfoId)
        preparedStatement.setInt(2, vaxAddInfo.vaxId)
        preparedStatement.setString(3, vaxAddInfo.vaxNameCompany)
        preparedStatement.setString(4, vaxAddInfo.vaxDescription)

        val result = !preparedStatement.execute()
        preparedStatement.close()

        return result
    }

    override fun deleteVaxInfo(vaxId: Int): Boolean {
        val query = "CALL deleteAuth(?)"
        val preparedStatement = connection.prepareCall(query)
        preparedStatement.setInt(1, vaxId)

        return preparedStatement.executeUpdate() > 0
    }

    override fun getVaxAddInfo(vaxId: Int): VaxAddInfoData {
        val query = "CALL getVaxAddInfo(?)"
        connection.prepareCall(query).use { preparedStatement ->
            preparedStatement.setInt(1, vaxId)
            val resultSet = preparedStatement.executeQuery()

            if (resultSet.next()) {
                val vaxAddInfoId = resultSet.getInt("vaxAddInfoId")
                val vaxId = resultSet.getInt("vaxId")
                val vaxNameCompany = resultSet.getString("vaxNameCompany")
                val vaxDescription = resultSet.getString("vaxDescription")

                return VaxAddInfoData(vaxAddInfoId, vaxId, vaxNameCompany, vaxDescription)
            } else {
                throw NoSuchElementException("No vaccine found with ID: $vaxId")
            }
        }
    }

}
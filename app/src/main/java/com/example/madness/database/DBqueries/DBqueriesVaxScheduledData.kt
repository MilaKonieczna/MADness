package com.example.vaccination.database.DBqueries

import com.example.vaccination.database.dao.VaxScheduledDataDAO
import com.example.vaccination.database.entities.VaccinationsData
import com.example.vaccination.database.entities.VaxScheduledData
import com.example.vaccination.database.entities.VaxStatus
import java.sql.Connection

class DBqueriesVaxScheduledData(private val connection: Connection) : VaxScheduledDataDAO {
    override fun getAllUserVaxScheduled(userId: Int): Set<VaxScheduledData?>? {
        val query = "CALL getAllUserVaxScheduled(?)"
        connection.prepareCall(query).use { preparedStatement ->
            preparedStatement.setInt(1, userId)
            val resultSet = preparedStatement.executeQuery()
            val vaxScheduledData = mutableSetOf<VaxScheduledData>()
            while (resultSet.next()) {
                val vaxScheduledId = resultSet.getInt("vax_scheduled_id")
                val vaxId = resultSet.getInt("vax_id")
                val userId = resultSet.getInt("user_id")
                val vaxScheduledDate = resultSet.getDate("dateOfFirstDose")
                val vaxScheduledStatus = resultSet.getString("statusOfVax")
                val vaxScheduled = VaxScheduledData(vaxScheduledId, vaxId, userId, vaxScheduledDate, vaxScheduledStatus)
                vaxScheduledData.add(vaxScheduled)
            }
            return vaxScheduledData
        }
    }

    override fun insertVaxScheduled(vaxScheduledData: VaxScheduledData): Boolean {
        val query = "CALL insertVaxScheduled(?, ?, ?, ?, ?)"
        val preparedStatement = connection.prepareCall(query)
        preparedStatement.setInt(1, vaxScheduledData.vaxScheduledId)
        preparedStatement.setInt(2, vaxScheduledData.vaxId)
        preparedStatement.setInt(3, vaxScheduledData.userId)
        val sqlDate = java.sql.Date(vaxScheduledData.dateOfFirstDose.time)
        preparedStatement.setDate(4, sqlDate)
        preparedStatement.setString(5, vaxScheduledData.statusOfVax)

        val result = preparedStatement.executeUpdate()

        return result > 0
    }

    override fun getAllUserVaccinations(userId: Int): Set<VaxScheduledData?> {
        val query = "CALL getAllUserVaccinations(?)"
        connection.prepareCall(query).use { preparedStatement ->
            preparedStatement.setInt(1, userId)
            val resultSet = preparedStatement.executeQuery()
            val vaccinationsData = mutableSetOf<VaxScheduledData>()
            while (resultSet.next()) {
                val vaxId = resultSet.getInt("vax_scheduled_id")
                val vaxName = resultSet.getInt("vax_id")
                val vaxDescription = resultSet.getInt("user_id")
                val vaxDose = resultSet.getDate("date_of_first_dose")
                val vaxInterval = resultSet.getString("vax_status")
                val vax = VaxScheduledData(vaxId, vaxName, vaxDescription, vaxDose, vaxInterval)
                vaccinationsData.add(vax)
            }
            return vaccinationsData
        }
    }

    override fun getAllUserToDoVaxScheduled(userId: Int): Set<VaxScheduledData?>? {
        val query = "CALL getAllUserToDoVaxScheduled(?)"
        connection.prepareCall(query).use { preparedStatement ->
            preparedStatement.setInt(1, userId)
            val resultSet = preparedStatement.executeQuery()
            val vaxScheduledData = mutableSetOf<VaxScheduledData>()
            while (resultSet.next()) {
                val vaxScheduledId = resultSet.getInt("vax_scheduled_id")
                val vaxId = resultSet.getInt("vax_id")
                val userId = resultSet.getInt("user_id")
                val vaxScheduledDate = resultSet.getDate("date_of_first_dose")
                val vaxScheduledStatus = resultSet.getString("vax_status")
                val vaxScheduled = VaxScheduledData(vaxScheduledId, vaxId, userId, vaxScheduledDate, vaxScheduledStatus)
                vaxScheduledData.add(vaxScheduled)
            }
            return vaxScheduledData
        }
    }

    override fun getAllUserDoneVaxScheduled(userId: Int): Set<VaxScheduledData?>? {
        val query = "CALL getAllUserDoneVaxScheduled(?)"
        connection.prepareCall(query).use { preparedStatement ->
            preparedStatement.setInt(1, userId)
            val resultSet = preparedStatement.executeQuery()
            val vaxScheduledData = mutableSetOf<VaxScheduledData>()
            while (resultSet.next()) {
                val vaxScheduledId = resultSet.getInt("vax_scheduled_id")
                val vaxId = resultSet.getInt("vax_id")
                val userId = resultSet.getInt("user_id")
                val vaxScheduledDate = resultSet.getDate("date_of_first_dose")
                val vaxScheduledStatus = resultSet.getString("vax_status")
                val vaxScheduled = VaxScheduledData(vaxScheduledId, vaxId, userId, vaxScheduledDate, vaxScheduledStatus)
                vaxScheduledData.add(vaxScheduled)
            }
            return vaxScheduledData
        }
    }

    override fun getAllUserProgressVaxScheduled(userId: Int): Set<VaxScheduledData?>? {
        val query = "CALL getAllUserProgressVaxScheduled(?)"
        connection.prepareCall(query).use { preparedStatement ->
            preparedStatement.setInt(1, userId)
            val resultSet = preparedStatement.executeQuery()
            val vaxScheduledData = mutableSetOf<VaxScheduledData>()
            while (resultSet.next()) {
                val vaxScheduledId = resultSet.getInt("vax_scheduled_id")
                val vaxId = resultSet.getInt("vax_id")
                val userId = resultSet.getInt("user_id")
                val vaxScheduledDate = resultSet.getDate("date_of_first_dose")
                val vaxScheduledStatus = resultSet.getString("vax_status")
                val vaxScheduled = VaxScheduledData(vaxScheduledId, vaxId, userId, vaxScheduledDate, vaxScheduledStatus)
                vaxScheduledData.add(vaxScheduled)
            }
            return vaxScheduledData
        }
    }

    override fun updateVaxScheduledStatus(vaxId: Int, userId: Int): Boolean {
        val query = "CALL updateVaxScheduledStatus(?, ?)"
        val preparedStatement = connection.prepareCall(query)
        preparedStatement.setInt(1, vaxId)
        preparedStatement.setInt(2, userId)

        val result = preparedStatement.executeUpdate()

        return result > 0
    }

    override fun deleteVaxScheduled(id: Int): Boolean {
        val query = "CALL deleteVaxScheduled(?)"
        val preparedStatement = connection.prepareCall(query)
        preparedStatement.setInt(1, id)

        val result = preparedStatement.executeUpdate()

        return result > 0
    }

}
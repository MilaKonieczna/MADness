package com.example.vaccination.database.DBqueries

import com.example.vaccination.database.dao.VaccinationsDAO
import com.example.vaccination.database.entities.VaccinationsData
import com.mysql.jdbc.Connection

class DBqueriesVaccination(private val connection: Connection): VaccinationsDAO{
    override fun insertVaccination(vax: VaccinationsData): Boolean {
        val query = "CALL insertVaccination(?, ?, ?, ?, ?, ?)"
        val preparedStatement = connection.prepareCall(query)
        preparedStatement.setInt(1, vax.vaxId)
        preparedStatement.setInt(2, vax.noOfDoses)
        preparedStatement.setInt(3, vax.timeBetweenDoses)

        val result = !preparedStatement.execute()
        preparedStatement.close()

        return result
    }

    override fun deleteVaccination(vaxId: Int): Boolean {
        val query = "CALL deleteVaccination(?)"
        val preparedStatement = connection.prepareCall(query)
        preparedStatement.setInt(1, vaxId)

        return preparedStatement.executeUpdate() > 0
    }

    override fun getNumberOfDoses(vaxId: Int): Int {
        val query = "CALL getNumberOfDoses(?)"
        connection.prepareCall(query).use { preparedStatement ->
            preparedStatement.setInt(1, vaxId)
            val resultSet = preparedStatement.executeQuery()
            if (resultSet.next()) {
                return resultSet.getInt("no_of_doses")
            } else {
                throw NoSuchElementException("Vaccination not found with vaxId: $vaxId")
            }
        }
    }

    override fun getTimeBetweenDoses(vaxId: Int): Int {
        val query = "CALL getNumberOfDoses(?)"
        connection.prepareCall(query).use { preparedStatement ->
            preparedStatement.setInt(1, vaxId)
            val resultSet = preparedStatement.executeQuery()
            if (resultSet.next()) {
                return resultSet.getInt("no_of_doses")
            } else {
                throw NoSuchElementException("Vaccination not found with vaxId: $vaxId")
            }
        }
    }

    override fun updateNoOfDosesVaccination(vaxId: Int, noOfDoses: Int): Boolean {
        val query = "CALL updateNoOfDosesVaccination(?, ?)"
        val preparedStatement = connection.prepareCall(query)
        preparedStatement.setInt(1, vaxId)
        preparedStatement.setInt(2, noOfDoses)

        return preparedStatement.executeUpdate() > 0
    }

    override fun updateTimeBetweenDosesVaccination(vaxId: Int, timeBetweenDoses: Int): Boolean {
        val query = "CALL updateTimeBetweenDosesVaccination(?, ?)"
        val preparedStatement = connection.prepareCall(query)
        preparedStatement.setInt(1, vaxId)
        preparedStatement.setInt(2, timeBetweenDoses)

        return preparedStatement.executeUpdate() > 0
    }

    override fun getVaccination(vaxId: Int): VaccinationsData? {
        val query = "CALL getVaccination(?)"
        connection.prepareCall(query).use { preparedStatement ->
            preparedStatement.setInt(1, vaxId)
            val resultSet = preparedStatement.executeQuery()

            if (resultSet.next()) {
                val vaxId = resultSet.getInt("vax_id")
                val noOfDoses = resultSet.getInt("no_of_doses")
                val timeBetweenDoses = resultSet.getInt("time_between_doses")

                return VaccinationsData(vaxId, noOfDoses, timeBetweenDoses)
            } else {
                throw NoSuchElementException("No vaccine found with ID: $vaxId")
            }
        }
    }

    override fun getAllVaccinations(): Set<VaccinationsData?>? {
        val query = "CALL getVaccination(?)"
        connection.prepareCall(query).use { preparedStatement ->
            val resultSet = preparedStatement.executeQuery()
            val vaccinations = mutableSetOf<VaccinationsData?>()

            while (resultSet.next()) {
                val vaxId = resultSet.getInt("vax_id")
                val noOfDoses = resultSet.getInt("no_of_doses")
                val timeBetweenDoses = resultSet.getInt("time_between_doses")

                vaccinations.add(VaccinationsData(vaxId, noOfDoses, timeBetweenDoses))
            }

            return vaccinations
        }
    }
}
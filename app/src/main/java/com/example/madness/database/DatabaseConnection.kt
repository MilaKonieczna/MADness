package com.example.vaccination.database

//UNDER PROGRESS - MIGHT BE REDUNDANT
//UNDER PROGRESS - MIGHT BE REDUNDANT
//UNDER PROGRESS - MIGHT BE REDUNDANT
//UNDER PROGRESS - MIGHT BE REDUNDANT
//UNDER PROGRESS - MIGHT BE REDUNDANT
//UNDER PROGRESS - MIGHT BE REDUNDANT
//UNDER PROGRESS - MIGHT BE REDUNDANT
//UNDER PROGRESS - MIGHT BE REDUNDANT
//UNDER PROGRESS - MIGHT BE REDUNDANT
//UNDER PROGRESS - MIGHT BE REDUNDANT
//UNDER PROGRESS - MIGHT BE REDUNDANT
//UNDER PROGRESS - MIGHT BE REDUNDANT
//UNDER PROGRESS - MIGHT BE REDUNDANT

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
object TestFreeSQLServer {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver")
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }

        // Connection details
        val urlSB = StringBuilder("jdbc:mysql://")
        urlSB.append("sql11.freesqldatabase.com:3306/")
        urlSB.append("sql11698479?") // database name
        urlSB.append("useUnicode=true&characterEncoding=utf-8")
        urlSB.append("&user=sql11698479") // your user name
        urlSB.append("&password=WDStXNVDaD") // generate password
        urlSB.append("&serverTimezone=CET")
        val connectionUrl = urlSB.toString()
        try {
            val conn =
                DriverManager.getConnection(connectionUrl)
                    conn.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    private fun checkAndAddColumn(conn: Connection, table:
    String ,newColumn: String) {
        try {
            val metaData = conn.metaData
            val resultSet = metaData.getColumns(null, null,
                table, newColumn)
            if (resultSet.next()) {
                println("Column $newColumn already exists in table $table.")
            } else {
                val alterTableQuery = "ALTER TABLE $table ADD COLUMN $newColumn DOUBLE"
                val statement = conn.createStatement()
                statement.executeUpdate(alterTableQuery)
                println("New column $newColumn has been successfully added to table $table.")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    private fun displayTables(conn: Connection) {
        try {
            println("Tables:")
            val showTablesST = conn.prepareStatement("SHOW TABLES")
                val rs1 = showTablesST.executeQuery()
            while (rs1.next()) {
                val s = rs1.getString(1)
                print("$s ")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    @Throws(SQLException::class)
    private fun printResultSet(resultSet: ResultSet) {
        val rsmd = resultSet.metaData
        val columnsNumber = rsmd.columnCount
        while (resultSet.next()) {
            for (i in 1..columnsNumber) {
                if (i > 1) print(", ")
                val columnValue = resultSet.getString(i)
                print(rsmd.getColumnName(i) + ": " +
                        columnValue)
            }
            println("")
        }
        println("")
    }
}
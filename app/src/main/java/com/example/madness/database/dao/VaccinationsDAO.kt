package com.example.vaccination.database.dao

import com.example.vaccination.database.entities.VaccinationsData

interface VaccinationsDAO {
    fun insertVaccination(vax: VaccinationsData): Boolean
    fun deleteVaccination(vaxId: Int): Boolean
    fun getNumberOfDoses(vaxId: Int): Int
    fun getTimeBetweenDoses(vaxId: Int): Int
    fun updateNoOfDosesVaccination(vaxId: Int, noOfDoses: Int): Boolean
    fun updateTimeBetweenDosesVaccination(vaxId: Int, timeBetweenDoses: Int): Boolean
    fun getVaccination(vaxId: Int): VaccinationsData?
    fun getAllVaccinations(): Set<VaccinationsData?>?
}
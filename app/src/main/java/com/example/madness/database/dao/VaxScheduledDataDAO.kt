package com.example.vaccination.database.dao

import com.example.vaccination.database.entities.VaccinationsData
import com.example.vaccination.database.entities.VaxScheduledData


interface VaxScheduledDataDAO {
      fun getAllUserVaxScheduled(userId: Int): Set<VaxScheduledData?>?
      fun insertVaxScheduled(vaxScheduledData: VaxScheduledData): Boolean
      fun getAllUserVaccinations(userId: Int): Set<VaxScheduledData?>?
      fun getAllUserToDoVaxScheduled(userId: Int): Set<VaxScheduledData?>?
      fun getAllUserDoneVaxScheduled(userId: Int): Set<VaxScheduledData?>?
      fun getAllUserProgressVaxScheduled(userId: Int): Set<VaxScheduledData?>?
      fun updateVaxScheduledStatus(vaxId: Int, userId: Int): Boolean
      fun deleteVaxScheduled(id: Int): Boolean
}


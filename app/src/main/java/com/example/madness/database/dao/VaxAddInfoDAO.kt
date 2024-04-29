package com.example.vaccination.database.dao

import com.example.vaccination.database.entities.VaxAddInfoData


interface VaxAddInfoDAO {
      fun insertVaxInfo(vaxAddInfo: VaxAddInfoData): Boolean
      fun deleteVaxInfo(vaxId: Int): Boolean
      fun getVaxAddInfo(vaxId: Int): VaxAddInfoData
}
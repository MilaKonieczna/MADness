package com.example.vaccination.database.entities

data class VaxAddInfoData(
    var vaxAddInfoId: Int,
    var vaxId: Int,
    var vaxNameCompany: String? = null,
    var vaxDescription: String? = null
)

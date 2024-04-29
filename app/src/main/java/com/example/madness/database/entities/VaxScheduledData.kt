package com.example.vaccination.database.entities

import java.util.Date

data class VaxScheduledData(
    var vaxScheduledId: Int,
    var vaxId: Int,
    var userId: Int,
    var dateOfFirstDose: Date,
    var statusOfVax: String? = null
)

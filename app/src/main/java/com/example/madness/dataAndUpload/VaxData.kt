package com.example.madness.dataAndUpload

import java.sql.Date

data class VaxData(
     var userId: String? = null,
     var vaccineName: String? = null,
     var administered: Date? = null,
     var nextDose: Date? = null
     )



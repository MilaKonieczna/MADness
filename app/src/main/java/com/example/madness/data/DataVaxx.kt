package com.example.madness.data
import java.time.LocalDate

data class DataVaxx(
    val name: String,
    val nextDose: LocalDate,
    val lastDate: LocalDate,
    val desc: String,
    var expandable: Boolean = false
)

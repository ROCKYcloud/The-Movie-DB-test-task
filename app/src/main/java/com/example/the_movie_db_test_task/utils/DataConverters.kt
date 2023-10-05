package com.example.the_movie_db_test_task.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun convertData(inputData: String): Date? {
    return when {
        inputData.isNotEmpty() -> {
            val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
            val outputFormat: DateFormat = SimpleDateFormat("MMM yyyy")
            val date: Date? = inputFormat.parse(inputData)
            val stringData = date?.let { outputFormat.format(it) }
            stringData?.let { outputFormat.parse(it) }
        }
        else -> null
    }
}
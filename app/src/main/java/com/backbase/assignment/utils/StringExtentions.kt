package com.backbase.assignment.utils

import java.text.SimpleDateFormat
import java.util.*

fun String.getDateString() : String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val date = dateFormat.parse(this)
    val outDateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
    return outDateFormat.format(date!!)
}
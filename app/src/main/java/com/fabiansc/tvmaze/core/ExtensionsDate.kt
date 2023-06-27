package com.fabiansc.tvmaze.core

import android.text.format.DateFormat
import android.widget.ImageView
import java.util.*

fun Date.getCompleteDate(): String{
    val currentDate = Date()
    return DateFormat.format("EEEE d * MMMM yyyy", currentDate).toString().replace("*","de")
}

fun Date.getShortDate(): String{
    val currentDate = Date()
    return DateFormat.format("yyyy-MM-dd", currentDate).toString()
}
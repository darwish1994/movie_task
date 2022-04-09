package com.demo.movieapp.common.util

import java.util.*


fun Long.getDiffHours(): Long {
    val diff = Date().time - this
    return diff / 1000 / 60 / 60
}
/**
* Add field date to current date
*/
fun Date.add(field: Int, amount: Int): Date {
    Calendar.getInstance().apply {
        time = this@add
        add(field, amount)
        return time
    }
}

fun Date.addHours(hours: Int): Date{
    return add(Calendar.HOUR_OF_DAY, hours)
}

fun Date.addDays(days: Int): Date{
    return add(Calendar.DAY_OF_MONTH, days)
}
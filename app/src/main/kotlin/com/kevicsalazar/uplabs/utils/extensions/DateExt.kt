package com.kevicsalazar.uplabs.utils.extensions

import java.text.SimpleDateFormat
import java.util.*


/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */

val TIME_ZONE = "America/Lima"
val TIME_ZONE_PERU = Locale("es", "PE")

fun Date.formatString(format: String): String {
    val df = SimpleDateFormat(format, TIME_ZONE_PERU)
    return df.format(this)
}

fun String.parseDate(format: String): Date? {
    val df = SimpleDateFormat(format, TIME_ZONE_PERU)
    try {
        return df.parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}

fun Date.isToday(): Boolean {
    return toCalendar().isToday()
}

fun Date.isTomorrow(): Boolean {
    return toCalendar().isTomorrow()
}

fun Date.dayOfWeek(): String {
    return toCalendar().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, TIME_ZONE_PERU)
}

fun Date.isSameDayAsDate(date1: Date): Boolean {
    return toCalendar().isSameDayAsDate(date1.toCalendar())
}

fun millis(): Long = System.currentTimeMillis()

val TimeIntervalMinute: Long = 60
val TimeIntervalHour: Long = 3600
val TimeIntervalDay: Long = 86400

fun Date.secondsAfterNow(): Int {
    return secondsAfterDate(Date())
}

fun Date.secondsBeforeNow(): Int {
    return secondsBeforeDate(Date())
}

fun Date.secondsAfterDate(date: Date): Int {
    val timeInterval = timeIntervalSinceDate(date)
    return timeInterval.toInt()
}

fun Date.secondsBeforeDate(date: Date): Int {
    val timeInterval = date.timeIntervalSinceDate(date)
    return timeInterval.toInt()
}

fun Date.minutesAfterNow(): Int {
    return minutesAfterDate(Date())
}

fun Date.minutesBeforeNow(): Int {
    return minutesBeforeDate(Date())
}

fun Date.minutesAfterDate(date: Date): Int {
    val timeInterval = timeIntervalSinceDate(date)
    return (timeInterval / TimeIntervalMinute).toInt()
}

fun Date.minutesBeforeDate(date: Date): Int {
    val timeInterval = date.timeIntervalSinceDate(this)
    return (timeInterval / TimeIntervalMinute).toInt()
}

fun Date.hoursAfterNow(): Int {
    return hoursAfterDate(Date())
}

fun Date.hoursBeforeNow(): Int {
    return hoursBeforeDate(Date())
}

fun Date.hoursAfterDate(date: Date): Int {
    val timeInterval = timeIntervalSinceDate(date)
    return (timeInterval / TimeIntervalHour).toInt()
}

fun Date.hoursBeforeDate(date: Date): Int {
    val timeInterval = date.timeIntervalSinceDate(this)
    return (timeInterval / TimeIntervalHour).toInt()
}

fun Date.daysAfterNow(): Int {
    return daysAfterDate(Date())
}

fun Date.daysAfterDate(date: Date): Int {
    val timeInterval = timeIntervalSinceDate(date)
    return (timeInterval / TimeIntervalDay).toInt()
}

fun Date.timeIntervalSinceDate(date: Date): Long {
    return (time - date.time) / 1000
}

fun Calendar.isToday(): Boolean {
    val cal1 = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE))
    return cal1.get(Calendar.YEAR) == get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == get(Calendar.DAY_OF_YEAR)
}

fun Calendar.isTomorrow(): Boolean {
    val cal1 = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE))
    return cal1.get(Calendar.YEAR) == get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == get(Calendar.DAY_OF_YEAR) - 1
}

fun Calendar.isSameDayAsDate(cal1: Calendar): Boolean {
    return cal1.get(Calendar.YEAR) == get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == get(Calendar.DAY_OF_YEAR)
}

fun Date.toCalendar(): Calendar {
    val calendar = Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE))
    calendar.time = this
    return calendar
}

fun Long.toDate(): Date {
    return Date(this)
}
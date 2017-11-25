package com.kevicsalazar.uplabs.utils.extensions

import java.text.SimpleDateFormat
import java.util.*


fun now(): Date = Date()

fun today(): Date = Date().truncate()

enum class TimeInterval(val seconds: Long, val millis: Long) {
    WEEK(604800, 604800 * 1000),
    DAY(86400, 86400 * 1000),
    HOUR(3600, 3600 * 1000),
    MINUTE(60, 60 * 1000)
}

val Calendar.year get() = get(Calendar.YEAR)
val Calendar.month get() = get(Calendar.MONTH)
val Calendar.weekOfYear get() = get(Calendar.WEEK_OF_YEAR)
val Calendar.dayOfYear get() = get(Calendar.DAY_OF_YEAR)
val Calendar.dayOfMonth get() = get(Calendar.DAY_OF_MONTH)
val Calendar.dayOfWeek get() = get(Calendar.DAY_OF_WEEK)
val Calendar.hour get() = get(Calendar.HOUR)
val Calendar.hourOfDay get() = get(Calendar.HOUR_OF_DAY)
val Calendar.minute get() = get(Calendar.MINUTE)
val Calendar.second get() = get(Calendar.SECOND)
val Calendar.lastDayOfMonth get() = getActualMaximum(Calendar.DAY_OF_MONTH)

val Date.isoDateFormat get() = "yyyy-MM-dd'T'HH:mm:ssZ"

val Date.timeInSeconds get() = time / 1000

/**************************************************************************************************
 * MARK: String Date Methods
 **************************************************************************************************/

fun Date.string(format: String, locale: String? = null): String? {
    val df = SimpleDateFormat(format, locale?.let { Locale(it) } ?: Locale.getDefault())
    return df.format(this)
}

fun String.toDate(format: String, locale: String? = null): Date? {
    val df = SimpleDateFormat(format, locale?.let { Locale(it) } ?: Locale.getDefault())
    return try {
        df.parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun Date.toCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar
}

/**************************************************************************************************
 * MARK: Comparing Date Methods
 **************************************************************************************************/

fun Date.isSameDayAsDate(date: Date): Boolean {
    val components1 = toCalendar()
    val components2 = date.toCalendar()
    return ((components1.year == components2.year) &&
            (components1.month == components2.month) &&
            (components1.dayOfMonth == components2.dayOfMonth))
}

fun Date.isToday(): Boolean = isSameDayAsDate(now())

fun Date.isTomorrow(): Boolean = isSameDayAsDate(now().dateByAddingDays(1))

fun Date.isYesterday(): Boolean = isSameDayAsDate(now().dateBySubtractingDays(1))

fun Date.isSameWeekAsDate(date: Date): Boolean {
    val components1 = toCalendar()
    val components2 = date.toCalendar()
    // Must be same week. 12/31 and 1/1 will both be week "1" if they are in the same week
    if (components1.weekOfYear != components2.weekOfYear) {
        return false
    }
    // Must have a time interval under 1 week. Thanks @aclark
    return timeIntervalSince(date) < TimeInterval.WEEK.seconds
}

fun Date.isThisWeek(): Boolean = isSameWeekAsDate(now())

fun Date.isNextWeek(): Boolean = isSameWeekAsDate(now().dateByAddingDays(7))

fun Date.isLastWeek(): Boolean = isSameWeekAsDate(now().dateBySubtractingDays(7))

fun Date.isSameMonthAsDate(date: Date): Boolean {
    val components1 = toCalendar()
    val components2 = date.toCalendar()
    return ((components1.month == components2.month) &&
            (components1.year == components2.year))
}

fun Date.isThisMonth(): Boolean = isSameMonthAsDate(now())

fun Date.isSameYearAsDate(date: Date): Boolean {
    val components1 = toCalendar()
    val components2 = date.toCalendar()
    return (components1.year == components2.year)
}

fun Date.isThisYear(): Boolean = isSameYearAsDate(now())

fun Date.isEarlierThanDate(date: Date): Boolean = compareTo(date) == -1

fun Date.isLaterThanDate(date: Date): Boolean = compareTo(date) == 1

fun Date.isBetweenDates(dateStart: Date, dateEnd: Date, including: Boolean = false): Boolean {
    if (including && isSameDayAsDate(dateStart)) {
        return true
    }
    if (including && isSameDayAsDate(dateEnd)) {
        return true
    }
    return isLaterThanDate(dateStart) && isEarlierThanDate(dateEnd)
}

fun Date.isBetweenDays(dateStart: Date, dateEnd: Date, including: Boolean = false): Boolean {
    if (isSameDayAsDate(dateStart)) {
        return including
    }
    if (isSameDayAsDate(dateEnd)) {
        return including
    }
    return isLaterThanDate(dateStart) && isEarlierThanDate(dateEnd)
}

fun Date.isInPast(): Boolean = isEarlierThanDate(now())

fun Date.isInFuture(): Boolean = isLaterThanDate(now())

/**************************************************************************************************
 * MARK: Adjusting Date Methods
 **************************************************************************************************/

fun Date.truncate(): Date {
    with(toCalendar()) {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
        return time
    }
}

fun Date.dateByAddingDays(days: Int): Date {
    val timeInterval = timeInSeconds + TimeInterval.DAY.seconds * days
    return Date(timeInterval * 1000L)
}

fun Date.dateBySubtractingDays(days: Int): Date = dateByAddingDays(-days)

fun Date.dateByAddingHours(hours: Int): Date {
    val timeInterval = timeInSeconds + TimeInterval.HOUR.seconds * hours
    return Date(timeInterval * 1000L)
}

fun Date.dateBySubtractingHours(hours: Int): Date = dateByAddingHours(-hours)

fun Date.dateByAddingMinutes(minutes: Int): Date {
    val timeInterval = timeInSeconds + TimeInterval.MINUTE.seconds * minutes
    return Date(timeInterval * 1000L)
}

fun Date.dateBySubtractingMinutes(minutes: Int): Date = dateByAddingMinutes(-minutes)

fun Date.dateByAddingSeconds(seconds: Int): Date {
    val timeInterval = timeInSeconds + seconds
    return Date(timeInterval * 1000L)
}

fun Date.dateBySubtractingSeconds(seconds: Int): Date = dateByAddingSeconds(-seconds)

fun Date.timeIntervalSince(date: Date): Long = (time - date.time) / 1000

/**************************************************************************************************
 * MARK: Retrieving Intervals Date Methods
 **************************************************************************************************/

fun Date.secondsAfterNow(): Int = secondsAfterDate(now())

fun Date.secondsBeforeNow(): Int = secondsBeforeDate(now())

fun Date.secondsAfterDate(date: Date): Int {
    val timeInterval = timeIntervalSince(date)
    return timeInterval.toInt()
}

fun Date.secondsBeforeDate(date: Date): Int {
    val timeInterval = date.timeIntervalSince(this)
    return timeInterval.toInt()
}

fun Date.minutesAfterDate(date: Date): Int {
    val timeInterval = timeIntervalSince(date)
    return (timeInterval / TimeInterval.MINUTE.seconds).toInt()
}

fun Date.minutesBeforeDate(date: Date): Int {
    val timeInterval = date.timeIntervalSince(this)
    return (timeInterval / TimeInterval.MINUTE.seconds).toInt()
}

fun Date.hoursAfterDate(date: Date): Int {
    val timeInterval = timeIntervalSince(date)
    return (timeInterval / TimeInterval.HOUR.seconds).toInt()
}

fun Date.hoursBeforeDate(date: Date): Int {
    val timeInterval = date.timeIntervalSince(this)
    return (timeInterval / TimeInterval.HOUR.seconds).toInt()
}

fun Date.daysAfterDate(date: Date): Int {
    val timeInterval = timeIntervalSince(date)
    return (timeInterval / TimeInterval.DAY.seconds).toInt()
}

fun Date.daysBeforeDate(date: Date): Int {
    val timeInterval = date.timeIntervalSince(this)
    return (timeInterval / TimeInterval.DAY.seconds).toInt()
}

fun Date.changeDate(year: Int, month: Int, dayOfMonth: Int): Date {
    with(toCalendar()) {
        set(Calendar.YEAR, year)
        set(Calendar.MONTH, month)
        set(Calendar.DAY_OF_MONTH, dayOfMonth)
        return time
    }
}

fun Date.changeDay(dayOfMonth: Int): Date {
    with(toCalendar()) {
        set(Calendar.DAY_OF_MONTH, dayOfMonth)
        return time
    }
}
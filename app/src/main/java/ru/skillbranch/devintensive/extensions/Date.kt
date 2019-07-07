package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND) : Date {
    var time = this.time

    time  += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()):String {
    var toSeconds = (date.time - this.time)/1000
    when (toSeconds) {
        in 0..1 -> return "только что"
        in 1..45 -> return "несколько секунд назад"
        in 45..75 -> return "минуту назад"
        in 75..2700 -> return "${toSeconds/60} минут назад"
        in 2700..4500 -> return "час назад"
        in 4500..79200 -> return "${toSeconds/3600} часов назад"
        in 79200..93600 -> return "день назад"
        in 93600..31104000 -> return "${toSeconds/86400} дней назад"
        else -> {
            return "более года назад"
        }
    }
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        val absValue = abs(value)
        val is11to14 = absValue % 100 in 11..14
        val valMod10 = absValue % 10
        return when (this) {
            SECOND ->
                when {
                    is11to14 -> "$absValue секунд"
                    valMod10 == 1 -> "$absValue секунду"
                    valMod10 in 2..4 -> "$absValue секунды"
                    else -> "$absValue секунд"
                }
            MINUTE ->
                when {
                    is11to14 -> "$absValue минут"
                    valMod10 == 1 -> "$absValue минуту"
                    valMod10 in 2..4 -> "$absValue минуты"
                    else -> "$absValue минут"
                }
            HOUR ->
                when {
                    is11to14 -> "$absValue часов"
                    valMod10 == 1 -> "$absValue час"
                    valMod10 in 2..4 -> "$absValue часа"
                    else -> "$absValue часов"
                }
            DAY ->
                when {
                    is11to14 -> "$absValue дней"
                    valMod10 == 1 -> "$absValue день"
                    valMod10 in 2..4 -> "$absValue дня"
                    else -> "$absValue дней"
                }
        }
    }
}
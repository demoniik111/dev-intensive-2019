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
    val past = toSeconds < 0
    when (toSeconds) {
        in 0..1 -> return "только что"
        in 1..45 -> return if (past) "несколько секунд назад" else "через несколько секунд"
        in 45..75 -> return if (past) "минуту назад" else "через минуту"
        in 75..2700 -> return "${TimeUnits.MINUTE.plural(toSeconds.toInt()/60)} назад"
        in 2700..4500 -> return  if (past) "час назад" else "через час"
        in 4500..79200 -> return "${TimeUnits.HOUR.plural(toSeconds.toInt()/3600)} часов назад"
        in 79200..93600 -> return if (past) "день назад" else "через день"
        in 93600..31104000 -> return "${TimeUnits.DAY.plural(toSeconds.toInt()/86400)} дней назад"
        else -> {
            return if (past) "более года назад" else "более чем через год"
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
package ger.girod.spacex.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun fromLongToString(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("dd/MM/yyyy")
        return format.format(date)
    }

}
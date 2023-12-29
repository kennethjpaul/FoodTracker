package com.kinetx.foodtracker.helpers

import java.util.*

object HelperFunctions {

    fun resetToMidnight(calendar: Calendar): Calendar {

        calendar.set(android.icu.util.Calendar.HOUR_OF_DAY, 0)
        calendar.set(android.icu.util.Calendar.MINUTE, 0)
        calendar.set(android.icu.util.Calendar.SECOND, 0)
        calendar.set(android.icu.util.Calendar.MILLISECOND, 0)

        return calendar
    }

}
package com.kinetx.foodtracker.helpers

import android.icu.util.Calendar


object HelperFunctions {

    fun resetToMidnight(calendar: Calendar): Calendar {

        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar
    }

    fun changeDayByN(myCalendar: Calendar, n : Int, direction: Int) : Calendar
    {
        var calendarNew: Calendar = myCalendar
        calendarNew = resetToMidnight(calendarNew)

        val day = calendarNew.get(Calendar.DAY_OF_MONTH)
        var month = calendarNew.get(Calendar.MONTH)
        var year = calendarNew.get(Calendar.YEAR)
        val maxDays = calendarNew.getActualMaximum(Calendar.DAY_OF_MONTH)
        var shiftedDay = day + direction*n
        if (shiftedDay<=0 && month>0)
        {
            month-=1
            calendarNew.set(Calendar.MONTH,month)
            val maxDaysNew = calendarNew.getActualMaximum(Calendar.DAY_OF_MONTH)
            shiftedDay += maxDaysNew
        }
        else if(shiftedDay<=0 && month==0)
        {
            month = 11
            year-=1
            calendarNew.set(Calendar.MONTH,month)
            calendarNew.set(Calendar.YEAR,year)
            val maxDaysNew = calendarNew.getActualMaximum(Calendar.DAY_OF_MONTH)
            shiftedDay += maxDaysNew
        }

        else if(shiftedDay>maxDays && month <11)
        {
            month +=1
            calendarNew.set(Calendar.MONTH,month)
            shiftedDay -= maxDays
        }
        else if (shiftedDay>maxDays && month ==11)
        {
            month = 0
            year +=1
            calendarNew.set(Calendar.MONTH,month)
            calendarNew.set(Calendar.YEAR,year)
            shiftedDay -= maxDays
        }

        calendarNew.set(Calendar.DAY_OF_MONTH,shiftedDay)

        return calendarNew
    }

    fun convertToString(float: Float?) : String
    {
        return if (float==0.0f) {
            ""
        } else {
            float.toString()
        }
    }

    fun convertToFloat(s:String) : Float
    {
        return if (s=="" || s==".") {
            0.0f
        } else {
            s.toFloat()
        }
    }

}
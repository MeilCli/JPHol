package com.twitter.meil_mitu.jphol

import java.util.*

interface IHoliday {
    val DayName : String
    val Type:HolidayType

    fun matchDay(c:Calendar):Boolean
}
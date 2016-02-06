package com.twitter.meil_mitu.jphol

import java.util.*

class DayOfMonthHoliday(dayName: String, type: HolidayType, month: Int, day: Int) : AbsHoliday(dayName, type) {
    private val month: Int
    private val day: Int

    init {
        this.month = month
        this.day = day
    }

    override fun matchDay(c: Calendar): Boolean {
        var month: Int = c.get(Calendar.MONTH)
        var day: Int = c.get(Calendar.DAY_OF_MONTH)
        return this.month == month && this.day == day
    }
}
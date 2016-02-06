package com.twitter.meil_mitu.jphol

import java.util.*

class DayOfWeekHoliday(dayName: String, type: HolidayType, month: Int, dayOfWeekInMonth: Int, dayOfWeek: Int) : AbsHoliday(dayName, type) {
    private val month: Int
    private val dayOfWeekInMonth: Int
    private val dayOfWeek: Int

    init {
        this.month = month
        this.dayOfWeekInMonth = dayOfWeekInMonth
        this.dayOfWeek = dayOfWeek
    }

    override fun matchDay(c: Calendar): Boolean {
        var month: Int = c.get(Calendar.MONTH)
        var dayOfWeekInMonth: Int = c.get(Calendar.DAY_OF_WEEK_IN_MONTH)
        var dayOfWeek: Int = c.get(Calendar.DAY_OF_WEEK)
        return this.month == month && this.dayOfWeekInMonth == dayOfWeekInMonth && this.dayOfWeek == dayOfWeek
    }
}
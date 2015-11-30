package com.twitter.meil_mitu.jphol

import java.util.*

internal class Holiday(name:String,type:HolidayType):AbsHoliday(name,type) {
    override fun matchDay(c: Calendar): Boolean {
        throw UnsupportedOperationException()
    }
}
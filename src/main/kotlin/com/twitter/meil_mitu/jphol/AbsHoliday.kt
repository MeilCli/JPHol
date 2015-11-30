package com.twitter.meil_mitu.jphol

abstract class AbsHoliday(dayName:String,type:HolidayType):IHoliday {
    override val DayName: String
    override val Type: HolidayType

    init{
        this.DayName=dayName
        this.Type = type
    }
}
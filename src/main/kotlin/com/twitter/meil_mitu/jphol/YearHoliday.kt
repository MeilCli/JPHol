package com.twitter.meil_mitu.jphol

import java.util.*

class YearHoliday(dayName:String,type:HolidayType,yearData:Array<YearHoliday.YearData>):AbsHoliday(dayName,type) {

    object data class YearData(val years:Iterable<Int>,val month:Int,val dayInYearOfSurplus:Array<Int>)

    private val yeayData:Array<YearData>

    init{
        this.yeayData=yearData
    }

    override fun matchDay(c: Calendar): Boolean {
        var year: Int = c.get(Calendar.YEAR)
        var month :Int=c.get(Calendar.MONTH)
        var day: Int = c.get(Calendar.DAY_OF_MONTH)
        if(yeayData.all { x -> (year in x.years)==false}) return false
        var data: YearData = yeayData.single { x-> year in x.years }
        if(month!=data.month)return false
        return data.dayInYearOfSurplus[year%data.dayInYearOfSurplus.size]==day
    }
}
package com.twitter.meil_mitu.jphol

import java.util.*
import com.twitter.meil_mitu.klinq.*

class HolidayManager {
    internal object Data {
        val month1: Int = Calendar.JANUARY
        val month2: Int = Calendar.FEBRUARY
        val month3: Int = Calendar.MARCH
        val month4: Int = Calendar.APRIL
        val month5: Int = Calendar.MAY
        val month6: Int = Calendar.JUNE
        val month7: Int = Calendar.JULY
        val month8: Int = Calendar.AUGUST
        val month9: Int = Calendar.SEPTEMBER
        val month10: Int = Calendar.OCTOBER
        val month11: Int = Calendar.NOVEMBER
        val month12: Int = Calendar.DECEMBER
        val dayOfWeek1: Int = Calendar.SUNDAY
        val dayOfWeek2: Int = Calendar.MONDAY
        val holidays2015: List<IHoliday> = maketHolidays(2015)
        val holidays2016: List<IHoliday> = maketHolidays(2016)

        fun getHolidayList(year: Int): List<IHoliday> {
            if (year < 2016) return holidays2015
            return holidays2016
        }

        private fun maketHolidays(year: Int): List<IHoliday> {
            var list = ArrayList<IHoliday>()
            run {
                //http://www8.cao.go.jp/chosei/shukujitsu/gaiyou.html
                list.add(DayOfMonthHoliday("元旦", HolidayType.NationalHoliday, month1, 1))
                list.add(DayOfWeekHoliday("成人の日", HolidayType.NationalHoliday, month1, 2, dayOfWeek2))
                list.add(DayOfMonthHoliday("建国記念の日", HolidayType.NationalHoliday, month2, 11))
                run {
                    //https://ja.wikipedia.org/wiki/%E6%98%A5%E5%88%86
                    val data1 = YearHoliday.YearData(1992..2023, month3, arrayOf(20, 20, 21, 21))
                    val data2 = YearHoliday.YearData(2024..2055, month3, arrayOf(20, 20, 20, 21))
                    list.add(YearHoliday("春分の日", HolidayType.NationalHoliday, arrayOf(data1, data2)))
                }
                list.add(DayOfMonthHoliday("昭和の日", HolidayType.NationalHoliday, month4, 29))
                list.add(DayOfMonthHoliday("憲法記念日", HolidayType.NationalHoliday, month5, 3))
                list.add(DayOfMonthHoliday("みどりの日", HolidayType.NationalHoliday, month5, 4))
                list.add(DayOfMonthHoliday("こどもの日", HolidayType.NationalHoliday, month5, 5))
                list.add(DayOfWeekHoliday("海の日", HolidayType.NationalHoliday, month7, 3, dayOfWeek2))
                if (year >= 2016) list.add(DayOfMonthHoliday("山の日", HolidayType.NationalHoliday, month8, 11))
                list.add(DayOfWeekHoliday("敬老の日", HolidayType.NationalHoliday, month9, 3, dayOfWeek2))
                run {
                    //https://ja.wikipedia.org/wiki/%E7%A7%8B%E5%88%86
                    val data1 = YearHoliday.YearData(2012..2043, month9, arrayOf(22, 23, 23, 23))
                    list.add(YearHoliday("秋分の日", HolidayType.NationalHoliday, arrayOf(data1)))
                }
                list.add(DayOfWeekHoliday("体育の日", HolidayType.NationalHoliday, month10, 2, dayOfWeek2))
                list.add(DayOfMonthHoliday("文化の日", HolidayType.NationalHoliday, month11, 3))
                list.add(DayOfMonthHoliday("勤労感謝の日", HolidayType.NationalHoliday, month11, 23))
                list.add(DayOfMonthHoliday("天皇誕生日", HolidayType.NationalHoliday, month12, 23))
            }
            run {
                //https://ja.wikipedia.org/wiki/%E4%BC%91%E6%97%A5
                list.add(DayOfMonthHoliday("公共団体の休日", HolidayType.Holiday, month1, 1))
                list.add(DayOfMonthHoliday("公共団体の休日", HolidayType.Holiday, month1, 2))
                list.add(DayOfMonthHoliday("公共団体の休日", HolidayType.Holiday, month1, 3))
                list.add(DayOfMonthHoliday("公共団体の休日", HolidayType.Holiday, month12, 29))
                list.add(DayOfMonthHoliday("公共団体の休日", HolidayType.Holiday, month12, 30))
                list.add(DayOfMonthHoliday("公共団体の休日", HolidayType.Holiday, month12, 31))
            }
            return list
        }
    }

    fun isHoliday(year: Int, monthInHuman: Int, day: Int): Boolean {
        var c = GregorianCalendar().apply {
            set(year, monthInHuman - 1, day)
        }
        return isHoliday(c)
    }

    fun isHoliday(c: Calendar): Boolean {
        return getHoliday(c) != null
    }

    fun getHoliday(year: Int, monthInHuman: Int, day: Int): List<IHoliday>? {
        var c = GregorianCalendar().apply {
            set(year, monthInHuman - 1, day)
        }
        return getHoliday(c)
    }

    fun getHoliday(c: Calendar): List<IHoliday>? {
        val year: Int = c.get(Calendar.YEAR)
        val list: List<IHoliday> = Data.getHolidayList(year)
        run {
            var r = getHoliday(c, list)
            if (isMakeUpHoliday(c, list, r)) r.plus(Holiday("振替休日", HolidayType.MakeUpHoliday))
            if (r.size > 0) return r
        }
        run {
            //国民の祝日に挟まれていたら休日
            var c1 = (c.clone() as Calendar).apply { add(Calendar.DAY_OF_YEAR, 1) }
            var c2 = (c.clone() as Calendar).apply { add(Calendar.DAY_OF_YEAR, -1) }
            if (list.any { x -> x.Type == HolidayType.NationalHoliday && x.matchDay(c1) }
                    && list.any { x -> x.Type == HolidayType.NationalHoliday && x.matchDay(c2) }) {
                return arrayListOf(Holiday("国民の休日", HolidayType.Holiday))
            }
        }
        if (isMakeUpHoliday(c, list, null)) return arrayListOf(Holiday("振替休日", HolidayType.MakeUpHoliday))
        return null
    }

    private fun isMakeUpHoliday(c: Calendar, list: List<IHoliday>, holiday: List<IHoliday>?): Boolean {
        if (holiday?.any { x -> x.Type == HolidayType.NationalHoliday } ?: false) return false
        if (isSunday(c)) return false
        var c1 = c.clone() as Calendar
        for (i in 2..c1.get(Calendar.DAY_OF_WEEK)) {
            c1.add(Calendar.DAY_OF_YEAR, -1)
            if (getHoliday(c1, list).all { x -> x.Type != HolidayType.NationalHoliday }) return false
        }
        return true
    }

    fun isSunday(c: Calendar): Boolean {
        return c.get(Calendar.DAY_OF_WEEK) == Data.dayOfWeek1
    }

    private fun isHoliday(c: Calendar, list: List<IHoliday>): Boolean {
        return getHoliday(c, list).size > 0
    }

    private fun getHoliday(c: Calendar, list: List<IHoliday>): List<IHoliday> {
        return list.toEnumerable().where { x -> x.matchDay(c) }.toList()
    }

}
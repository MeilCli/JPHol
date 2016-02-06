package com.twitter.meil_mitu.jphol

import org.junit.*
import org.junit.Assert.*

class HolidayManagerTest {

    val manager = HolidayManager()

    private fun isHoliday(year:Int,month:Int,day:Int,condition:Boolean){
        assertEquals(manager.isHoliday(year,month,day),condition)
    }

    private fun isNationalHoliday(year:Int,month:Int,day:Int,condition:Boolean){
        assertEquals(
                manager.getHoliday(year,month,day)?.any{x->x.Type== HolidayType.NationalHoliday}?:false,
                condition)
    }

    //元旦
    @Test fun test1_1(){
        isHoliday(2015,1,1,true)
        isNationalHoliday(2015,1,1,true)
    }

    //成人の日
    @Test fun test1_12(){
        isHoliday(2015,1,12,true)
        isNationalHoliday(2015,1,12,true)
    }

    //平日
    @Test fun test1_15(){
        isHoliday(2015,1,15,false)
        isNationalHoliday(2015,1,15,false)
    }

    //日曜であるが祝日ではない
    @Test fun test2_1(){
        isHoliday(2015,2,1,false)
        isNationalHoliday(2015,2,1,false)
    }

    //建国記念の日
    @Test fun test2_11(){
        isHoliday(2015,2,11,true)
        isNationalHoliday(2015,2,11,true)
    }

    //春分の日
    @Test fun test3_21(){
        isHoliday(2015,3,21,true)
        isNationalHoliday(2015,3,21,true)
    }

    //昭和の日
    @Test fun test4_29(){
        isHoliday(2015,4,29,true)
        isNationalHoliday(2015,4,29,true)
    }

    //憲法記念日
    @Test fun test5_3(){
        isHoliday(2015,5,3,true)
        isNationalHoliday(2015,5,3,true)
    }

    //みどりの日
    @Test fun test5_4(){
        isHoliday(2015,5,4,true)
        isNationalHoliday(2015,5,4,true)
    }

    //こどもの日
    @Test fun test5_5(){
        isHoliday(2015,5,5,true)
        isNationalHoliday(2015,5,5,true)
    }

    //振替休日
    @Test fun test5_6(){
        isHoliday(2015,5,6,true)
        isNationalHoliday(2015,5,6,false)
    }

    //海の日
    @Test fun test7_20(){
        isHoliday(2015,7,20,true)
        isNationalHoliday(2015,7,20,true)
    }

    //山の日
    @Test fun test8_11(){
        isHoliday(2015,8,11,false)
        isNationalHoliday(2015,8,11,false)
        isHoliday(2016,8,11,true)
        isNationalHoliday(2016,8,11,true)
    }

    //敬老の日
    @Test fun test9_21(){
        isHoliday(2015,9,21,true)
        isNationalHoliday(2015,9,21,true)
    }

    //国民の休日
    @Test fun test9_22(){
        isHoliday(2015,9,22,true)
        isNationalHoliday(2015,9,22,false)
    }

    //秋分の日
    @Test fun test9_23(){
        isHoliday(2015,9,23,true)
        isNationalHoliday(2015,9,23,true)
    }

    //体育の日
    @Test fun test10_12(){
        isHoliday(2015,10,12,true)
        isNationalHoliday(2015,10,12,true)
    }

    //文化の日
    @Test fun test11_3(){
        isHoliday(2015,11,3,true)
        isNationalHoliday(2015,11,3,true)
    }

    //勤労感謝の日
    @Test fun test11_23(){
        isHoliday(2015,11,23,true)
        isNationalHoliday(2015,11,23,true)
    }

    //天皇誕生日
    @Test fun test12_23(){
        isHoliday(2015,12,23,true)
        isNationalHoliday(2015,12,23,true)
    }
}
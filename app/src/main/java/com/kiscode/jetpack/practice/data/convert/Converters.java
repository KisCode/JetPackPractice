package com.kiscode.jetpack.practice.data.convert;

import java.util.Calendar;

import androidx.room.TypeConverter;

/**
* Description: room数据库类型转换
* Author: keno
* Date : 2021/3/24 14:57
**/
public class Converters {

    @TypeConverter
    public long calendarToDatestamp(Calendar calendar) {
        return calendar.getTimeInMillis();
    }

    @TypeConverter
    public Calendar datestampToCalendar(long value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(value);
        return calendar;
    }
}
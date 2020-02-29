package com.kiscode.jetpack.room.db.conveter;

import java.util.Date;

import androidx.room.TypeConverter;

/**
 * Description: 时间存储转换器
 * RoomDatabase除了必须添加@Database注解也可以添加@TypeConverter注解。用于提供一个把自定义类转化为一个Room能够持久化的已知类型的，
 * 持久化日期的实例，可以用如下代码写一个TypeConverter去存储相等的Unix时间戳在数据库中
 *
 * Author: KrisKeno
 * Date : 2020/2/29 3:14 PM
 **/
public class DateConverters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}

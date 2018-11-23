package com.pulse.roomdemo.helper;



import android.arch.persistence.room.TypeConverter;

import java.sql.Date;

/**
 * Created by nikhil.vadoliya on 12-04-2018.
 */


public class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}

package com.example.weathermon.database.typeconverters;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;

public class LocalDateTimeTypeConverter {
    @TypeConverter
    public String convertDateTimeToString(LocalDateTime localDateTime){
        return localDateTime.toString();
    }

    @TypeConverter
    public LocalDateTime convertStringToDateTime(String dateTimeString){
        return LocalDateTime.parse(dateTimeString);
    }

}

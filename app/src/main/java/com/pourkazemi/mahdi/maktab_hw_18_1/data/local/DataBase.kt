package com.pourkazemi.mahdi.maktab_hw_18_1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pourkazemi.mahdi.maktab_hw_18_1.data.model.Person

@Database(entities = [Person::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DataBase:RoomDatabase() {
    abstract fun personDao():PersonDao
}
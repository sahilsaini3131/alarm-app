package com.my.alarmtask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.my.alarmtask.db.dao.AlarmDao
import com.my.alarmtask.model.AlarmData

@Database(entities = [AlarmData::class], version = 2)
abstract class AppDataBase: RoomDatabase() {

    abstract fun alarmDao(): AlarmDao

}
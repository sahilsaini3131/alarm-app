package com.my.alarmtask.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarm")
class AlarmData (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Int = 0,
    @ColumnInfo("alarm_time")
    var alarmTime : Long,
    @ColumnInfo("alarm_name")
    var alarmName : String,
    @ColumnInfo("alarm_ringtone")
    var alarmRingtone: String,
    @ColumnInfo("is_enable")
    var isEnable : Boolean
)
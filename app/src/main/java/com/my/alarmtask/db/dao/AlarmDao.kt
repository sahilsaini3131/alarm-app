package com.my.alarmtask.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.my.alarmtask.model.AlarmData
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarm")
    fun getAllAlarm(): Flow<List<AlarmData>?>

    @Query("SELECT * FROM alarm WHERE id=:id")
    fun getAlarm(id: Int): AlarmData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAlarm(alarmData: AlarmData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAlarm(alarmData: AlarmData)

    @Query("DELETE FROM alarm WHERE id=:id")
    suspend fun deleteAlarm(id: Int)

}
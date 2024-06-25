package com.my.alarmtask.di

import android.app.Application
import androidx.room.Room
import com.my.alarmtask.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Singleton
    @Provides
    fun database(application: Application): AppDataBase {
        return Room.databaseBuilder(
            context = application.applicationContext,
            AppDataBase::class.java,
            "alarm-manager.db"
        ).build()
    }

}
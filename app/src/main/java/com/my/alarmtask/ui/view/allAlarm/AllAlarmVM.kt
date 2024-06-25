package com.my.alarmtask.ui.view.allAlarm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.alarmtask.db.AppDataBase
import com.my.alarmtask.model.AlarmData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllAlarmVM @Inject constructor(
    private val appDataBase: AppDataBase
) : ViewModel() {

    private val _allAlarms = MutableSharedFlow<List<AlarmData>>(replay = 1)
    val allAlarm get() = _allAlarms.asSharedFlow()


    /**
     * Get All Alarms
     * */
    fun getAlarms() {
        viewModelScope.launch {
            appDataBase.alarmDao().getAllAlarm()
                .catch {
                    Log.e("Error Occure", "$it")
                }.collect {
                    _allAlarms.tryEmit(it ?: emptyList())
            }
        }
    }


    /**
     * Update Alarm
     * */
    fun updateAlarm(alarmData: AlarmData){
        viewModelScope.launch {
            appDataBase.alarmDao().updateAlarm(alarmData)
        }
    }

}
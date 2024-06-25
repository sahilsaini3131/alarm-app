package com.my.alarmtask.ui.view.addAlarm

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.my.alarmtask.db.AppDataBase
import com.my.alarmtask.model.AlarmData
import com.my.alarmtask.utils.addAlarm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAlarmVM @Inject constructor(
    private val appDataBase: AppDataBase
) : ViewModel() {

    var selectedTimeMillis: Long? = null

    fun addAlarm(view: View, alarmData: AlarmData) = viewModelScope.launch {
        view.context.addAlarm(selectedTimeMillis ?: System.currentTimeMillis())
        appDataBase.alarmDao().addAlarm(alarmData)
        view.findNavController().popBackStack()
    }

}
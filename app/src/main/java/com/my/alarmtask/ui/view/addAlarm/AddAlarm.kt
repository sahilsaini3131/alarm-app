package com.my.alarmtask.ui.view.addAlarm

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.my.alarmtask.R
import com.my.alarmtask.databinding.AddAlarmBinding
import com.my.alarmtask.model.AlarmData
import com.my.alarmtask.utils.addAlarm
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar

@AndroidEntryPoint
class AddAlarm: Fragment(R.layout.add_alarm) {

    private lateinit var binding: AddAlarmBinding
    private val viewModel by viewModels<AddAlarmVM>()


    /**
     * On View Created
     * */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AddAlarmBinding.bind(view)
        clickHandler()
    }


    /**
     * Click Handler
     * */
    private fun clickHandler(){
        val calendar = Calendar.getInstance()
        val formattedTime = SimpleDateFormat("HH:mm").format(calendar.time)
        binding.tvTime.text = formattedTime
        viewModel.selectedTimeMillis = calendar.timeInMillis

        binding.tvTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePickerDialog = TimePickerDialog(
                requireContext(),
                { _, hourOfDay, minute ->
                    val selectedTime = Calendar.getInstance()
                    selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    selectedTime.set(Calendar.MINUTE, minute)

                    val formattedTime = SimpleDateFormat("HH:mm").format(selectedTime.time)
                    binding.tvTime.text = formattedTime
                    viewModel.selectedTimeMillis = selectedTime.timeInMillis
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false
            )
            timePickerDialog.show()
        }

        binding.save.setOnClickListener {
            viewModel.addAlarm(requireView(), AlarmData(
                alarmName = binding.etAlarmName.text?.trim().toString(),
                alarmTime = viewModel.selectedTimeMillis ?: System.currentTimeMillis(),
                alarmRingtone = binding.etSound.text?.trim().toString(),
                isEnable = true
            ))
        }
    }

}
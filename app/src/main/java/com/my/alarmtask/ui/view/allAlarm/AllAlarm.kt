package com.my.alarmtask.ui.view.allAlarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.my.alarmtask.R
import com.my.alarmtask.ui.adapter.AlarmAdapter
import com.my.alarmtask.databinding.AllAlarmBinding
import com.my.alarmtask.model.AlarmData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllAlarm : Fragment(R.layout.all_alarm) {

    private lateinit var binding: AllAlarmBinding
    private val alarmAdapter by lazy { AlarmAdapter(::updateAlarm) }
    private val viewModel by viewModels<AllAlarmVM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AllAlarmBinding.bind(view)
        binding.rvAlarm.adapter = alarmAdapter
        viewModel.getAlarms()
        clickHandler()
        observeAllAlarms()
    }


    private fun clickHandler(){
        binding.ivAdd.setOnClickListener {
            findNavController().navigate(R.id.addAlarm)
        }
    }

    private fun observeAllAlarms() = lifecycleScope.launch {
        viewModel.allAlarm.collectLatest {
            alarmAdapter.submitList(it)
        }
    }


    private fun updateAlarm(alarmData: AlarmData){
        viewModel.updateAlarm(alarmData)
    }
}
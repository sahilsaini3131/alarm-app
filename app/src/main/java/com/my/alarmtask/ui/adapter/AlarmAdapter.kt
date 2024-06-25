package com.my.alarmtask.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.my.alarmtask.databinding.AlarmItemBinding
import com.my.alarmtask.model.AlarmData
import com.my.alarmtask.utils.millisToDateString

class AlarmAdapter(
    private val switchEnableClick: (AlarmData) -> Unit
) : RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {

    private val alarmList = mutableListOf<AlarmData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AlarmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return alarmList.size
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val taskInfo = alarmList[position]
        holder.bind(taskInfo, switchEnableClick)
    }


    class ViewHolder(private val binding : AlarmItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(alarmInfo: AlarmData, switchEnableClick: (AlarmData) -> Unit){
            binding.tvTime.text = millisToDateString(alarmInfo.alarmTime)
            binding.tbTime.isChecked = alarmInfo.isEnable

            binding.tbTime.setOnClickListener {
                alarmInfo.isEnable = !alarmInfo.isEnable
                switchEnableClick(alarmInfo)
            }
        }
    }


    fun submitList(tasks: List<AlarmData>) {
        alarmList.clear()
        alarmList.addAll(tasks)
        notifyDataSetChanged()
    }


}
package com.my.alarmtask.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.my.alarmtask.broadcastReceiver.AlarmReceiver
import java.text.SimpleDateFormat
import java.util.Date

fun Context.addAlarm(
    time: Long
){
    try {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        intent.action = "com.my.alarmtask.broadcastReceiver.AlarmReceiver"
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                // Handle the case where exact alarms are not supported
                return
            }
        }
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
    }catch (e: Exception){
        e.printStackTrace()
    }
}


fun Context.disableAlarm(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
    alarmManager.cancel(pendingIntent)
 }


fun millisToDateString(milliseconds: Long): String {
    val date = Date(milliseconds)
    return SimpleDateFormat("hh:mm a").format(date)
}
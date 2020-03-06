package com.example.reminder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.room.Room
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast

class ReminderReceiver :BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val uid = intent.getIntExtra("uid",0)
        val text = intent.getStringExtra("message")

        //context.toast(text!!)

        MainActivity.showNotification(context,text!!)

        //Will not work because no uid is set for alarm
        doAsync {
            val db = Room.databaseBuilder(context,AppDataBase::class.java,"reminders")
                .build()
            db.reminderDao().delete(uid)
            db.close()
        }
    }


}
package com.example.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_time.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import java.util.*

class TimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        time_create.setOnClickListener{


            val calender = GregorianCalendar(

                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth,
                timePicker.currentHour,
                timePicker.currentMinute
            )

            if((ed_message.text.toString() != "") &&
                (calender.timeInMillis > System.currentTimeMillis())) {

                val reminder = Reminder(
                    uid = null,
                    time =  calender.timeInMillis,
                    location = null,
                    message = ed_message.text.toString()
                )

                doAsync {

                    val db = Room.databaseBuilder(applicationContext,AppDataBase::class.java, "reminders").build()
                    db.reminderDao().insert(reminder)
                    db.close()

                    setAlarm(reminder.time!!,reminder.message)

                    finish()
                }
            }else {
                toast("Error saving reminder!!!")
            }



        }
    }

    private fun setAlarm(time : Long , message: String){

        val intent = Intent(this,ReminderReceiver::class.java)
        intent.putExtra("message",message)

        val pendingIntent = PendingIntent.getBroadcast(this,1,intent,PendingIntent.FLAG_ONE_SHOT)

        val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        manager.setExact(AlarmManager.RTC,time,pendingIntent)

        runOnUiThread{toast("Reminder is created")}

    }
}

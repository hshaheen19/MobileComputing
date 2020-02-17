package com.example.reminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fabOpened = false;

        fab.setOnClickListener{
            if(!fabOpened)
            {
                fabOpened = true;
                fab_Map.animate().translationY(-resources.getDimension(R.dimen.standard_66))
                fab_Time.animate().translationY(-resources.getDimension(R.dimen.standard_116))
            }
            else
            {
                fabOpened = false;
                fab_Map.animate().translationY(0f)
                fab_Time.animate().translationY(0f)
            }
        }

        fab_Time.setOnClickListener{
            val intent = Intent(applicationContext, TimeActivity::class.java)
            startActivity(intent)
        }

        fab_Map.setOnClickListener{
            val intent = Intent(applicationContext, MapActivity::class.java)
            startActivity(intent)
        }



    }

    override fun onResume() {
        super.onResume()

        refreshList()
    }

    private fun refreshList(){

        doAsync {

            val db = Room.databaseBuilder(applicationContext,AppDataBase::class.java,"reminders").build()
            val reminders = db.reminderDao().getReminders()
            db.close()

            uiThread {

                if(reminders.isNotEmpty()){
                    val adapter = ReminderAdapter(applicationContext,reminders)
                    list.adapter = adapter
                }else{
                    toast("No reminders yet!!!")
                }

            }
        }
    }
}

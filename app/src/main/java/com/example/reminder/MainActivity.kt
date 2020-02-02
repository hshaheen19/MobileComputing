package com.example.reminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

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

        val data = arrayOf("Oulu","Helsinki","Tampere");
        val reminder_adapter = ReminderAdapter(applicationContext,data)
        list.adapter = reminder_adapter
    }
}

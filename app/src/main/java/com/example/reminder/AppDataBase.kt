package com.example.reminder

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Reminder :: class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun reminderDao() : ReminderDao
}
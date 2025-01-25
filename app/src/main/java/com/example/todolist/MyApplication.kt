package com.example.todolist

import android.app.Application
import com.example.todolist.data.local.database.MyDatabase
import dagger.hilt.android.HiltAndroidApp

// Esta clase permite la gestión global de dependencias con Hilt
@HiltAndroidApp
class MyApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        // Initialize the database here
        MyDatabase.getDatabase(this)
    }
}
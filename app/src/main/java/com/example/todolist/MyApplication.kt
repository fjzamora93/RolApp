package com.example.todolist

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Esta clase permite la gestión global de dependencias con Hilt
@HiltAndroidApp
class MyApplication: Application()
package com.example.todolist.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavigationViewModel : ViewModel() {

    val LocalNavigationViewModel = compositionLocalOf<NavigationViewModel> {
        error("NavigationViewModel no está disponible")
    }

    private val _navigationEvent = MutableLiveData<String?>()
    val navigationEvent: LiveData<String?> = _navigationEvent

    fun navigate(route: String) {
        _navigationEvent.value = route
    }



    fun clearNavigationEvent() {
        _navigationEvent.value = null
    }
}

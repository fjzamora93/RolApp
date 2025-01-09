package com.example.todolist.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavigationViewModel : ViewModel() {

    private val _navigationEvent = MutableLiveData<NavigationEvent?>()
    val navigationEvent: LiveData<NavigationEvent?> = _navigationEvent

    fun navigate(route: String) {
        _navigationEvent.value = NavigationEvent.Navigate(route)
    }

    fun navigateAndPopUp(route: String, popUpToRoute: String, inclusive: Boolean) {
        _navigationEvent.value = NavigationEvent.NavigateAndPopUp(route, popUpToRoute, inclusive)
    }

    fun clearNavigationEvent() {
        _navigationEvent.value = null
    }


}



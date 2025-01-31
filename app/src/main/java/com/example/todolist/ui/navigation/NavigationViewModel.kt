package com.example.todolist.ui.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NavigationViewModel : ViewModel() {

    // Mantener un stack de rutas
    private val _routeStack = mutableListOf<String>()
    val routeStack: List<String> get() = _routeStack


    private val _navigationEvent = MutableLiveData<NavigationEvent?>()
    val navigationEvent: LiveData<NavigationEvent?> = _navigationEvent

    fun navigate(route: String) {
        _routeStack.add(route)
        _navigationEvent.value = NavigationEvent.Navigate(route)
    }

    fun navigateAndPopUp(currentRoute: String, popUpToRoute: String, inclusive: Boolean) {
        _routeStack.remove(currentRoute)

        _navigationEvent.value = NavigationEvent.NavigateAndPopUp(
            currentRoute,
            popUpToRoute,
            inclusive = false
        )
    }

    fun clearNavigationEvent() {
        _navigationEvent.value = null
    }

    // Nuevo método para navegar hacia atrás
    fun goBack() {
        if (_routeStack.size > 1) {
            var currentRoute = _routeStack.removeAt(_routeStack.size - 1)
            var previousRoute = _routeStack.last()
            this.navigateAndPopUp(currentRoute, previousRoute, true)
            println("La ruta ANTERIOR es... $previousRoute El tamaño es : ${_routeStack.size}")
            _navigationEvent.value = NavigationEvent.Navigate(previousRoute)
        } else {
            this.navigate(ScreensRoutes.MainScreen.route)
            println("No hay más pantallas para retroceder.")
        }
    }

}



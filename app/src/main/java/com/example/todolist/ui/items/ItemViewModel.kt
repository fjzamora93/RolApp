package com.example.todolist.ui.items

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.local.model.Item
import com.example.todolist.data.local.model.RolCharacter
import com.example.todolist.data.remote.repository.RemoteItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val repository: RemoteItemRepository
) : ViewModel() {

    private val _itemList = MutableLiveData<List<Item>>()
    val itemList: LiveData<List<Item>> get() = _itemList

    private val _itemDetail = MutableLiveData<Item?>()
    val itemDetail: LiveData<Item?> = _itemDetail



    fun getItems(
        name: String = "",
        onSuccess: (List<Item>) -> Unit = { },
        onError: () -> Unit = { }
    ) {
        viewModelScope.launch {
            val result = repository.fetchItems(name)
            result.onSuccess {
                items ->
                _itemList.value = items
                onSuccess(items)
            }.onFailure {
                onError()
            }

        println("LISTA ACTUALIZADA EN EL ITEM VIEW MODEL: ${itemList.value}")
        }

    }
}

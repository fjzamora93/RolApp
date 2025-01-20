package com.example.todolist.ui.items

import androidx.lifecycle.ViewModel
import com.example.todolist.data.local.model.Item
import com.example.todolist.data.local.repository.RemoteItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val repository: RemoteItemRepository
) : ViewModel() {



    fun getItems(
        name: String = "",
        onSuccess: (List<Item>) -> Unit,
        onError: () -> Unit
    ) {
        repository.fetchItems(name, onSuccess, onError)
    }
}

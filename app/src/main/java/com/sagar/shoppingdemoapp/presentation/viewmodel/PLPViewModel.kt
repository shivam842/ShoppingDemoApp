package com.sagar.shoppingdemoapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.repository.ShoppingRepository
import com.sagar.shoppingdemoapp.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PLPViewModel @Inject constructor(
    private val repository: ShoppingRepository
) : BaseViewModel(){
    private val _ProductList = MutableStateFlow<List<Product>>(emptyList())
    val productList: StateFlow<List<Product>> = _ProductList

    fun getAllProducts(){
        viewModelScope.launch {
            updateLoadingState(true)
            repository.getAllItems().collect {
                _ProductList.value = it
            }
            updateLoadingState(false)
        }
    }
}
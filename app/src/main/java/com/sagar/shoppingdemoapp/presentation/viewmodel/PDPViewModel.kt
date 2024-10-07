package com.sagar.shoppingdemoapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.repository.ShoppingRepository
import com.sagar.shoppingdemoapp.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PDPViewModel @Inject constructor(
    private val repository: ShoppingRepository
):BaseViewModel(){
    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product

    fun getProduct(id:Int){
        viewModelScope.launch {
            updateLoadingState(true)
            repository.getProduct(id).collect {
                _product.value = it
            }
            updateLoadingState(false)
        }
    }
    fun addTOCart(product: Product){
        viewModelScope.launch (Dispatchers.IO) {
            updateLoadingState(true)
            delay(1000)
            repository.insertInCart(product)
            updateLoadingState(false)
        }
    }
}
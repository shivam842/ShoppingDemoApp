package com.sagar.shoppingdemoapp.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.repository.ShoppingRepository
import com.sagar.shoppingdemoapp.data.model.Cart
import com.sagar.shoppingdemoapp.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: ShoppingRepository
) : BaseViewModel() {
    private val _cartList = MutableStateFlow<List<Cart>>(emptyList())
    val cartList: StateFlow<List<Cart>> = _cartList

    fun getAllProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            updateLoadingState(true)
            repository.getAllCartProducts().collect {
                _cartList.value = it
            }
            updateLoadingState(false)
        }
    }

    fun onAddQuantity(cart: Cart) {
        viewModelScope.async(Dispatchers.IO) {
            repository.updateCart(cart.apply { cart.quantity++ })
            getAllProducts()
        }
    }

    fun onRemoveQuantity(cart: Cart) {
        if (cart.quantity == 1) {
            viewModelScope.async(Dispatchers.IO) {
                repository.deleteCardItem(cart)
                getAllProducts()
            }
        } else {
            viewModelScope.async(Dispatchers.IO) {
                repository.updateCart(cart.apply { cart.quantity-- })
                getAllProducts()
            }
        }
    }

}
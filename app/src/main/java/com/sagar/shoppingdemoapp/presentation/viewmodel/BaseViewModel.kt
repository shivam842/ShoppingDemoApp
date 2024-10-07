package com.sagar.shoppingdemoapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel()  {
    val _isLoading = MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean> = _isLoading

    fun updateLoadingState(loading: Boolean) {
        _isLoading.postValue(loading)
    }
}
package com.sagar.shoppingdemoapp.presentation.view.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.sagar.shoppingdemoapp.presentation.view.composibles.Loader
import com.sagar.shoppingdemoapp.presentation.view.composibles.ProductDetailView
import com.sagar.shoppingdemoapp.presentation.viewmodel.PDPViewModel

@Composable
fun PDPPageComposable(itemId: Int,context:Context, navigateToCart: () -> Unit) {
    val viewModel = hiltViewModel<PDPViewModel>()
    val product by viewModel.product.collectAsState(null)
    val isLoading by viewModel.isLoading.observeAsState(false)
    LaunchedEffect(Unit) {
        viewModel.getProduct(itemId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        product?.let {
            ProductDetailView(product = it, {
                viewModel.addTOCart(product!!)
            },{navigateToCart()})
        }
        if (isLoading) {
            Loader()
        }
    }
}

package com.sagar.shoppingdemoapp.presentation.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sagar.shoppingdemoapp.presentation.view.composibles.CartItem
import com.sagar.shoppingdemoapp.presentation.view.composibles.Loader
import com.sagar.shoppingdemoapp.presentation.view.composibles.PLPItem
import com.sagar.shoppingdemoapp.presentation.viewmodel.CartViewModel
import com.sagar.shoppingdemoapp.presentation.viewmodel.PLPViewModel

@Composable
fun CartPageComposable() {
    val viewModel = hiltViewModel<CartViewModel>()
    val cartList by viewModel.cartList.collectAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White
                )
                .verticalScroll(rememberScrollState())
        ) {
            LaunchedEffect(Unit) {
                viewModel.getAllProducts()
            }
            if (cartList.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "No product in cart")
                }
            } else {
                cartList.forEach {
                    CartItem(
                        it,
                        { viewModel.onAddQuantity(cart = it) },
                        { viewModel.onRemoveQuantity(cart = it) })
                    Spacer(modifier = Modifier.height(5.dp))
                }
                var total = 0.0;
                cartList.forEach{
                    total = total+ (it.quantity * it.product!!.price)
                }
                Text(
                    text = "Total: ${(total).toInt()}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )

            }
        }
        if (isLoading) {
            Loader()
        }
    }

}
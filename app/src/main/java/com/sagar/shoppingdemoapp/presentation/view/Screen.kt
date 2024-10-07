package com.sagar.shoppingdemoapp.presentation.view
sealed class Screen(val route: String) {
    object PLPPage : Screen("Product_list_page")
    object PDPPage : Screen("Product_detail_page/{itemId}"){
        fun createRoute(itemId: String) = "Product_detail_page/$itemId"
    }
    object CartPage : Screen("Cart_page")
}
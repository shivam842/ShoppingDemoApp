package com.example.todolist.domain.repository

import android.content.Context
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sagar.shoppingdemoapp.data.model.Cart
import com.sagar.shoppingdemoapp.data.model.Product
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow

public interface ShoppingRepository {
    suspend fun insert(product: Product)
    //suspend fun searchItems(search: String): Flow<List<Product>>
    suspend fun getAllItems():Flow<List<Product>>
    suspend fun addToCard(item: Product)
    suspend fun deleteCardItem(itemId: Cart)
    suspend fun updateCart(item: Cart)
    suspend fun insertInCart(item: Product)
    suspend fun getAllProducts(): Flow<List<Product>>
    suspend fun getAllCartProducts(): Flow<List<Cart>>
    suspend fun getProduct(id: Int): Flow<Product?>
    suspend fun getProductModel(id: Int): Product?
    suspend fun getCartByProduct(id: Int): Cart?

}
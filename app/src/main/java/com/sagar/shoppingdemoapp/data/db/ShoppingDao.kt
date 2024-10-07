package com.example.todolist.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sagar.shoppingdemoapp.data.model.Cart
import com.sagar.shoppingdemoapp.data.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {
    @Insert
    suspend fun insert(item: Product)

    @Insert(onConflict = OnConflictStrategy.FAIL)
    suspend fun insertInCart(item: Cart)

    @Insert
    suspend fun addToCard(item: Cart)

    @Delete
    suspend fun deleteCardItem(cart: Cart)

    @Update
    suspend fun updateCart(item: Cart)

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<Product>>

    @Query("SELECT * FROM Cart")
    fun getAllCartProducts(): Flow<List<Cart>>

    @Query("SELECT * FROM Cart")
    fun getAllCartProductsList(): List<Cart>

    // Optional: Query with search filter
    @Query("SELECT * FROM products WHERE id = :id")
    fun getProduct(id: String): Flow<Product>

    @Query("SELECT * FROM cart WHERE productId = :id")
    fun getCartByProduct(id: Int): Cart
}
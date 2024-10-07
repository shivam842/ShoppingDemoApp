package com.sagar.shoppingdemoapp.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Cart", indices = [Index(value = ["productId"], unique = true)])
data class Cart(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productId: Int,
    var quantity: Int,
) {
    @Ignore
    var product: Product? = null
}
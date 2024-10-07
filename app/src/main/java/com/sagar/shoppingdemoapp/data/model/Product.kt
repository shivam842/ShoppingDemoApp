package com.sagar.shoppingdemoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Double,
    val rating: Float,
    val specification: String,
    val imageUrl: String
)
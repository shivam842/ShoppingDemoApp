package com.example.todolist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sagar.shoppingdemoapp.data.model.Cart
import com.sagar.shoppingdemoapp.data.model.Product

@Database(entities = [Product::class,Cart::class], version = 1, exportSchema = false)
public abstract class ShoppingDatabase : RoomDatabase() {

    public abstract fun todoDao(): ShoppingDao

    companion object {
        @Volatile
        private var INSTANCE: ShoppingDatabase? = null

        fun getDatabase(context: Context): ShoppingDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoppingDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
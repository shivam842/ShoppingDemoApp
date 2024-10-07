package com.example.todolist.data.di

import android.content.Context
import com.example.todolist.data.db.ShoppingDao
import com.example.todolist.data.db.ShoppingDatabase
import com.example.todolist.data.repository.ShoppingRepositoryImpl
import com.example.todolist.domain.repository.ShoppingRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    /*generally context is not pass in repo but for reading json from asset it required*/
    @Provides
    fun provideToDoRepository(dao: ShoppingDao,@ApplicationContext appContext: Context): ShoppingRepository {
        return ShoppingRepositoryImpl(dao,appContext)
    }

    @Provides
    fun provideDataBase(@ApplicationContext context: Context): ShoppingDatabase {
        return ShoppingDatabase.getDatabase(context)
    }

    @Provides
    fun provideDAO(db: ShoppingDatabase): ShoppingDao {
        return db.todoDao()
    }
}
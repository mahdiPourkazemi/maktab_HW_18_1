package com.pourkazemi.mahdi.maktab_hw_18_1.di

import android.content.Context
import androidx.room.*
import com.pourkazemi.mahdi.maktab_hw_18_1.data.local.DataBase
import com.pourkazemi.mahdi.maktab_hw_18_1.data.local.PersonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun getDatabase(
        @ApplicationContext context: Context
    ): DataBase = Room.databaseBuilder(
        context.applicationContext,
        DataBase::class.java,
        "person_database"
    ).build()

    @Singleton
    @Provides
    fun personDao(dataBase: DataBase): PersonDao = dataBase.personDao()

}
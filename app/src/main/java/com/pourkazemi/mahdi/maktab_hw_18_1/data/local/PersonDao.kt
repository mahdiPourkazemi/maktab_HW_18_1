package com.pourkazemi.mahdi.maktab_hw_18_1.data.local

import androidx.room.*
import com.pourkazemi.mahdi.maktab_hw_18_1.data.model.Person
import kotlinx.coroutines.flow.Flow
@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPerson(vararg person: Person)

    @Delete
    suspend fun deletePerson(person: Person)

    @Query("SELECT * FROM person")
    fun getAllPersons(): Flow<List<Person>>

    @Update
    suspend fun updateUser(person: Person)
}
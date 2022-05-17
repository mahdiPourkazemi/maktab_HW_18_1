package com.pourkazemi.mahdi.maktab_hw_18_1.data.local

import com.pourkazemi.mahdi.maktab_hw_18_1.data.model.Person
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val personDao: PersonDao) {

    suspend fun updatePersonOnDataBase(person: Person) {
        personDao.updateUser(person)
    }

    suspend fun insertAllPerson(vararg person: Person) = personDao.insertAllPerson(*person)

    fun getListOfPersonDataBase() = personDao.getAllPersons()

    suspend fun deletePerson(person: Person) {
        personDao.deletePerson(person)
    }
}
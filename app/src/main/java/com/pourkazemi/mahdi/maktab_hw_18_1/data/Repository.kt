package com.pourkazemi.mahdi.maktab_hw_18_1.data

import com.pourkazemi.mahdi.maktab_hw_18_1.data.local.LocalDataSource
import com.pourkazemi.mahdi.maktab_hw_18_1.data.model.Person
import com.pourkazemi.mahdi.maktab_hw_18_1.data.model.SendPerson
import com.pourkazemi.mahdi.maktab_hw_18_1.data.remote.RemoteDataSource
import com.pourkazemi.mahdi.maktab_hw_18_1.di.DispatchersModule
import com.pourkazemi.mahdi.maktab_hw_18_1.util.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.MultipartBody
import javax.inject.Inject

class Repository @Inject constructor(
    @DispatchersModule.IoDispatcher
    private val dispatcher: CoroutineDispatcher,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    fun getPersonLocalList() = localDataSource.getListOfPersonDataBase()

    suspend fun insertUser(vararg person: Person) = localDataSource.insertAllPerson(*person)

    suspend fun deleteUser(person: Person) = localDataSource.deletePerson(person)

    suspend fun updateUser(person: Person) = localDataSource.updatePersonOnDataBase(person)

    suspend fun getUserList() = safeApiCall(dispatcher) {
        remoteDataSource.getPersonList()
    }

    suspend fun showInfo(id: String) = remoteDataSource.showInfo(id)

    suspend fun uploadImage(id: String, image: MultipartBody.Part) =
        remoteDataSource.uploadImage(id, image)

    suspend fun createPerson(sendPerson: SendPerson) = remoteDataSource.createPerson(sendPerson)
}
package com.pourkazemi.mahdi.maktab_hw_18_1.data.remote

import com.pourkazemi.mahdi.maktab_hw_18_1.data.model.Person
import com.pourkazemi.mahdi.maktab_hw_18_1.data.model.SendPerson
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val personAPI: PersonAPI) {
    suspend fun getPersonList(): Response<List<Person>> {
        return personAPI.getPersonList()
    }

    suspend fun showInfo(
        id: String
    ) = personAPI.getShowInfo(id)

    suspend fun uploadImage(
        id: String,
        image: MultipartBody.Part
    ) = personAPI.uploadImage(id, image)

    suspend fun createPerson(sendPerson: SendPerson) = personAPI.createUser(sendPerson)

}
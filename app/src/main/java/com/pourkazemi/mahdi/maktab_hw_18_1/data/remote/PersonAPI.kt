package com.pourkazemi.mahdi.maktab_hw_18_1.data.remote

import com.pourkazemi.mahdi.maktab_hw_18_1.data.model.Person
import com.pourkazemi.mahdi.maktab_hw_18_1.data.model.SendPerson
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface PersonAPI {

    @GET("users")
    suspend fun getPersonList(): Response<List<Person>>

    @POST("users")
    suspend fun createUser(@Body person: SendPerson): String

    @Multipart
    @POST("users/{id}/image")
    suspend fun uploadImage(
        @Path("id") id: String,
        @Part image: MultipartBody.Part
    ): Any

    //http://papp.ir/api/v1/
    @GET("users/{id}")
    suspend fun getShowInfo(@Path("id") id: String): Response<Person>
}